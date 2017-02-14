package com.dpndncy.app.impl

import org.springframework.security.core.GrantedAuthority

/**
 * Created by vaibhav on 06/02/17.
 */
class Role implements GrantedAuthority {

    String name;

    @Override
    String getAuthority() {
        return name;
    }
}
