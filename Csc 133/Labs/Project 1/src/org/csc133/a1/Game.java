package org.csc133.a1;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener; import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent; import java.lang.String;

public class Game extends Form{
    private GameWorld gw;

    public Game(){
        gw = new GameWorld();
        gw.init();
        play();
    }

    private void play(){ //takes all inputs and runs commands if applicable
        Label myLabel=new Label("Enter a Command:");
        this.addComponent(myLabel);
        final TextField myTextField=new TextField();
        this.addComponent(myTextField);
        this.show();
        myTextField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                String sCommand=myTextField.getText().toString();
                if (sCommand == null || sCommand.equals(""))
                    return; myTextField.clear();
                switch (sCommand.charAt(0)){
                    case 'a':
                        gw.accelerate();
                        break;
                    case 'b':
                        gw.brake();
                        break;
                    case 'l':
                        gw.leftTurn();
                        break;
                    case 'r':
                        gw.rightTurn();
                        break;
                    case 'c':
                        gw.collidedWithHelicopter();
                        break;
                    case 'e':
                        gw.refuelingAtRefuelingBlimp();
                        break;
                    case 'g':
                        gw.birdHitHelicopter();
                        break;
                    case 't':
                        gw.gameClockHasTicked();
                        break;
                    case 'd':
                        gw.displayCurrentGameState();
                        break;
                    case 'm':
                        gw.showMap();
                        break;
                    case 'x':
                        gw.exit();
                        break;
                    case 'y':
                        gw.yesExit();
                        break;
                    case 'n':
                        gw.noExit();
                        break;
                    case '1':
                        gw.skyScraperHit(1);
                        break;
                    case '2':
                        gw.skyScraperHit(2);
                        break;
                    case '3':
                        gw.skyScraperHit(3);
                        break;
                    case '4':
                        gw.skyScraperHit(4);
                        break;
                    case '5':
                        gw.skyScraperHit(5);
                        break;
                    case '6':
                        gw.skyScraperHit(6);
                        break;
                    case '7':
                        gw.skyScraperHit(7);
                        break;
                    case '8':
                        gw.skyScraperHit(8);
                        break;
                    case '9':
                        gw.skyScraperHit(9);
                        break;
                }
            }
        });
    }
}
