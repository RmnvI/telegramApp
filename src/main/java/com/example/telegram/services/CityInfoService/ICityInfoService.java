package com.example.telegram.services.CityInfoService;

import com.example.telegram.models.CityInfo;

import java.util.List;

public interface ICityInfoService {
    CityInfo getById(long id);
    CityInfo getByName(String cityName);
    List<String> getAllCityNames();
    List<CityInfo> getAll();
    void create(CityInfo cityInfo);
    void update(CityInfo cityInfo);
    void delete(long id);
}
