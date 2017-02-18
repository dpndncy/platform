package com.dpndncy.db.entity.course

import com.dpndncy.db.entity.Auditable
import com.dpndncy.db.entity.User
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Lob
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Created by vaibhav on 18/02/17.
 */
@Entity
@Table(name = 'courses')
@ToString
class Course extends Auditable implements Serializable {
    String name;
    @Lob
    String description;
    Boolean published;
    CourseCategory category;
    User author;
    @ManyToMany
    List<Tag> tagList;
    @OneToMany
    List<Module> moduleList;
    @Enumerated(EnumType.STRING)
    CourseLevel level;

    public static enum CourseLevel {
        BEGINNER, INTERMEDIATE, ADVANCED
    }
}
