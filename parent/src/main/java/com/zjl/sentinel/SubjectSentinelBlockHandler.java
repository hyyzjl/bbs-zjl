package com.zjl.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zjl.dto.user.constant.JwtEnum;
import com.zjl.dto.user.dto.JwtTokenDto;
import org.springframework.http.ResponseEntity;

public class SubjectSentinelBlockHandler {

    public static ResponseEntity<JwtTokenDto> getToken(String account, String password, BlockException exception) {

        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.setToken("SubjectSentinelBlockHandler");
        jwtTokenDto.setJwtEnum(JwtEnum.FAILED);
        return ResponseEntity.ok(jwtTokenDto);

    }

}
