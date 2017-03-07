package com.dpndncy.app.impl

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by vaibhav on 07/02/17.
 */
class OAuth2AuthenticationErrorHandler extends RequestHeaderAuthenticationFilter {

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        super.unsuccessfulAuthentication(request, response, failed);

        // see comments in Servlet API around using sendError as an alternative
        //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
