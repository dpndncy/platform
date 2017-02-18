package com.dpndncy.db.entity.course

import com.dpndncy.db.entity.Auditable
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table

/**
 * Created by vaibhav on 18/02/17.
 */
@Entity
@Table(name = 'tags', indexes = [@Index(columnList = "name")])
@ToString
class Tag extends Auditable implements Serializable {
    String name;
}
