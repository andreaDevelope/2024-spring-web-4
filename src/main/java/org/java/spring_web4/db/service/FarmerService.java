package org.java.spring_web4.db.service;

import java.util.List;
import java.util.Optional;

import org.java.spring_web4.db.pojo.Farmer;
import org.java.spring_web4.db.repo.FarmerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmerService {
    @Autowired
    private FarmerRepo fp;

    public List<Farmer> getAll(){
        return fp.findAll();
    }

    public Optional<Farmer> getById(int id){
        return fp.findById(id);
    }

    public void save(Farmer farmer){
        fp.save(farmer);
    }

    public void delete(Farmer farmer){
        fp.delete(farmer);
    }
}
