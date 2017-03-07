package com.dpndncy.app.impl.google

import com.dpndncy.app.api.AuthenticationService
import com.dpndncy.app.impl.RestUtil
import com.dpndncy.app.impl.github.GithubUser
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
class GoogleAuthenticationService implements AuthenticationService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserService userService;

    private String GOOGLE_BASE_URL = "https://www.googleapis.com/plus/v1/people/me";


    @Override
    public UserDetail authenticate(String token) {
        GoogleUser googleUser = getGoogleUser(token);
        if(googleUser == null) {
            return null;
        }
        else {
            User user = userService.findByLogin(googleUser.id);
            if(user == null) {
                user = new User(name: googleUser.displayName, login: googleUser.id, email: googleUser.emails[0].value, role: "ROLE_USER", lastLoginDate: new Date());
                user = userService.save(user);
            }
            else {
                user.setLastLoginDate(new Date());
                user = userService.save(user);
            }
            return getUserDetail(user);
        }
    }

    private GoogleUser getGoogleUser(String token) {
        ResponseEntity<GoogleUser> responseEntity = restTemplate.getForEntity(GOOGLE_BASE_URL + "?access_token=" + token, GoogleUser);
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
