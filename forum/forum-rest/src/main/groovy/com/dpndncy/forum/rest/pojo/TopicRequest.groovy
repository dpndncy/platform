package com.dpndncy.forum.rest.pojo

/**
 * Created by vaibhav on 17/02/17.
 */
class TopicRequest implements Serializable {
    Long id;
    String title;
    Long categoryId;
    String description;
}
