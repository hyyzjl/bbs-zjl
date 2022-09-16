package com.zjl.subject.adapter;

import com.zjl.dto.user.dto.AddUserDto;
import com.zjl.dto.user.dto.JwtTokenDto;
import com.zjl.subject.adapter.feignFallbackFactory.UserFeignAdapterSentinelFallbackFactory;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@FeignClient(name = "user-service",
//    fallbackFactory = UserFeignAdapterSentinelFallbackFactory.class,
    configuration = FeignAutoConfiguration.class)
public interface UserFeignAdapter {

    @RequestMapping(path = "/jwt/get", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    ResponseEntity<JwtTokenDto> getToken(@RequestParam("account") String account, @RequestParam("password") String password);

    @RequestMapping(path = "/test" ,method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<String> test();


    @RequestMapping(path = "user/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public ResponseEntity<Integer> addOneUser(@RequestBody AddUserDto req);

}
