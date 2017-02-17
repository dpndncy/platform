package com.dpndncy.shared.util

import com.dpndncy.shared.exception.DpndncyException
import com.dpndncy.shared.pojo.ApiErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by vaibhav on 17/02/17.
 */
@ControllerAdvice
class ExceptionHandlerAdvice {

    @ExceptionHandler(DpndncyException)
    @ResponseBody
    public ResponseEntity<ApiErrorResponse> handleException(DpndncyException e) {
        ApiErrorResponse response = new ApiErrorResponse(error: e.customMessage, code: e.errorCode);
        return new ResponseEntity<ApiErrorResponse>(response, e.httpStatus);
    }
}
