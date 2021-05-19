package org.csc133.a3;

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

    public void setHeading(int newHeading){
        if(this.heading == 0 || this.heading == 360)
            this.heading = newHeading;
        else {
            if (this.heading > newHeading)
                this.heading -= 5;
            else if (this.heading < newHeading)
                this.heading += 5;
        }
    }

    public void setHeading(int newHeading, boolean override){
        if(!override)
            setHeading(newHeading);
        else{
            this.heading = newHeading;
        }
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

        int objectSize = this.getSize();
        if(newXLocation + objectSize >= 1024){
            if(this instanceof Bird){
                setHeading(270, true);
            }
            else{
                newXLocation = oldCoordinates.elementAt(0);
                newYLocation = oldCoordinates.elementAt(1);
                if( this instanceof NonPlayerHelicopter){
                    setHeading(getHeading() + 15);
                }
            }
        }
        else if(newXLocation <= objectSize){
            if(this instanceof Bird){
                setHeading(90, true);
            }
            else{
                newXLocation = oldCoordinates.elementAt(0);
                newYLocation = oldCoordinates.elementAt(1);
                if( this instanceof NonPlayerHelicopter){
                    setHeading(getHeading()+ 10);
                }
            }
        }
        else if(150 + newYLocation <= 160){
            if(this instanceof Bird){
                setHeading(0, true);
            }
            else{
                newXLocation = oldCoordinates.elementAt(0);
                newYLocation = oldCoordinates.elementAt(1);
                if( this instanceof NonPlayerHelicopter){
                    setHeading(getHeading()+ 10);
                }
            }
        }
        else if(newYLocation  + objectSize >= 510){
            if(this instanceof Bird){
                setHeading(180, true);
            }
            else{
                newXLocation = oldCoordinates.elementAt(0);
                newYLocation = oldCoordinates.elementAt(1);
                if( this instanceof NonPlayerHelicopter){
                    setHeading(getHeading()+ 10);
                }
            }
        }

        this.setCoordinates(newXLocation, newYLocation);
    }
}
