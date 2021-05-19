package org.csc133.commands;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.GameWorld;

public class SoundToggleCommand extends Command {
    private GameWorld gw;

    public SoundToggleCommand(GameWorld gw){
        super("Sound On or Off");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Command toggleOn = new Command("On");
        Command toggleOff = new Command("Off");
        Command[] toggleCommands = new Command[2];
        toggleCommands[0] = toggleOn;
        toggleCommands[1] = toggleOff;

        //added to draw less attention to game in background
        Dialog.setDefaultBlurBackgroundRadius(10);
        Command toggleChoice = Dialog.show("Toggle Menu",
                "\tWould you like sound on or off?",
                toggleCommands);

        if(toggleChoice == toggleOn) {
            gw.setSoundToggle(true);
            gw.playBgSound();
        }
        else{
            gw.setSoundToggle(false);
            gw.pauseBgSound();
        }
    }
}
