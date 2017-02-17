package com.dpndncy.forum.service.api

import com.dpndncy.db.entity.User
import com.dpndncy.db.entity.forum.Category
import com.dpndncy.db.entity.forum.Post
import com.dpndncy.db.entity.forum.Topic
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
    Topic lockTopic(Long topicId, Boolean lock);
    Topic makeTopicSticky(Long topicId, Boolean sticky);
    Topic viewTopic(Long topicId, String ip, String agent);
    Boolean voteOnTopic(Long topicId, Boolean vote, User user);

    List<Post> getPostsForTopic(Long topicId);
    Post save(Post post);
    @PreAuthorize("hasPermission(#post, 'WRITE')")
    Post saveSecured(@P("post") Post post);
    Post findPostById(Long postId);
    Boolean voteOnPost(Long postId, Boolean vote, User user);
}
