package com.dpndncy.db.entity.forum

import com.dpndncy.db.entity.Auditable
import com.dpndncy.db.entity.User
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * Created by vaibhav on 13/02/17.
 */
@Entity
@Table(name = 'posts')
@ToString
class Post extends Auditable implements Serializable {

    @ManyToOne
    Topic topic;
    @ManyToOne
    User creator;
    @Lob
    String body;
}
