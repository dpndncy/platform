package com.dpndncy.user.rest.config

import com.dpndncy.user.service.config.UserServiceConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import

/**
 * Created by vaibhav on 28/02/17.
 */
@Configuration
@ComponentScan(basePackages = "com.dpndncy.user.rest")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import([UserServiceConfig])
class UserRestConfig {
}
