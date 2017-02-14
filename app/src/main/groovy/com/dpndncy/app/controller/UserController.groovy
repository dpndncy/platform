package com.dpndncy.app.controller

import com.dpndncy.app.impl.UserDetail
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by vaibhav on 06/02/17.
 */
@RestController
class UserController {

    @RequestMapping(value = '/login', method = RequestMethod.POST)
    public UserDetail login(@AuthenticationPrincipal UserDetail userDetail) {
        return userDetail;
    }

    @RequestMapping(value = '/logout', method = RequestMethod.POST)
    public Boolean logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return true;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public Boolean test() {
        return true;
    }
}
