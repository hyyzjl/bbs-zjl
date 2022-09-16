package com.zjl.user.mapper.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String account;

    private String password;

    private String nickname;

    private String portrait;

    private Date ct;

    private Date ut;

    public User() {
    }

    public User(Integer id, String account, String password, String nickname, String portrait, Date ct, Date ut) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.nickname = nickname;
        this.portrait = portrait;
        this.ct = ct;
        this.ut = ut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }

    public Date getUt() {
        return ut;
    }

    public void setUt(Date ut) {
        this.ut = ut;
    }
}
