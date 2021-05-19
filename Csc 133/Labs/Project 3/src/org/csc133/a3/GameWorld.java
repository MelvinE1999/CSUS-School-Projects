package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class GameWorld{


    private int clockTime;
    private int lives;
    private GameWorldCollection gameObjects;

    private static int gameHeight;
    private static  int gameWidth;
    private int amountOfSkyScrapers;
    private boolean playSound;

    private Sound helicopterCollisionSound;
    private Sound refuelingBlimpCollisionSound;
    private Sound lostLifeSound;
    private BGSound sound_BG;


    public void init(){
        this.clockTime = 0;
        this.lives = 3;
        this.gameObjects = new GameWorldCollection();
        addGameObjectsToGameWorld(this.gameObjects);
        IIterator skyScrapperIterator = gameObjects.getIterator();
        GameObject currentObject = skyScrapperIterator.getNext();
        ((SkyScrapper)currentObject).setSequenceNumberTextColor();
        this.playSound = true;
    }

    public void create(){
        sound_BG = new BGSound("wind.wav");
        helicopterCollisionSound = new Sound("collision.wav");
        refuelingBlimpCollisionSound = new Sound("VOLTS.wav");
        lostLifeSound = new Sound("lostLife.wav");
    }

    public boolean getSoundToggle(){
        return this.playSound;
    }

    public void setSoundToggle(boolean toggle){
        this.playSound = toggle;
    }

    public void pauseBgSound(){
        sound_BG.pause();
    }

    public void playBgSound(){
        sound_BG.playSound();
    }

    public void playCollisionSound(){
        helicopterCollisionSound.playSound();
    }

    public void playRefuelingSound(){
        refuelingBlimpCollisionSound.playSound();
    }

    public void playLostLifeSound(){
        lostLifeSound.playSound();
    }

    private void addGameObjectsToGameWorld(GameWorldCollection gameObjects) {
        int[] xCordForSkyScrapers = {200, 900, 400, 700, 100};
        int[] yCordForSkyScrapers = {100, 400, 200, 300, 375};
        int[] cordForNpcHelicopters = {500, 200, 50};
        amountOfSkyScrapers = 5;
        for(int i = 0; i < amountOfSkyScrapers; i++ ) {
            gameObjects.add(new SkyScrapper(i, xCordForSkyScrapers[i],
                            yCordForSkyScrapers[i]));
        }
        for(int blimpMade = 0; blimpMade < 2; blimpMade++){
            gameObjects.add(new RefuelingBlimp());
        }
        gameObjects.add(PlayerHelicopter.getInstanceofPlayer(
                xCordForSkyScrapers[0],
                yCordForSkyScrapers[0],
                30,
                ColorUtil.BLACK));
        for(int j = 0; j < 3; j++) {
            NonPlayerHelicopter npc = NonPlayerHelicopter.addNpcHelicopter(
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


    public void pickNewStrategy(NonPlayerHelicopter npc){
        Random random = new Random();
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

    public void checkIfHelicopterDied(PlayerHelicopter helicopter){
        if(helicopter.getDamageLevel() == 100 || helicopter.getFuelLevel() == 0)
        {
            this.lives--;
            if(this.lives == 0){
                printGameOverMessage();
            }
            if (getSoundToggle())
                playLostLifeSound();

            helicopter.playerLostLife();
        }
    }

    public void checkIfHelicopterDied(NonPlayerHelicopter npcHelicopter){
        if(npcHelicopter.getDamageLevel() == 100 )
        {
            npcHelicopter.npcLostLife();
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

    public void gameClockHasTicked(){
        IIterator objectIterator = gameObjects.getIterator();
        while(objectIterator.hasNext()){
            GameObject currentObject = objectIterator.getNext();
            if(currentObject instanceof PlayerHelicopter) {
                PlayerHelicopter heliHolder = (PlayerHelicopter) currentObject;
                if(this.clockTime % 50 == 0)
                    heliHolder.setFuelLevel();
                heliHolder.move();
            }else if (currentObject instanceof Bird) {
                ((Bird) currentObject).move();
            }
            else if(currentObject instanceof NonPlayerHelicopter){
                NonPlayerHelicopter npcHolder =
                                (NonPlayerHelicopter) currentObject;
                if(this.clockTime % 50 == 0)
                    npcHolder.setFuelLevel();
                npcHolder.enactStrategy();
                npcHolder.move();


                if(npcHolder.getFuelLevel() <= 5)
                    npcHolder.resetFuel();
            }
        }
        checkCollision();
        if(this.getSoundToggle())
            sound_BG.playSound();

        removeNonCollidingObjectsFromCollision();
        this.clockTime++;
    }

    public void checkCollision(){
        IIterator object1Iterator = gameObjects.getIterator();
        while (object1Iterator.hasNext()){
            GameObject object1Holder = object1Iterator.getNext();
            IIterator object2Iterator = gameObjects.getIterator();
            while(object2Iterator.hasNext()){
                GameObject object2Holder = object2Iterator.getNext();
                if(object1Holder == object2Holder) continue;

                if(!(object1Holder.collidesWith(object2Holder))) continue;

                if(object2Holder instanceof PlayerHelicopter) {
                    object1Holder.handleCollision((PlayerHelicopter)
                            object2Holder);
                }
                else if(object2Holder instanceof NonPlayerHelicopter) {
                    object1Holder.handleCollision((NonPlayerHelicopter)
                            object2Holder);
                }
          
                object1Holder.handleCollision(object2Holder);

            }
        }
    }

    public void removeNonCollidingObjectsFromCollision(){
        IIterator objectIterator = gameObjects.getIterator();
        while(objectIterator.hasNext()){
            GameObject currentObject = objectIterator.getNext();

            ArrayList<GameObject> collisions = currentObject.getCollisions();
            for(Iterator<GameObject> collisionChecker = collisions.iterator();
                                                collisionChecker.hasNext();){
                GameObject objectBeingChecked = collisionChecker.next();
                if(currentObject.collidesWith(objectBeingChecked)) continue;
                collisionChecker.remove();
            }
        }
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

    public void checkIfThereIsWinner(Helicopter helicopter){
        int lastSkyScraperHit = helicopter.getLastSkyScraperReached();

        if(lastSkyScraperHit != amountOfSkyScrapers){
            return;
        }

        if (helicopter instanceof PlayerHelicopter)
            printGameWonMessage("player");
    }

    public void printGameWonMessage(String winner){
        if(winner.equals("player"))
            System.out.println("Game over, You Won. Total Time " +
                    this.clockTime + "ms");
        else
            System.out.println("Game over, You Lose. Npc hit the last " +
                    "skyScrapper before you.");

        Display.getInstance().exitApplication();
    }

}
