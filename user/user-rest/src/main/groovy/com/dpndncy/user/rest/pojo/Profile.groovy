package com.dpndncy.user.rest.pojo

import com.dpndncy.db.entity.User
import com.dpndncy.db.entity.course.Course

/**
 * Created by vaibhav on 28/02/17.
 */
class Profile implements Serializable {

    User user;
    List<Course> enrolledCourseList;
    List<Course> authoredCourseList;
}
