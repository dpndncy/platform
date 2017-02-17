package com.dpndncy.db.repository.forum

import com.dpndncy.db.entity.forum.TopicView
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 17/02/17.
 */
interface TopicViewRepository extends PagingAndSortingRepository<TopicView, Long> {

}