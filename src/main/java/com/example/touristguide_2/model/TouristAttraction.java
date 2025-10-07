package com.example.touristguide_2.model;

import java.util.List;
import java.util.Map;

public class TouristAttraction {

    private String name;
    private String description;
    private List<Integer> tags;
    private int city;
    private int id;

    public TouristAttraction(String name, String description, int city, List<Integer> tags, int id) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
        this.id = id;


    }

    public TouristAttraction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String setDescription(String newDescription) {
        description = newDescription;
        return description;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return name + "," + description + "," + tags + "," + city;
    }
}
