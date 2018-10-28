package com.aliexpress.model;

import com.aliexpress.mapper.GoodsMapper;
import com.aliexpress.beans.Goods;
import com.aliexpress.util.Page;
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

    public Page getGoodsList(Map<String, Object> params) {
        String tagids = (String) params.get("tagids");
        String name = (String) params.get("name");
        String type = (String) params.get("type");
        String roleName = (String) params.get("ticket");
        int pageNo = (int) params.get("pageNo");
        int pageSize = (int) params.get("pageSize");
        StringBuilder sb = new StringBuilder("select  gb.id,gb.multiproduct,gb.status,gb.name,gb.ename,gb.shop,gb.tagids,gb.cost,gb.weight,gb.kind,gb.remarks,gb.idpic,gb.idpics,gb.createtime,gb.updatetime,FROM_UNIXTIME( gb.createtime/1000, '%Y-%m-%d %H:%i:%S') createtime2 from goodsinfo gb where 1=1 ");

//        if (!Validation.isNULL(roleName) && !"产品编辑".equals(Util.decryptBASE64(roleName))) {
//            sb = new StringBuilder("select   gb.id,gb.status,gb.name,gb.ename,gb.declareen,gb.declarezh,gb.shop,gb.tagids,gb.supplier,gb.cost,gb.remarks,gb.weight,gb.kind,gb.description,gb.idpic,gb.idpics,gb.createtime,gb.updatetime,FROM_UNIXTIME( gb.createtime/1000, '%Y-%m-%d %H:%i:%S') createtime2 from goodsinfo gb where 1=1 ");
//        }
        if (!Validation.isNULL(tagids)) {
            String[] ids = tagids.split(",");
            for (int i = 0; i < ids.length; i++) {
                String id = ids[i];
                sb.append(" and gb.tagids like ('%").append(id).append("%') ");
            }
        }
        if (!Validation.isNULL(type) && (!Validation.isNULL(name)|| !Validation.isNULL( params.get("status")))) {
            if ("code".equals(type)) {//编码
                sb.append(" and gb.id = ").append(name).append("  ");
            } else if ("status".equals(type)) {//状态
                sb.append(" and status = ").append(params.get("status")).append(" ");
            } else {//名称
                sb.append(" and LOWER(gb.name) like LOWER('%").append(name).append("%') ");
            }
        }
        Page page = new Page();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString() + " order by gb.createtime DESC");
        if (list.size() == 0) {
            page.generate(pageNo, pageSize, 0, list);
            return page;
        }
        sb.append(" order by gb.createtime DESC").append(" limit ").append((pageNo - 1) * pageSize).append(" , ").append(pageSize);
        List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sb.toString());
        page.generate(pageNo, pageSize, list.size(), list2);
        return page;
    }

    public Goods getGoods(Map<String, Object> parms) {
        String sql = "select gb.id,gb.supplier,gb.multiproduct,gb.status,gb.name," +
                "gb.ename,gb.declareen,gb.declarezh,gb.shop,gb.tagids,gb.cost,gb.weight,gb.kind,gb.description" +
                ",gb.remarks,gb.idpics,gb.idpic,gb.createtime,gb.updatetime,FROM_UNIXTIME( gb.createtime/1000, '%Y-%m-%d %H:%i:%S') createtime2 " +
                "from goodsinfo gb  where gb.id=  ";
        //        新建MyRowMapper类实现RowMapper接口，重写mapRow方法，
        String roleName = (String) parms.get("ticket");

//    long[] param ={Long.valueOf((String) parms.get("id"))};
        try {
//        Goods = jdbcTemplate.queryForObject(sql,new GoodsMapper(),param);

        if (!Validation.isNULL(roleName) && !"产品编辑".equals(Util.decryptBASE64(roleName))) {
            sql="select   gb.id,gb.status,gb.multiproduct,gb.name,gb.ename,gb.declareen,gb.declarezh," +
                    "gb.shop,gb.tagids,gb.supplier,gb.cost,gb.remarks,gb.weight,gb.kind,gb.description," +
                    "gb.idpic,gb.idpics,gb.createtime,gb.updatetime,FROM_UNIXTIME( gb.createtime/1000, '%Y-%m-%d %H:%i:%S') createtime2 from goodsinfo gb where gb.id= ";
        }
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString() + parms.get("id"));
            if (list.size() == 0) return new Goods();
            Map<String, Object> map = list.get(0);
            int id = (int) map.get("id");
            int status = (int) map.get("status");
            String name = (String) map.get("name");
            String ename = (String) map.get("ename");
            String tagids = (String) map.get("tagids");
            String cost = (String) map.get("cost");
            String weight = (String) map.get("weight");
            String kind = (String) map.get("kind");
            String supplier = (String) map.get("supplier");
            String desc = (String) map.get("description");
            String remarks = (String) map.get("remarks");
            String idpic = (String) map.get("idpic");
            String idpics = (String) map.get("idpics");
            String createtime = (String) map.get("createtime");
            String upatetime = (String) map.get("updatetime");

            String shop = (String) map.get("shop");
            String declareen = (String) map.get("declareen");
            String declarezh = (String) map.get("declarezh");
            String multiproduct = (String) map.get("multiproduct");
//        把数据封装成对象
            Goods goods = new Goods();
            goods.setName(name);
            goods.setId(id);
            goods.setEname(ename);
            goods.setTagids(tagids);
            goods.setCost(cost);
            goods.setWeight(weight);
            goods.setKind(kind);
            goods.setSupplier(supplier);
            goods.setDescription(desc);
            goods.setIdpics(idpics);
            goods.setIdpic(idpic);
            goods.setRemarks(remarks);
            goods.setUpatetime(upatetime);
            goods.setCreatetime(createtime);
            goods.setShop(shop);
            goods.setDeclareen(declareen);
            goods.setDeclarezh(declarezh);
            goods.setStatus(status);
            goods.setMultiproduct(multiproduct);
            return goods;

        } catch (Exception e) {
            logger.error("e", e);
        }
        return new Goods();
    }

    public boolean addGoods(Map<String, Object> parms) {

        String roleName = (String) parms.get("ticket");
        if (!Validation.isNULL(roleName) && "产品编辑".equals(Util.decryptBASE64(roleName))) {
            return addGoodsByEditor(parms);
        }
        if (!Validation.isNULL(parms.get("id"))) {
            return editGoods(parms);
        }
        String sql = "insert into goodsinfo ( name, ename,tagids,declareen, declarezh,shop, cost, weight, kind, supplier,remarks,idpics,createtime,description,status,idpic,multiproduct)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        String name = (String) parms.get("name");
        String ename = (String) parms.get("ename");
        String tagids = (String) parms.get("tagids");
        String declareen = (String) parms.get("declareen");
        String declarezh = (String) parms.get("declarezh");
        String shop = (String) parms.get("shop");
        String cost = (String) parms.get("cost");
        String weight = (String) parms.get("weight");
        String kind = (String) parms.get("kind");
        String supplier = (String) parms.get("supplier");
        String remarks = (String) parms.get("remarks");
        String idpics = (String) parms.get("idpics");
        String idpic = (String) parms.get("idpic");
        String description = (String) parms.get("description");
        String statusStr = (String) parms.get("status");
        String multiproduct = (String) parms.get("multiproduct");
        int status = 1;
        if (!Validation.isNULL(statusStr)) {
            status = Integer.valueOf(statusStr);
        }
        Long timestamp = System.currentTimeMillis();
        Object[] param = {name, ename, tagids, declareen, declarezh, shop, cost, weight, kind, supplier, remarks, idpics, timestamp.toString(), description, status, idpic,multiproduct};

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


    public boolean addGoodsByEditor(Map<String, Object> parms) {

        if (!Validation.isNULL(parms.get("id"))) {
            return editGoods(parms);
        }
        String sql = "insert into goodsinfo ( name, ename,declareen, declarezh,shop,tagids, cost, weight, kind,remarks,idpics,createtime,status,idpic,multiproduct)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        String name = (String) parms.get("name");
        String ename = (String) parms.get("ename");
        String tagids = (String) parms.get("tagids");
        String declareen = (String) parms.get("declareen");
        String declarezh = (String) parms.get("declarezh");
        String shop = (String) parms.get("shop");
        String cost = (String) parms.get("cost");
        String weight = (String) parms.get("weight");
        String kind = (String) parms.get("kind");
        String remarks = (String) parms.get("remarks");
        String idpics = (String) parms.get("idpics");
        String idpic = (String) parms.get("idpic");
        String multiproduct = (String) parms.get("multiproduct");
        String statusStr = (String) parms.get("status");
        int status = 1;
        if (!Validation.isNULL(statusStr)) {
            status = Integer.valueOf(statusStr);
        }
        Long timestamp = System.currentTimeMillis();
        Object[] param = {name, ename, tagids, declareen, declarezh, shop, cost, weight, kind, remarks, idpics, timestamp.toString(), status, idpic,multiproduct};

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

    public boolean delGoods(String goodsIds, String picIds) {
        String sql = "delete from  goodsinfo where id  in(" + goodsIds + ")";
        String[] picIdsArr = picIds.split(",");
        boolean flag = true;
        try {
            jdbcTemplate.execute(sql);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < picIdsArr.length; i++) {
                if (picIdsArr[i] != "") {
                    sb.append("'").append(picIdsArr[i]).append("',");
                }
            }
            picIds = sb.toString();
            if(picIds.length()==0){
                return true;
            }
            picIds = picIds.substring(1, picIds.length() - 2);
            imgAction.delImgByIds(picIds);
        } catch (Exception e) {
            logger.error("e", e);
            flag = false;
        }
        return flag;
    }

    public boolean editGoods(Map<String, Object> parms) {
        String roleName = (String) parms.get("ticket");
        if (!Validation.isNULL(roleName) && !"产品编辑".equals(Util.decryptBASE64(roleName))) {
            Long timestamp = System.currentTimeMillis();
            String sql = "update goodsinfo set name = ?,ename = ?,tagids = ?, declareen= ?,declarezh = ?,shop = ?,cost = ?,weight = ?,kind = ?,supplier = ?,description=? ,idpics=?,updatetime=?,remarks=? ,status=? ,idpic=? ,multiproduct=? where id = ?";
            int id = (int) parms.get("id");
            String name = (String) parms.get("name");
            String ename = (String) parms.get("ename");
            String tagids = (String) parms.get("tagids");
            String declareen = (String) parms.get("declareen");
            String declarezh = (String) parms.get("declarezh");
            String shop = (String) parms.get("shop");
            String cost = (String) parms.get("cost");
            String weight = (String) parms.get("weight");
            String kind = (String) parms.get("kind");
            String supplier = (String) parms.get("supplier");
            String description = (String) parms.get("description");
            String remarks = (String) parms.get("remarks");
            String idpics = (String) parms.get("idpics");
            String idpic = (String) parms.get("idpic");
            String multiproduct = (String) parms.get("multiproduct");

            String statusStr = (String) parms.get("status");
            int status = 1;
            if (!Validation.isNULL(statusStr)) {
                status = Integer.valueOf(statusStr);
            }

            Object[] param = {name, ename, tagids, declareen, declarezh, shop, cost, weight, kind, supplier, description, idpics, timestamp.toString(), remarks, status, idpic, multiproduct,id};
            return editGoodsAction(sql, param);

        } else {

            Long timestamp = System.currentTimeMillis();
            String sql = "update goodsinfo set name = ?,ename = ?,tagids = ?, declareen= ?,declarezh = ?,shop = ?,cost = ?,weight = ?,kind = ?,remarks=? ,idpics=?,updatetime=? ,status=?,idpic=? ,multiproduct=? where id = ?";
            int id = (int) parms.get("id");
            String name = (String) parms.get("name");
            String ename = (String) parms.get("ename");
            String tagids = (String) parms.get("tagids");
            String cost = (String) parms.get("cost");
            String weight = (String) parms.get("weight");
            String kind = (String) parms.get("kind");
            String remarks = (String) parms.get("remarks");
            String idpics = (String) parms.get("idpics");
            String idpic = (String) parms.get("idpic");
            String declareen = (String) parms.get("declareen");
            String declarezh = (String) parms.get("declarezh");
            String shop = (String) parms.get("shop");
            String multiproduct = (String) parms.get("multiproduct");

            String statusStr = (String) parms.get("status");
            int status = 1;
            if (!Validation.isNULL(statusStr)) {
                status = Integer.valueOf(statusStr);
            }
            Object[] param = {name, ename, tagids, declareen, declarezh, shop, cost, weight, kind, remarks, idpics, timestamp.toString(), status, idpic,multiproduct, id};
            return editGoodsAction(sql, param);

        }
    }

    public boolean editGoodsAction(String sql, Object[] param) {

        // 新建MyRowMapper类实现RowMapper接口，重写mapRow方法，
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

    public Object queryGoodsStatus() {
        return jdbcTemplate.queryForList("select  count(status) as num , status   from goodsinfo group by status");
    }
}
