package org.ost.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.ost.aop.AspectLogClientsController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Slf4j
@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnClass(LoggingProperties.class)
@ConditionalOnProperty(prefix = "app.service.logging", name = "enable", havingValue = "true")
public class LoggingAutoConfiguration {

    @Bean
    @Order(1)
    public AspectLogClientsController aspectLogClientsController() {
        return new AspectLogClientsController();
    }

    @PostConstruct
    public void init() {
        log.info("LoggingAutoConfiguration initialized");
    }

}
