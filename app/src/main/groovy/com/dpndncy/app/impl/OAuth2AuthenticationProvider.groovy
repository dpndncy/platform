package com.dpndncy.app.impl

import com.dpndncy.app.api.AuthenticationService
import com.dpndncy.shared.pojo.UserDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException

/**
 * Created by vaibhav on 06/02/17.
 */
class OAuth2AuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AuthenticationService authenticationService;

    @Override
    Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetail userDetail = authenticationService.authenticate(((OAuth2AuthenticationToken) authentication).token);
        if(userDetail == null) {
            throw new BadCredentialsException("Access token was not valid");
        }
        return new OAuth2AuthenticationToken(authenticated: true, userDetail: userDetail);
    }

    @Override
    boolean supports(Class<?> aClass) {
        return aClass == OAuth2AuthenticationToken;
    }
}
