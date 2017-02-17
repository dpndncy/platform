package com.dpndncy.db.repository

import com.dpndncy.db.entity.Category
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 13/02/17.
 */
interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

}