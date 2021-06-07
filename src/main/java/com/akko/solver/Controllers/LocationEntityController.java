package com.akko.solver.Controllers;

import com.akko.solver.domain.Location;
import com.akko.solver.exceptions.EmptyLocationDatabase;
import com.akko.solver.exceptions.LocationNotFound;
import com.akko.solver.models.LocationEntity;
import com.akko.solver.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/location")
@ResponseBody
public class LocationEntityController {
    @Autowired
    LocationRepository locationRepository;

    @GetMapping("")
    public List<LocationEntity> list(){
        if(locationRepository.findAll().isEmpty()){
            throw new EmptyLocationDatabase();
        }else{
            return locationRepository.findAll();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationEntity> get(@PathVariable Long id){
        try{
            LocationEntity location = locationRepository.getById(id);
            return new ResponseEntity<LocationEntity>(location, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<LocationEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody LocationEntity location){
        locationRepository.save(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody LocationEntity location, @PathVariable Long id){
        try{
            LocationEntity exist = locationRepository.getById(id);
            exist.setLatitude(location.getLatitude());
            exist.setLongitude(location.getLongitude());
            locationRepository.save(exist);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        boolean success = true;
        try{
            locationRepository.findById(id).orElseThrow(()-> new LocationNotFound(id));
        }catch(LocationNotFound e){
            success = false;
        }
        if(success){
            locationRepository.deleteById(id);
        }
    }
}
