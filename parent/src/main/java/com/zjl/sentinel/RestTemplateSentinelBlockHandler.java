package com.zjl.sentinel;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

public class RestTemplateSentinelBlockHandler {


    public static SentinelClientHttpResponse block(HttpRequest request, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution,
                                                   BlockException exception){
        return new SentinelClientHttpResponse(null);
    }

}
