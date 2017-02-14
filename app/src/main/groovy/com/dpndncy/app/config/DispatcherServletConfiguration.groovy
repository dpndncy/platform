package com.dpndncy.app.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.env.Environment
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.multipart.MultipartResolver
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

/**
 * Created by user-1 on 24/6/16.
 */
@Configuration
@ComponentScan(["com.dpndncy.app.controller"])
@Import(value = [SwaggerConfig])
@PropertySources(@PropertySource("file:/dpndncy.properties"))
@EnableWebMvc
@EnableAspectJAutoProxy
class DispatcherServletConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment environment;


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
        Object[] interceptors = [localeChangeInterceptor()].toArray();
        requestMappingHandlerMapping.setInterceptors(interceptors);
        return requestMappingHandlerMapping;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        addDefaultHttpMessageConverters(converters);
        for (HttpMessageConverter<?> converter: converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = (MappingJackson2HttpMessageConverter) converter;
                jackson2HttpMessageConverter.setObjectMapper(objectMapper);
                break;
            }
        }
    }

    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
