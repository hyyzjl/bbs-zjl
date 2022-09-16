package com.zjl.subject.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zjl.dto.user.dto.AddUserDto;
import com.zjl.dto.user.dto.GetUserDto;
import com.zjl.dto.user.dto.JwtTokenDto;
import com.zjl.sentinel.SubjectSentinelBlockHandler;
import com.zjl.sentinel.SubjectSentinelFallback;
import com.zjl.subject.adapter.UserFeignAdapter;
import com.zjl.subject.adapter.UserAdapter;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@Slf4j
public class TestController {

    @Resource
    private UserAdapter userAdapter;

    @Resource
    private UserFeignAdapter feignAdapter;

//    @RequestMapping(path = "get/{id}", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
//    @ResponseBody
//    public ResponseEntity<GetUserDto> getUserById(@PathVariable("id") Integer id) {
//        return userAdapter.getUserById(id);
//    }

    @RequestMapping(path = "get/testRt", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<String> testRt() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(new Date().toString());
    }


//    fallback：若本接口出现未知异常，则调用fallback指定的接口。
//    blockHandler：若本次访问被限流或服务降级，则调用blockHandler指定的接口
    @RequestMapping(path = "jwt/get", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    @ResponseBody
//    @SentinelResource(value = "jg", fallback = "getToken", fallbackClass = SubjectSentinelFallback.class)
//    @SentinelResource(value = "jg", blockHandler = "getToken", blockHandlerClass = SubjectSentinelBlockHandler.class)
    public ResponseEntity<JwtTokenDto> getToken(@Param("account") String account, @Param("password") String password) {
        return feignAdapter.getToken(account, password);
    }

    @RequestMapping(path = "user/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    @GlobalTransactional
    public ResponseEntity<Integer> getToken(@RequestBody AddUserDto addUserDto) {
        log.info("==="+ RootContext.getXID());
        return userAdapter.addUser(null,addUserDto);
    }

}
