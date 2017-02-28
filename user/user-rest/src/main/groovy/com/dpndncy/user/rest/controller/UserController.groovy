package com.dpndncy.user.rest.controller

import com.dpndncy.course.service.api.CourseRepositoryService
import com.dpndncy.db.entity.User
import com.dpndncy.db.entity.course.Course
import com.dpndncy.shared.pojo.UserDetail
import com.dpndncy.user.rest.pojo.Profile
import com.dpndncy.user.service.api.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by vaibhav on 06/02/17.
 */
@RestController
@RequestMapping('/user')
class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CourseRepositoryService courseRepositoryService;

    @RequestMapping(value = '/login', method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public UserDetail login(@AuthenticationPrincipal UserDetail userDetail) {
        return userDetail;
    }

    @RequestMapping(value = '/logout', method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public Boolean logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return true;
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public User me(@AuthenticationPrincipal UserDetail userDetail) {
        return userDetail.user;
    }

    @RequestMapping(value = "/profile/{{username}}", method = RequestMethod.GET)
    public Profile profile(@PathVariable String username) {
        User user =  userService.findByUsername(username);
        List<Course> authoredCourseList = courseRepositoryService.findByAuthor(user);
        List<Course> enrolledCourseList = courseRepositoryService.findByConsumer(user);
        return new Profile(user: user, enrolledCourseList: enrolledCourseList, authoredCourseList: authoredCourseList);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public Profile profile(@AuthenticationPrincipal UserDetail userDetail) {
        List<Course> authoredCourseList = courseRepositoryService.findByAuthor(userDetail.user);
        List<Course> enrolledCourseList = courseRepositoryService.findByConsumer(userDetail.user);
        return new Profile(user: userDetail.user, enrolledCourseList: enrolledCourseList, authoredCourseList: authoredCourseList);
    }
}
