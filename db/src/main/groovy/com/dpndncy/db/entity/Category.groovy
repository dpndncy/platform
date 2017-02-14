package com.dpndncy.db.entity

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table

/**
 * Created by vaibhav on 13/02/17.
 */
@Entity
@Table(name = 'categories')
@ToString
class Category extends Auditable implements Serializable {

    String name;
    @Lob
    String description;
    Integer position;
}
