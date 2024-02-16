package ru.letsdigit.timemeterspringbootstarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("executiontimer")
public class TimeMeterProperties {
    /**
     * Включение/отключение логирования
     */
    private boolean loggingOn = false;
}
