package com.dpndncy.course.service.api

import com.dpndncy.db.entity.User
import com.dpndncy.db.entity.course.Activity
import com.dpndncy.db.entity.course.ActivitySubmission
import com.dpndncy.db.entity.course.Course
import com.dpndncy.db.entity.course.CourseCategory
import com.dpndncy.db.entity.course.Enrollment
import com.dpndncy.db.entity.course.Evaluation
import com.dpndncy.db.entity.course.EvaluationRequest
import com.dpndncy.db.entity.course.LearningResource
import com.dpndncy.db.entity.course.Module
import com.dpndncy.db.entity.course.Tag
import org.springframework.security.access.method.P
import org.springframework.security.access.prepost.PreAuthorize

/**
 * Created by vaibhav on 18/02/17.
 */
interface CourseRepositoryService {
    Tag addTagIfNotExists(String name);
    CourseCategory addCourseCategory(CourseCategory courseCategory);
    LearningResource save(LearningResource learningResource);
    Activity save(Activity activity);
    Module save(Module module);
    Course save(Course course);
    @PreAuthorize("hasPermission(#course, 'WRITE')")
    Course saveSecured(@P('course') Course course);
    @PreAuthorize("hasPermission(#course, 'WRITE')")
    Course publish(@P('course') Course course);
    @PreAuthorize("hasPermission(#course, 'WRITE')")
    Course tag(@P('course') Course course, List<String> tags);
    Enrollment save(Enrollment enrollment);
    ActivitySubmission save(ActivitySubmission activitySubmission);
    EvaluationRequest save(EvaluationRequest evaluationRequest);
    Evaluation save(Evaluation evaluation);
    List<Course> findByAuthor(User user);
    List<Course> findByConsumer(User user);
    List<CourseCategory> getCourseCategories();
    List<Course> getLatestCourses(Integer count);
    List<Course> findByCategoryName(String categoryName, Integer page, Integer count);
    List<Course> findByTagName(String tagName, Integer page, Integer count);
    List<Course> findByNameMatching(String name, Integer page, Integer count);
    List<Course> find(String query, Integer page, Integer count);
    Course findByNameAndAuthorName(String name, String author);
    Integer getEnrollmentCount(Course course);
    Module findByNameAndCourseNameAndAuthorName(String name, String course, String author);
    Activity findByNameAndModuleNameAndCourseNameAndAuthorName(String name, String module, String course, String author);
    CourseCategory findCourseCategoryById(Long id);
    Course findCourseById(Long id);
    List<Tag> findTagsByName(String query, Integer page, Integer count);
    Long countTotalSubmissions(Activity activity);
    List<ActivitySubmission> findSubmissionsByActivity(Activity activity, Integer page, Integer count);
}