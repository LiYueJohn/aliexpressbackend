package com.aliexpress.model;

import com.aliexpress.mapper.UserMapper;
import com.aliexpress.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserAction {

    Logger logger = LoggerFactory.getLogger(UserAction.class);

@Autowired
    private JdbcTemplate jdbcTemplate;

    public  List<Map<String, Object>> getUserList() {
        String sql = "select id,account,nickname,role ,FROM_UNIXTIME(  createtime/1000, '%Y-%m-%d %H:%i:%S') addtime ,FROM_UNIXTIME(  updatetime/1000, '%Y-%m-%d %H:%i:%S') as updatetime  from myuser";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        return list;
    }

    public boolean delUser(int id) {
    String sql = "delete from  myuser where id  in(?)";
    boolean flag = true;
    try {

    Object[] param = {id};
    int temp = jdbcTemplate.update(sql, param);
    if (temp <= 0) {
    flag = false;
}
} catch (Exception e) {
    logger.error("e", e);
    flag = false;
}
return flag;
}

public boolean addUser(Map<String, Object> parms) {
    String sql = "insert into myuser (nickname,account,role,password,createtime)  VALUES (?,?,?,?,?)";
    int role = (int) parms.get("role");
    String nickname = (String) parms.get("nickname");
    String account = (String) parms.get("account");
    String password = (String) parms.get("password");
    Long timestamp = System.currentTimeMillis();
    Object[] param = {nickname,account,role,password, timestamp.toString()};

    boolean flag = true;
    try {
        int temp = jdbcTemplate.update(sql, param);
        if (temp <= 0) {
            flag = false;
        }
    } catch (Exception e) {
        logger.error("e", e);
        flag = false;
    }
    return flag;
}
public List<Map<String, Object>> login(Map<String, Object> parms) {
    String sql = "select * from myuser where account='"+parms.get("username")+"' and password='"+parms.get("password")+"'";
    List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
    return list;
}

public User login2(Map<String,Object> parms){
    String sql = "select * from myuser where account=? and password=?";
    //        新建MyRowMapper类实现RowMapper接口，重写mapRow方法，指定返回User对象
    String[] param ={(String)parms.get("username"),(String)parms.get("password")};
    User user = null;
    try{
        user = jdbcTemplate.queryForObject(sql,new UserMapper(),param);
    }catch (Exception e){
        logger.error("e",e);
    }
    return user;
}


public List<Map<String, Object>> getUser(String account) {
    String sql = "select * from myuser where account='"+account+"'";
    List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
    return list;
}
}
