package com.zjl.user.controller;

import com.zjl.dto.user.dto.GetTokenDto;
import com.zjl.dto.user.dto.JwtTokenDto;
import com.zjl.user.service.JwtService;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Resource
    private JwtService jwtService;

    @RequestMapping(path = "/get", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<JwtTokenDto> getToken(@RequestParam("account") String account, @RequestParam("password") String password) throws UnsupportedEncodingException {
        return ResponseEntity.ok(jwtService.getToken(new GetTokenDto(account, password)));
    }

}
