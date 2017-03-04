package com.dpndncy.db.entity.course

import com.dpndncy.db.entity.Auditable
import com.dpndncy.db.entity.User
import com.fasterxml.jackson.annotation.JsonManagedReference
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.Lob
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
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
    @ManyToOne
    @JoinColumn
    CourseCategory category;
    @ManyToOne
    @JoinColumn
    User author;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Tag> tagList;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = 'course')
    @JsonManagedReference
    List<Module> moduleList;
    @Enumerated(EnumType.STRING)
    CourseLevel level;
    @Column(name = 'was_published')
    Boolean wasPublished;

    public static enum CourseLevel {
        BEGINNER, INTERMEDIATE, ADVANCED
    }
}
