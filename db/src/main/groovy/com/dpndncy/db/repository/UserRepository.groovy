package com.dpndncy.db.repository

import com.dpndncy.db.entity.User
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

/**
 * Created by vaibhav on 06/02/17.
 */
@RepositoryRestResource(exported = false)
interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByGithubId(Long id);
}
