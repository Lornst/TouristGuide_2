package com.example.touristguide_2.rowMapper;


import com.example.touristguide_2.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TagRowMapper implements RowMapper<Tag>{

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tag tag = new Tag();
        tag.setId(rs.getInt("tags.id"));
        tag.setTagName(rs.getString("tags.name"));
        return tag;
    }


}
