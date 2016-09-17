package com.thoughtworks.save.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Snapshot {

    private Date timeStamp;
    private List<Animal> animals = new ArrayList<>();
    private String id;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
