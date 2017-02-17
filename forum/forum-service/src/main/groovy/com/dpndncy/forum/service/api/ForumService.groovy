package com.dpndncy.forum.service.api

import com.dpndncy.db.entity.Category
import com.dpndncy.db.entity.Post
import com.dpndncy.db.entity.Topic

/**
 * Created by vaibhav on 17/02/17.
 */
interface ForumService {

    List<Category> getCategoriesForCourse(Long courseId);
    Category save(Category category);
    Category findCategoryById(Long categoryId);

    List<Topic> getTopicsForCategory(Long categoryId);
    Topic save(Topic topic);
    Topic findTopicById(Long topicId);

    List<Post> getPostsForTopic(Long topicId);
    Post save(Post post);
    Post findPostById(Long postId);
}
