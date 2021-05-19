package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

import java.io.IOException;

public class SevenSegmentSetup extends Component {
    Image[] digitImages = new Image[10];
    Image colonImage;


    private int ledColor;
    private final int numDigitsShowing;
    Image[] clockDigits;

    public SevenSegmentSetup(int amountOfDigits){
        try{
            digitImages[0] = Image.createImage("/LED_digit_0.png");
            digitImages[1] = Image.createImage("/LED_digit_1.png");
            digitImages[2] = Image.createImage("/LED_digit_2.png");
            digitImages[3] = Image.createImage("/LED_digit_3.png");
            digitImages[4] = Image.createImage("/LED_digit_4.png");
            digitImages[5] = Image.createImage("/LED_digit_5.png");
            digitImages[6] = Image.createImage("/LED_digit_6.png");
            digitImages[7] = Image.createImage("/LED_digit_7.png");
            digitImages[8] = Image.createImage("/LED_digit_8.png");
            digitImages[9] = Image.createImage("/LED_digit_9.png");

            colonImage = Image.createImage("/LED_colon.png");
        }catch (IOException e){
            e.printStackTrace();
        }

        numDigitsShowing = amountOfDigits;
        clockDigits = new Image[numDigitsShowing];

        for(int i = 0; i < numDigitsShowing; i++)
            clockDigits[i] = digitImages[0];


    }

    public int getLedColor(){
        return this.ledColor;
    }

    public  void changeColor(int color){
        this.ledColor = color;
    }

    public void start(){getComponentForm().registerAnimated(this);}

    public void laidOut(){
        this.start();
    }

    @Override
    protected Dimension calcPreferredSize() {
        return new Dimension(colonImage.getWidth()*numDigitsShowing,
                            (int)(colonImage.getHeight()/ 1.5));
    }

    public void paint(Graphics clock){ //renamed g to stand for what g is
        super.paint(clock);
        final int COLOR_PAD = 1;

        int digitWidth = clockDigits[0].getWidth();
        int digitHeight = clockDigits[0].getHeight();
        int clockWidth = numDigitsShowing*digitWidth;

        float scaleFactor = Math.min(
                getInnerHeight()/(float)digitHeight,
                getInnerHeight()/(float)clockWidth
        );

        //multiplied both by 1.7 to make the clock look like given image
        int displayDigitWidth = (int)((scaleFactor*digitWidth));
        int displayDigitHeight = (int)((scaleFactor*digitHeight));

        int displayClockWidth = displayDigitWidth*numDigitsShowing;

        int displayX = getX() + (getWidth() - displayClockWidth) / 2;
        int displayY = getY() + (getHeight() - displayDigitHeight) / 2;

        clock.setColor(ColorUtil.BLACK);
        clock.fillRect(getX(),getY(),getWidth(),getHeight());

        clock.setColor(ledColor);
        clock.fillRect(displayX+COLOR_PAD,
                displayY+COLOR_PAD,
                displayClockWidth-COLOR_PAD*2,
                displayDigitHeight-COLOR_PAD*2);

        for(int digitIndex =0; digitIndex < numDigitsShowing; digitIndex++) {
            if (digitIndex == 5)
                clock.setColor(ColorUtil.YELLOW);
            clock.drawImage(clockDigits[digitIndex],
                    displayX + digitIndex * displayDigitWidth,
                    displayY,
                    displayDigitWidth,
                    displayDigitHeight);
        }
        clock.setColor(ledColor);
    }
}
