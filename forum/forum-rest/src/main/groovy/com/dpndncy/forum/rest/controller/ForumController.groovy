package com.dpndncy.forum.rest.controller

import com.dpndncy.db.entity.Post
import com.dpndncy.db.entity.Topic
import com.dpndncy.forum.rest.pojo.TopicRequest
import com.dpndncy.forum.service.api.ForumService
import com.dpndncy.shared.exception.InvalidValueException
import com.dpndncy.shared.pojo.UserDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.access.method.P
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import com.dpndncy.db.entity.Category
/**
 * Created by vaibhav on 17/02/17.
 */
@RestController
class ForumController {

    @Autowired
    ForumService forumService;

    @Autowired
    PermissionEvaluator permissionEvaluator;

    @RequestMapping(path = "/categories", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Category createCategory(@RequestBody Category category) {
        return forumService.save(category);
    }

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    List<Category> getCategories(@RequestParam Long courseId) {
        return forumService.getCategoriesForCourse(courseId);
    }

    @RequestMapping(path = "/category/{id}", method = RequestMethod.GET)
    Category getCategory(@PathVariable Long id) {
        return forumService.findCategoryById(id);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    Topic createTopic(@RequestBody TopicRequest topicRequest, @AuthenticationPrincipal UserDetail userDetail) {
        if(topicRequest.id != null) {
            throw new InvalidValueException("id", topicRequest.id);
        }
        Topic topic = new Topic(title: topicRequest.title, description: topicRequest.description, creator: userDetail.user, category: forumService.findCategoryById(topicRequest.category_id));
        return forumService.save(topic);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    Topic updateTopic(@RequestBody TopicRequest topicRequest, @AuthenticationPrincipal UserDetail userDetail) {
        if(topicRequest.id == null) {
            throw new InvalidValueException("id", topicRequest.id);
        }
        Topic topic = new Topic(id: topicRequest.id, title: topicRequest.title, description: topicRequest.description, category: forumService.findCategoryById(topicRequest.category_id));
        return update(topic);
    }

    @PreAuthorize("hasPermission(#topic, 'WRITE')")
    Topic update(@P("topic") Topic topic) {
        if(permissionEvaluator.hasPermission(SecurityContextHolder.context.authentication, topic, BasePermission.WRITE)) {
            return forumService.save(topic);
        }
        else {
            throw new AccessDeniedException("403 Returned")
        }
    }

    @RequestMapping(path = "/topics", method = RequestMethod.GET)
    List<Topic> getTopics(@RequestParam Long categoryId) {
        return forumService.getTopicsForCategory(categoryId);
    }

    @RequestMapping(path = "/topic/{id}", method = RequestMethod.GET)
    Topic getTopic(@PathVariable Long id) {
        return forumService.findTopicById(id);
    }

    @RequestMapping(path = "/posts", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    Post createPost(@RequestBody Post post, @AuthenticationPrincipal UserDetail userDetail) {
        if(post.id != null) {
            throw new InvalidValueException("id", post.id);
        }
        post.creator = userDetail.user;
        post = forumService.save(post);
        updateTopicAfterPostCreation(post, userDetail);
        return post;
    }

    @RequestMapping(path = "/posts", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated() and hasPermission(#post, 'WRITE')")
    Post updatePost(@RequestBody @P("post") Post post, @AuthenticationPrincipal UserDetail userDetail) {
        if(post.id == null) {
            throw new InvalidValueException("id", post.id);
        }
        post.creator = userDetail.user;
        return forumService.save(post);
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    List<Post> getPosts(@RequestParam Long topicId) {
        return forumService.getPostsForTopic(topicId);
    }

    @RequestMapping(path = "/post/{id}", method = RequestMethod.GET)
    Post getPost(@PathVariable Long id) {
        return forumService.findPostById(id);
    }

    private void updateTopicAfterPostCreation(Post post, UserDetail userDetail) {
        Topic topic = post.topic;
        topic.lastReplyAt = new Date();
        topic.lastReplyBy = userDetail.user;
        forumService.save(topic);
    }
}
