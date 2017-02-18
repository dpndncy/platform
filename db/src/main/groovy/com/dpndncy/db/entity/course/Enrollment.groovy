package com.dpndncy.db.entity.course

import com.dpndncy.db.entity.Auditable
import com.dpndncy.db.entity.User
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * Created by vaibhav on 18/02/17.
 */
@Entity
@Table(name = 'enrollments')
@ToString
class Enrollment extends Auditable implements Serializable {
    @ManyToOne
    User user;
    @ManyToOne
    Course course;
    Boolean completed;
    Integer score;
}
