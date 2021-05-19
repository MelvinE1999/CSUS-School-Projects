package org.csc133.a3;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.InputStream;

public class BGSound implements Runnable {

    private Media sound;

    public BGSound(String Filename){
        try{
            InputStream file = Display.getInstance().getResourceAsStream(getClass(), "/" + Filename);
            sound = MediaManager.createMedia(file, "audio/wav", this);
        }catch (Exception FileNotFound){
            FileNotFound.printStackTrace();
        }
    }

    public void pause(){
        sound.pause();
    }

    public void playSound(){
        sound.play();
    }

    @Override
    public void run() {
        sound.setTime(0);
        sound.play();
    }
}
