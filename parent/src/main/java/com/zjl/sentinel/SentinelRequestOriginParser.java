package com.zjl.sentinel;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;


//public class SentinelRequestOriginParser implements RequestOriginParser {
//    @Override
//    public String parseOrigin(HttpServletRequest request) {
//
//        String origin = request.getParameter("origin");
//
//        if (StringUtils.isBlank(origin)){
//            origin = "unknown";
//        }
//
//        return origin;
//    }
//}
