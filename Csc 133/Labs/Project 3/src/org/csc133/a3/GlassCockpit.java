package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;


public class GlassCockpit extends Container{
    private final GameWorld gw;

    public GlassCockpit(GameWorld gameWorld){
        this.gw = gameWorld;

        Container cockpit = new Container(new GridLayout(2,6));
        Label clock = new Label("Game Time");

        Label fuel = new Label("Fuel");

        Label damage = new Label("Damage");

        Label lives = new Label("Lives");

        Label lastSkyScrapperHit = new Label("Last");

        Label heading = new Label("Heading");

        GameClockComponent time = new GameClockComponent();
        FuelComponent fuelAmount = new FuelComponent(gw);
        DamageLevelComponent damageLevel = new DamageLevelComponent(gw);
        LivesComponent livesAmount = new LivesComponent(gw);
        HeadingCompnent headingAmount = new HeadingCompnent(gw);
        LastSkyScrapperHitComponent lastSkyScrapper =
                               new LastSkyScrapperHitComponent(gw);

        cockpit.add(clock).add(fuel).add(damage)
                .add(lives).add(lastSkyScrapperHit).add(heading);
        cockpit.add(time).add(fuelAmount).add(damageLevel)
                .add(livesAmount).add(lastSkyScrapper).add(headingAmount);
        cockpit.getAllStyles().setBgColor(ColorUtil.GRAY);
        cockpit.getAllStyles().setBgTransparency(100);
        add(cockpit);

    }


}
