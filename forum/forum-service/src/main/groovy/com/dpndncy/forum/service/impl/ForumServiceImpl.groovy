package com.dpndncy.forum.service.impl

import com.dpndncy.db.entity.forum.Category
import com.dpndncy.db.entity.forum.Post
import com.dpndncy.db.entity.forum.Topic
import com.dpndncy.db.repository.CategoryRepository
import com.dpndncy.db.repository.PostRepository
import com.dpndncy.db.repository.TopicRepository
import com.dpndncy.forum.service.api.ForumService
import com.dpndncy.shared.exception.ActionNotAllowedException
import com.dpndncy.shared.exception.InvalidValueException
import com.dpndncy.shared.exception.MissingEntityException
import com.dpndncy.shared.pojo.UserDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.method.P
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

/**
 * Created by vaibhav on 17/02/17.
 */
@Component
class ForumServiceImpl implements ForumService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    List<Category> getCategoriesForCourse(Long courseId) {
        return categoryRepository.findAll().asList();
    }

    @Override
    Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    Category saveSecured(Category category) {
        return save(category);
    }

    @Override
    Category findCategoryById(Long categoryId) {
        if(categoryId == null) {
            return null;
        }
        return categoryRepository.findOne(categoryId);
    }

    @Override
    List<Topic> getTopicsForCategory(Long categoryId) {
        return topicRepository.findByCategoryId(categoryId);
    }

    @Override
    Topic save(Topic topic) {
        if(topic.id != null) {
            Topic existingTopic = topicRepository.findOne(topic.id);
            existingTopic.title = topic.title;
            existingTopic.description = topic.description;
            return topicRepository.save(existingTopic);
        }
        else {
            return topicRepository.save(topic);
        }
    }

    @Override
    Topic saveSecured(@P("topic") Topic topic) {
        return save(topic);
    }

    @Override
    Topic findTopicById(Long topicId) {
        if(topicId == null) {
            return null;
        }
        return topicRepository.findOne(topicId);
    }

    @Override
    Topic lockTopic(Long topicId, Boolean lock) {
        Topic topic = findTopicById(topicId);
        if(topic == null) {
            throw new MissingEntityException(topicId);
        }
        topic.locked = lock;
        return topicRepository.save(topic);
    }

    @Override
    Topic makeTopicSticky(Long topicId, Boolean sticky) {
        Topic topic = findTopicById(topicId);
        if(topic == null) {
            throw new MissingEntityException(topicId);
        }
        topic.sticky = sticky;
        return topicRepository.save(topic);
    }

    @Override
    List<Post> getPostsForTopic(Long topicId) {
        return postRepository.findByTopicId(topicId);
    }

    @Override
    Post save(Post post) {
        if(!isTopicUnlocked(post.topic)) {
            throw new ActionNotAllowedException("Cannot post to locked topic");
        }
        if(post.id != null) {
            Post existingPost = postRepository.findOne(post.id);
            existingPost.body = post.body;
            return postRepository.save(existingPost);
        }
        else {
            post = postRepository.save(post);
            updateTopicAfterPostCreation(post);
            return post;
        }
    }

    @Override
    Post saveSecured(Post post) {
        return save(post);
    }

    @Override
    Post findPostById(Long postId) {
        if(postId == null) {
            return null;
        }
        return postRepository.findOne(postId);
    }

    private void updateTopicAfterPostCreation(Post post) {
        Topic topic = post.topic;
        topic.lastReplyAt = new Date();
        topic.lastReplyBy = SecurityContextHolder.context.authentication.principal.user;
        topicRepository.save(topic);
    }

    private static Boolean isTopicUnlocked(Topic topic) {
        return !topic.locked;
    }
}
