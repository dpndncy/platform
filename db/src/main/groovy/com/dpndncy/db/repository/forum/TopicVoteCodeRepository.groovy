package com.dpndncy.db.repository.forum

import com.dpndncy.db.entity.forum.TopicVoteCode
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 17/02/17.
 */
interface TopicVoteCodeRepository extends PagingAndSortingRepository<TopicVoteCode, Long> {
    TopicVoteCode findByDirection(Boolean direction);
}