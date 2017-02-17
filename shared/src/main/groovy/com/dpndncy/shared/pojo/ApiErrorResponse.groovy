package com.dpndncy.shared.pojo

/**
 * Created by user-1 on 6/4/16.
 */
class ApiErrorResponse {

    String error;
    Integer code;

    public static class ApiErrorResponseCode {
        public static final Integer MISSING_REQUIRED_PROPERTY = 1;
        public static final Integer INVALID_DATA = 2;
        public static final Integer RESOURCE_EXPIRED = 3;
        public static final Integer OPERATION_FAILED = 4;
        public static final Integer MISSING_ENTITY = 5;
        public static final Integer ACTION_NOT_ALLOWED = 6;
    }
}
