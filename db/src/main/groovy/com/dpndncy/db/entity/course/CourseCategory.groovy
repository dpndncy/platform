package com.dpndncy.db.entity.course

import com.dpndncy.db.entity.Auditable
import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Created by vaibhav on 18/02/17.
 */
@Entity
@Table(name = 'course_categories')
@ToString
class CourseCategory extends Auditable implements Serializable {
    String name;
    @Lob
    String description;
}
