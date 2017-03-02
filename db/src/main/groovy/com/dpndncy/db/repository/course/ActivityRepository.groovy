package com.dpndncy.db.repository.course

import com.dpndncy.db.entity.course.Activity
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 18/02/17.
 */
interface ActivityRepository extends PagingAndSortingRepository<Activity, Long> {
    Activity findByNameIgnoreCaseAndModule_NameIgnoreCaseAndModule_Course_NameIgnoreCaseAndModule_Course_Author_Login(String name, String module, String course, String author);
}