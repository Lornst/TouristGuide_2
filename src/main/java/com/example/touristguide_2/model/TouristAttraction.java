package com.example.touristguide_2.model;

import java.util.List;

public class TouristAttraction {

    private int ID;
    private String name;
    private String description;
    private List<String> tags;
    private String city;

    public TouristAttraction(int ID, String name, String description, String city, List<String> tags) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;

    }

    public int getID(){
        return ID;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public TouristAttraction() {
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

    public String getCity() {
        return city;
    }

    public String toString(){
        return name+","+description+","+tags+","+city;
    }
}
