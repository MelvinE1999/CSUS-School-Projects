package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.Vector;

public class PlayerHelicopter extends Helicopter implements IDrawable{

    private static PlayerHelicopter playerHelicopter;

    private PlayerHelicopter (double xCord, double yCord, int heliMaxSpeed,
                             int color){
        super(xCord,yCord,heliMaxSpeed,color);
        super.setSkyScraperReached();
    }

    public static PlayerHelicopter getInstanceofPlayer(double xCord,
                                                       double yCord,
                                                       int heliMaxSpeed,
                                                       int color){
        if(playerHelicopter == null){
            playerHelicopter = new PlayerHelicopter(xCord, yCord, heliMaxSpeed,
                                                    color);
        }
        return playerHelicopter;
    }

    @Override
    public void draw(Graphics g, Point point) {
        Vector<Double> coordinates = getCoordinates();
        Transform t = Transform.makeIdentity();
        Transform t_Trans = Transform.makeIdentity();
        Transform t_Scale = Transform.makeIdentity();
        g.getTransform(t);
        try {
            int xCord = (int)(coordinates.get(0) * 1) - getSize();
            int yCord = (int)(coordinates.get(1) * 1)+
                    (getMapViewYOrigin() - getSize());

            Image heliImage = Image.createImage("/PlayerHelicopter_1.png");
            heliImage = heliImage.rotate(this.getHeading() + 90);

            g.drawImage(heliImage,
                    xCord,
                    yCord ,
                    getSize() + 20,
                    getSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setColor(ColorUtil.WHITE);
        g.resetAffine();
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

    @Override
    public void handleCollision(GameObject otherObject) {
        //empty to avoid doing collision twice
    }
}
