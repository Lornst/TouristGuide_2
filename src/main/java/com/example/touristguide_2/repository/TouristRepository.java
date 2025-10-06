package com.example.touristguide_2.repository;

import com.example.touristguide_2.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getCityList(){
        return jdbcTemplate.queryForList("select * from cities", String.class);
    }

    public List<String> getTagList() {
        return jdbcTemplate.queryForList("select * from tags", String.class);
    }

    public List<TouristAttraction> getAttractionList() {
        return jdbcTemplate.query("select * from attractions", new attractionRowMapper(jdbcTemplate));
    }

    public TouristAttraction getAttraction(TouristAttraction attraction) {
        return jdbcTemplate.queryForObject("select * from attractions where name = ?", new attractionRowMapper(jdbcTemplate), attraction.getName());
    }

    public void addAttraction(TouristAttraction attraction) {
        jdbcTemplate.update("insert into attractions (name, description, city)", attraction.getName(), attraction.getDescription(), attraction.getCity());

        for(String tag : attraction.getTags()){
            jdbcTemplate.update("insert into attractiontags (attractionKey, tagKey) values(?,?)", attraction.getName(), tag);
        }
    }

    public void editAttraction(TouristAttraction attraction) {
    }

    public void deleteAttraction(TouristAttraction attraction) {
    }
}
