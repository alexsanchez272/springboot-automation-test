package com.magaya.dfp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:application.properties")}
)
@ComponentScan(basePackages = {"com.magaya.dfp"})
public class ApplicationContextConfig {
}
