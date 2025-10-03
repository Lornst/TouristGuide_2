package com.example.touristguide_2.repository;

import com.example.touristguide_2.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private JdbcTemplate jdbcTemplate;

    ArrayList<TouristAttraction> attractionList = new ArrayList<>();

    public TouristRepository(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    public ArrayList<TouristAttraction> getAll() {
        ArrayList<TouristAttraction> attractions = new ArrayList<>();

        SqlRowSet attractionRows = jdbcTemplate.queryForRowSet("SELECT * FROM attractions");

        while (attractionRows.next()) {
            ArrayList<String> tags = new ArrayList<>();

            SqlRowSet attractionTagRows = jdbcTemplate.queryForRowSet("SELECT * FROM attractiontags");

            while(attractionTagRows.next()){
                String tagKey = attractionTagRows.getString("tagKey");

                tags.add(tagKey);
            }

            int ID = attractionRows.getInt("id");
            String name = attractionRows.getString("name");
            String description = attractionRows.getString("description");
            String city = attractionRows.getString("city");
            attractions.add(new TouristAttraction(ID, name, description, city, tags));
        }

        return attractions;
    }

    public List<String> getTags() {
        ArrayList<String> tags = new ArrayList<>();

        SqlRowSet tagRows = jdbcTemplate.queryForRowSet("SELECT * from tags");

        while(tagRows.next()){
            String tagName = tagRows.getString("name");

            tags.add(tagName);
        }
        return tags;
    }

    public List<String> getCityList() {
        ArrayList<String> cities = new ArrayList<>();

        SqlRowSet cityRows = jdbcTemplate.queryForRowSet("SELECT * from cities");

        while(cityRows.next()){
            String tagName = cityRows.getString("name");

            cities.add(tagName);
        }
        return cities;
    }

    public TouristAttraction getAttractionByName(String name) {
        SqlRowSet attractionRows = jdbcTemplate.queryForRowSet("SELECT * FROM attractions");

        while(attractionRows.next()){
            String attractionName = attractionRows.getString("name");

            if(attractionName.equalsIgnoreCase(name)){
                ArrayList<String> tags = new ArrayList<>();

                SqlRowSet attractionTagRows = jdbcTemplate.queryForRowSet("SELECT * FROM attractiontags");

                while(attractionTagRows.next()){
                    String tagKey = attractionTagRows.getString("tagKey");

                    tags.add(tagKey);
                }

                int ID = attractionRows.getInt("id");
                String description = attractionRows.getString("description");
                String city = attractionRows.getString("city");

                return new TouristAttraction(ID, name, description, city, tags);
            }
        }
        return null;
    }

    public void addAttraction(TouristAttraction attraction) {
        jdbcTemplate.update("insert into attractions (name, description, city) values (?,?,?)",
                attraction.getName(), attraction.getDescription(), attraction.getCity());

        for(String tag : attraction.getTags()){
            jdbcTemplate.update("insert into attractionTags (attractionKey, tagKey) values (?,?)",
                    attraction.getName(), tag);
        }
    }


    public void editAttraction(TouristAttraction attraction) {
        jdbcTemplate.update("update attractions set name = ?, description = ?, city = ? where id = ?",
                attraction.getName(), attraction.getDescription(), attraction.getCity(), attraction.getID());

        jdbcTemplate.update("delete from attractiontags where attractionKey = ?", attraction.getID());

        for(String tag : attraction.getTags()){
            jdbcTemplate.update("insert into attractiontags (attractionKey, tagKey) values (?,?)",
                    attraction.getID(), tag);
        }
    }

    public void deleteAttraction(TouristAttraction attraction) {
        jdbcTemplate.update("delete from attractions where id = ?", attraction.getID());

        jdbcTemplate.update("delete from attractiontags where attractionKey = ?", attraction.getID());
    }
}
