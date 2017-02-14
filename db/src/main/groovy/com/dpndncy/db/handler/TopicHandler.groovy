package com.dpndncy.db.handler

import com.dpndncy.db.entity.Topic
import com.dpndncy.db.entity.User
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

/**
 * Created by vaibhav on 13/02/17.
 */
@RepositoryEventHandler
class TopicHandler {

    @HandleBeforeCreate
    void handleBeforeTopicCreate(Topic topic) {
        topic.creator = getLoggedInUser();
    }

    static User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.context.authentication;
        return authentication.principal.user;
    }
}
