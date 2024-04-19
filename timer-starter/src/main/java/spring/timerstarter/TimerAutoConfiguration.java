package spring.timerstarter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TimerProperties.class)
public class TimerAutoConfiguration {
@Bean
    TimerAspect timerAspect(){
    return new TimerAspect();
}

}
