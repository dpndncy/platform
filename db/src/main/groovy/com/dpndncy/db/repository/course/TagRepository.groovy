package com.dpndncy.db.repository.course

import com.dpndncy.db.entity.course.Tag
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 18/02/17.
 */
interface TagRepository extends PagingAndSortingRepository<Tag, Long> {
    Tag findByNameIgnoreCase(String name);
    List<Tag> findByNameLikeIgnoreCase(String name, Pageable pageable);
}