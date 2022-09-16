package com.zjl.dto.user.constant;

public enum  JwtEnum {

    SUCCESS(1,"获取成功"),

    FAILED(-1,"获取失败"),

    PASSWORD_VERIFICATION_FAILED(-2,"密码校验失败"),

    ACCOUNT_NOT_FOUNT(-3,"账号未找到");

    private int code;

    private String msg;

    JwtEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
