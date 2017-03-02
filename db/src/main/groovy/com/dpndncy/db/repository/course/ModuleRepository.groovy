package com.dpndncy.db.repository.course

import com.dpndncy.db.entity.course.Module
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 18/02/17.
 */
interface ModuleRepository extends PagingAndSortingRepository<Module, Long> {
    Module findByNameIgnoreCaseAndCourse_NameIgnoreCaseAndCourse_Author_Login(String name, String course, String author);
}