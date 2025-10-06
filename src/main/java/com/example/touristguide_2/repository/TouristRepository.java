package com.example.touristguide_2.repository;

import com.example.touristguide_2.model.*;
import com.example.touristguide_2.rowMapper.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tag> getTagList(){
        return jdbcTemplate.query("select * from tags", new TagRowMapper());
    }

    public List<City> getCityList(){
        return jdbcTemplate.query("select * from cities", new CityRowMapper());
    }

    public List<TouristAttraction> getAttractionList(){
        return jdbcTemplate.query("select * from attractions", new AttractionRowMapper(jdbcTemplate));
    }

    public TouristAttraction getAttraction(int id){
        TouristAttraction attraction = new TouristAttraction();

        jdbcTemplate.queryForObject("select * from attractions where id = ?", new AttractionRowMapper(jdbcTemplate), id);

        return attraction;
    }

    public void addAttraction(TouristAttraction attraction){
        jdbcTemplate.update("insert into attractions (name, description, cityKey) values (?,?,?)", attraction.getName(), attraction.getDescription(), attraction.getCity().getPostalCode());

        for(Tag tag : attraction.getTags()){
            jdbcTemplate.update("insert into attractiontags (attractionKey, tagKey) values (?,?)", attraction.getId(), tag.getId());
        }
    }

    public void  updateAttraction(TouristAttraction touristAttraction) throws DataAccessException {
        String sql = "update attractions SET name= ?, age = ?, description = ?, WHERE id = ?, city = ?";
        jdbcTemplate.update(sql, touristAttraction.getName(), touristAttraction.getDescription(), touristAttraction.getId(), touristAttraction.getCity());

    }

    public void editAttraction(int id, TouristAttraction attraction){

    }

    public void deleteAttraction(int id){

    }
}
