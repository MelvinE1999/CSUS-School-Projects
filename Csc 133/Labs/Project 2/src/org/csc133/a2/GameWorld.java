package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;

import java.util.Random;
import java.util.Vector;

public class GameWorld{


    private int clockTime;
    private int lives;
    private GameWorldCollection gameObjects;

    private static int gameHeight;
    private static  int gameWidth;



    public void init(){
        this.clockTime = 0;
        this.lives = 3;
        this.gameObjects = new GameWorldCollection();
        addGameObjectsToGameWorld(this.gameObjects);
        IIterator skyScrapperIterator = gameObjects.getIterator();
        GameObject currentObject = skyScrapperIterator.getNext();
        ((SkyScrapper)currentObject).setSequenceNumberTextColor();
    }

    private void addGameObjectsToGameWorld(GameWorldCollection gameObjects) {
        int[] xCordForSkyScrapers = {200, 900, 400, 700, 100};
        int[] yCordForSkyScrapers = {100, 400, 200, 300, 375};
        int[] cordForNpcHelicopters = {250, 100, 50};
                int amountOfSkyScrapers = 5;
        for(int i = 0; i < amountOfSkyScrapers; i++ ) {
            gameObjects.add(new SkyScrapper(i, xCordForSkyScrapers[i],
                            yCordForSkyScrapers[i]));
        }
        for(int blimpMade = 0; blimpMade < 2; blimpMade++){
            gameObjects.add(new RefuelingBlimp());
        }
        gameObjects.add(new PlayerHelicopter(xCordForSkyScrapers[0],
                yCordForSkyScrapers[0], 30,
                    ColorUtil.BLACK));
        for(int j = 0; j < 3; j++) {
            NonPlayerHelicopter npc = new NonPlayerHelicopter(
                    cordForNpcHelicopters[j],
                    (cordForNpcHelicopters[j] / 2),
                    10);
            pickNewStrategy(npc);
            gameObjects.add(npc);
        }
        for(int birdMade = 0; birdMade < 2; birdMade++){
            gameObjects.add(new Bird());
        }
    }

    public void changeNpcStrategy(){
        NonPlayerHelicopter npc;
        IIterator npcIterator = gameObjects.getIterator();
        while(npcIterator.hasNext()){
            GameObject currentObject = npcIterator.getNext();
            if(currentObject instanceof NonPlayerHelicopter){
                npc = (NonPlayerHelicopter) currentObject;
                if(npc.getLastSkyScraperReached() == 9){
                    System.out.println("Game over, You lose!");
                    System.exit(1);
                }
                pickNewStrategy(npc);
            }
        }
    }

    public static void setGameHeight(int gameWorldHeight){
        gameHeight = gameWorldHeight;
    }

    public static void setGameWidth(int gameWorldWidth){
        gameWidth = gameWorldWidth;
    }

    //need to add this out more
    public void pickNewStrategy(NonPlayerHelicopter npc){
        Random random = new Random();
        npc.setSkyScraperReached();
        String oldStrategy = npc.getStrategy();
        do{
            int strategyNumber = random.nextInt(3);
            switch (strategyNumber){
                case 0:
                    npc.setStrategy(new AttackStrategy(gameObjects, npc));
                    break;
                case 1:
                    npc.setStrategy(new ResourceStarveStrategy(gameObjects,npc));
                    break;
                case 2:
                    npc.setStrategy(new ObjectiveStrategy(gameObjects,npc));
            }
        }while(oldStrategy.equals(npc.getStrategy()));
    }

    public PlayerHelicopter getPlayerHelicopter(){
        IIterator objectIterator = gameObjects.getIterator();
        while(objectIterator.hasNext()){
            GameObject currentObject = objectIterator.getNext();
            if(currentObject instanceof PlayerHelicopter)
                return (PlayerHelicopter) currentObject;
        }
        return null;
    }

    public void accelerate(){ //only for Helicopter Obj
        System.out.println("Helicopter has accelerated.");
        PlayerHelicopter playerHelicopter = getPlayerHelicopter();
        playerHelicopter.increaseSpeed();
    }

    public void brake(){//only for Helicopter Obj
        System.out.println("Helicopter brakes have been applied.");
        PlayerHelicopter playerHelicopter = getPlayerHelicopter();
        playerHelicopter.decreaseSpeed();
    }

    public void leftTurn(){ //only for Helicopter Obj
        System.out.println("Helicopter has turned left 5 degrees.");
        PlayerHelicopter playerHelicopter = getPlayerHelicopter();
        playerHelicopter.turnLeft();
    }

    public void rightTurn(){ //only for Helicopter Obj
        System.out.println("Helicopter has turned right 5 degrees.");
        PlayerHelicopter playerHelicopter = getPlayerHelicopter();
        playerHelicopter.turnRight();
    }

    public void collidedWithHelicopter(){
        System.out.println("User's helicopter has collided with another " +
                           "helicopter");
        Random randomNumber = new Random();
        int randomNpcHelicopter = randomNumber.nextInt(3);
        int npcCounter = 0;
        IIterator objectIterator = gameObjects.getIterator();
        while(objectIterator.hasNext()){
            GameObject currentObject = objectIterator.getNext();
            if(currentObject instanceof PlayerHelicopter) {
                ((PlayerHelicopter)currentObject).setDamageLevel(50);
                updateHelicopterColorOnDamageTaken(
                                        (PlayerHelicopter) currentObject);
                checkIfHelicopterDied((PlayerHelicopter) currentObject);
            }else if(currentObject instanceof NonPlayerHelicopter){
                if(randomNpcHelicopter == npcCounter){
                    ((Helicopter)currentObject).setDamageLevel(50);
                    updateHelicopterColorOnDamageTaken(
                                        (Helicopter) currentObject);
                    checkIfHelicopterDied((NonPlayerHelicopter) currentObject);
                }
                npcCounter++;
            }
        }
    }

    public void checkIfHelicopterDied(PlayerHelicopter helicopter){
        if(helicopter.getDamageLevel() == 100 || helicopter.getFuelLevel() == 0)
        {
            this.lives--;
            if(this.lives == 0){
                printGameOverMessage();
            }
            gameObjects.remove(helicopter);
            gameObjects.add(new PlayerHelicopter(200,100,
                                    40, ColorUtil.BLACK));
        }
    }

    public void checkIfHelicopterDied(NonPlayerHelicopter npcHelicopter){
        if(npcHelicopter.getDamageLevel() == 100 )
        {
            gameObjects.remove(npcHelicopter);
            NonPlayerHelicopter npc = new NonPlayerHelicopter(
                    500,
                    500,
                    20);
            pickNewStrategy(npc);
            gameObjects.add(npc);
        }
    }

    public void printGameOverMessage(){
        System.out.println("Game over, better luck next time!");
        System.exit(0);
    }

    public void updateHelicopterColorOnDamageTaken(Helicopter heliHolder){
        int multiplierOfColor = heliHolder.getDamageLevel() / 25;
        multiplierOfColor = multiplierOfColor * 32; //used to find new
        // color
        int currentColor = ColorUtil.red(heliHolder.getColor());// all 3
        // have same value
        heliHolder.setColor(ColorUtil.rgb(
                currentColor + multiplierOfColor,
                currentColor + multiplierOfColor,
                currentColor + multiplierOfColor),
                heliHolder
        );
    }

    public void refuelingAtRefuelingBlimp(){// need to seperate this method
        System.out.println("Helicopter has reached a refueling blimp.");

        Vector<Double> firstBlimpCord = null; //set as a precaution
        Vector<Double> secondBlimpCord = null; //set as a precaution
        int blimpCount = 1;

        Helicopter heliHolder = getPlayerHelicopter();
        Vector<Double> helicopterLocation = heliHolder.getCoordinates();

        IIterator blimpIterator = gameObjects.getIterator();
        while(blimpIterator.hasNext()){
            GameObject currentObject = blimpIterator.getNext();
            if(currentObject instanceof RefuelingBlimp){
                switch (blimpCount){ //way to only have proper var modified
                    case 1:
                        firstBlimpCord = currentObject.getCoordinates();
                        break;
                    case 2:
                        secondBlimpCord = currentObject.getCoordinates();
                        break;
                }
                blimpCount++;
            }

        }
        int closestBlimpIndex = calculateClosestBlimpIndex(
                                            helicopterLocation,
                                            firstBlimpCord,
                                            secondBlimpCord);


        IIterator closetBlimpIterator = gameObjects.getIterator();
        while(closetBlimpIterator.hasNext()){
            GameObject currentObject = closetBlimpIterator.getNext();
            if(currentObject instanceof RefuelingBlimp){
                int addFuel;
                if(closestBlimpIndex == 1 &&
                        firstBlimpCord == currentObject.getCoordinates()){
                   addFuel = ((RefuelingBlimp)currentObject).getGasCapacity() +
                             heliHolder.getFuelLevel();
                    heliHolder.setFuelLevel(addFuel);
                    ((RefuelingBlimp) currentObject)
                            .setColor(ColorUtil.rgb(50, 205, 50),
                                    ((RefuelingBlimp) currentObject));
                    ((RefuelingBlimp) currentObject).setGasCapacity(0);
                    gameObjects.remove(currentObject);
                    gameObjects.add(new RefuelingBlimp());
                }
                else if(closestBlimpIndex == 2 &&
                secondBlimpCord == currentObject.getCoordinates()){
                    addFuel = ((RefuelingBlimp)currentObject).getGasCapacity() +
                            heliHolder.getFuelLevel();
                    heliHolder.setFuelLevel(addFuel);
                    ((RefuelingBlimp) currentObject)
                            .setColor(ColorUtil.rgb(50, 205, 50),
                                    ((RefuelingBlimp) currentObject));
                    ((RefuelingBlimp) currentObject).setGasCapacity(0);
                    gameObjects.remove(currentObject);
                    gameObjects.add(new RefuelingBlimp());
                }
            }

        }
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
            return 1;
        }
        else{
            return 2;
        }
    }

    public void birdHitHelicopter(){
        System.out.println("Bird has hit Helicopter.");

        PlayerHelicopter playerHelicopter = getPlayerHelicopter();
        playerHelicopter.setDamageLevel(15);
        updateHelicopterColorOnDamageTaken(playerHelicopter);
        checkIfHelicopterDied(playerHelicopter);
    }

    public void gameClockHasTicked(){
        IIterator objectIterator = gameObjects.getIterator();
        while(objectIterator.hasNext()){
            GameObject currentObject = objectIterator.getNext();
            if(currentObject instanceof PlayerHelicopter) {
                PlayerHelicopter heliHolder = (PlayerHelicopter) currentObject;
                if(this.clockTime % 50 == 0)
                    heliHolder.setFuelLevel();
                heliHolder.move();
                checkIfHelicopterDied(heliHolder);
            }else if (currentObject instanceof Bird) {
                ((Bird) currentObject).move();
            }
            else if(currentObject instanceof NonPlayerHelicopter){
                NonPlayerHelicopter npcHolder =
                                (NonPlayerHelicopter) currentObject;
                if(this.clockTime % 50 == 0)
                    npcHolder.setFuelLevel();
                npcHolder.move();
                npcHolder.enactStrategy();

                if(npcHolder.getFuelLevel() <= 5)
                    npcHolder.resetFuel();
                checkIfHelicopterDied(npcHolder);
            }
        }
        this.clockTime++;
    }


    public int getLives(){
        return this.lives;
    }

    public GameWorldCollection getGameObjects() {
        return gameObjects;
    }

    public int getLastSkyScrapperHit(){
        IIterator helicopterIterator = gameObjects.getIterator();
        while(helicopterIterator.hasNext()){
            GameObject currentObject = helicopterIterator.getNext();
            if(currentObject instanceof PlayerHelicopter) {
                Helicopter heliHolder = (Helicopter) currentObject;
                return heliHolder.getLastSkyScraperReached();
            }
        }
        return 0;
    }

    public int getDamageLevel(){
        PlayerHelicopter playerHelicopter = getPlayerHelicopter();
        return playerHelicopter.getDamageLevel();
    }

    public int getHeading(){
        PlayerHelicopter playerHelicopter = getPlayerHelicopter();
        return playerHelicopter.getHeading();
    }

    public int getFuelLevel(){
        IIterator helicopterIterator = gameObjects.getIterator();
        while(helicopterIterator.hasNext()){
            GameObject currentObject = helicopterIterator.getNext();
            if(currentObject instanceof PlayerHelicopter) {
                Helicopter heliHolder = (Helicopter) currentObject;
                return heliHolder.getFuelLevel();
            }
        }
        return 0;
    }

    public void showMap(){
        System.out.println("Here is the current map of the world:");
        IIterator objectIterator = gameObjects.getIterator();
        while(objectIterator.hasNext()){
            GameObject currentObject = objectIterator.getNext();
            System.out.println(currentObject.toString());
        }
    }

    public void skyScraperHit(int num) {
        System.out.println("Sky scrapper #" + num + " has " +
                "been hit");

        PlayerHelicopter heliHolder = getPlayerHelicopter();
        if (num == heliHolder.getLastSkyScraperReached() + 1) {
            heliHolder.setSkyScraperReached();
            System.out.println("New checkpoint reached");
            updateSkyScrapperTextColor(num);
        } else if (num <= heliHolder.getLastSkyScraperReached() + 1) {
            System.out.println("You already hit this sky scrapper.");
        } else {
            System.out.println("Sky scrappers were hit out of order");
        }

        checkIfPlayerWon();
    }

    public void updateSkyScrapperTextColor(int skyScraperHit){
        IIterator objectIterator = gameObjects.getIterator();
        while(objectIterator.hasNext()){
            GameObject currentObject = objectIterator.getNext();
            if (currentObject instanceof SkyScrapper &&
                    ((SkyScrapper) currentObject).getSequenceNumber() ==
                                                        skyScraperHit - 1) {
                ((SkyScrapper) currentObject).setSequenceNumberTextColor();
            }
        }
    }

    public void checkIfPlayerWon(){
        int skyScraperAmount = 0;
        int lastSkyScraperHit = 0;

        IIterator objectIterator = gameObjects.getIterator();
        while(objectIterator.hasNext()){
            GameObject currentObject = objectIterator.getNext();
            if(currentObject instanceof PlayerHelicopter) {
                Helicopter heliHolder = (Helicopter) currentObject;
                lastSkyScraperHit = heliHolder.getLastSkyScraperReached();
            }else if (currentObject instanceof SkyScrapper)
                skyScraperAmount++;
        }
        if(lastSkyScraperHit == skyScraperAmount){
            printGameWonMessage();
        }
    }

    public void printGameWonMessage(){
        System.out.println("Game over, You Won. Total Time " + this.clockTime +
                           "ms");
        Display.getInstance().exitApplication();
    }

}
