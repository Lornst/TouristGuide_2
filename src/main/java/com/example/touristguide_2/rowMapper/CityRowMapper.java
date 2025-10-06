package com.example.touristguide_2.rowMapper;

import com.example.touristguide_2.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRowMapper implements RowMapper<City> {

    @Override
    public City mapRow(ResultSet halla, int rowNum) throws SQLException {
        City city = new City();
        city.setCityName(halla.getString("cities.name"));
        city.setId(halla.getInt("cities.id"));
        city.setPostalCode(halla.getString("cities.postalCode"));

        return city;
    }


}
