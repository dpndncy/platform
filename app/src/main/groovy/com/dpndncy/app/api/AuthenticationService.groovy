package com.dpndncy.app.api

import com.dpndncy.shared.pojo.UserDetail

/**
 * Created by vaibhav on 07/03/17.
 */
interface AuthenticationService {

    UserDetail authenticate(String token)
}