package com.dpndncy.shared.exception

import com.dpndncy.shared.pojo.ApiErrorResponse
import org.springframework.http.HttpStatus

/**
 * Created by user-1 on 5/4/16.
 */
class InvalidValueException extends DpndncyException implements Serializable {
    String key;
    String invalidValueProvided;

    InvalidValueException(String key, Object invalidValueProvided) {
        super(ApiErrorResponse.ApiErrorResponseCode.INVALID_DATA, "Invalid value $invalidValueProvided provided for field $key.", HttpStatus.BAD_REQUEST);
        this.key = key;
        this.invalidValueProvided = invalidValueProvided;
    }
    
}
