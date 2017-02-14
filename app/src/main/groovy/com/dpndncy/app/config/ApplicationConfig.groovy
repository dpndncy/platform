package com.dpndncy.app.config

import com.dpndncy.app.impl.GithubAuthenticationService
import com.dpndncy.app.impl.RestErrorHandler
import com.dpndncy.db.config.DbConfig
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import
import org.springframework.web.client.RestTemplate

/**
 * Created by user-1 on 24/6/16.
 */
@Configuration
@ComponentScan(basePackages = ["com.dpndncy.app"])
@Import([DbConfig])
@EnableAspectJAutoProxy
class ApplicationConfig {

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE).setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE).setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper;
    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestErrorHandler());
        return restTemplate;
    }

    @Bean
    GithubAuthenticationService githubAuthenticationService() {
        return new GithubAuthenticationService();
    }
}
