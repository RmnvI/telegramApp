package com.example.telegram.repositories;

import com.example.telegram.models.CityInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CityInfoRepository extends CrudRepository<CityInfo,Long> {
    CityInfo findByCityName(String s);
    List<CityInfo> findAll();
    @Query("select c.cityName from CityInfo c")
    List<String> findAllCityName();

}
