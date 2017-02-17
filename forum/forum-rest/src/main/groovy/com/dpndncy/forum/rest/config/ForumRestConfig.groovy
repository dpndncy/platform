package com.dpndncy.forum.rest.config

import com.dpndncy.forum.service.config.ForumServiceConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import

/**
 * Created by vaibhav on 17/02/17.
 */
@Configuration
@ComponentScan(basePackages = "com.dpndncy.forum.rest")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import([ForumServiceConfig])
class ForumRestConfig {
}
