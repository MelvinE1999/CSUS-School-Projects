package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.Vector;

public class PlayerHelicopter extends Helicopter implements IDrawable{


    public PlayerHelicopter (double xCord, double yCord, int heliMaximumSpeed,
                             int color){
        super(xCord,yCord,heliMaximumSpeed,color);
        super.setSkyScraperReached();
    }

    @Override
    public void draw(Graphics g, Point point) {
        Vector<Double> coordinates = getCoordinates();
        try {
            int xCord = (int)(coordinates.get(0) * 1);
            int yCord = (int)(coordinates.get(1) * 1);
            Image heliImage = Image.createImage("/PlayerHelicopter.png");
            heliImage = heliImage.rotate(this.getHeading());
            g.drawImage(heliImage,
                    xCord - getSize(),
                    yCord + (getMapViewYOrigin() - getSize()),
                    getSize() + 20,
                    getSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setColor(ColorUtil.WHITE);
    }


    @Override
    public String toString(){
        Vector<Double> coordinates = super.getCoordinates();
        double xValue = coordinates.elementAt(0);
        double yValue = coordinates.elementAt(1);

        return "Helicopter: " +
                "loc=" + (Math.round(xValue * 10.0) / 10.0) + "," +
                (Math.round(yValue * 10.0) / 10.0) +
                " color=" + super.toStringForGetColor() +
                " heading=" + super.getHeading() +
                " speed=" + super.getSpeed() +
                " size=" + super.getSize() +
                " maxSpeed=" + this.getCurrentMaxSpeed() +
                " stickAngle=" + super.getStickAngle() +
                " fuelLevel=" + this.getFuelLevel() +
                " damageLevel=" + this.getDamageLevel();
    }
}
