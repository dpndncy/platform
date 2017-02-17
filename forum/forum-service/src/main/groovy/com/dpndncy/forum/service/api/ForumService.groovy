package com.dpndncy.forum.service.api

import com.dpndncy.db.entity.Category
import com.dpndncy.db.entity.Post
import com.dpndncy.db.entity.Topic
import org.springframework.security.access.method.P
import org.springframework.security.access.prepost.PreAuthorize

/**
 * Created by vaibhav on 17/02/17.
 */
interface ForumService {

    List<Category> getCategoriesForCourse(Long courseId);
    Category save(Category category);
    Category saveSecured(@P("category") Category category);
    Category findCategoryById(Long categoryId);

    List<Topic> getTopicsForCategory(Long categoryId);
    Topic save(Topic topic);
    @PreAuthorize("hasPermission(#topic, 'WRITE')")
    Topic saveSecured(@P("topic") Topic topic);
    Topic findTopicById(Long topicId);

    List<Post> getPostsForTopic(Long topicId);
    Post save(Post post);
    @PreAuthorize("hasPermission(#post, 'WRITE')")
    Post saveSecured(@P("post") Post post);
    Post findPostById(Long postId);
}
