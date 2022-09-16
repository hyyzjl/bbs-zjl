package com.zjl.dto.user.dto;

public class GetTokenDto {

    private String account;

    private String password;

    public GetTokenDto() {
    }

    public GetTokenDto(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
