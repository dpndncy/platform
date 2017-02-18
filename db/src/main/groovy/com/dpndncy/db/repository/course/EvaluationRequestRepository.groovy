package com.dpndncy.db.repository.course

import com.dpndncy.db.entity.course.EvaluationRequest
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 18/02/17.
 */
interface EvaluationRequestRepository extends PagingAndSortingRepository<EvaluationRequest, Long> {

}