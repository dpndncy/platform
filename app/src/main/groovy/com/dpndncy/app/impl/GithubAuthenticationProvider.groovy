package com.dpndncy.app.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException

/**
 * Created by vaibhav on 06/02/17.
 */
class GithubAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    GithubAuthenticationService githubAuthenticationService;

    @Override
    Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetail userDetail = githubAuthenticationService.authenticate(((GithubAuthenticationToken) authentication).token);
        if(userDetail == null) {
            throw new BadCredentialsException("Github access token was not valid");
        }
        return new GithubAuthenticationToken(authenticated: true, userDetail: userDetail);
    }

    @Override
    boolean supports(Class<?> aClass) {
        return aClass == GithubAuthenticationToken;
    }
}
