package com.dpndncy.db.entity.course

import com.dpndncy.db.entity.Auditable
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * Created by vaibhav on 18/02/17.
 */
@Entity
@Table(name = 'activity_submissions')
@ToString
class ActivitySubmission extends Auditable implements Serializable {
    @ManyToOne
    Activity activity;
    @ManyToOne
    Enrollment enrollment;
    @Column(name = "github_link")
    String githubLink;
    Boolean completed;
    Boolean evaluated;
}
