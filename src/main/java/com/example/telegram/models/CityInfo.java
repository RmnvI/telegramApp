package com.example.telegram.models;

import javax.persistence.*;

@Entity
public class CityInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cityName;

    @Column(columnDefinition = "nvarchar(max)")
    private String cityDescription;

    public CityInfo() {
    }

    public CityInfo(String name, String description){
        cityName = name;
        cityDescription = description;
    }
    public long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityDescription() {
        return cityDescription;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityDescription(String cityDescription) {
        this.cityDescription = cityDescription;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", cityDescription='" + cityDescription + '\'' +
                '}';
    }
}
