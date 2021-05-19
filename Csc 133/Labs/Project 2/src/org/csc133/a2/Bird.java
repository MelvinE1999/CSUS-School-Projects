package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class Bird extends Movable implements IDrawable{
    public Bird() {
        super(ColorUtil.YELLOW);
        Random randomNumber = new Random();
        super.setSize(20);
        super.setSpeed(5 + randomNumber.nextInt(8));
        super.setHeading(randomNumber.nextInt(360));
    }
    @Override
    public void move(){
        Random randomNumber = new Random();
        int birdMovementChoice = randomNumber.nextInt(2);
        if(birdMovementChoice == 0)
            super.setHeading(5 + (super.getHeading() +
                                    randomNumber.nextInt(5)));
        else
            super.setHeading(5 - (super.getHeading() -
                    randomNumber.nextInt(5)));
        super.move(); //allows use of overridden method
    }

    @Override
    public void draw(Graphics g, Point point) {
        Vector<Double> coordinates = getCoordinates();
        try {
            int xCord = (int)(coordinates.get(0) * 1);
            int yCord = (int)(coordinates.get(1) * 1);
            Image birdImage = Image.createImage("/Bird.png");
            birdImage = birdImage.rotate(this.getHeading() + 270);
            g.drawImage(birdImage,
                    xCord - getSize(),
                    yCord + (getMapViewYOrigin() - getSize()),
                    getSize() + 20,
                    getSize() + 20);

        } catch (IOException e) {
            e.printStackTrace();
        }

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
