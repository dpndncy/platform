package com.dpndncy.forum.service.impl

import com.dpndncy.db.entity.Category
import com.dpndncy.db.entity.Post
import com.dpndncy.db.entity.Topic
import com.dpndncy.db.repository.CategoryRepository
import com.dpndncy.db.repository.PostRepository
import com.dpndncy.db.repository.TopicRepository
import com.dpndncy.forum.service.api.ForumService
import org.springframework.beans.factory.annotation.Autowired
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
        return categoryRepository.findAll();
    }

    @Override
    Category save(Category category) {
        return categoryRepository.save(category);
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
            if(topic.category != null) {
                existingTopic.category = topic.category;
            }
            return topicRepository.save(existingTopic);
        }
        else {
            return topicRepository.save(topic);
        }
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
        return postRepository.save(post);
    }

    @Override
    Post findPostById(Long postId) {
        return postRepository.findOne(postId);
    }
}
