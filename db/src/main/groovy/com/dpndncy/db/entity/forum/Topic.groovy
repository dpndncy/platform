package com.dpndncy.db.entity.forum

import com.dpndncy.db.entity.Auditable
import com.dpndncy.db.entity.User
import groovy.transform.ToString

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * Created by vaibhav on 13/02/17.
 */
@Entity
@Table(name = 'topics')
@ToString
class Topic extends Auditable implements Serializable {

    String title;
    @ManyToOne
    Category category;
    @ManyToOne
    User creator;
    Boolean sticky;
    Boolean locked;
    @Column(name = "last_reply_at")
    Date lastReplyAt;
    @ManyToOne(cascade = CascadeType.ALL)
    User lastReplyBy;
    @Lob
    String description;
}
