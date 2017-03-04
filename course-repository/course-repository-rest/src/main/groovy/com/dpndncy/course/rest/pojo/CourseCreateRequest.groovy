package com.dpndncy.course.rest.pojo

/**
 * Created by vaibhav on 04/03/17.
 */
class CourseCreateRequest implements Serializable {
    String name;
    String description;
    Long categoryId;
    String level;
}
