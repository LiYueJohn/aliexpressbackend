package com.aliexpress.mapper;

import com.aliexpress.beans.Goods;
import com.aliexpress.beans.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RowMapper接口，返回User对象
 */
public class GoodsMapper implements RowMapper<Goods> {

    @Override
    public Goods mapRow(ResultSet resultSet, int i) throws SQLException {

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String ename = resultSet.getString("ename");
        String tagids = resultSet.getString("tagids");
        String cost = resultSet.getString("cost");
        String weight = resultSet.getString("weight");
        String kind = resultSet.getString("kind");
        String supplier = resultSet.getString("supplier");
        String desc = resultSet.getString("description");
        String remarks = resultSet.getString("remarks");
        String idpic = resultSet.getString("idpics");
        String createtime = resultSet.getString("createtime");
        String upatetime = resultSet.getString("updatetime");

        String shop = resultSet.getString("shop");
        String declareen = resultSet.getString("declareen");
        String declarezh = resultSet.getString("declarezh");
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
        goods.setIdpics(idpic);
        goods.setRemarks(remarks);
        goods.setUpatetime(upatetime);
        goods.setCreatetime(createtime);
        goods.setShop(shop);
        goods.setDeclareen(declareen);
        goods.setDeclarezh(declarezh);
        return goods;
    }
}