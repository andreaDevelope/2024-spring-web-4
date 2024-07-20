package org.java.spring_web4.web.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.java.spring_web4.db.pojo.Farm;
import org.java.spring_web4.db.pojo.Farmer;
import org.java.spring_web4.db.service.FarmService;
import org.java.spring_web4.db.service.FarmerService;
import org.java.spring_web4.web.dto.FarmerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/farmers")
@CrossOrigin(origins = "http://localhost:5173")
public class MainFarmerController {
    @Autowired
    private FarmerService farmerService;

    @Autowired
    private FarmService farmService;

    @GetMapping("/test/add")
    public ResponseEntity<Void> testAdd(){
        // CREO FARMS E LE SALVO
        Farm farm1 = new Farm("Farm di Roma", "Roma");
        Farm farm2 = new Farm("Farm di Napoli", "Napoli");
        Farm farm3 = new Farm("Farm di Torino", "Torino");


        farmService.save(farm1);
        farmService.save(farm2);
        farmService.save(farm3);


        // CREO FARMERS E LI SALVO
        Farmer farmer1 = new Farmer("Gigi", "La trottola", 20, farm1);
        Farmer farmer2 = new Farmer("Gig", "Robot", 30, farm1);
        Farmer farmer3 = new Farmer("Ken", "Shiro", 26, farm1);
        Farmer farmer4 = new Farmer("Alvaruccio", "E camilla", 35, farm1);

        farmerService.save(farmer1);
        farmerService.save(farmer2);
        farmerService.save(farmer3);
        farmerService.save(farmer4);

        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Farmer>> getAllFarmerWhitFarm(){
        List<Farmer> farmers = farmerService.getAll();
        return ResponseEntity.ok(farmers);
    }

    @PostMapping("")
    public ResponseEntity<Farmer> addFarmer(@RequestBody FarmerDto fd){
        Farmer farmer = new Farmer(fd);

        Optional<Farm> optFarm = farmService.getById(fd.getFarmId());

        if (optFarm.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Farm farm = optFarm.get();
        farmer.setFarm(farm);

        farmerService.save(farmer);

        return ResponseEntity.ok(farmer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable int id){
        Optional<Farmer> optFarmer = farmerService.getById(id);

        if (optFarmer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Farmer farmer = optFarmer.get();
        farmerService.delete(farmer);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable int id, @RequestBody FarmerDto fd){
        Optional<Farmer> optFarmer = farmerService.getById(id);

        if (optFarmer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Farmer farmer = optFarmer.get();
        farmer.update(fd);

        Optional<Farm> optFarm = farmService.getById(fd.getFarmId());

        if (optFarm.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Farm farm = optFarm.get();
        farmer.setFarm(farm);

        farmerService.save(farmer);

        return ResponseEntity.ok(farmer);
    }
}
