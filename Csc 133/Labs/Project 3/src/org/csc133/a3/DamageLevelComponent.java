package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;

public class DamageLevelComponent extends SevenSegmentSetup{
    private final GameWorld gw;
    public DamageLevelComponent(GameWorld gameWorld){
        super(3);
        gw = gameWorld;
        changeColor(ColorUtil.GREEN);
    }

    public void setCurrentDamage(){
        int damageLevel = gw.getDamageLevel();
        if(damageLevel > 80)
            changeColor(ColorUtil.rgb(250, 0,0));
        else if(damageLevel > 50)
            changeColor(ColorUtil.YELLOW);
        else
            changeColor(ColorUtil.GREEN);
        clockDigits[0] = digitImages[damageLevel / 100];
        damageLevel = damageLevel % 100;
        clockDigits[1] = digitImages[damageLevel / 10];
        clockDigits[2] = digitImages[damageLevel % 10];
    }

    public boolean animate(){
        setCurrentDamage();
        return true;
    }
}
