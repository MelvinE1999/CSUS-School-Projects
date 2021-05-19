package org.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;

public class BirdCollisionCommand extends Command {
    private GameWorld gw;

    public BirdCollisionCommand(GameWorld gw){
        super("Bird Collision");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gw.birdHitHelicopter();
    }
}
