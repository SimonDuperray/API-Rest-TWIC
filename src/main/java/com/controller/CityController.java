package com.controller;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;
import com.service.CityService;
import com.model.City;
import com.dto.CityDTO;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value="/city")
@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping()
    @ResponseBody
    public Object listCities(@RequestParam(required=false, value="codePostal") String codePostal,
                             @RequestParam(required=false, value="codeCommune") String codeCommune,
                             @RequestParam(required=false, value="size", defaultValue="-1") int size,
                             @RequestParam(required=false, value="page", defaultValue="-1") int page,
                             HttpServletResponse response) {
        if(codeCommune!=null && codePostal!=null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        } else if(codeCommune!=null) {
            Optional<City> ville = cityService.getCity(codeCommune);
            if(ville.isPresent()) {
                return ville.get();
            } else {
                response.setStatus(HttpStatus.NOT_FOUND.value());
                return null;
            }
        } else {
            return cityService.getCities(codePostal, page, size);
        }
    }

    @PostMapping()
    public void addCity(@RequestBody CityDTO cityDTO, HttpServletResponse response) {
        try {
            cityService.createCity(cityDTO);
        } catch(SQLException e) {
            response.setStatus(HttpStatus.CONFLICT.value());
        }
    }

    @PutMapping()
    public void replaceCity(@RequestBody CityDTO cityDTO, HttpServletResponse response) {
        try {
            cityService.modifyCity(cityDTO);
        } catch(ObjectNotFoundException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    @DeleteMapping()
    public void deleteCity(@RequestParam(required=true, value="codeCommune") String codeCommune, HttpServletResponse response) {
        try {
            cityService.deleteCity(codeCommune);
        } catch(ObjectNotFoundException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

}