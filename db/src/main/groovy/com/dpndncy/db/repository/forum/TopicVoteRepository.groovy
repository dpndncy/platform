package com.dpndncy.db.repository.forum

import com.dpndncy.db.entity.forum.TopicVote
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 17/02/17.
 */
interface TopicVoteRepository extends PagingAndSortingRepository<TopicVote, Long> {
    TopicVote findByUserIdAndTopicId(Long userId, Long topicId);
}