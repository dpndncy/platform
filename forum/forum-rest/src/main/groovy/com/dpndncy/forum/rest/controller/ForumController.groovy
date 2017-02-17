package com.dpndncy.forum.rest.controller

import com.dpndncy.db.entity.forum.Post
import com.dpndncy.db.entity.forum.Topic
import com.dpndncy.forum.rest.pojo.PostRequest
import com.dpndncy.forum.rest.pojo.TopicRequest
import com.dpndncy.forum.rest.pojo.TopicVoteRequest
import com.dpndncy.forum.rest.pojo.TopicVoteResponse
import com.dpndncy.forum.service.api.ForumService
import com.dpndncy.shared.exception.InvalidValueException
import com.dpndncy.shared.pojo.UserDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import com.dpndncy.db.entity.forum.Category

import javax.servlet.http.HttpServletRequest

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
        Topic topic = new Topic(title: topicRequest.title, description: topicRequest.description, creator: userDetail.user, category: forumService.findCategoryById(topicRequest.categoryId));
        return forumService.save(topic);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    Topic updateTopic(@RequestBody TopicRequest topicRequest, @AuthenticationPrincipal UserDetail userDetail) {
        if(topicRequest.id == null) {
            throw new InvalidValueException("id", topicRequest.id);
        }
        if(topicRequest.categoryId != null) {
            throw new InvalidValueException("categoryId", topicRequest.categoryId);
        }
        Topic topic = new Topic(id: topicRequest.id, title: topicRequest.title, description: topicRequest.description, category: forumService.findCategoryById(topicRequest.categoryId));
        return forumService.saveSecured(topic);
    }

    @RequestMapping(path = "/lock_topic/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    Topic lockTopic(@PathVariable Long id) {
        if(id == null) {
            throw new InvalidValueException("id", id);
        }
        return forumService.lockTopic(id, true);
    }

    @RequestMapping(path = "/unlock_topic/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    Topic unlockTopic(@PathVariable Long id) {
        if(id == null) {
            throw new InvalidValueException("id", id);
        }
        return forumService.lockTopic(id, false);
    }

    @RequestMapping(path = "/make_topic_sticky/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    Topic makeTopicSticky(@PathVariable Long id) {
        if(id == null) {
            throw new InvalidValueException("id", id);
        }
        return forumService.makeTopicSticky(id, true);
    }

    @RequestMapping(path = "/make_topic_unsticky/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    Topic makeTopicUnsticky(@PathVariable Long id) {
        if(id == null) {
            throw new InvalidValueException("id", id);
        }
        return forumService.makeTopicSticky(id, false);
    }

    @RequestMapping(path = "/topic/vote", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    TopicVoteResponse voteOnTopic(@RequestBody TopicVoteRequest topicVoteRequest, @AuthenticationPrincipal UserDetail userDetail) {
        if(topicVoteRequest.topicId == null) {
            throw new InvalidValueException("topicId", topicVoteRequest.topicId);
        }
        Boolean success = forumService.voteOnTopic(topicVoteRequest.topicId, topicVoteRequest.vote, userDetail.user);
        return new TopicVoteResponse(success: success);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.GET)
    List<Topic> getTopics(@RequestParam Long categoryId) {
        return forumService.getTopicsForCategory(categoryId);
    }

    @RequestMapping(path = "/topic/{id}", method = RequestMethod.GET)
    Topic getTopic(@PathVariable Long id, HttpServletRequest request) {
        return forumService.viewTopic(id, request.getRemoteAddr(), request.getHeader(HttpHeaders.USER_AGENT));
    }

    @RequestMapping(path = "/posts", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    Post createPost(@RequestBody PostRequest postRequest, @AuthenticationPrincipal UserDetail userDetail) {
        if(postRequest.id != null) {
            throw new InvalidValueException("id", postRequest.id);
        }
        Post post = new Post(body: postRequest.body, creator: userDetail.user, topic: forumService.findTopicById(postRequest.topicId));
        return forumService.save(post);
    }

    @RequestMapping(path = "/posts", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    Post updatePost(@RequestBody PostRequest postRequest, @AuthenticationPrincipal UserDetail userDetail) {
        if(postRequest.id == null) {
            throw new InvalidValueException("id", postRequest.id);
        }
        if(postRequest.topicId != null) {
            throw new InvalidValueException("topicId", postRequest.topicId);
        }
        Post post = new Post(body: postRequest.body, creator: userDetail.user, topic: forumService.findTopicById(postRequest.topicId));
        return forumService.saveSecured(post);
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    List<Post> getPosts(@RequestParam Long topicId) {
        return forumService.getPostsForTopic(topicId);
    }

    @RequestMapping(path = "/post/{id}", method = RequestMethod.GET)
    Post getPost(@PathVariable Long id) {
        return forumService.findPostById(id);
    }
}
