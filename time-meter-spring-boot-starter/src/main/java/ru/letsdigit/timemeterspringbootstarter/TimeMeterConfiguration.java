package ru.letsdigit.timemeterspringbootstarter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.letsdigit.timemeterspringbootstarter.aspect.TimerAspect;

@AutoConfiguration
@EnableConfigurationProperties(TimeMeterProperties.class)
@ConditionalOnProperty(value = "executiontimer.logging-on", havingValue = "true")
public class TimeMeterConfiguration {

    @Bean
    public TimerAspect timer() {
        return new TimerAspect();
    }
}
