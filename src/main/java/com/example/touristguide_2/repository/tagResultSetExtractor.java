package com.example.touristguide_2.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class tagResultSetExtractor implements ResultSetExtractor<Map<Integer, String>> {
    @Override
    public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
        HashMap<Integer,String> tagMap= new HashMap<>();
        while(rs.next()){
            tagMap.put(rs.getInt("id"),rs.getString("name"));
        }
        return tagMap;
    }
}
