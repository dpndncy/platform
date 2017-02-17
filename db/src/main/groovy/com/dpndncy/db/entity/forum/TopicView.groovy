package com.dpndncy.db.entity.forum

import com.dpndncy.db.entity.Auditable
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

/**
 * Created by vaibhav on 17/02/17.
 */
@Entity
@Table(name = 'topic_views', uniqueConstraints=[@UniqueConstraint(columnNames=['ip', 'topic_id'])])
@ToString
class TopicView extends Auditable implements Serializable {

    @ManyToOne
    Topic topic;

    @Column(nullable = false)
    String ip;

    String agent;
}
