package com.dpndncy.course.rest.controller

import com.dpndncy.course.service.api.CourseRepositoryService
import com.dpndncy.db.entity.course.Course
import com.dpndncy.db.entity.course.CourseCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by vaibhav on 28/02/17.
 */
@RestController
@RequestMapping('course')
class CourseController {

    @Autowired
    CourseRepositoryService courseRepositoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<CourseCategory> categories() {
        return courseRepositoryService.getCourseCategories();
    }

    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public List<Course> latest(@RequestParam(defaultValue = '10') Integer count) {
        return courseRepositoryService.getLatestCourses(count);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Course> byTag(@RequestParam String tag) {
        return courseRepositoryService.findByTagName(tag);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Course> byCategory(@RequestParam String category) {
        return courseRepositoryService.findByCategoryName(category);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Course> byName(@RequestParam String name) {
        return courseRepositoryService.findByNameMatching(name);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Course> byQuery(@RequestParam String q) {
        return courseRepositoryService.find(q);
    }
}
