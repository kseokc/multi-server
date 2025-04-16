package multistudy.global.config;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//memo
// 각 서버에 오는 api의 처리율을 제한하기위해서 bucket4j-redis를 이용한다.
@Component
@Slf4j
public class RateLimitFilter implements Filter {

    private ProxyManager<String> proxyManager;

    @Autowired
    public void setProxyManager(ProxyManager<String> proxyManager) {
        this.proxyManager = proxyManager;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
        FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String ip = httpRequest.getRemoteAddr();
        Supplier<BucketConfiguration> configurationLazySupplier = getConfigSupplier();
        Bucket bucket = proxyManager.builder().build(ip, configurationLazySupplier);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // fail case
            log.info(
                "수용 불가능한 처리율" + "\trequest ip : " + ip + "\turi : " + httpRequest.getRequestURI()
                    + "\tremainingToken : " + probe.getRemainingTokens());
            HttpServletResponse httpServletResponse = makeRateLimiterResponse(servletResponse,
                probe);
        }
    }

    //memo
    // 기존 공식문서에서는 userid를 입력받아 사용을 했지만 나는 그냥 사용하겠디.
    private Supplier<BucketConfiguration> getConfigSupplier() {
        return () -> {
            // 유저 별로 token의 개수를 설정할수있다.
//            long translationsPerDay = limitProvider.readPerDayLimitFromAgreementsDatabase(userId);
            return BucketConfiguration.builder()
                .addLimit(
                    limit -> limit.capacity(60).refillGreedy(60, Duration.ofMinutes(1)))
                .build();
        };
    }

    private HttpServletResponse makeRateLimiterResponse(ServletResponse servletResponse,
        ConsumptionProbe probe) throws IOException {

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setContentType("text/plain");
        httpResponse.setHeader("X-Rate-Limit-Limit", "" +
            TimeUnit.NANOSECONDS.toSeconds(probe.getNanosToWaitForRefill()));
        httpResponse.setStatus(429);
        httpResponse.getWriter().append("Too many requests");

        return httpResponse;
    }


}
