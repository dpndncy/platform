package com.dpndncy.db.entity.course

import com.dpndncy.db.entity.Auditable
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Created by vaibhav on 18/02/17.
 */
@Entity
@Table(name = 'activities')
@ToString
class Activity extends Auditable implements Serializable {
    String title;
    @Lob
    String description;
    Boolean published;
    @Lob
    @Column(name = "evaluation_guidelines")
    String evaluationGuidelines;
    @OneToMany
    List<LearningResource> resourceList;
}
