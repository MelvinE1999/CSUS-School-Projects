package org.csc133.a3;

import com.codename1.util.MathUtil;

import java.util.Vector;

//Goal: make the npc helicopter circle around and defend the refueling blimp
//      basically with the intention of slowly strangling competition even at
//      the loss of there helicopter
//      mentality is if they can't win then neither will the player
public class ResourceStarveStrategy implements IStrategy{
    private RefuelingBlimp refuelingBlimp;
    private final NonPlayerHelicopter nonPlayerHelicopter;

    public ResourceStarveStrategy(GameWorldCollection gameObjects,
                                  NonPlayerHelicopter npc){
        nonPlayerHelicopter = npc;
        IIterator refuelingBlimpIterator = gameObjects.getIterator();
        while (refuelingBlimpIterator.hasNext()){
            GameObject currentObject = refuelingBlimpIterator.getNext();
            if(currentObject instanceof RefuelingBlimp){
                refuelingBlimp = (RefuelingBlimp) currentObject;
                break;
            }
        }
    }

    @Override
    public void invokeStrategy() {
        Vector<Double> refuelingBlimpCoordinates =
                            refuelingBlimp.getCoordinates();
        Vector<Double> nonPlayerHelicopterCoordinates =
                            nonPlayerHelicopter.getCoordinates();
        double xCoordDifference = refuelingBlimpCoordinates.elementAt(0)
                            - nonPlayerHelicopterCoordinates.elementAt(0);
        double yCoordDifference = refuelingBlimpCoordinates.elementAt(1)
                            - nonPlayerHelicopterCoordinates.elementAt(1);
        int angleOfDifference = (int) (90 - Math.toDegrees(MathUtil.atan2(
                            xCoordDifference, yCoordDifference)));
        nonPlayerHelicopter.setHeading(nonPlayerHelicopter.getStickAngle() +
                            (90 - angleOfDifference));
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
