package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.Vector;

public class NonPlayerHelicopter extends Helicopter implements IDrawable{


    private IStrategy currentStrategy;

    public NonPlayerHelicopter(double xCord, double yCord, int heliMaximumSpeed)
    {
        super(xCord, yCord, heliMaximumSpeed, ColorUtil.CYAN);

    }

    public void resetFuel(){
        if(super.getFuelLevel() <= 5)
            super.setFuelLevel(50);
    }

    public void setStrategy(IStrategy newStrategy){
        currentStrategy = newStrategy;
    }

    public String getStrategy(){
        if(currentStrategy instanceof AttackStrategy)
            return "Attack Strategy";
        else if(currentStrategy instanceof ResourceStarveStrategy)
            return "Resource Starve Strategy";
        else
            return "Objective Strategy";
    }

    public void enactStrategy (){
        currentStrategy.invokeStrategy();
    }

    @Override
    public void draw(Graphics g, Point point) {
        Vector<Double> coordinates = getCoordinates();
        try {
            int xCord = (int)(coordinates.get(0) * 1);
            int yCord = (int)(coordinates.get(1) * 1);
            g.drawImage(Image.createImage("/NPC.png"),
                    xCord - getSize(),
                    yCord + (getMapViewYOrigin() - getSize()),
                    getSize() + 20,
                    getSize());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString(){
        Vector<Double> coordinates = super.getCoordinates();
        double xValue = coordinates.elementAt(0);
        double yValue = coordinates.elementAt(1);

        return "NPC Helicopter: " +
                "loc=" + (Math.round(xValue * 10.0) / 10.0) + "," +
                (Math.round(yValue * 10.0) / 10.0) +
                " color=" + super.toStringForGetColor() +
                "heading=" + super.getHeading() +
                " speed=" + super.getSpeed() +
                " size=" + super.getSize() +
                " maxSpeed=" + this.getCurrentMaxSpeed() +
                " fuelLevel=" + this.getFuelLevel() +
                " damageLevel=" + this.getDamageLevel() +
                " strategy=" + getStrategy();
    }
}
