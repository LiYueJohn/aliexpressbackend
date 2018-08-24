package com.aliexpress.mapper;

import com.aliexpress.beans.Img;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RowMapper接口，返回User对象
 */
public class ImgMapper implements RowMapper<Img> {

    @Override
public Img mapRow(ResultSet resultSet, int i) throws SQLException {
//        获取结果集中的数据
    String idpic = resultSet.getString("idpic");
    String name = resultSet.getString("name");
    String img = resultSet.getString("img");
//        把数据封装成User对象
    Img imgObj = new Img();
    imgObj.setIdpic(idpic);
    imgObj.setImg(img);
    imgObj.setName(name);
    return imgObj;
}
}