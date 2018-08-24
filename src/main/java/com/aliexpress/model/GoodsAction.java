package com.aliexpress.model;

import com.aliexpress.mapper.GoodsMapper;
import com.aliexpress.beans.Goods;
import com.aliexpress.util.Util;
import com.aliexpress.util.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsAction {

    Logger logger = LoggerFactory.getLogger(GoodsAction.class);

@Autowired
    private JdbcTemplate jdbcTemplate;
@Autowired
    ImgAction imgAction;
@Autowired
    GoodsDetailsAction goodsDetailsAction;

    public  List<Map<String, Object>> getGoodsList(Map<String,Object> params) {
    String tagids = (String) params.get("tagids");
    String name = (String) params.get("name");
    String type = (String) params.get("type");
    String roleName = (String) params.get("ticket");

    StringBuilder sb = new StringBuilder("select  gb.id,gb.status,gb.name,gb.ename,gb.tagids,gb.cost,gb.weight,gb.kind,gb.remarks,gb.description,gb.idpic,gb.idpics,gb.createtime,gb.updatetime,FROM_UNIXTIME( gb.createtime/1000, '%Y-%m-%d %H:%i:%S') createtime2 from goodsinfo gb where 1=1 ");

    if(!Validation.isNULL(roleName)&&!"产品编辑".equals(Util.decryptBASE64(roleName))){
    sb = new StringBuilder("select   gb.id,gb.status,gb.name,gb.ename,gb.tagids,gb.supplier,gb.cost,gb.remarks,gb.weight,gb.kind,gb.description,gb.idpic,gb.idpics,gb.createtime,gb.updatetime,FROM_UNIXTIME( gb.createtime/1000, '%Y-%m-%d %H:%i:%S') createtime2 from goodsinfo gb where 1=1 ");
}
if(!Validation.isNULL(tagids)){
    String[] ids = tagids.split(",");
    for (int i = 0; i < ids.length; i++) {
        String id = ids[i];
        sb.append(" and gb.tagids like ('%").append(id).append("%') ");
    }
}
if(!Validation.isNULL(name)&&!Validation.isNULL(type)){
    if("code".equals(type)){
        sb.append(" and LOWER(gb.id) like LOWER('%").append(name).append("%')");
    }else {
        sb.append(" and LOWER(gb.name) like LOWER('%").append(name).append("%')");
    }
}
List<Map<String, Object>> list =  jdbcTemplate.queryForList(sb.toString()+" order by gb.createtime DESC");
return list;
}

public Goods getGoods(Map<String,Object> parms){
    String sql = "select gb.id,gb.status,gb.name,gb.ename,gb.tagids,gb.cost,gb.weight,gb.kind,gb.description,gb.remarks,gb.idpics,gb.idpic,gb.createtime,gb.updatetime,FROM_UNIXTIME( gb.createtime/1000, '%Y-%m-%d %H:%i:%S') createtime2 from gb.goodsinfo  where gb.id=?  order by gb.createtime DESC ";
    //        新建MyRowMapper类实现RowMapper接口，重写mapRow方法，
    String[] param ={(String)parms.get("id")};
    Goods Goods = null;
    try{
        Goods = jdbcTemplate.queryForObject(sql,new GoodsMapper(),param);
    }catch (Exception e){
        logger.error("e",e);
    }
    return Goods;
}
public boolean addGoods(Map<String,Object> parms){

    String roleName = (String) parms.get("ticket");
    if(!Validation.isNULL(roleName)&&"产品编辑".equals(Util.decryptBASE64(roleName))){
        return addGoodsByEditor(parms);
    }
    if(!Validation.isNULL(parms.get("id"))){
        return editGoods(parms);
    }
    String sql = "insert into goodsinfo ( name, ename,tagids, cost, weight, kind, supplier,remarks,idpics,createtime,description,status,idpic)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    String name = (String)parms.get("name");
    String ename = (String)parms.get("ename");
    String tagids = (String)parms.get("tagids");
    String cost = (String)parms.get("cost");
    String weight = (String)parms.get("weight");
    String kind = (String)parms.get("kind");
    String supplier = (String)parms.get("supplier");
    String remarks = (String)parms.get("remarks");
    String idpics = (String)parms.get("idpics");
    String idpic = (String)parms.get("idpic");
    String description = (String)parms.get("description");
    String statusStr = (String)parms.get("status");
    int status =1;
    if(!Validation.isNULL(statusStr)){
        status = Integer.valueOf(statusStr);
    }
    Long timestamp = System.currentTimeMillis();
    Object[] param ={name,ename,tagids,cost,weight,kind,supplier,remarks,idpics,timestamp.toString(),description,status,idpic};

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


public boolean addGoodsByEditor(Map<String,Object> parms){

    if(!Validation.isNULL(parms.get("id"))){
        return editGoods(parms);
    }
    String sql = "insert into goodsinfo ( name, ename,tagids, cost, weight, kind,remarks,idpics,createtime,status,idpic)  VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    String name = (String)parms.get("name");
    String ename = (String)parms.get("ename");
    String tagids = (String)parms.get("tagids");
    String cost = (String)parms.get("cost");
    String weight = (String)parms.get("weight");
    String kind = (String)parms.get("kind");
    String remarks = (String)parms.get("remarks");
    String idpics = (String)parms.get("idpics");
    String idpic = (String)parms.get("idpic");
    String statusStr = (String)parms.get("status");
    int status =1;
    if(!Validation.isNULL(statusStr)){
        status = Integer.valueOf(statusStr);
    }
    Long timestamp = System.currentTimeMillis();
    Object[] param ={name,ename,tagids,cost,weight,kind,remarks,idpics,timestamp.toString(),status,idpic};

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

public boolean delGoods(String goodsIds, String picIds){
    String sql = "delete from  goodsinfo where id  in(?)";
    //        新建MyRowMapper类实现RowMapper接口，重写mapRow方法，

    String[] goodsIdsArr = goodsIds.split(",");

    String[] picIdsArr = picIds.split(",");

    boolean flag = true;
    try{
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < goodsIdsArr.length ; i++) {
            sb.append("'").append(goodsIdsArr[i]).append("',") ;
        }
        goodsIds = sb.toString();
        goodsIds = goodsIds.substring(1,goodsIds.length()-2);
        Object[] param = {goodsIds};

        int temp =  jdbcTemplate.update(sql,param);
        if (temp <=0) {
            flag = false;
        }

        sb  = new StringBuilder();
        for (int i = 0; i < picIdsArr.length ; i++) {
            sb.append("'").append(picIdsArr[i]).append("',") ;
        }
        picIds = sb.toString();
        picIds = picIds.substring(1,picIds.length()-2);
        imgAction.delImgByIds(picIds);
    }catch (Exception e){
        logger.error("e",e);
        flag = false;
    }
    return flag;
}

public boolean editGoods(Map<String,Object> parms){
    String roleName = (String) parms.get("ticket");
    if(!Validation.isNULL(roleName)&&!"产品编辑".equals(Util.decryptBASE64(roleName))){
        Long timestamp = System.currentTimeMillis();
        String sql = "update goodsinfo set name = ?,ename = ?,tagids = ?,cost = ?,weight = ?,kind = ?,supplier = ?,description=? ,idpics=?,updatetime=?,remarks=? ,status=? ,idpic=? where id = ?";
        int id = (int)parms.get("id");
        String name = (String)parms.get("name");
        String ename = (String)parms.get("ename");
        String tagids = (String)parms.get("tagids");
        String cost = (String)parms.get("cost");
        String weight = (String)parms.get("weight");
        String kind = (String)parms.get("kind");
        String supplier = (String)parms.get("supplier");
        String description = (String)parms.get("description");
        String remarks = (String)parms.get("remarks");
        String idpics = (String)parms.get("idpics");
        String idpic = (String)parms.get("idpic");

        String statusStr = (String)parms.get("status");
        int status =1;
        if(!Validation.isNULL(statusStr)){
            status = Integer.valueOf(statusStr);
        }

        Object[] param ={name,ename,tagids,cost,weight,kind,supplier,description,idpics,timestamp.toString(),remarks,status,idpic,id};
        return editGoodsAction(sql,param);

    }else{

        Long timestamp = System.currentTimeMillis();
        String sql = "update goodsinfo set name = ?,ename = ?,tagids = ?,cost = ?,weight = ?,kind = ?,remarks=? ,idpics=?,updatetime=? ,status=?,idpic=? where id = ?";
        int id = (int)parms.get("id");
        String name = (String)parms.get("name");
        String ename = (String)parms.get("ename");
        String tagids = (String)parms.get("tagids");
        String cost = (String)parms.get("cost");
        String weight = (String)parms.get("weight");
        String kind = (String)parms.get("kind");
        String remarks = (String)parms.get("remarks");
        String idpics = (String)parms.get("idpics");
        String idpic = (String)parms.get("idpic");

        String statusStr = (String)parms.get("status");
        int status =1;
        if(!Validation.isNULL(statusStr)){
            status = Integer.valueOf(statusStr);
        }
        Object[] param ={name,ename,tagids,cost,weight,kind,remarks,idpics,timestamp.toString(),status,idpic,id};
        return editGoodsAction(sql,param);

    }
}
public boolean editGoodsAction(String sql, Object[] param){

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
