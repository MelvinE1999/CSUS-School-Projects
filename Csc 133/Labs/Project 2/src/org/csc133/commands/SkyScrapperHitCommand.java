package org.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;


// need to work on as can't pass in int and event like this
public class SkyScrapperHitCommand extends Command {
    private GameWorld gw;

    public SkyScrapperHitCommand(GameWorld gw){
        super("Sky Scrapper hit");
        this.gw = gw;
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        //added to draw less attention to game in background
        Dialog.setDefaultBlurBackgroundRadius(10);
        Command enterCommand = new Command("Enter");
        TextField skyScrapperNumberTextField = new TextField();
        Dialog.show("Enter number of hit sky scrapper:",
                    skyScrapperNumberTextField,
                    enterCommand);
        try{
            int skyScrapperHit = Integer.parseInt(
                               skyScrapperNumberTextField.getText().toString());
            if(skyScrapperHit < 0 || skyScrapperHit > 9){
                String tryAgainMessageNotInNumRange = "Please only enter " +
                        "numbers within the range of 1 - 9. Please enter the " +
                        "command again to try again.";
                Dialog.show("Invalid Input", tryAgainMessageNotInNumRange,
                        "Enter", null);
            }
            else {
                this.gw.skyScraperHit(skyScrapperHit);
            }
        }catch(NumberFormatException invalidInput){
            String tryAgainMessageWrongDataType = "Numbers are the only " +
                                     "accepted value. Please enter the " +
                                     "command again to try again.";
            Dialog.show("Invalid Input", tryAgainMessageWrongDataType,
                        "Enter", null);

        }
    }
}
