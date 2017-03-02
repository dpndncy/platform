package com.dpndncy.db.entity.course

import com.dpndncy.db.entity.Auditable
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.Lob
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Created by vaibhav on 18/02/17.
 */
@Entity
@Table(name = 'modules')
@ToString
class Module extends Auditable implements Serializable {
    String name;
    @Lob
    String description;
    Boolean published;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = 'module')
    @JsonManagedReference
    List<Activity> activityList;
    @ManyToOne
    @JsonBackReference
    Course course;
}
