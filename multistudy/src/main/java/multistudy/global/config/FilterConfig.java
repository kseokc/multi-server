package multistudy.global.config;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final RateLimitFilter rateLimitFilter;
    private static final String[] INCLUDE_PATHS = {
        "/post/*"
    };

    @Bean
    public FilterRegistrationBean<RateLimitFilter> filterBean() {
        FilterRegistrationBean<RateLimitFilter> registrationBean
            = new FilterRegistrationBean<>();
        // 내가 커스텀한 filter를 적용을 시킨다.
        // 이 필터는 우선순위가 1이고 커스텀 rateLimitfilter를 가지고있고 설정한 path에만 filter가 동작을 한다.
        registrationBean.setFilter(rateLimitFilter);
        registrationBean.setOrder(1);
        registrationBean.setUrlPatterns(Arrays.asList(INCLUDE_PATHS));

        return registrationBean;
    }
}
