package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

import java.util.Random;
import java.util.Vector;

public class Bird extends Movable{
    public Bird() {
        super(ColorUtil.YELLOW);
        Random randomNumber = new Random();
        super.setSize(10 + randomNumber.nextInt(21));
        super.setSpeed(5 + randomNumber.nextInt(6));
        super.setHeading(randomNumber.nextInt(360));
    }
    @Override
    public void move(){
        Random randomNumber = new Random();
        super.setHeading(1 - (super.getHeading() -
                                randomNumber.nextInt(5)));
        super.move(); //allows use of overridden method
    }
    @Override
    public String toString(){
        Vector<Double> coordinates = super.getCoordinates();
        double xValue = coordinates.elementAt(0);
        double yValue = coordinates.elementAt(1);

        return "Bird: " +
                "loc=" + (Math.round(xValue * 10.0) / 10.0) + "," +
                         (Math.round(yValue * 10.0) / 10.0) +
                " color=" + super.toStringForGetColor() +
                " heading= " + super.getHeading() +
                " speed=" + this.getSpeed() +
                " size=" + this.getSize();
    }
}
