package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;

public class LivesComponent extends SevenSegmentSetup{
    private final GameWorld gw;
    public LivesComponent(GameWorld gameWorld){
        super(1);
        gw = gameWorld;
        changeColor(ColorUtil.rgb(188,0,255));
    }

    public void setCurrentLives(){
        int lives = gw.getLives();
        clockDigits[0] = digitImages[lives];
    }

    public boolean animate(){
        setCurrentLives();
        return true;
    }
}
