package com.dpndncy.course.rest.controller

import com.dpndncy.course.rest.pojo.ActivityInfo
import com.dpndncy.course.rest.pojo.CourseInfo
import com.dpndncy.course.rest.pojo.ModuleInfo
import com.dpndncy.course.service.api.CourseRepositoryService
import com.dpndncy.db.entity.course.Activity
import com.dpndncy.db.entity.course.Course
import com.dpndncy.db.entity.course.CourseCategory
import com.dpndncy.db.entity.course.Module
import com.dpndncy.shared.exception.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by vaibhav on 28/02/17.
 */
@RestController
class CourseController {

    @Autowired
    CourseRepositoryService courseRepositoryService;

    @RequestMapping(path = "/course/categories", method = RequestMethod.GET)
    public List<CourseCategory> categories() {
        return courseRepositoryService.getCourseCategories();
    }

    @RequestMapping(path = "/course/latest", method = RequestMethod.GET)
    public List<Course> latest(@RequestParam(defaultValue = '10') Integer count) {
        return courseRepositoryService.getLatestCourses(count);
    }

    @RequestMapping(path = "/course/search/byTag", method = RequestMethod.GET)
    public List<Course> byTag(@RequestParam String tag, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return courseRepositoryService.findByTagName(tag, page, size);
    }

    @RequestMapping(path = "/course/search/byCategory", method = RequestMethod.GET)
    public List<Course> byCategory(@RequestParam String category, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return courseRepositoryService.findByCategoryName(category, page, size);
    }

    @RequestMapping(path = "/course/search/byName", method = RequestMethod.GET)
    public List<Course> byName(@RequestParam String name, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return courseRepositoryService.findByNameMatching(name, page, size);
    }

    @RequestMapping(path = "/course/search", method = RequestMethod.GET)
    public List<Course> byQuery(@RequestParam String q, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return courseRepositoryService.find(q, page, size);
    }

    @RequestMapping(path = "/course/info", method = RequestMethod.GET)
    public CourseInfo info(@RequestParam String author, @RequestParam String name) {
        Course course = courseRepositoryService.findByNameAndAuthorName(name, author);
        if(course == null) {
            throw new NotFoundException("Course not found");
        }
        Integer enrollmentCount = courseRepositoryService.getEnrollmentCount(course);
        return new CourseInfo(course: course, enrollmentCount: enrollmentCount);
    }

    @RequestMapping(path = "/module/info", method = RequestMethod.GET)
    public ModuleInfo info(@RequestParam String author, @RequestParam String name, @RequestParam String course) {
        Module module = courseRepositoryService.findByNameAndCourseNameAndAuthorName(name, course, author);
        if(module == null) {
            throw new NotFoundException("Module not found");
        }
        return new ModuleInfo(module: module);
    }

    @RequestMapping(path = "/activity/info", method = RequestMethod.GET)
    public ActivityInfo info(@RequestParam String author, @RequestParam String name, @RequestParam String course, @RequestParam String module) {
        Activity activity = courseRepositoryService.findByNameAndModuleNameAndCourseNameAndAuthorName(name, module, course, author);
        if(activity == null) {
            throw new NotFoundException("Activity not found");
        }
        return new ActivityInfo(activity: activity);
    }
}
