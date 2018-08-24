package com.aliexpress.mapper;

import com.aliexpress.beans.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RowMapper接口，返回User对象
 * */
public class TagMapper implements RowMapper<Tag>{

    @Override
public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
//        获取结果集中的数据
    String id = resultSet.getString("id");
    String name = resultSet.getString("name");

    String createtime = resultSet.getString("createtime");
    String updatetime = resultSet.getString("updatetime");
//        把数据封装成User对象
    Tag tag = new Tag();
    tag.setName(name);
    tag.setId(id);
    tag.setCreatetime(createtime);
    tag.setUpdatetime(updatetime);
    return tag;
}
}