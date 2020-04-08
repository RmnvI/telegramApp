package com.example.telegram.services.CityInfoService;

import com.example.telegram.models.CityInfo;
import com.example.telegram.repositories.CityInfoRepository;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityInfoService implements ICityInfoService {
    private CityInfoRepository repository;

    public CityInfoService(CityInfoRepository repository) {
        this.repository = repository;
    }

    /**
     * Method that return city info by id
     * @param id - id of city
     * @return - city info
     */
    @Override
    public CityInfo getById(long id) {
        return repository.findById(id).get();
    }

    /**
     * Method that return city info by name
     * @param cityName - city name
     * @return - city info
     */
    @Override
    public CityInfo getByName(String cityName) {
        return repository.findByCityName(cityName);
    }

    /**
     * Method that return all cities name
     * @return - list of cities  name
     */
    @Override
    public List<String> getAllCityNames() {
        return repository.findAllCityName();
    }

    /**
     * Method that return all cities info
     * @return - list of cities info
     */
    @Override
    public List<CityInfo> getAll() {
        return repository.findAll();
    }

    /**
     * Method need to create city info
     * @param cityInfo - city info
     */
    @Override
    public void create(CityInfo cityInfo) {
        cityInfo.setCityName(cityInfo.getCityName().toLowerCase());
        repository.save(cityInfo);
    }

    /**
     * Method need to update city info
     * @param cityInfo - city info
     */
    @Override
    public void update(CityInfo cityInfo) {
        cityInfo.setCityName(cityInfo.getCityName().toLowerCase());
        repository.save(cityInfo);
    }

    /**
     * Method need to delete city info by id
     * @param id - id of city info
     */
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
