package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;


public class RefuelingBlimp extends Fixed implements IDrawable{
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
    public void draw(Graphics g, Point point) {
        Vector<Double> coordinates = getCoordinates();
        try {
            int xCord = (int)(coordinates.get(0) * 1);
            int yCord = (int)(coordinates.get(1) * 1);
            g.drawImage(Image.createImage("/RefuelingBlimp.png"),
                    xCord - getSize(),
                    yCord + (getMapViewYOrigin() - getSize()),
                    getSize() + 40,
                    getSize() + 20);

        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setColor(ColorUtil.WHITE);
        double refuelingBlimpXCord = (coordinates.get(0) + 5) + point.getX();
        double refuelingBlimpYCord = (coordinates.get(1) - 5) + point.getY();
        g.drawString(Integer.toString(gasCapacity),
                (int)refuelingBlimpXCord, (int)refuelingBlimpYCord);
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
