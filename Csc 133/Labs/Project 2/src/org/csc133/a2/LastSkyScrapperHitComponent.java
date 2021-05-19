package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;

public class LastSkyScrapperHitComponent extends SevenSegmentSetup{
    private GameWorld gw;
    public LastSkyScrapperHitComponent(GameWorld gameWorld){
        super(1);
        gw = gameWorld;
        changeColor(ColorUtil.rgb(102,153,255));
    }

    public void setCurrentLastSkyScrapper(){
        int lastSkyScrapperHit = gw.getLastSkyScrapperHit();
        clockDigits[0] = digitImages[lastSkyScrapperHit];
    }

    public boolean animate(){
        setCurrentLastSkyScrapper();
        return true;
    }
}