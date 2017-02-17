package com.dpndncy.shared.exception

import org.springframework.http.HttpStatus

/**
 * Created by user-1 on 5/4/16.
 */
class DpndncyException extends RuntimeException implements Serializable {

    Integer errorCode;
    String customMessage;
    HttpStatus httpStatus;

    DpndncyException(Integer errorCode, String customMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.customMessage = customMessage;
        this.httpStatus = httpStatus;
    }
}
