package com.example.touristguide_2.rowMapper;

import com.example.touristguide_2.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttractionRowMapper implements RowMapper<TouristAttraction> {
    JdbcTemplate jdbcTemplate;

    public AttractionRowMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TouristAttraction mapRow(ResultSet rs, int rowNum) throws SQLException {
        TouristAttraction touristAttraction = new TouristAttraction();

        touristAttraction.setName(rs.getString("attractions.name"));
        touristAttraction.setDescription(rs.getString("attractions.description"));
        touristAttraction.setId(rs.getInt("attractions.id"));
        touristAttraction.setCity(rs.getInt("attractions.cityKey"));

        List<Integer> tagList = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from attractiontags where attractionKey = ?", rs.getInt("id"));

        while (rowSet.next()) {
            int id = rowSet.getInt("tagKey");
            tagList.add(id);
        }

        touristAttraction.setTags(tagList);

        return touristAttraction;
    }
}


