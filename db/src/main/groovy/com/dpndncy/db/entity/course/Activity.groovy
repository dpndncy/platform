package com.dpndncy.db.entity.course

import com.dpndncy.db.entity.Auditable
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Lob
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Created by vaibhav on 18/02/17.
 */
@Entity
@Table(name = 'activities')
@ToString
class Activity extends Auditable implements Serializable {
    String name;
    @Lob
    String description;
    Boolean published;
    @Lob
    @Column(name = "evaluation_guidelines")
    String evaluationGuidelines;
    @OneToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    List<LearningResource> resourceList;
    @JsonBackReference
    @ManyToOne
    Module module;
}
