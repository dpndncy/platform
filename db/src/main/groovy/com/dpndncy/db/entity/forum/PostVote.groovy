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
@Table(name = 'post_votes', uniqueConstraints=[@UniqueConstraint(columnNames=['user_id', 'post_id'])])
@ToString
class PostVote extends Auditable implements Serializable {

    @ManyToOne
    PostVoteCode code;

    @ManyToOne
    User user;

    @ManyToOne
    Post post;
}
