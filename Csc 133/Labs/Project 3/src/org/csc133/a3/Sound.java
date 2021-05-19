package org.csc133.a3;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.InputStream;

public class Sound {

    private Media sound;

    public Sound(String Filename){
        try{
            InputStream file = Display.getInstance().getResourceAsStream(getClass(), "/" + Filename);
            sound = MediaManager.createMedia(file, "audio/wav");
        }catch (Exception FileNotFound){
            FileNotFound.printStackTrace();
        }
    }

    public void playSound(){
        sound.setTime(0);
        sound.play();
    }
}
