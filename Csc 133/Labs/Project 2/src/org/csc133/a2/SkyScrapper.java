package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.Vector;



public class SkyScrapper extends Fixed implements IDrawable{
    private int sequenceNumber;
    private int sequenceNumberTextColor;

    public SkyScrapper(int sequenceNumber, double xCord, double yCord){
        super(ColorUtil.BLUE, 30);
        this.sequenceNumber = sequenceNumber;
        super.setCoordinates(xCord, yCord);
        sequenceNumberTextColor = ColorUtil.WHITE;
    }

    public void setSequenceNumberTextColor(){
        sequenceNumberTextColor = ColorUtil.YELLOW;
    }

    public int getSequenceNumber(){
        return this.sequenceNumber;
    }

    @Override
    public String toString() {
        Vector<Double> coordinates = super.getCoordinates();
        double xValue = coordinates.elementAt(0);
        double yValue = coordinates.elementAt(1);

        return "SkyScraper: loc=" + (Math.round(xValue * 10.0) / 10.0) + "," +
                                    (Math.round(yValue * 10.0) / 10.0) +
                " color=" + super.toStringForGetColor() +
                " size= " + super.getSize() +
                " seqNum=" + this.getSequenceNumber();
    }

    @Override
    public void draw(Graphics g, Point point) {
        Vector<Double> coordinates = getCoordinates();
        try {
            int xCord = (int)(coordinates.get(0) * 1);
            int yCord = (int)(coordinates.get(1) * 1);
            g.drawImage(Image.createImage("/SkyScrapper.jpg"),
                    xCord - getSize(),
                    yCord + (getMapViewYOrigin() - getSize()),
                    getSize() + 20,
                    getSize() + 20);

        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setColor(this.sequenceNumberTextColor);
        double skyScrapperNumberXCord = (coordinates.get(0) - 10) +
                                                point.getX();
        double skyScrapperNumberYCord = (coordinates.get(1) - 10) +
                                                point.getY();
        g.drawString(Integer.toString(sequenceNumber + 1),
                (int)skyScrapperNumberXCord, (int)skyScrapperNumberYCord);

    }
}
