package com.thoughtworks.save.model;

import com.thoughtworks.save.exception.ConflictException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

public class Snapshot {

    private static final String CONFLICT_EXCEPTION_MESSAGE_FORMAT = "Conflict found at %s";
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

    public void addOrOverrideAnimal(Animal newAnimalRecord) throws ConflictException {
        boolean hasExistedAnimal = false;
        for (Animal existedAnimal : animals) {
            hasExistedAnimal = overrideIfExisted(existedAnimal, newAnimalRecord, hasExistedAnimal);
        }
        if (!hasExistedAnimal) {
            this.animals.add(newAnimalRecord);
        }
    }

    private boolean overrideIfExisted(Animal existedAnimalRecord, Animal newAnimalRecord, boolean hasExistedAnimal) throws ConflictException {
        if (existedAnimalRecord.getName().equals(newAnimalRecord.getName())) {
            throwConflictExceptionWhenIfConflict(existedAnimalRecord, newAnimalRecord);
            existedAnimalRecord.setX(newAnimalRecord.getX());
            existedAnimalRecord.setY(newAnimalRecord.getY());
            existedAnimalRecord.setxOffset(newAnimalRecord.getxOffset());
            existedAnimalRecord.setyOffset(newAnimalRecord.getyOffset());
            hasExistedAnimal = true;
        }
        return hasExistedAnimal;
    }

    private void throwConflictExceptionWhenIfConflict(Animal existedAnimalRecord, Animal newAnimalRecord) throws ConflictException {
        if (existedAnimalRecord.getCalculatedX() != newAnimalRecord.getX() || existedAnimalRecord.getCalculatedY() != newAnimalRecord.getY()) {
            throw new ConflictException(format(CONFLICT_EXCEPTION_MESSAGE_FORMAT, this.id));
        }
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
