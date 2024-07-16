package org.java.spring_web4.db.pojo;

import org.java.spring_web4.web.dto.FarmerDto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(length = 64, nullable = false)
    private String surname;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private int age;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(nullable = false)
    private Farm farm;

    public Farmer(){

    }

    public Farmer(String name, String surname, int age, Farm farm){
        setId(id);
        setName(name);
        setSurname(surname);
        setAge(age);
        setFarm(farm);
    }

    // COTRUTTORE PER LA CLASSE DTO DI  FARMER
    public Farmer(FarmerDto fd) {

        update(fd);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
    
    public void update(FarmerDto fd) {

        setName(fd.getName());
        setSurname(fd.getSurname());
        setAge(fd.getAge());
    }
    
}
