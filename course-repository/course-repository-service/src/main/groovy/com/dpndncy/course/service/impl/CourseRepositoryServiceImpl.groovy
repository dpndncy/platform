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
import com.sun.org.apache.xpath.internal.operations.Mod
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

import java.util.stream.Collectors

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
        return courseRepository.findByAuthor(user);
    }

    @Override
    List<Course> findByConsumer(User user) {
        List<Enrollment> enrollmentList = enrollmentRepository.findByUser(user);
        return enrollmentList.stream().map({enrollment -> enrollment.course}).collect(Collectors.toList());
    }

    @Override
    List<CourseCategory> getCourseCategories() {
        return courseCategoryRepository.findAll().asList();
    }

    @Override
    List<Course> getLatestCourses(Integer count) {
        PageRequest pageRequest = new PageRequest(0, count);
        return courseRepository.findAllByOrderByCreatedAtDesc(pageRequest).toList();
    }

    @Override
    List<Course> findByCategoryName(String categoryName, Integer page, Integer count) {
        PageRequest pageRequest = new PageRequest(page, count);
        return courseRepository.findByCategory_Name(categoryName, pageRequest).toList();
    }

    @Override
    List<Course> findByTagName(String tagName, Integer page, Integer count) {
        PageRequest pageRequest = new PageRequest(page, count);
        return courseRepository.findByTagList_NameIgnoreCase(tagName, pageRequest).toList();
    }

    @Override
    List<Course> findByNameMatching(String name, Integer page, Integer count) {
        PageRequest pageRequest = new PageRequest(page, count);
        return courseRepository.findByNameLikeIgnoreCase(name, pageRequest).toList();
    }

    @Override
    List<Course> find(String query, Integer page, Integer count) {
        List<Course> matchingName = findByNameMatching(query, page, count);
        List<Course> matchingCategory = findByCategoryName(query, page, count);
        List<Course> matchingTag = findByTagName(query, page, count);

        return  matchingName + matchingCategory + matchingTag;
    }

    @Override
    Course findByNameAndAuthorName(String name, String author) {
        return courseRepository.findByNameIgnoreCaseAndAuthor_Login(name, author);
    }

    @Override
    Integer getEnrollmentCount(Course course) {
        return enrollmentRepository.countByCourse(course);
    }

    @Override
    Module findByNameAndCourseNameAndAuthorName(String name, String course, String author) {
        return moduleRepository.findByNameIgnoreCaseAndCourse_NameIgnoreCaseAndCourse_Author_Login(name, course, author);
    }

    @Override
    Activity findByNameAndModuleNameAndCourseNameAndAuthorName(String name, String module, String course, String author) {
        return activityRepository.findByNameIgnoreCaseAndModule_NameIgnoreCaseAndModule_Course_NameIgnoreCaseAndModule_Course_Author_Login(name, module, course, author);
    }
}
