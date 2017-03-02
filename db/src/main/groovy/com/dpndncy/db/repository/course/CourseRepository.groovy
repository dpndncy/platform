package com.dpndncy.db.repository.course

import com.dpndncy.db.entity.User
import com.dpndncy.db.entity.course.Course
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 18/02/17.
 */
interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    List<Course> findByAuthor(User user);
    Page<Course> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Course> findByCategory_Name(String categoryName, Pageable pageable);
    Page<Course> findByNameLikeIgnoreCase(String name, Pageable pageable);
    Page<Course> findByTagList_NameIgnoreCase(String tagName, Pageable pageable);
    Course findByNameIgnoreCaseAndAuthor_Login(String name, String author);
}