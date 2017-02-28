package com.dpndncy.db.repository.course

import com.dpndncy.db.entity.course.CourseCategory
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 18/02/17.
 */
interface CourseCategoryRepository extends PagingAndSortingRepository<CourseCategory, Long> {
    CourseCategory findByNameIgnoreCase(String name);
}