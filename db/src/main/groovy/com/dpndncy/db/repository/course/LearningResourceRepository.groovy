package com.dpndncy.db.repository.course

import com.dpndncy.db.entity.course.LearningResource
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 18/02/17.
 */
interface LearningResourceRepository extends PagingAndSortingRepository<LearningResource, Long> {

}