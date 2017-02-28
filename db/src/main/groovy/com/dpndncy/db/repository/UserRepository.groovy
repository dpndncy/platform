package com.dpndncy.db.repository

import com.dpndncy.db.entity.User
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 06/02/17.
 */
interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByGithubId(Long id);
    User findByLogin(String login);
}
