package com.dpndncy.db.acl

import com.dpndncy.db.entity.Topic
import com.dpndncy.db.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.core.annotation.HandleAfterCreate
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.security.acls.domain.BasePermission
import org.springframework.security.acls.domain.GrantedAuthoritySid
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

/**
 * Created by vaibhav on 14/02/17.
 */
@RepositoryEventHandler
class TopicACLEntryCreateHandler {

    @Autowired
    ACLService aclService;

    @HandleAfterCreate
    void createACLEntry(Topic topic) {
        aclService.addPermission(topic, getLoggedInUser(), BasePermission.ADMINISTRATION);
        aclService.addPermission(topic, getLoggedInUser(), BasePermission.READ);
        aclService.addPermission(topic, getLoggedInUser(), BasePermission.WRITE);
        aclService.addPermission(topic, getLoggedInUser(), BasePermission.CREATE);
        aclService.addPermission(topic, getLoggedInUser(), BasePermission.DELETE);

        aclService.addPermission(topic, new GrantedAuthoritySid("ROLE_ADMIN"), BasePermission.ADMINISTRATION);
    }

    static User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.context.authentication;
        return authentication.principal.user;
    }

}
