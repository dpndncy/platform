package com.dpndncy.db.repository.forum

import com.dpndncy.db.entity.forum.Topic
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 13/02/17.
 */
interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {

    List<Topic> findByCategoryId(Long categoryId);
}