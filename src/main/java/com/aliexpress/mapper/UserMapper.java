package com.aliexpress.mapper;

import com.aliexpress.beans.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RowMapper接口，返回User对象
 * */
public class UserMapper implements RowMapper<User>{

    @Override
public User mapRow(ResultSet resultSet, int i) throws SQLException {
//        获取结果集中的数据
    String name = resultSet.getString("account");
    String password = resultSet.getString("password");
    String nickname = resultSet.getString("nickname");
    String createtime = resultSet.getString("createtime");
    String updatetime = resultSet.getString("updatetime");
    int role = resultSet.getInt("role");
//        把数据封装成User对象
    User user = new User();
    user.setUserName(name);
    user.setPassword(password);
    user.setNickename(nickname);
    user.setRole(role);
    user.setCreatetime(createtime);
    user.setUpdatetime(updatetime);
    return user;
}
}