package com.dpndncy.db.entity

import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by vaibhav on 06/02/17.
 */
@Entity
@Table(name = 'users')
@ToString
class User extends Auditable implements Serializable {

    String name;
    @Column(unique = true)
    String email;
    @Column(unique = true)
    String login;
    String role;
    @Column(name = "last_login_date")
    Date lastLoginDate;
}
