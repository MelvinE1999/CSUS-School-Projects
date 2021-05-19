package org.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpMenuCommand extends Command {
    public HelpMenuCommand(){
        super("Help");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        StringBuilder helpInformation = new StringBuilder();

        helpInformation.append("These are all of the key bindings for this " +
                "game.\n Format is (key to press : what the key does)\n\n");
        helpInformation.append("a: Accelerate\n");
        helpInformation.append("b: Brake\n");
        helpInformation.append("l: Left Turn\n");
        helpInformation.append("n: Helicopter Collision\n");
        helpInformation.append("s: SkyScrapper Collision\n");
        helpInformation.append("e: Refueling at Blimp\n");
        helpInformation.append("g: Bird Collision\n");
        helpInformation.append("x: Exit Program\n");

        //added to draw less attention to game in background
        Dialog.setDefaultBlurBackgroundRadius(10);
        Dialog.show("Help Menu",
                String.valueOf(helpInformation),
                "Ok", null);
    }
}
