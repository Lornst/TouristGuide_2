package com.example.touristguide_2.model;

public class City {
    private int ID;
    private String name;

    public City(int ID, String name){
        this.ID = ID;
        this.name = name;
    }

    public City(){}

    public int getID(){
        return ID;
    }

    public void setID(int newID){
        ID = newID;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }
}
