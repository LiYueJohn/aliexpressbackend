package com.aliexpress.model;

import com.aliexpress.beans.GoodsDetails;
import com.aliexpress.mapper.GoodsDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GoodsDetailsAction {

    Logger logger = LoggerFactory.getLogger(GoodsDetailsAction.class);

@Autowired
    private JdbcTemplate jdbcTemplate;
@Autowired
    ImgAction imgAction;

    public GoodsDetails getGoodsDetails(Map<String, Object> parms) {
    String sql = "select g.*,FROM_UNIXTIME( g.createtime/1000, '%Y-%m-%d %H:%i:%S') createtime2,FROM_UNIXTIME( g.updatetime/1000, '%Y-%m-%d %H:%i:%S') updatetime2 " +
        " from goodsdetails g,myuser u  where g.id=? and u.account=? and u.role!=2";
    //        新建MyRowMapper类实现RowMapper接口，重写mapRow方法，
    Object[] param = { parms.get("id"),parms.get("account")};
GoodsDetails goodsDetails = null;
try {
    goodsDetails = jdbcTemplate.queryForObject(sql, new GoodsDetailsMapper(), param);
} catch (Exception e) {
    goodsDetails = new GoodsDetails();
    logger.error("e", e);

}
return goodsDetails;
}

public boolean addGoodsDetails(Map<String, Object> parms) {

    String action = (String) parms.get("action");
    if("edit".equals(action)){
        return editGoodsDetails(parms);
    }
    String sql = "insert into goodsdetails ( id,description,idpic1,idpic2,idpic3,idpic4,idpic5,idpic6,createtime)  VALUES (?,?,?,?,?,?,?,?,?)";

    int id = (int) parms.get("id");
    String description = (String) parms.get("description");
    String idpic1 = (String) parms.get("idpic1");
    String idpic2 = (String) parms.get("idpic2");
    String idpic3 = (String) parms.get("idpic3");
    String idpic4 = (String) parms.get("idpic4");
    String idpic5 = (String) parms.get("idpic5");
    String idpic6 = (String) parms.get("idpic6");
    Long timestamp = System.currentTimeMillis();
    Object[] param = {id, description, idpic1, idpic2, idpic3, idpic4, idpic5, idpic6, timestamp.toString()};

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

public boolean delGoodsDetails(int goodsId) {
    String sql = "delete from  goodsdetails where id  in(?)";
    boolean flag = true;
    try {

        Object[] param = {goodsId};
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

public boolean editGoodsDetails(Map<String, Object> parms) {
    Long timestamp = System.currentTimeMillis();
    String sql = "update goodsdetails set description=? ,idpic1=?,idpic2=?,idpic3=?,idpic4=?,idpic5=?,idpic6=?,updatetime=?  where id = ?";
    int id = (int) parms.get("id");
    String idpic1 = (String) parms.get("idpic1");
    String idpic2 = (String) parms.get("idpic2");
    String idpic3 = (String) parms.get("idpic3");
    String idpic4 = (String) parms.get("idpic4");
    String idpic5 = (String) parms.get("idpic5");
    String idpic6 = (String) parms.get("idpic6");
    String description = (String) parms.get("description");

    Object[] param = {description, idpic1, idpic2, idpic3, idpic4, idpic5, idpic6, timestamp.toString(), id};
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

public boolean delGoodsDetails(String goodsIds) {
    String sql1 =  "delete from pic where  idpic in ("+
        "select gd.idpic1 from goodsdetails gd right join goodsbasic gb on gd.id = gb.id where gb.id in(?)"+
        ") or idpic in ("+
        "select gd.idpic2 from goodsdetails gd right join goodsbasic gb on gd.id = gb.id where gb.id in(?)"+
        ") or idpic in ("+
        "select gd.idpic3 from goodsdetails gd right join goodsbasic gb on gd.id = gb.id where gb.id in(?)"+
        ") or idpic in ("+
        "select gd.idpic4 from goodsdetails gd right join goodsbasic gb on gd.id = gb.id where gb.id in(?)"+
        ") or idpic in ("+
        "select gd.idpic5 from goodsdetails gd right join goodsbasic gb on gd.id = gb.id where gb.id in(?)"+
        ") or idpic in ("+
        "select gd.idpic6 from goodsdetails gd right join goodsbasic gb on gd.id = gb.id where gb.id in(?)"+
        ")";

    String sql = "delete from  goodsdetails where id  in(?)";
    boolean flag = true;
    try {
        Object[] param2 ={goodsIds,goodsIds,goodsIds,goodsIds,goodsIds,goodsIds};
        jdbcTemplate.update(sql1,param2);

        Object[] param ={goodsIds};
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
