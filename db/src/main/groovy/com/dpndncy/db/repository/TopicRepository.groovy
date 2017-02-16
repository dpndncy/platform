package com.dpndncy.db.repository

import com.dpndncy.db.entity.Topic
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.security.access.method.P
import org.springframework.security.access.prepost.PreAuthorize

/**
 * Created by vaibhav on 13/02/17.
 */
@RepositoryRestResource
interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {

    @RestResource(exported = false)
    List<Topic> findAll();

    @RestResource(exported = false)
    void delete(Long id);

    @RestResource(exported = false)
    void delete(Topic topic);

    @PreAuthorize("#topic.creator.login == authentication.principal.user.login")
    Topic save(@P("topic") Topic topic);

    @RestResource(path = "byCategoryId")
    List<Topic> findByCategoryId(Long categoryId);
}