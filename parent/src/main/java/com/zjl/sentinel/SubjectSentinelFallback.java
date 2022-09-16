package com.zjl.sentinel;

import com.zjl.dto.user.constant.JwtEnum;
import com.zjl.dto.user.dto.JwtTokenDto;
import org.springframework.http.ResponseEntity;

public class SubjectSentinelFallback {


    public static ResponseEntity<JwtTokenDto> getToken(String account, String password) {

        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.setToken("unknow");
        jwtTokenDto.setJwtEnum(JwtEnum.FAILED);
        return ResponseEntity.ok(jwtTokenDto);

    }


}
