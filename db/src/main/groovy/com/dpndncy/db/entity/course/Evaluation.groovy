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
@Table(name = 'evaluations')
@ToString
class Evaluation extends Auditable implements Serializable {
    @OneToOne
    EvaluationRequest evaluationRequest;
    @ManyToOne
    User evaluator;
    Boolean completed;
    Integer score;
}
