package com.example.touristguide_2.repository;

import com.example.touristguide_2.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    // Opret DataSource manuelt
    static DataSource dataSource_attractions = new DriverManagerDataSource(
            "jdbc:mysql://localhost:3306/attractions",
            "root",
            "Kode");

    // Opret DataSource manuelt
    static DataSource dataSource_tags = new DriverManagerDataSource(
            "jdbc:mysql://localhost:3306/tags",
            "root",
            "Kode");

    // Opret DataSource manuelt
    static DataSource dataSource_cities = new DriverManagerDataSource(
            "jdbc:mysql://localhost:3306/cities",
            "root",
            "Kode");

    // Opret JdbcTemplate med DataSource
    static JdbcTemplate jdbcTemplate_attractions = new JdbcTemplate(dataSource_attractions);

    // Opret JdbcTemplate med DataSource
    static JdbcTemplate jdbcTemplate_tags = new JdbcTemplate(dataSource_tags);

    // Opret JdbcTemplate med DataSource
    static JdbcTemplate jdbcTemplate_cities = new JdbcTemplate(dataSource_cities);

    static public void InsertAttraction(){
        // Indsæt testdata (undgå duplikerede data ved at bruge INSERT IGNORE)
        jdbcTemplate_attractions.update("INSERT IGNORE INTO attractions (id, name, description, tags_matching_ID) VALUES (?, ?)",
                1, "Jens");
    }

    ArrayList<TouristAttraction> attractionList = new ArrayList<>();
    List<String> tagList = new ArrayList<>();
    List<String> cityList = new ArrayList<>();

    public TouristRepository() {
        attractionList.add(new TouristAttraction("Tivoli", "Dansk forlystelsespark", "København", List.of("Koncert", "Runes tag")));
        attractionList.add(new TouristAttraction("Rundetårn", "Tårn placeret i centrum af København", "København ", List.of("Koncert", "Yac tag")));
        attractionList.add(new TouristAttraction("Parken", "Hjem til Danmarks bedste fodboldklub", "København", List.of("Hardcodet tag", "Koncert")));
        attractionList.add(new TouristAttraction("Lille havfrue", "Historisk statue", "Hvidovre", List.of("Tag test 4", "Hej med dig min ven")));


        tagList.add("Underholdning");
        tagList.add("Koncert");
        tagList.add("Børnevenligt");
        tagList.add("Natur");
        tagList.add("Museum");

        cityList.add("København");
        cityList.add("Stenløse");
        cityList.add("Odense");
        cityList.add("AAlborg");
        cityList.add("Randers");
        cityList.add("Hvidovre");
    }

    public ArrayList<TouristAttraction> getAll() {


        return attractionList;
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
