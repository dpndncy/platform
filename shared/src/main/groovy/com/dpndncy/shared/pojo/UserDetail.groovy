package com.dpndncy.shared.pojo

import com.dpndncy.db.entity.User
import lombok.Data
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * Created by vaibhav on 06/02/17.
 */
@Data
class UserDetail implements UserDetails, Serializable {

    User user;
    List<SimpleGrantedAuthority> roles;

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    String getPassword() {
        return null
    }

    @Override
    String getUsername() {
        return user.login;
    }

    @Override
    boolean isAccountNonExpired() {
        return true;
    }

    @Override
    boolean isAccountNonLocked() {
        return true;
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    boolean isEnabled() {
        return true;
    }
}
