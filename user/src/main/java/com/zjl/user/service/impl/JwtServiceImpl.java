package com.zjl.user.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zjl.dto.user.constant.JwtEnum;
import com.zjl.dto.user.dto.GetTokenDto;
import com.zjl.dto.user.dto.JwtTokenDto;
import com.zjl.user.mapper.UserMapper;
import com.zjl.user.mapper.model.User;
import com.zjl.user.service.JwtService;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Service
public class JwtServiceImpl implements JwtService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    public JwtTokenDto getToken(GetTokenDto getTokenDto) throws UnsupportedEncodingException {

        TraceContext.putCorrelation("TestKey", "testValue");

        User user = userMapper.selectUserByAccount(getTokenDto.getAccount());
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        //密码校验
        if (user == null) {
            jwtTokenDto.setJwtEnum(JwtEnum.ACCOUNT_NOT_FOUNT);
            return jwtTokenDto;
        }
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(getTokenDto.getPassword().getBytes("utf-8")))) {
            jwtTokenDto.setJwtEnum(JwtEnum.PASSWORD_VERIFICATION_FAILED);
            return jwtTokenDto;
        }
        String token = JWT.create().withAudience(user.getId().toString())
                .sign(Algorithm.HMAC256(user.getPassword()));
        jwtTokenDto.setJwtEnum(JwtEnum.SUCCESS);
        jwtTokenDto.setToken(token);
        System.out.println("trace ID: " + TraceContext.traceId());
        System.out.println("TestKey : " + TraceContext.getCorrelation("TestKey"));
        testTrace();
        setToken(user.getId(),token);
        return jwtTokenDto;
    }


    @Trace(operationName = "testTrace")
    public void testTrace() {
        System.out.println("test trace ...");
    }

    @Trace(operationName = "setToken")
    public void setToken(Integer id, String token) {
        redisTemplate.opsForValue().set("token:"+id,token);
    }
}

