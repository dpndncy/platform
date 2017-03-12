package com.dpndncy.course.rest.pojo

import com.dpndncy.db.entity.course.Course

/**
 * Created by vaibhav on 04/03/17.
 */
class CourseCreateRequest implements Serializable {
    String name;
    String description;
    Long categoryId;
    Course.CourseLevel level;
}
