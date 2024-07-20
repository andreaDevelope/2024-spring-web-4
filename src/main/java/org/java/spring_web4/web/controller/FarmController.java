package org.java.spring_web4.web.controller;

import java.util.List;
import java.util.Optional;

import org.java.spring_web4.db.pojo.Farm;
import org.java.spring_web4.db.service.FarmService;
import org.java.spring_web4.web.dto.FarmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/farms")
@CrossOrigin(origins = "http://localhost:5173")
public class FarmController {
    @Autowired
    private FarmService farmService;

    @GetMapping("")
    public ResponseEntity<List<Farm>> getAllFarms() {
        List<Farm> farms = farmService.getAll();
        return ResponseEntity.ok(farms);
    }

    @PostMapping("")
    public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
        Farm farm = new Farm(farmDto.getName(), farmDto.getCity());
        farmService.save(farm);
        return ResponseEntity.ok(farm);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Farm> updateFarm(@PathVariable int id, @RequestBody FarmDto farmDto) {
        Optional<Farm> optFarm = farmService.getById(id);

        if (optFarm.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Farm farm = optFarm.get();
        farm.setName(farmDto.getName());
        farm.setCity(farmDto.getCity());
        farmService.save(farm);

        return ResponseEntity.ok(farm);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Farm> deleteFarm(@PathVariable int id){
        Optional<Farm> optF = farmService.getById(id);
        if (optF.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Farm f = optF.get();
        farmService.delete(f);

        return ResponseEntity.ok(f);
    }
}
