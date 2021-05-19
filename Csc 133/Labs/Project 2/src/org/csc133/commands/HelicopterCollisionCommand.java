package org.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;

public class HelicopterCollisionCommand extends Command {
    private GameWorld gw;

    public HelicopterCollisionCommand(GameWorld gw){
        super("Helicopter Collision");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gw.collidedWithHelicopter();
    }
}
