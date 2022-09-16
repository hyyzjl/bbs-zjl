package com.zjl.user.service;

import com.zjl.dto.user.dto.GetTokenDto;
import com.zjl.dto.user.dto.JwtTokenDto;

import java.io.UnsupportedEncodingException;

public interface JwtService {


    JwtTokenDto getToken(GetTokenDto getTokenDto) throws UnsupportedEncodingException;
}
