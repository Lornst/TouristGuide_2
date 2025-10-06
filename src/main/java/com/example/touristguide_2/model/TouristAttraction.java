package com.example.touristguide_2.model;

import java.util.List;

public class TouristAttraction {

    private String name;
    private String description;
    private List<Tag> tags;
    private City city;
    private double id;

    public TouristAttraction(String name, String description, City city, List<Tag> tags, double id) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
        this.id = id;


    }

    public TouristAttraction() {
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
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
