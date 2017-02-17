package com.dpndncy.shared.exception

import com.dpndncy.shared.pojo.ApiErrorResponse
import org.springframework.http.HttpStatus

/**
 * Created by vaibhav on 17/02/17.
 */
class MissingEntityException extends DpndncyException implements Serializable {

    MissingEntityException(Long id) {
        super(ApiErrorResponse.ApiErrorResponseCode.MISSING_ENTITY, "No entity with id $id found", HttpStatus.BAD_REQUEST);
    }
}
