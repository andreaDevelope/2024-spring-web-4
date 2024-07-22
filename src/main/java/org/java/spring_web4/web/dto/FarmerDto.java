package org.java.spring_web4.web.dto;

import java.util.List;

public class FarmerDto {
    private String name;
    private String surname;
    private int age;
    private int farmId;
    private List<Integer> specs;

    public FarmerDto() {}

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

    public int getFarmId() {
        return farmId;
    }

    public List<Integer> getSpecs() {

        return specs;
    }

    public void setSpecs(List<Integer> specs) {

        this.specs = specs;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }
}
