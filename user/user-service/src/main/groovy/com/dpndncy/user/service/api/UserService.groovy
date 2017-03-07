package com.dpndncy.user.service.api

import com.dpndncy.db.entity.User

/**
 * Created by vaibhav on 28/02/17.
 */
interface UserService {

    User findByUsername(String username);
    User findByLogin(String login);
    User save(User user);

}