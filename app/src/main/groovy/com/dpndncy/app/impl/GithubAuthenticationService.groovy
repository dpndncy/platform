package com.dpndncy.app.impl

import com.dpndncy.db.entity.User
import com.dpndncy.db.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.client.RestTemplate

/**
 * Created by vaibhav on 06/02/17.
 */
class GithubAuthenticationService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    private String GITHUB_BASE_URL = "https://api.github.com";

    public UserDetail authenticate(String token) {
        GithubUser githubUser = getGithubUser(token);
        if(githubUser == null) {
            return null;
        }
        else {
            User user = userRepository.findByGithubId(githubUser.id);
            if(user == null) {
                user = new User(name: githubUser.name, login: githubUser.login, email: githubUser.email, role: "ROLE_USER", githubId: githubUser.id, lastLoginDate: new Date());
                user = userRepository.save(user);
            }
            else {
                user.setLastLoginDate(new Date());
                user = userRepository.save(user);
            }
            return getUserDetail(user);
        }
    }

    private GithubUser getGithubUser(String token) {
        ResponseEntity<GithubUser> responseEntity = restTemplate.getForEntity(GITHUB_BASE_URL + "/user?access_token=" + token, GithubUser);
        if(RestUtil.isError(responseEntity.getStatusCode())) {
            return null;
        }
        else {
            return responseEntity.getBody();
        }
    }

    private static UserDetail getUserDetail(User user) {
        return new UserDetail(user: user, roles: [new SimpleGrantedAuthority(user.role)]);
    }
}