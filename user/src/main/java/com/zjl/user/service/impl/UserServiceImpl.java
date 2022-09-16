package com.zjl.user.service.impl;

import com.zjl.dto.user.dto.AddUserDto;
import com.zjl.dto.user.dto.GetUserDto;
import com.zjl.user.mapper.UserMapper;
import com.zjl.user.mapper.model.User;
import com.zjl.user.service.UserService;
import io.seata.core.context.RootContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int addOneUser(AddUserDto addUserDto) throws UnsupportedEncodingException {
        System.out.println("========"+ RootContext.getXID());

        User user = new User();
        BeanUtils.copyProperties(addUserDto, user);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes("utf-8")));
        return userMapper.insert(user);
    }

    public GetUserDto getUserById(int uid) {
        User user = userMapper.selectById(uid);
        GetUserDto getUserDto = new GetUserDto();
        BeanUtils.copyProperties(user, getUserDto);
        return getUserDto;
    }


}
