package com.dpndncy.course.rest.config

import com.dpndncy.course.service.config.CourseRepositoryServiceConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import

/**
 * Created by vaibhav on 18/02/17.
 */
@Configuration
@ComponentScan(basePackages = "com.dpndncy.course.rest")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import([CourseRepositoryServiceConfig])
class CourseRepositoryRestConfig {
}
