package com.zjl.subject.adapter.feignFallbackFactory;

import com.zjl.dto.user.constant.JwtEnum;
import com.zjl.dto.user.dto.AddUserDto;
import com.zjl.dto.user.dto.JwtTokenDto;
import com.zjl.subject.adapter.UserFeignAdapter;
import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserFeignAdapterSentinelFallbackFactory implements FallbackFactory<UserFeignAdapter> {
    @Override
    public UserFeignAdapter create(Throwable throwable) {
        return new UserFeignAdapter(){

            @Override
            public ResponseEntity<JwtTokenDto> getToken(String account, String password) {
                JwtTokenDto jwtTokenDto = new JwtTokenDto();
                jwtTokenDto.setToken("fail");
                jwtTokenDto.setJwtEnum(JwtEnum.FAILED);
                return ResponseEntity.ok(jwtTokenDto);
            }

            @Override
            public ResponseEntity<String> test() {
                return ResponseEntity.ok("testRrror");
            }

            @Override
            public ResponseEntity<Integer> addOneUser(AddUserDto req) {
                return ResponseEntity.ok(-1);
            }
        };
    }
}
