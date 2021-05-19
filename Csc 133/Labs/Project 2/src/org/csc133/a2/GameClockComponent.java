package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.util.UITimer;

import java.io.IOException;
import java.util.ArrayList;

public class GameClockComponent extends SevenSegmentSetup{

    private ArrayList<Integer> timer;
    private static int MINUTE_SECOND_COLON_INDEX = 2;//expanded for clarity
    public GameClockComponent(){
        super(6);
        timer = new ArrayList<Integer>(3);
        timer.add(0);
        timer.add(0);
        timer.add(0);
        clockDigits[MINUTE_SECOND_COLON_INDEX] = colonImage;
        super.changeColor(ColorUtil.CYAN);//changed to dim color
    }

    public void resetResetElapsedTime(){
        timer.set(0,0);
        timer.set(1,0);
        timer.set(2,0);
    }

    public void stopElapsedTime(){
        getComponentForm().deregisterAnimated(this);
    }

    public ArrayList<Integer> getElapsedTime(){return timer;}


    public void setCurrentTime(){
        if(timer.get(0) >= 10)
            changeColor(ColorUtil.rgb(255,0,0));
        clockDigits[0] = digitImages[timer.get(0) /10];
        clockDigits[1] = digitImages[timer.get(0) % 10];
        clockDigits[3] = digitImages[timer.get(1)/10];
        clockDigits[4] = digitImages[timer.get(1) % 10];
        clockDigits[5] = digitImages[timer.get(2)];
    }

    public void increaseGameTime(){
        this.timer.set(2, this.timer.get(2) + 1);
        if(this.timer.get(2) == 10){
            this.timer.set(2, 0);
            this.timer.set(1, this.timer.get(1) + 1);
        }
        if(this.timer.get(1) == 60){
            this.timer.set(1, 0);
            this.timer.set(0, this.timer.get(0) + 1);
        }
    }

    public boolean animate(){
        increaseGameTime();
        setCurrentTime();
        return true;
    }

}
