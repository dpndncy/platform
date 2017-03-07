package com.dpndncy.app.impl.github

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * Created by vaibhav on 06/02/17.
 */
class GithubUser implements Serializable {

    boolean hireable;

    @JsonProperty(value = "created_at")
    Date createdAt;

    @JsonProperty(value = "updated_at")
    Date updatedAt;

    int collaborators;

    int followers;

    int following;

    int id;

    @JsonProperty(value = "avatar_url")
    String avatarUrl;

    String bio;

    String blog;

    String company;

    String email;

    @JsonProperty(value = "gravatar_id")
    String gravatarId;

    @JsonProperty(value = "html_url")
    String htmlUrl;

    String location;

    String login;

    String name;

    String type;

    String url;
}
