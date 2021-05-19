package org.csc133.a2;

import com.codename1.util.MathUtil;

import java.util.Vector;

//Goal: hit all all of the skyScrappers in order before the player
public class ObjectiveStrategy implements IStrategy{
    private SkyScrapper skyScrapper;
    private NonPlayerHelicopter nonPlayerHelicopter;

    public ObjectiveStrategy(GameWorldCollection gameObjects,
                             NonPlayerHelicopter npc){
        nonPlayerHelicopter = npc;
        IIterator skyScrapperIterator = gameObjects.getIterator();
        while (skyScrapperIterator.hasNext()){
            GameObject currentObject = skyScrapperIterator.getNext();
            if(currentObject instanceof SkyScrapper){
                if(((SkyScrapper)currentObject).getSequenceNumber() ==
                        nonPlayerHelicopter.getLastSkyScraperReached() - 1) {
                    skyScrapper = (SkyScrapper) currentObject;
                    break;
                }
            }
        }
    }

    @Override
    public void invokeStrategy() {
        Vector<Double> skyScrapperCoordinates = skyScrapper.getCoordinates();
        Vector<Double> nonPlayerHelicopterCoordinates =
                            nonPlayerHelicopter.getCoordinates();
        double xCoordDifference = skyScrapperCoordinates.elementAt(0)
                            - nonPlayerHelicopterCoordinates.elementAt(0);
        double yCoordDifference = skyScrapperCoordinates.elementAt(1)
                            - nonPlayerHelicopterCoordinates.elementAt(1);
        int angleOfDifference = (int) (90 - Math.toDegrees(MathUtil.atan2(
                xCoordDifference, yCoordDifference)));
        nonPlayerHelicopter.setHeading(90 - angleOfDifference);

        if(nonPlayerHelicopter.getSpeed() <
                nonPlayerHelicopter.getCurrentMaxSpeed() &&
                xCoordDifference >= 0 && yCoordDifference >= 0) {
            nonPlayerHelicopter.increaseSpeed();
        }
        else if(xCoordDifference <= 0 && yCoordDifference <= 0){
            while (nonPlayerHelicopter.getSpeed() != 0)
                nonPlayerHelicopter.decreaseSpeed();
        }
    }
}
