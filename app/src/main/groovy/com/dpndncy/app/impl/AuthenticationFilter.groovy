package com.dpndncy.app.impl

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.filter.GenericFilterBean

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by vaibhav on 06/02/17.
 */
class AuthenticationFilter extends GenericFilterBean {

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Optional<String> token = Optional.ofNullable(request.getHeader("X-Auth-Token"));

        if(token.isPresent() && isAuthenticationNeeded()) {
            try {
                processTokenAuthentication(token);
            }
            catch (BadCredentialsException e) {
                unsuccessfulAuthentication(request, response, e);
            }
        }

        filterChain.doFilter(request, response);
    }


    private void processTokenAuthentication(Optional<String> token) {
        Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
    }

    private Authentication tryToAuthenticateWithToken(Optional<String> token) {
        GithubAuthenticationToken requestAuthentication = new GithubAuthenticationToken(token: token.get());
        return tryToAuthenticate(requestAuthentication);
    }

    private Authentication tryToAuthenticate(Authentication requestAuthentication) {
        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("Unable to authenticate Domain User for provided token");
        }
        logger.debug("User successfully authenticated");
        return responseAuthentication;
    }

    private static Boolean isAuthenticationNeeded() {
        Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

        if (existingAuth == null || !existingAuth.isAuthenticated()) {
            return true;
        }

        if (existingAuth instanceof AnonymousAuthenticationToken) {
            return true;
        }

        return false;
    }

    private static void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
