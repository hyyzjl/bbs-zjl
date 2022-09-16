package com.zjl.subject.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SentinelRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {

        String origin = request.getParameter("origin");

        if (StringUtils.isBlank(origin)){
            origin = "unknown";
        }

        return origin;
    }
}
