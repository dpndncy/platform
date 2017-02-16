package com.dpndncy.db.acl

import com.dpndncy.db.entity.Post
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
class PostACLEntryCreateHandler {

    @Autowired
    ACLService aclService;

    @HandleAfterCreate
    void createACLEntry(Post post) {
        aclService.addPermission(post, getLoggedInUser(), BasePermission.ADMINISTRATION);
        aclService.addPermission(post, getLoggedInUser(), BasePermission.READ);
        aclService.addPermission(post, getLoggedInUser(), BasePermission.WRITE);
        aclService.addPermission(post, getLoggedInUser(), BasePermission.CREATE);
        aclService.addPermission(post, getLoggedInUser(), BasePermission.DELETE);

        aclService.addPermission(post, new GrantedAuthoritySid("ROLE_ADMIN"), BasePermission.ADMINISTRATION);

    }

    static User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.context.authentication;
        return authentication.principal.user;
    }

}
