package com.dpndncy.db.entity.course

import com.dpndncy.db.entity.Auditable
import com.dpndncy.db.entity.User
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

/**
 * Created by vaibhav on 18/02/17.
 */
@Entity
@Table(name = 'evaluation_requests')
@ToString
class EvaluationRequest extends Auditable implements Serializable {
    @OneToOne
    ActivitySubmission activitySubmission;
    @ManyToOne
    User assignee;
    Boolean completed;
}
