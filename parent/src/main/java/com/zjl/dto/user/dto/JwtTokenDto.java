package com.zjl.dto.user.dto;

import com.zjl.dto.user.constant.JwtEnum;

public class JwtTokenDto {

    private JwtEnum jwtEnum;

    private String token;

    public JwtEnum getJwtEnum() {
        return jwtEnum;
    }

    public void setJwtEnum(JwtEnum jwtEnum) {
        this.jwtEnum = jwtEnum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
