package com.zjl.subject.adapter.impl;

import com.alibaba.fastjson.JSON;
import com.zjl.dto.user.dto.AddUserDto;
import com.zjl.dto.user.dto.GetUserDto;
import com.zjl.subject.adapter.UserAdapter;
import com.zjl.subject.adapter.UserFeignAdapter;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserAdapterImpl implements UserAdapter {

    @Resource
    private UserFeignAdapter userFeignAdapter;

//    @Override
//    public ResponseEntity<GetUserDto> getUserById(Integer userId) {
//
//        ServiceInstance serviceInstance = loadBalancerClient.choose("user-service");
//
//        ResponseEntity<GetUserDto> response = restTemplate.getForEntity("http://user-service" + ":" +
//                serviceInstance.getPort() + "/user/get/" + userId, GetUserDto.class);
//
//        return response;
//    }

    @Override
    public ResponseEntity<Integer> addUser(BusinessActionContext actionContext,AddUserDto addUserDto) {
        ResponseEntity<Integer> response = userFeignAdapter.addOneUser(addUserDto);
        System.out.println("========" + RootContext.getXID());
//        int a = 1 / 0;
        return response;
    }

    @Override
    public boolean commit(BusinessActionContext context) {

        Object addUserDto = context.getActionContext("addUserDto");
        log.info("-----------------------------------");
        log.info("commit : {}", JSON.toJSON(addUserDto));
        log.info("-----------------------------------");
        return true;
    }

    @Override
    public boolean cancel(BusinessActionContext context) {
        Object addUserDto = context.getActionContext("addUserDto");
        log.info("-----------------------------------");
        log.error("rollback : {}", JSON.toJSON(addUserDto));
        log.info("-----------------------------------");
        return true;
    }


}
