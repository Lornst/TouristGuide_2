package com.example.touristguide_2.model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {

private String name;
private String description;
private List<String> tags;
private String city;

public TouristAttraction(String name, String description, String city, List<String> tags){
    this.name = name;
    this.description = description;
    this.city = city;
    this.tags = tags;

}

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    public String getRegisterBy() {
        return city;
    }

    public void setRegisterBy(String registerBy) {
        this.city = city;
    }


    public TouristAttraction(){
}

public String setName(String newName){
    name = newName;

    return name;
}

public String getName(){
    return name;
}

public String setDescription(String newDescription){
    description = newDescription;

    return description;
}


public String getDescription(){

    return description;
}
public String getCity(){
    return city;
}


}
