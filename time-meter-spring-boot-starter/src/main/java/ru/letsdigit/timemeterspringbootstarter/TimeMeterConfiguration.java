package ru.letsdigit.timemeterspringbootstarter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import ru.letsdigit.timemeterspringbootstarter.aspect.TimerAspect;

@AutoConfiguration
public class TimeMeterConfiguration {

    @Bean
    public TimerAspect timer() {
        return new TimerAspect();
    }
}
