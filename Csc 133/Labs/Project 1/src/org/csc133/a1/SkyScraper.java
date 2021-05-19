package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;
import java.util.Vector;



public class SkyScraper extends Fixed{
    private int sequenceNumber;

    public SkyScraper(int sequenceNumber,double xCord, double yCord){
        super(ColorUtil.BLUE, 10);
        this.sequenceNumber = sequenceNumber;
        super.setCoordinates(xCord, yCord);
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

}
