package com.thoughtworks.save.model;

public class Animal implements Cloneable{

    private String name;
    private int x;
    private int y;
    private int xOffset;
    private int yOffset;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int getCalculatedX() {
        return this.x + this.xOffset;
    }

    public int getCalculatedY() {
        return this.y + this.yOffset;
    }

    @Override
    public Animal clone() throws CloneNotSupportedException {
        Animal animal = new Animal();
        animal.setName(this.name);
        animal.setX(this.x);
        animal.setY(this.y);
        animal.setxOffset(this.xOffset);
        animal.setyOffset(this.yOffset);
        return animal;
    }
}
