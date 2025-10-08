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

        makeTables();
        insertTableData();
    }

    public void makeTables() {
        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS cities (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL UNIQUE
                );
                """);

        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS tags (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL UNIQUE
                );
                """);

        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS attractions (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    description VARCHAR(255) NOT NULL,
                    cityKey INT NOT NULL
                );
                """);

        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS attractiontags (
                    attractionKey INT NOT NULL,
                    tagKey INT NOT NULL,
                    PRIMARY KEY (attractionKey, tagKey)
                );
                """);
    }

    public void insertTableData(){
        jdbcTemplate.update("INSERT IGNORE INTO cities (name) VALUES (?)", "København");
        jdbcTemplate.update("INSERT IGNORE INTO cities (name) VALUES (?)", "Aarhus");
        jdbcTemplate.update("INSERT IGNORE INTO cities (name) VALUES (?)", "Odense");
        jdbcTemplate.update("INSERT IGNORE INTO cities (name) VALUES (?)", "Aalborg");
        jdbcTemplate.update("INSERT IGNORE INTO cities (name) VALUES (?)", "Esbjerg");
        jdbcTemplate.update("INSERT IGNORE INTO cities (name) VALUES (?)", "Randers");
        jdbcTemplate.update("INSERT IGNORE INTO cities (name) VALUES (?)", "Horsens");

        jdbcTemplate.update("INSERT IGNORE INTO tags (name) VALUES (?)", "Sjov");
        jdbcTemplate.update("INSERT IGNORE INTO tags (name) VALUES (?)", "Uhyggelig");
        jdbcTemplate.update("INSERT IGNORE INTO tags (name) VALUES (?)", "Familievenlig");
        jdbcTemplate.update("INSERT IGNORE INTO tags (name) VALUES (?)", "Historisk");
        jdbcTemplate.update("INSERT IGNORE INTO tags (name) VALUES (?)", "Romantisk");
        jdbcTemplate.update("INSERT IGNORE INTO tags (name) VALUES (?)", "Natur");
        jdbcTemplate.update("INSERT IGNORE INTO tags (name) VALUES (?)", "Kunst");
        jdbcTemplate.update("INSERT IGNORE INTO tags (name) VALUES (?)", "Eventyr");
        jdbcTemplate.update("INSERT IGNORE INTO tags (name) VALUES (?)", "Mad og drikke");
        jdbcTemplate.update("INSERT IGNORE INTO tags (name) VALUES (?)", "Action");
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
