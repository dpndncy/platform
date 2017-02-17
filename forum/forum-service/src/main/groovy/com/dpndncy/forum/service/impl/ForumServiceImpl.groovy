package com.dpndncy.forum.service.impl

import com.dpndncy.db.entity.Category
import com.dpndncy.db.entity.Post
import com.dpndncy.db.entity.Topic
import com.dpndncy.db.repository.CategoryRepository
import com.dpndncy.db.repository.PostRepository
import com.dpndncy.db.repository.TopicRepository
import com.dpndncy.forum.service.api.ForumService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.method.P
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
        return topicRepository.findOne(topicId);
    }

    @Override
    List<Post> getPostsForTopic(Long topicId) {
        return postRepository.findByTopicId(topicId);
    }

    @Override
    Post save(Post post) {
        if(post.id != null) {
            Post existingPost = postRepository.findOne(post.id);
            existingPost.body = post.body;
            return postRepository.save(existingPost);
        }
        else {
            return postRepository.save(post);
        }
    }

    @Override
    Post saveSecured(Post post) {
        return save(post);
    }

    @Override
    Post findPostById(Long postId) {
        return postRepository.findOne(postId);
    }
}
