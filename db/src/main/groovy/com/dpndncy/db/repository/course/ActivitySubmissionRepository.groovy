package com.dpndncy.db.repository.course

import com.dpndncy.db.entity.course.Activity
import com.dpndncy.db.entity.course.ActivitySubmission
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 18/02/17.
 */
interface ActivitySubmissionRepository extends PagingAndSortingRepository<ActivitySubmission, Long> {
    List<ActivitySubmission> findByActivityOrderByCreatedAtDesc(Activity activity, Pageable pageable);
    Long countByActivity(Activity activity);
}