package com.aliexpress.mapper;

import com.aliexpress.beans.GoodsDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RowMapper接口，返回GoodsDetails对象
 */
public class GoodsDetailsMapper implements RowMapper<GoodsDetails> {

    @Override
public GoodsDetails mapRow(ResultSet resultSet, int i) throws SQLException {
//        获取结果集中的数据
    /*
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                `description` varchar(1024) DEFAULT NULL COMMENT 'description',
                `idpic1` varchar(32) DEFAULT NULL COMMENT 'idpic1',
                `idpic2` varchar(32) DEFAULT NULL COMMENT 'idpic2',
                `idpic3` varchar(32) DEFAULT NULL COMMENT 'idpic3',
                `idpic4` varchar(32) DEFAULT NULL COMMENT 'idpic4',
                `idpic5` varchar(32) DEFAULT NULL COMMENT 'idpic5',
                `idpic6` varchar(32) DEFAULT NULL COMMENT 'idpic6',
                `createtime` varchar(32) default null COMMENT 'CreateTimeStamp',
                `updatetime` varchar(32) default null COMMENT 'CreateTimeStamp',
    */

    String id = resultSet.getString("id");
    String idpic1 = resultSet.getString("idpic1");
    String idpic2 = resultSet.getString("idpic2");
    String idpic3 = resultSet.getString("idpic3");
    String idpic4 = resultSet.getString("idpic4");
    String idpic5 = resultSet.getString("idpic5");
    String idpic6 = resultSet.getString("idpic6");
    String description = resultSet.getString("description");
    String createtime = resultSet.getString("createtime");
    String upatetime = resultSet.getString("updatetime");
//        把数据封装成对象
    GoodsDetails goodsDetails = new GoodsDetails();
    goodsDetails.setId(id);
    goodsDetails.setDescription(description);
    goodsDetails.setIdpic1(idpic1);
    goodsDetails.setIdpic2(idpic2);
    goodsDetails.setIdpic3(idpic3);
    goodsDetails.setIdpic4(idpic4);
    goodsDetails.setIdpic5(idpic5);
    goodsDetails.setIdpic6(idpic6);
    goodsDetails.setUpatetime(upatetime);
    goodsDetails.setCreatetime(createtime);
    return goodsDetails;
}
}