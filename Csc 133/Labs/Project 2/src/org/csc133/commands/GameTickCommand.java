package org.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;

public class GameTickCommand extends Command {
    private GameWorld gw;

    public GameTickCommand(GameWorld gw){
        super("Game Ticked");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gw.gameClockHasTicked();
    }
}
