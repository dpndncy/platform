package com.dpndncy.db.entity.course

import com.dpndncy.db.entity.Auditable
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Created by vaibhav on 18/02/17.
 */
@Entity
@Table(name = 'modules')
@ToString
class Module extends Auditable implements Serializable {
    String name;
    @Lob
    String description;
    Boolean published;
    @OneToMany
    List<Activity> activityList;
}
