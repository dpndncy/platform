package com.dpndncy.user.service.impl

import com.dpndncy.db.entity.User
import com.dpndncy.db.repository.UserRepository
import com.dpndncy.user.service.api.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by vaibhav on 28/02/17.
 */
@Component
class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    User findByUsername(String username) {
        return userRepository.findByLogin(username);
    }

    @Override
    User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    User save(User user) {
        return userRepository.save(user);
    }
}
