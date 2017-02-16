package com.dpndncy.db.config

import com.dpndncy.db.acl.ACLService
import com.dpndncy.db.acl.PostACLEntryCreateHandler
import com.dpndncy.db.acl.TopicACLEntryCreateHandler
import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.cache.ehcache.EhCacheFactoryBean
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.acls.AclPermissionCacheOptimizer
import org.springframework.security.acls.AclPermissionEvaluator
import org.springframework.security.acls.domain.AclAuthorizationStrategy
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl
import org.springframework.security.acls.domain.AuditLogger
import org.springframework.security.acls.domain.ConsoleAuditLogger
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy
import org.springframework.security.acls.domain.EhCacheBasedAclCache
import org.springframework.security.acls.jdbc.BasicLookupStrategy
import org.springframework.security.acls.jdbc.JdbcMutableAclService
import org.springframework.security.acls.jdbc.LookupStrategy
import org.springframework.security.acls.model.AclCache
import org.springframework.security.acls.model.AclService
import org.springframework.security.acls.model.PermissionGrantingStrategy
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import javax.sql.DataSource

/**
 * Created by vaibhav on 15/02/17.
 */
@Configuration
class AclConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    ACLService customAclService() {
        return new ACLService();
    }

    @Bean
    PostACLEntryCreateHandler postACLEntryCreateHandler() {
        return new PostACLEntryCreateHandler();
    }

    @Bean
    TopicACLEntryCreateHandler topicACLEntryCreateHandler() {
        return new TopicACLEntryCreateHandler();
    }

    @Bean
    GrantedAuthority grantedAuthority() {
        return new SimpleGrantedAuthority("ROLE_ADMIN");
    }

    @Bean
    AclAuthorizationStrategy aclAuthorizationStrategy() {
        return new AclAuthorizationStrategyImpl(grantedAuthority());
    }

    @Bean
    PermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(auditLogger());
    }

    @Bean
    AuditLogger auditLogger() {
        return new ConsoleAuditLogger();
    }

    @Bean
    EhCacheManagerFactoryBean cacheManager() {
        return new EhCacheManagerFactoryBean();
    }

    @Bean
    EhCacheFactoryBean ehCacheFactoryBean() {
        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
        ehCacheFactoryBean.setCacheManager(cacheManager().getObject());
        ehCacheFactoryBean.setCacheName("aclCache");
        return ehCacheFactoryBean;
    }

    @Bean
    AclCache aclCache() {
        return new EhCacheBasedAclCache(ehCacheFactoryBean().getObject(), permissionGrantingStrategy(), aclAuthorizationStrategy());
    }

    @Bean
    LookupStrategy lookupStrategy() {
        return new BasicLookupStrategy(dataSource, aclCache(), aclAuthorizationStrategy(), auditLogger());
    }

    @Bean
    AclService aclService() {
        AclService aclService = new JdbcMutableAclService(dataSource, lookupStrategy(), aclCache());
        aclService.setClassIdentityQuery("SELECT @@IDENTITY");
        aclService.setSidIdentityQuery("SELECT @@IDENTITY");
        return aclService;
    }

    @Bean
    AclPermissionEvaluator aclPermissionEvaluator() {
        return new AclPermissionEvaluator(aclService());
    }

    @Bean
    AclPermissionCacheOptimizer aclPermissionCacheOptimizer() {
        return new AclPermissionCacheOptimizer(aclService());
    }

    @Bean
    MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        MethodSecurityExpressionHandler methodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
        methodSecurityExpressionHandler.setPermissionEvaluator(aclPermissionEvaluator());
        methodSecurityExpressionHandler.setPermissionCacheOptimizer(aclPermissionCacheOptimizer());
        return methodSecurityExpressionHandler;
    }
}
