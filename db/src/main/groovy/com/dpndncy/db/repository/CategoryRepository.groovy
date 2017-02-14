package com.dpndncy.db.repository

import com.dpndncy.db.entity.Category
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource

/**
 * Created by vaibhav on 13/02/17.
 */
@RepositoryRestResource
interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    @RestResource(exported = false)
    List<Category> findAll();

    @RestResource(exported = false)
    void delete(Long id);

    @RestResource(exported = false)
    void delete(Category category);
}