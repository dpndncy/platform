package com.dpndncy.shared.exception

import com.dpndncy.shared.pojo.ApiErrorResponse
import org.springframework.http.HttpStatus

/**
 * Created by vaibhav on 03/03/17.
 */
class NotFoundException extends DpndncyException implements Serializable {

    NotFoundException(String customMessage) {
        super(ApiErrorResponse.ApiErrorResponseCode.NOT_FOUND, customMessage, HttpStatus.NOT_FOUND);
    }
}
