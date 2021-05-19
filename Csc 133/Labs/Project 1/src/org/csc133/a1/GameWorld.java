package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class GameWorld {

    private boolean quit;
    private int clockTime;
    private int lives;
    private ArrayList<GameObject> gameObjects;


    public void init(){
        this.clockTime = 0;
        this.quit = false;
        this.lives = 3;
        this.gameObjects = new ArrayList<>();
        addGameObjectsToGameWorld(this.gameObjects);
    }

    private void addGameObjectsToGameWorld(ArrayList<GameObject> gameObjects) {
        int[] cordForSkyScrapers = {200, 300, 400, 700, 1000, 100, 900, 250, 8};
        Random randomNumber = new Random(); //randomizes amt of skyScrappers to
                                            // increase playability
        int amountOfSkyScrapers = 4 + randomNumber.nextInt(5);
        for(int i = 0; i < amountOfSkyScrapers; i++ ) {
            gameObjects.add(new SkyScraper(i, cordForSkyScrapers[i],
                            (cordForSkyScrapers[i] / 2)));
        }
        gameObjects.add(new Helicopter(cordForSkyScrapers[0],
                (cordForSkyScrapers[0] / 2), 30));
        for(int birdMade = 0; birdMade < 2; birdMade++){
            gameObjects.add(new Bird());
        }
        for(int blimpMade = 0; blimpMade < 2; blimpMade++){
            gameObjects.add(new RefuelingBlimp());
        }
    }

    public void accelerate(){ //only for Helicopter Obj
        System.out.println("Helicopter has accelerated.");
        for(int i = 0; i < gameObjects.size(); i++){
            if(gameObjects.get(i) instanceof Helicopter){
                ((Helicopter) gameObjects.get(i)).increaseSpeed();
            }
        }
    }

    public void brake(){//only for Helicopter Obj
        System.out.println("Helicopter brakes have been applied.");
        for(int i = 0; i < gameObjects.size(); i++){
            if(gameObjects.get(i) instanceof Helicopter){
                ((Helicopter) gameObjects.get(i)).decreaseSpeed();
            }
        }
    }

    public void leftTurn(){ //only for Helicopter Obj
        System.out.println("Helicopter has turned left 5 degrees.");
        for(int i = 0; i < gameObjects.size(); i++){
            if(gameObjects.get(i) instanceof Helicopter){
                ((Helicopter) gameObjects.get(i)).turnLeft();
            }
        }
    }

    public void rightTurn(){ //only for Helicopter Obj
        System.out.println("Helicopter has turned right 5 degrees.");
        for(int i = 0; i < gameObjects.size(); i++){
            if(gameObjects.get(i) instanceof Helicopter){
                ((Helicopter) gameObjects.get(i)).turnRight();
            }
        }
    }

    public void collidedWithHelicopter(){
        System.out.println("User's helicopter has collided with another " +
                           "helicopter");
        int heliIndex = 0;
        for(int i = 0; i < gameObjects.size(); i++){
            if(gameObjects.get(i) instanceof Helicopter){
                Helicopter heliHolder =  ((Helicopter) gameObjects.get(i));
                heliIndex = i;
                heliHolder.setDamageLevel(50);
                updateHelicopterColorOnDamageTaken(heliHolder, i);
            }
        }
        checkIfHelicopterDied(heliIndex);
    }

    public void checkIfHelicopterDied(int heliIndex){
        Helicopter heliHolder = (Helicopter)gameObjects.get(heliIndex);
        if(heliHolder.getDamageLevel() == 100){
            this.lives--;
            if(this.lives == 0){
                printGameOverMessage();
            }
            gameObjects.set(heliIndex, new Helicopter(200,100,
                                    40));
        }
    }

    public void printGameOverMessage(){
        System.out.println("Game over, better luck next time!");
        System.exit(0);
    }

    public void updateHelicopterColorOnDamageTaken(Helicopter heliHolder,
                                                   int index){
        int multiplierOfColor = heliHolder.getDamageLevel() / 25;
        multiplierOfColor = multiplierOfColor * 32; //used to find new
        // color
        int currentColor = ColorUtil.red(heliHolder.getColor());// all 3
        // have same value
        heliHolder.setColor(ColorUtil.rgb(
                currentColor + multiplierOfColor,
                currentColor + multiplierOfColor,
                currentColor + multiplierOfColor),
                gameObjects.get(index)
        );
    }

    public void refuelingAtRefuelingBlimp(){
        System.out.println("Helicopter has reached a refueling blimp.");

        Vector<Double> helicopterLocation = null; //set at a precaution
        Vector<Double> firstBlimpCord = null; //set as a precaution
        Vector<Double> secondBlimpCord = null; //set as a precaution
        int blimpCount = 1;
        int firstBlimpIndex = 0;
        Helicopter heliHolder = null;

        for(int i = 0; i < gameObjects.size(); i++){
            if(gameObjects.get(i) instanceof Helicopter){
                heliHolder = ((Helicopter) gameObjects.get(i));
                helicopterLocation = heliHolder.getCoordinates();
            }
            if(gameObjects.get(i) instanceof RefuelingBlimp){
               RefuelingBlimp blimpHolder = ((RefuelingBlimp) gameObjects
                                                .get(i));
                switch (blimpCount){ //way to only have proper var modified
                    case 1:
                        firstBlimpCord = blimpHolder.getCoordinates() ;
                        firstBlimpIndex = i;
                        break;
                    case 2:
                        secondBlimpCord = blimpHolder.getCoordinates();
                        break;
                }
                blimpCount++;
            }
        }
        int closestBlimpIndex = calculateClosestBlimpIndex(
                                            helicopterLocation,
                                            firstBlimpCord,
                                            secondBlimpCord) +
                                            firstBlimpIndex;

        int addFuel = ((RefuelingBlimp) gameObjects.get(closestBlimpIndex))
                        .getGasCapacity() + heliHolder.getFuelLevel();

        heliHolder.setFuelLevel(addFuel);
        ((RefuelingBlimp) gameObjects.get(closestBlimpIndex))
                .setColor(ColorUtil.rgb(50, 205, 50),
                ((RefuelingBlimp) gameObjects.get(closestBlimpIndex)));
        ((RefuelingBlimp) gameObjects.get(closestBlimpIndex)).setGasCapacity(0);
        gameObjects.set(closestBlimpIndex, new RefuelingBlimp());
    }

    // seperated it from the refueling method to promote looser coupling
    public int calculateClosestBlimpIndex(Vector<Double> heliLoc,
                                          Vector<Double> firstBlimpCord,
                                          Vector<Double> secondBlimpCord){

        double heliCordAddedTogether = heliLoc.get(0) + heliLoc.get(1);
        double firstBlimpCordAddedTogether = firstBlimpCord.get(0) +
                                             firstBlimpCord.get(1);
        double secondBlimpCordAddedTogether = secondBlimpCord.get(0) +
                secondBlimpCord.get(1);
        //took absolute value below as we do not want any negative values
        if( Math.abs(heliCordAddedTogether - firstBlimpCordAddedTogether) <
            Math.abs(heliCordAddedTogether - secondBlimpCordAddedTogether)){
            return 0;
        }
        else{
            return 1;
        }
    }

    public void birdHitHelicopter(){
        System.out.println("Bird has hit Helicopter.");
        int heliIndex = 0;
        for(int i = 0; i < gameObjects.size(); i++){
            if(gameObjects.get(i) instanceof Helicopter){
                Helicopter heliHolder =  ((Helicopter) gameObjects.get(i));
               heliIndex = i;
               heliHolder.setDamageLevel(25);
               updateHelicopterColorOnDamageTaken(heliHolder, i);
            }
        }
        checkIfHelicopterDied(heliIndex);
    }

    public void gameClockHasTicked(){ //still need to get working fully
                    // current issue move method for Moveable not called
        System.out.print("The game clock has moved forward by one. Current " +
                           "time is ");
        for(int i = 0; i < gameObjects.size(); i++){
            if(gameObjects.get(i) instanceof Helicopter){
                ((Helicopter) gameObjects.get(i)).setFuelLevel();
                ((Helicopter) gameObjects.get(i)).move();
            }
            if(gameObjects.get(i) instanceof Bird){
                ((Bird) gameObjects.get(i)).move();
            }
        }
        this.clockTime += 1;
        System.out.println(this.clockTime);
    }

    public void displayCurrentGameState(){
        System.out.println("Here is the current game state: ");
        System.out.print("currentGameTime=" + this.clockTime +
                         " lives=" + this.lives);
        for(int i = 0; i < gameObjects.size(); i++){
            if(gameObjects.get(i) instanceof Helicopter){
                Helicopter heliObj = ((Helicopter) gameObjects.get(i));
                System.out.println(" recentSkyScraper=" +
                        heliObj.getLastSkyScraperReached() +
                        " currentFuelLevel=" + heliObj.getFuelLevel() +
                        " damageLevel=" + heliObj.getDamageLevel());
            }
        }
    }

    public void showMap(){
        System.out.println("Here is the current map of the world:");
        for(int i = 0; i < gameObjects.size(); i++)
        {
            System.out.println(gameObjects.get(i).toString());
        }
    }

    public void yesExit(){
        if(this.quit){
            System.out.println("User has decided to exit the program.");
            System.exit(0);
        }
        else {
            System.out.println("You must enter the exit command before " +
                    "trying this command.");
        }
    }

    public void noExit(){
        if(this.quit){
            System.out.println("User has decided not to exit the " +
                    "program.");
            this.quit = false;
        }
        else {
            System.out.println("You must enter the exit command before " +
                               "trying this command.");
        }
    }

    public void skyScraperHit(int num){
        System.out.println("Sky scrapper #" + Integer.toString(num) + " has " +
                           "been hit");
        for(int i = 0; i < gameObjects.size(); i++){
            if(gameObjects.get(i) instanceof Helicopter){
                if(num == (((Helicopter) gameObjects.get(i))
                                .getLastSkyScraperReached() + 1)){
                    ((Helicopter) gameObjects.get(i))
                            .setSkyScraperReached();
                    System.out.println("New checkpoint reached");
                }
                else if(num <= (((Helicopter) gameObjects.get(i))
                                .getLastSkyScraperReached() + 1)){
                    System.out.println("You already hit this sky scrapper.");
                }
                else{
                    System.out.println("Sky scrappers were hit out of order");
                }
            }
        }
        checkIfPlayerWon();
    }

    public void checkIfPlayerWon(){
        int skyScraperAmount = 0;
        int lastSkyScraperHit = 0;
        for(int i = 0; i < gameObjects.size(); i++){
            if( gameObjects.get(i) instanceof SkyScraper){
                skyScraperAmount++;
            }
            if(gameObjects.get(i) instanceof Helicopter){
                lastSkyScraperHit = ((Helicopter)gameObjects.get(i))
                                        .getLastSkyScraperReached();
            }
        }
        if(lastSkyScraperHit == skyScraperAmount){
            printGameWonMessage();
        }
    }

    public void printGameWonMessage(){
        System.out.println("Game over, You Won. Total Time " + this.clockTime);
        System.exit(0);
    }

    public void exit(){
        System.out.println("User has tried to exit program. " +
                           "Awaiting choice");
        this.quit = true; //used as a checker as without it someone could end
                          // program prematurely
        System.out.println("Would you like to exit this program? Enter Y or N");
    }

}
