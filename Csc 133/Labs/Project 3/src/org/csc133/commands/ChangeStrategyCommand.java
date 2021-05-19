package org.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.GameWorld;

public class ChangeStrategyCommand extends Command{
    private final GameWorld gw;

    public ChangeStrategyCommand(GameWorld gw){
        super("NPC Strategy");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gw.changeNpcStrategy();
    }
}
