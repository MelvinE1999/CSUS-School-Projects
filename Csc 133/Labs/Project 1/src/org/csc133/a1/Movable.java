package org.csc133.a1;

import java.util.Vector;

public abstract class Movable extends GameObject{
    private int heading; // orientation based off of compass
    private int speed;

    public Movable(int color) {
        super(color);
    }

    public Movable(int color, int size){
        super(color, size);
    }

    public void setHeading(int heading){
        this.heading = heading;
    }

    public int getHeading(){
        return this.heading;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return this.speed;
    }

    public void move(){ //provided formula to calculate movement of Movable Obj
        Vector <Double> oldCoordinates = getCoordinates();
        double theta = 90 - this.heading;
        double thetaInRadians = Math.toRadians(theta);
        double deltaX = Math.cos(thetaInRadians) * this.speed;
        double deltaY = Math.sin(thetaInRadians) * this.speed;
        double newXLocation = oldCoordinates.elementAt(0) + deltaX;
        double newYLocation = oldCoordinates.elementAt(1) + deltaY;
        this.setCoordinates(newXLocation, newYLocation);
    }
}
