package com.dpndncy.db.entity.forum

import com.dpndncy.db.entity.Auditable
import com.dpndncy.db.entity.User
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

/**
 * Created by vaibhav on 17/02/17.
 */
@Entity
@Table(name = 'topic_votes', uniqueConstraints=[@UniqueConstraint(columnNames=['user_id', 'topic_id'])])
@ToString
class TopicVote extends Auditable implements Serializable {

    @ManyToOne
    TopicVoteCode code;

    @ManyToOne
    User user;

    @ManyToOne
    Topic topic;
}
