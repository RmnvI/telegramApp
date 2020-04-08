package com.example.telegram.controller;

import com.example.telegram.models.CityInfo;
import com.example.telegram.repositories.CityInfoRepository;
import com.example.telegram.services.CityInfoService.CityInfoService;
import com.example.telegram.services.CityInfoService.ICityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CityInfoController {
    private final CityInfoService service;

    public CityInfoController(CityInfoService service) {
        this.service = service;
    }

    /**
     * Controller that return info by input cityName
     * @param cityName - name of city
     * @return full info about city
     */
    @GetMapping("/getByName/{cityName}")
    public CityInfo getCityInfoByName(@PathVariable String cityName){
        return service.getByName(cityName);
    }

    /**
     * Controller that return all cities info
     * @return - list of cities info
     */
    @GetMapping("/all")
    public List<CityInfo> getAll(){
        return service.getAll();
    }

    /**
     * Controller that need to update city info
     * @param cityInfo cityInfo
     * @return response message
     */
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody CityInfo cityInfo){
        if(cityInfo.getId() < 1){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        service.update(cityInfo);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Controller that need to create a new city info
     * @param cityInfo - cityinfo
     * @return - response message
     */
    @PostMapping("/new")
    public ResponseEntity create(@RequestBody CityInfo cityInfo){
        service.create(cityInfo);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Controller that return city info by id
     * @param id - id of city
     * @return - city info
     */
    @GetMapping("/getById/{id}")
    public  CityInfo getById(@PathVariable int id){
        return service.getById(id);
    }

    /**
     * Controller that need to delete city info
     * @param id - id of city
     * @return - response message
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if(id < 1){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
