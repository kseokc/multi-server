package multistudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy // aop의 관점을 사용할떄 사용한다.
public class MultistudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultistudyApplication.class, args);
    }

}
