package com.zjl.user.service;

import com.zjl.dto.user.dto.AddUserDto;
import com.zjl.dto.user.dto.GetUserDto;

import java.io.UnsupportedEncodingException;

public interface UserService {

    int addOneUser(AddUserDto addUserDto) throws UnsupportedEncodingException;

    GetUserDto getUserById(int uid);

}
