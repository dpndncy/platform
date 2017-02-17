package com.dpndncy.forum.rest.aspect

import com.dpndncy.db.entity.Auditable
import com.dpndncy.db.entity.User
import com.dpndncy.shared.util.ACLService
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.acls.domain.BasePermission
import org.springframework.security.acls.domain.GrantedAuthoritySid
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

/**
 * Created by vaibhav on 17/02/17.
 */
@Aspect
@Component
class AclAspect {

    @Autowired
    ACLService aclService;

    @Pointcut("execution(* com.dpndncy.forum.rest.controller.ForumController.create*(..))")
    public void allCreateMethods() {

    }

    @AfterReturning(pointcut = "allCreateMethods()", returning = "object")
    public void addAclEntry(Auditable object) {
        aclService.addPermission(object, getLoggedInUser(), BasePermission.ADMINISTRATION);
        aclService.addPermission(object, getLoggedInUser(), BasePermission.READ);
        aclService.addPermission(object, getLoggedInUser(), BasePermission.WRITE);
        aclService.addPermission(object, getLoggedInUser(), BasePermission.CREATE);
        aclService.addPermission(object, getLoggedInUser(), BasePermission.DELETE);

        aclService.addPermission(object, new GrantedAuthoritySid("ROLE_ADMIN"), BasePermission.ADMINISTRATION);
    }

    static User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.context.authentication;
        return authentication.principal.user;
    }


}
