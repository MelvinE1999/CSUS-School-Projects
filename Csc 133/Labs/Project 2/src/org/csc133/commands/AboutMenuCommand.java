package org.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;

import com.codename1.ui.events.ActionEvent;

public class AboutMenuCommand extends Command {
    public AboutMenuCommand(){
        super("About");
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String aboutInformation = "Name: Melvin Evans\n" +
                                  "Course: Csc 133-05\n" +
                                  "Version # 2.0";

        //added to draw less attention to game in background
        Dialog.setDefaultBlurBackgroundRadius(10);
        Dialog.show("About menu",
                aboutInformation,
                "Ok", null);

    }
}
