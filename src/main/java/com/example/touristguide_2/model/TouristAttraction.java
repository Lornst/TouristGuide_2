package com.example.touristguide_2.model;

import java.util.List;

public class TouristAttraction {

    // marco forker det!!!!

    //YADIII WAS HEREEEE

    //BRANCH K O M E N T A R HEJ

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

    public void setCity(String city) {
        this.city = city;
    }

    public TouristAttraction(){
}


    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
    return name;
}


    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

//public String setDescription(String newDescription){
//    description = newDescription;
//
//    return description;
//}


public String getDescription(){

    return description;
}

    public String getCity() {
        return city;
    }
}
