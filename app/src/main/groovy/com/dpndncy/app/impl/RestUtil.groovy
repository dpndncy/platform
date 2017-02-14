package com.dpndncy.app.impl;

import org.springframework.http.HttpStatus;

/**
 * Created by user-1 on 9/9/15.
 */
class RestUtil {

    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series) || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
}
