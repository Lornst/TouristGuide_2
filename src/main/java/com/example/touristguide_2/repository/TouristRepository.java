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
    List<String> tagList = new ArrayList<>();
    List<String> cityList = new ArrayList<>();

    public TouristRepository(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    public List<TouristAttraction> getAll() {
        List<TouristAttraction> attractions = new ArrayList<>();

        SqlRowSet attractionRows = jdbcTemplate.queryForRowSet("SELECT * FROM attractions");

        while (attractionRows.next()) {
            List<String> tags = new ArrayList<>();

            SqlRowSet attractionTagRows = jdbcTemplate.queryForRowSet("SELECT * FROM attractiontags");

            while(attractionTagRows.next()){
                String tagKey = attractionTagRows.getString("attractionKey");

                tags.add(tagKey);
            }

            String name = attractionRows.getString("name");
            String description = attractionRows.getString("description");
            String city = attractionRows.getString("city");
            attractions.add(new TouristAttraction(name, description, city, tags));
        }

        return attractions;
    }

    public List<String> getTags() {

        return tagList;
    }

    public List<String> getCityList() {

        return cityList;
    }

    public TouristAttraction getAttractionByName(String name) {
        for (TouristAttraction attraction : attractionList) {
            if (attraction.getName().equalsIgnoreCase(name))
                return attraction;
        }
        return null;
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {

        attractionList.add(attraction);
        return attraction;
    }


    public TouristAttraction editAttraction(String nameID, TouristAttraction attraction) {
        TouristAttraction tempAttraction = getAttractionByName(nameID);

        if (tempAttraction != null) {
            tempAttraction.setName(attraction.getName());
            tempAttraction.setDescription(attraction.getDescription());
            tempAttraction.setCity(attraction.getCity());
            tempAttraction.setTags(attraction.getTags());

            return tempAttraction;
        }
        return null;
    }

    public TouristAttraction deleteAttraction(String name) {
        if (!(name == null)) {
            TouristAttraction tempAttraction = getAttractionByName(name);
            attractionList.remove(tempAttraction);

            return tempAttraction;
        }

        return null;
    }
}
