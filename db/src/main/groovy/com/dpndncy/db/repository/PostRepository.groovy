package com.dpndncy.db.repository

import com.dpndncy.db.entity.Post
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.security.access.prepost.PreAuthorize

/**
 * Created by vaibhav on 13/02/17.
 */
@RepositoryRestResource
interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    @RestResource(exported = false)
    List<Post> findAll();

    @RestResource(path = "byTopicId")
    List<Post> findByTopicId(Long topicId);

    @RestResource(exported = false)
    void delete(Long id);

    @RestResource(exported = false)
    void delete(Post post);

    @PreAuthorize("hasPermission(filterObject, 'write')")
    Post save(Post post);
}