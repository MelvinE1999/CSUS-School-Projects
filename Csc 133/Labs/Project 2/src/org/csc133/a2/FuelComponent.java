package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;

import java.util.ArrayList;

public class FuelComponent extends SevenSegmentSetup{
    private GameWorld gw;
    public FuelComponent(GameWorld gameWorld){
        super(4);
        gw = gameWorld;
        changeColor(ColorUtil.YELLOW);
    }

    public void setCurrentFuel(){
        int fuelLevel = gw.getFuelLevel();
        clockDigits[0] = digitImages[0];
        clockDigits[1] = digitImages[fuelLevel / 100];
        fuelLevel = fuelLevel % 100;
        clockDigits[2] = digitImages[fuelLevel / 10];
        clockDigits[3] = digitImages[fuelLevel % 10];
    }

    public boolean animate(){
        setCurrentFuel();
        return true;
    }
}
