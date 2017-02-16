package com.dpndncy.db.handler

import com.dpndncy.db.entity.Post
import com.dpndncy.db.entity.Topic
import com.dpndncy.db.entity.User
import com.dpndncy.db.repository.PostRepository
import com.dpndncy.db.repository.TopicRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.core.annotation.HandleAfterCreate
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.HandleBeforeSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

/**
 * Created by vaibhav on 13/02/17.
 */
@RepositoryEventHandler
class PostHandler {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    PostRepository postRepository;

    @HandleBeforeSave
    void handleBeforePostSave(Post post) {
        if(post.id == null) {
            post.creator = getLoggedInUser();
        }
        else {
            post.creator = getUserForPost(post);
        }
    }

    @HandleAfterCreate
    void handleAfterPostCreate(Post post) {
        Topic topic = post.topic;
        topic.lastReplyAt = getCurrentTime();
        topic.lastReplyBy = getLoggedInUser();
        topicRepository.save(topic);
    }

    static User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.context.authentication;
        return authentication.principal.user;
    }

    static Date getCurrentTime() {
        return new Date();
    }

    User getUserForPost(Post post) {
        return postRepository.findOne(post.id).creator;
    }
}
