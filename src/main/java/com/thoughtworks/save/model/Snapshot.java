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

    public void addOrOverrideAnimal(Animal animal) {
        boolean hasExistedAnimal = false;
        for (Animal existedAnimal : animals) {
            hasExistedAnimal = overrideIfExisted(animal, hasExistedAnimal, existedAnimal);
        }
        if (!hasExistedAnimal) {
            this.animals.add(animal);
        }
    }

    private boolean overrideIfExisted(Animal animal, boolean hasExistedAnimal, Animal existedAnimal) {
        if (existedAnimal.getName().equals(animal.getName())) {
            existedAnimal.setX(animal.getX());
            existedAnimal.setY(animal.getY());
            existedAnimal.setxOffset(animal.getxOffset());
            existedAnimal.setyOffset(animal.getyOffset());
            hasExistedAnimal = true;
        }
        return hasExistedAnimal;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
