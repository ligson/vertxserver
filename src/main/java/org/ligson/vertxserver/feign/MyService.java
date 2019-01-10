package org.ligson.vertxserver.feign;

import feign.Headers;
import feign.RequestLine;

public interface MyService {
    @RequestLine("POST /test")
    @Headers("Content-Type: application/json;charset=UTF-8")
    String userInfo(User user);
}
