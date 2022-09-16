package com.zjl.dto.user.dto;


import java.util.Date;


public class GetUserDto extends AddUserDto{

    private Integer id;

    private Date ct;

    private Date ut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
