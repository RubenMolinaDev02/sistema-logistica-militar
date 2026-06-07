package com.example.location_microservice.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
        return factory -> {
            Connector connector = new Connector(
                TomcatServletWebServerFactory.DEFAULT_PROTOCOL
            );
            connector.setPort(80);

            factory.addAdditionalConnectors(connector);
        };
    }
}