package com.example.touristguide_2.repository;

import com.example.touristguide_2.model.*;
import com.example.touristguide_2.rowMapper.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TouristRepository {
    JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void makeTable () {

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS cities (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), postalCode VARCHAR(255))");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS tags(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS attractions (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(225), description VARCHAR(225), cityKey VARCHAR(255))");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS attractiontags (attractionKey INT AUTO_INCREMENT PRIMARY KEY, tagKey INT)");

    }

    public void insertAttractionData(){

        jdbcTemplate.update("INSERT IGNORE INTO cities (id, name, postalCode) VALUES (?,?,?)", 1, "Halla", 2860);

        jdbcTemplate.update("INSERT IGNORE INTO tags (id, name) VALUES (?,?)", 1, "family friendly");

        jdbcTemplate.update("INSERT IGNORE INTO attractions (id, name, description, cityKey) VALUES (?, ?, ?, ?)", 1, "Yac", "Missekat", 1);

        jdbcTemplate.update("INSERT IGNORE INTO attractiontags (attractionKey, tagKey) VALUES (?,?)", 1, 1);
        
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
        String sqlUpdate = "update attractions SET name = ?, description = ?, cityKey = ? WHERE id = ?";
        jdbcTemplate.update(sqlUpdate,
                touristAttraction.getName(),
                touristAttraction.getDescription(),
                touristAttraction.getCity(),
                touristAttraction.getId());

        jdbcTemplate.update("delete from attractiontags where attractionKey = ?", touristAttraction.getId());

        for (int tag : touristAttraction.getTags()) {
            jdbcTemplate.update("insert into attractiontags (attractionKey, tagKey) values (?,?)", touristAttraction.getId(), tag);
        }
    }

    public void deleteAttraction(int id) throws DataAccessException {
        String sqlDelete = "DELETE FROM attractions WHERE id= ?";
        jdbcTemplate.update(sqlDelete, id);
    }
}
