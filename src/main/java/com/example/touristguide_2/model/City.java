package com.example.touristguide_2.model;

public class City {
    
    private int id;
    private String cityName;
    private String postalCode;
    
    public City(int id, String name, String postalCode){
        this.id = id;
        this.cityName = cityName;
        this.postalCode = postalCode;

    }
    public City(){
        //Tom construkør
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getCityName(){
        return cityName;
    }

    public void setCityName(String cityName){
        this.cityName = cityName;
    }

    public String getPostalCode(){
        return postalCode;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
}
