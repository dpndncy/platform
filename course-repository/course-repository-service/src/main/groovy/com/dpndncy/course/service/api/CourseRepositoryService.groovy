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
    Enrollment save(Enrollment enrollment);
    ActivitySubmission save(ActivitySubmission activitySubmission);
    EvaluationRequest save(EvaluationRequest evaluationRequest);
    Evaluation save(Evaluation evaluation);
    List<Course> findByAuthor(User user);
    List<Course> findByConsumer(User user);
    List<CourseCategory> getCourseCategories();
    List<Course> getLatestCourses(Integer count);
    List<Course> findByCategoryName(String categoryName);
    List<Course> findByTagName(String tagName);
    List<Course> findByNameMatching(String name);
    List<Course> find(String query);
}