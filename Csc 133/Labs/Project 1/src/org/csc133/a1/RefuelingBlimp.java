package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

import java.util.Random;
import java.util.Vector;


public class RefuelingBlimp extends Fixed{
    private int gasCapacity;
    private int size;

    public RefuelingBlimp() {
        super(ColorUtil.GREEN);
        Random randomNumber = new Random();
        this.size = 10 + randomNumber.nextInt(41);
        super.setSize(this.size);
        this.gasCapacity = super.getSize();
    }

    public void setGasCapacity(int capacity){
        this.gasCapacity = capacity;
    }

    public int getGasCapacity(){
        return this.gasCapacity;
    }

    @Override
    public String toString(){
        Vector<Double> coordinates = super.getCoordinates();
        double xValue = coordinates.elementAt(0);
        double yValue = coordinates.elementAt(1);

        return "RefuelingBlimp: " +
                "loc=" + (Math.round(xValue * 10.0) / 10.0) + "," +
                         (Math.round(yValue * 10.0) / 10.0) +
                " color=" + super.toStringForGetColor() +
                " size= " + super.getSize() +
                " capacity=" + this.getGasCapacity();
    }

}
