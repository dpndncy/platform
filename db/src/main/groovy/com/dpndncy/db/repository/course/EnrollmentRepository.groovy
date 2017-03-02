package com.dpndncy.db.repository.course

import com.dpndncy.db.entity.User
import com.dpndncy.db.entity.course.Course
import com.dpndncy.db.entity.course.Enrollment
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 18/02/17.
 */
interface EnrollmentRepository extends PagingAndSortingRepository<Enrollment, Long> {
    List<Enrollment> findByUser(User user);
    List<Enrollment> findByCourse(Course course);
    Integer countByCourse(Course course);
}