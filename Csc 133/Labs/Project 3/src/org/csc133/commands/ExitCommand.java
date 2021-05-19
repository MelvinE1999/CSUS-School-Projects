package org.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command {

    public ExitCommand(){
        super("Exit");
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Command yesToQuit = new Command("Yes");
        Command noToQuit = new Command("No");
        Command[] quitCommands = new Command[2];
        quitCommands[0] = yesToQuit;
        quitCommands[1] = noToQuit;

        //added to draw less attention to game in background
        Dialog.setDefaultBlurBackgroundRadius(10);
        Command quitGameChoice = Dialog.show("Exit Menu",
                                   "\tWould you like to quit this game?",
                                            quitCommands);

        if(quitGameChoice == yesToQuit)
            Display.getInstance().exitApplication();
    }
}
