package com.dpndncy.app.impl;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by user-1 on 9/9/15.
 */
@Component
class RestErrorHandler implements ResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return RestUtil.isError(response.getStatusCode());
    }
}
