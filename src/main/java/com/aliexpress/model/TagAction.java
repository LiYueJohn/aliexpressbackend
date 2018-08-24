package com.aliexpress.model;

import com.aliexpress.mapper.TagMapper;
import com.aliexpress.beans.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TagAction {

    Logger logger = LoggerFactory.getLogger(TagAction.class);

@Autowired
    private JdbcTemplate jdbcTemplate;

    public  List<Map<String, Object>> getTagList() {
        String sql = "select * from tag";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        return list;
    }

    public Tag getTag(Map<String,Object> parms){
    String sql = "select * from tag where id=? ";
    //        新建MyRowMapper类实现RowMapper接口，重写mapRow方法，
    String[] param ={(String)parms.get("id")};
Tag tag = null;
try{
    tag = jdbcTemplate.queryForObject(sql,new TagMapper(),param);
}catch (Exception e){
    logger.error("e",e);
}
return tag;
}
public boolean addTag(Map<String,Object> parms){
    Long timestamp = System.currentTimeMillis();
    String sql = "insert into tag (name,createtime) VALUES (?,?)";
    //        新建MyRowMapper类实现RowMapper接口，重写mapRow方法，
    String[] param ={(String)parms.get("name"),timestamp.toString()};

    boolean flag = true;
    try{
        int temp =  jdbcTemplate.update(sql,param);
        if (temp <=0) {
            flag = false;
        }
    }catch (Exception e){
        logger.error("e",e);
        flag = false;
    }
    return flag;
}

public boolean delTag(Map<String,Object> parms){
    String sql = "delete from  tag where id  = ?";
    //        新建MyRowMapper类实现RowMapper接口，重写mapRow方法，
    String ids = (String) parms.get("id");
    String[] arr = ids.split(",");

    boolean flag = true;
    try{
        for (int i = 0; i < arr.length ; i++) {
            Object[] param ={arr[i]};
            int temp =  jdbcTemplate.update(sql,param);
            if (temp <=0) {
                flag = false;
            }
        }
    }catch (Exception e){
        logger.error("e",e);
        flag = false;
    }
    return flag;
}

public boolean editTag(Map<String,Object> parms){
    Long timestamp = System.currentTimeMillis();
    String sql = "update tag set name = ?,updatetime = ?  where id = ?";
    Object param[] = new Object[]{parms.get("name"),parms.get("id"),timestamp.toString()};
    // 新建MyRowMapper类实现RowMapper接口，重写mapRow方法，
    boolean flag = true;
    try{
        int temp =  jdbcTemplate.update(sql,param);
        if (temp <=0) {
            flag = false;
        }
    }catch (Exception e){
        logger.error("e",e);
        flag = false;
    }
    return flag;
}
}
