package com.dpndncy.shared.exception

import com.dpndncy.shared.pojo.ApiErrorResponse
import org.springframework.http.HttpStatus

/**
 * Created by vaibhav on 17/02/17.
 */
class ActionNotAllowedException extends DpndncyException implements Serializable {

    ActionNotAllowedException(String customMessage) {
        super(ApiErrorResponse.ApiErrorResponseCode.ACTION_NOT_ALLOWED, customMessage, HttpStatus.LOCKED)
    }
}
