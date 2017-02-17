package com.dpndncy.db.repository.forum

import com.dpndncy.db.entity.forum.Category
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 13/02/17.
 */
interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

}