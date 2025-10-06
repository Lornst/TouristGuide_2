package com.example.touristguide_2.rowMapper;

import com.example.touristguide_2.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        touristAttraction.setId(rs.getDouble("attractions.id"));

        List<Tag> tagList = jdbcTemplate.query("select * from attractiontags where attractionKey = ?", new TagRowMapper(), rs.getInt("id"));
        touristAttraction.setTags(tagList);

        return touristAttraction;
    }
}


