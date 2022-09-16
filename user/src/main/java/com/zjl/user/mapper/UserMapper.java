package com.zjl.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjl.user.mapper.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select id,account,password from user where account = #{account}")
    public User selectUserByAccount(@Param("account") String account);

}
