package org.csc133.a0;


import static com.codename1.ui.CN.*;

import com.codename1.ui.*;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;

import javax.lang.model.element.Name;

class Sound {
    private Media m;
    public Sound(String fileName) {
        try {   m = MediaManager.createMedia(Display.getInstance()
                .getResourceAsStream(getClass(),
                "/"+fileName), "audio/wav");

        } catch(Exception e) {}
    }
    public void play() { m.setVolume(50); m.setTime(0); m.play();}
}

public class AppMain {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a " +
                    "networking error in the connection to " +
                    err.getConnectionRequest().getUrl(),
                    "OK", null);
        });        
    }

    public void start() {
        if(current != null){
            current.show();
            return;
        }
        new Sound("hello.wav").play();

        // Fonts used for the program
        Font largeBoldFont = Font.createSystemFont(Font.FACE_SYSTEM,
                             Font.STYLE_BOLD, SIZE_LARGE);
        Font monospaceSmallFont = Font.createSystemFont(Font.FACE_MONOSPACE,
                                  Font.STYLE_PLAIN, Font.SIZE_SMALL);
        Font monospaceMediumFont = Font.createSystemFont(Font.FACE_MONOSPACE,
                                   STYLE_ITALIC, SIZE_MEDIUM);

        //Each chunk below represents a different sections shown on screen
        //Except for the next three lines which provide the seperator and init
        Form hi = new Form("Melvin Evans", BoxLayout.y());
        Label topSeperator = (new Label("-----------------------------"));
        Label botSeperator = (new Label("-----------------------------"));

        Label name = (new Label("Melvin Evans"));
        name.getUnselectedStyle().setFont(largeBoldFont);
        hi.add(new Label(" ")).add(name).add(topSeperator);

        Label projectHead = (new Label("Past & Challenging Project: "));
        projectHead.getUnselectedStyle().setFont(monospaceMediumFont);
        Label projectBody = new Label("Website for First year Students");
        projectBody.getUnselectedStyle().setFont(monospaceSmallFont);
        hi.add(projectHead).add(projectBody).add(new Label(" "));

        Label description1 = new Label("This was a guided site that " +
                             "had a");
        description1.getUnselectedStyle().setFont(monospaceSmallFont);
        Label description2 = new Label("mini survey, route planning, " +
                             "and");
        description2.getUnselectedStyle().setFont(monospaceSmallFont);
        Label description3 = new Label("showed carbon emissions of " +
                             "route.");
        description3.getUnselectedStyle().setFont(monospaceSmallFont);
        hi.add(description1).add(description2).add(description3)
                            .add(new Label(" "));

        Label frontend = new Label("Frontend: Javascript and CSS");
        frontend.getUnselectedStyle().setFont(monospaceSmallFont);
        Label backend = new Label("Backend: Python and Google Api's");
        backend.getUnselectedStyle().setFont(monospaceSmallFont);
        hi.add(frontend).add(backend).add(new Label(" "))
                        .add(botSeperator);

        Label skillHead = new Label("Skills:");
        skillHead.getUnselectedStyle().setFont(monospaceMediumFont);
        Label skillBody = new Label("Api's, basic scripting and java");
        skillBody.getUnselectedStyle().setFont(monospaceSmallFont);
        hi.add(skillHead).add(skillBody);

        hi.show();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
