package com.dpndncy.course.service.impl

import com.dpndncy.course.service.api.CourseRepositoryService
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
import com.dpndncy.db.repository.course.ActivityRepository
import com.dpndncy.db.repository.course.ActivitySubmissionRepository
import com.dpndncy.db.repository.course.CourseCategoryRepository
import com.dpndncy.db.repository.course.CourseRepository
import com.dpndncy.db.repository.course.EnrollmentRepository
import com.dpndncy.db.repository.course.EvaluationRepository
import com.dpndncy.db.repository.course.EvaluationRequestRepository
import com.dpndncy.db.repository.course.LearningResourceRepository
import com.dpndncy.db.repository.course.ModuleRepository
import com.dpndncy.db.repository.course.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by vaibhav on 18/02/17.
 */
@Component
class CourseRepositoryServiceImpl implements CourseRepositoryService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ActivitySubmissionRepository activitySubmissionRepository;

    @Autowired
    CourseCategoryRepository courseCategoryRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    EvaluationRequestRepository evaluationRequestRepository;

    @Autowired
    LearningResourceRepository learningResourceRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired

    @Override
    Tag addTagIfNotExists(String name) {
        Tag existing = tagRepository.findByNameIgnoreCase(name);
        if(existing != null) {
            return existing;
        }
        else {
            Tag tag = new Tag(name: name);
            return tagRepository.save(tag);
        }
    }

    @Override
    CourseCategory addCourseCategory(CourseCategory courseCategory) {
        CourseCategory existing = courseCategoryRepository.findByNameIgnoreCase(courseCategory.name);
        if(existing != null) {
            return existing;
        }
        else {
            return courseCategoryRepository.save(courseCategory);
        }
    }

    @Override
    LearningResource save(LearningResource learningResource) {
        return null
    }

    @Override
    Activity save(Activity activity) {
        return null
    }

    @Override
    Module save(Module module) {
        return null
    }

    @Override
    Course save(Course course) {
        return null
    }

    @Override
    Enrollment save(Enrollment enrollment) {
        return null
    }

    @Override
    ActivitySubmission save(ActivitySubmission activitySubmission) {
        return null
    }

    @Override
    EvaluationRequest save(EvaluationRequest evaluationRequest) {
        return null
    }

    @Override
    Evaluation save(Evaluation evaluation) {
        return null
    }

    @Override
    List<Course> findByAuthor(User user) {
        return null
    }

    @Override
    List<Course> findByConsumer(User user) {
        return null
    }

    @Override
    List<CourseCategory> getCourseCategories() {
        return null
    }

    @Override
    List<Course> getLatestCourses(Integer count) {
        return null
    }

    @Override
    List<Course> findByCategoryName(String categoryName) {
        return null
    }

    @Override
    List<Course> findByTagName(String tagName) {
        return null
    }

    @Override
    List<Course> findByNameMatching(String name) {
        return null
    }

    @Override
    List<Course> find(String query) {
        return null
    }
}
