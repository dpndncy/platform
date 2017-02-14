package com.dpndncy.app.impl

import lombok.Data
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

/**
 * Created by vaibhav on 06/02/17.
 */
@Data
class GithubAuthenticationToken implements Authentication, Serializable {

    Boolean authenticated;
    String token;
    UserDetail userDetail;

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetail.authorities;
    }

    @Override
    Object getCredentials() {
        return token;
    }

    @Override
    Object getDetails() {
        return userDetail;
    }

    @Override
    Object getPrincipal() {
        return userDetail;
    }

    @Override
    boolean isAuthenticated() {
        if(authenticated == null) {
            return userDetail != null;
        }
        return authenticated;
    }

    @Override
    void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    String getName() {
        return userDetail.user.name;
    }
}
