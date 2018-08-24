package com.aliexpress.model;

import com.aliexpress.beans.Img;
import com.aliexpress.mapper.ImgMapper;
import com.aliexpress.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ImgAction {

    Logger logger = LoggerFactory.getLogger(ImgAction.class);

@Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getImgListByIds(Map<String,Object>parms) {
    String ids = (String) parms.get("ids");
    String[] idsArr = ids.split(",");
    String sql = "select * from pic WHERE  idpic in(";
    StringBuilder sb = new StringBuilder(sql);
    for (int i = 0; i < idsArr.length; i++) {
    sb.append("'").append(idsArr[i]).append("',");
}
String sqlStr = sb.toString();
sqlStr = sqlStr.substring(0,sqlStr.length()-1);

List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlStr+")");
return list;
}

public  Img getImgListById(String id) {

    String sql = "select * from pic WHERE  idpic ='" +id+ "'";

    Img img = jdbcTemplate.queryForObject(sql,new ImgMapper());
    return img;
}

public String addImg(Map<String, Object> parms) {
    String sql = "insert into pic (idpic,img,name) VALUES (?,?,?)";
    String uuid = (String) parms.get("uuid");
    String[] param = {uuid, (String) parms.get("img"), (String) parms.get("name")};

    String flag = uuid;
    try {
        int temp = jdbcTemplate.update(sql, param);
        if (temp <= 0) {
            flag = "";
        }
    } catch (Exception e) {
        logger.error("e", e);
        flag = "";
    }
    return flag;
}

public boolean delImg(Map<String, Object> parms) {
    String sql = "delete from  pic where idpic  = ?";
    boolean flag = true;
    try {
        Object[] param = {(String) parms.get("uuid")};
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


public boolean delImgByIds(String idpics) {
    String sql = "delete from  pic where idpic  in(?) ";
    boolean flag = true;
    try {
        Object[] param = {idpics};
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
}
