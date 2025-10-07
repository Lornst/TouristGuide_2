package com.example.touristguide_2.repository;

import com.example.touristguide_2.model.*;
import com.example.touristguide_2.rowMapper.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TouristRepository {
    JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<Integer, String> getTagList() {
        Map<Integer, String> tagList = jdbcTemplate.query("select * from tags", new tagResultSetExtractor());
        return tagList;
    }

    public Map<Integer, String> getCityList() {
        Map<Integer, String> cityList = jdbcTemplate.query("select * from cities", new cityResultSetExtractor());
        return cityList;
    }

    public List<TouristAttraction> getAttractionList() {
        return jdbcTemplate.query("select * from attractions", new AttractionRowMapper(jdbcTemplate));
    }

    public TouristAttraction getAttraction(int id) {
        return jdbcTemplate.queryForObject("select * from attractions where id = ?", new AttractionRowMapper(jdbcTemplate), id);
    }

    public void addAttraction(TouristAttraction attraction) {
        jdbcTemplate.update("insert into attractions (name, description, cityKey) values (?,?,?)",
                attraction.getName(), attraction.getDescription(), attraction.getCity());

        int id = jdbcTemplate.queryForObject("select id from attractions where name = ? limit 1", Integer.class, attraction.getName());

        for (int tag : attraction.getTags()) {
            jdbcTemplate.update("insert into attractiontags (attractionKey, tagKey) values (?,?)", id, tag);
        }
    }

    public void editAttraction(TouristAttraction touristAttraction) throws DataAccessException {
        String sqlUpdate = "update attractions SET name= ?, description = ?, cityKey = ? WHERE id = ?";
        jdbcTemplate.update(sqlUpdate,
                touristAttraction.getName(),
                touristAttraction.getDescription(),
                touristAttraction.getCity(),
                touristAttraction.getId());
    }

    public void deleteAttraction(int id) throws DataAccessException {
        String sqlDelete = "DELETE FROM attractions WHERE id= ?";
        jdbcTemplate.update(sqlDelete, id);
    }
}
