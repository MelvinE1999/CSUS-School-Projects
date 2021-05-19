package org.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;

public class RefuelingCommand extends Command {

    private GameWorld gw;

    public RefuelingCommand(GameWorld gw){
        super("Refueling");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gw.refuelingAtRefuelingBlimp();
    }
}
