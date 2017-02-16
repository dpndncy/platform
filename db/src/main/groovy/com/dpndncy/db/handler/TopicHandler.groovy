package com.dpndncy.db.handler

import com.dpndncy.db.entity.Topic
import com.dpndncy.db.entity.User
import com.dpndncy.db.repository.TopicRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.HandleBeforeSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

/**
 * Created by vaibhav on 13/02/17.
 */
@RepositoryEventHandler
class TopicHandler {

    @Autowired
    TopicRepository topicRepository;

    @HandleBeforeSave
    @HandleBeforeCreate
    void handleBeforeTopicSave(Topic topic) {
        if(topic.id == null) {
            topic.creator = getLoggedInUser();
        }
        else {
            topic.creator = getUserForTopic(topic);
        }
    }

    static User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.context.authentication;
        return authentication.principal.user;
    }

    User getUserForTopic(Topic topic) {
        return topicRepository.findOne(topic.id).creator;
    }
}
