package com.dpndncy.db.config

import com.dpndncy.db.handler.PostHandler
import com.dpndncy.db.handler.TopicHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration

/**
 * Created by vaibhav on 14/02/17.
 */
@Configuration
@Import([RepositoryRestMvcConfiguration])
class RestConfig {

    @Bean
    PostHandler postHandler() {
        return new PostHandler();
    }

    @Bean
    TopicHandler topicHandler() {
        return new TopicHandler();
    }
}
