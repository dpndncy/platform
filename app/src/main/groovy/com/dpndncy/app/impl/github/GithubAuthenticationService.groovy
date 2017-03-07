package com.dpndncy.app.impl.github

import com.dpndncy.app.api.AuthenticationService
import com.dpndncy.app.impl.RestUtil
import com.dpndncy.db.entity.User
import com.dpndncy.shared.pojo.UserDetail
import com.dpndncy.user.service.api.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.client.RestTemplate

/**
 * Created by vaibhav on 06/02/17.
 */
class GithubAuthenticationService implements AuthenticationService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserService userService;

    private String GITHUB_BASE_URL = "https://api.github.com";


    @Override
    public UserDetail authenticate(String token) {
        GithubUser githubUser = getGithubUser(token);
        if(githubUser == null) {
            return null;
        }
        else {
            User user = userService.findByGithubId(githubUser.id);
            if(user == null) {
                user = new User(name: githubUser.name, login: githubUser.login, email: githubUser.email, role: "ROLE_USER", githubId: githubUser.id, lastLoginDate: new Date());
                user = userService.save(user);
            }
            else {
                user.setLastLoginDate(new Date());
                user = userService.save(user);
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
