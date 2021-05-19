package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;

public class HeadingCompnent extends SevenSegmentSetup{

    private final GameWorld gw;
    public HeadingCompnent(GameWorld gameWorld){
        super(3);
        gw = gameWorld;
        changeColor(ColorUtil.rgb(255,145,0));
    }

    public void setCurrentHeading(){
        int heading = gw.getHeading();
        clockDigits[0] = digitImages[heading / 100];
        heading = heading % 100;
        clockDigits[1] = digitImages[heading / 10];
        clockDigits[2] = digitImages[heading % 10];
    }

    public boolean animate(){
        setCurrentHeading();
        return true;
    }
}
