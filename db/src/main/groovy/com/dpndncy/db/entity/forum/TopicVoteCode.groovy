package com.dpndncy.db.entity.forum

import com.dpndncy.db.entity.Auditable
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by vaibhav on 17/02/17.
 */
@Entity
@Table(name = 'topic_vote_codes')
@ToString
class TopicVoteCode extends Auditable implements Serializable {

    @Column(nullable = false, unique = true)
    Boolean direction;

    @Column(name = "vote_value", nullable = false)
    Integer voteValue;

    @Column(name = "creator_vote_value", nullable = false)
    Integer creatorVoteValue;
}
