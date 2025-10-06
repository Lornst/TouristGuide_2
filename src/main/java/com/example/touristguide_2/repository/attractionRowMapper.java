package com.example.touristguide_2.repository;

import com.example.touristguide_2.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class attractionRowMapper implements RowMapper<TouristAttraction> {
    JdbcTemplate jdbcTemplate;

    attractionRowMapper(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TouristAttraction mapRow(ResultSet rs, int rowNum) throws SQLException {
        TouristAttraction attraction = new TouristAttraction();

        attraction.setName(rs.getString("attractions.name"));
        attraction.setDescription(rs.getString("attractions.description"));

        List<String> attractionTags = jdbcTemplate.queryForList(
                "select tagKey from attractiontags where attractionKey = ?",
                        String.class, rs.getString("attractions.name"));

        attraction.setTags(attractionTags);

        return attraction;
    }
}
