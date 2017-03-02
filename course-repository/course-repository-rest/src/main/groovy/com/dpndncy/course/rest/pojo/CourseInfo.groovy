package com.dpndncy.course.rest.pojo

import com.dpndncy.db.entity.course.Course

/**
 * Created by vaibhav on 03/03/17.
 */
class CourseInfo implements Serializable {
    Course course;
    Integer enrollmentCount;
}
