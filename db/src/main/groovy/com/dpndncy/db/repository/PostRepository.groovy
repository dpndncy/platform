package com.dpndncy.db.repository

import com.dpndncy.db.entity.Post
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 13/02/17.
 */
interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    List<Post> findByTopicId(Long topicId);
}