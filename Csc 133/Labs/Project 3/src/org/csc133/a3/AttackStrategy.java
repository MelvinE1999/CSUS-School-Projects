package org.csc133.a3;


import com.codename1.util.MathUtil;

import java.util.Vector;

//Goal: To kill the player
public class AttackStrategy implements IStrategy{

    private Helicopter playerHelicopter;
    private final NonPlayerHelicopter nonPlayerHelicopter;

    public AttackStrategy(GameWorldCollection gameObjects,
                          NonPlayerHelicopter npc){
        nonPlayerHelicopter = npc;
        IIterator helicopterIterator = gameObjects.getIterator();
        while (helicopterIterator.hasNext()){
            GameObject currentObject = helicopterIterator.getNext();
            if(currentObject instanceof Helicopter &&
                    !(currentObject instanceof NonPlayerHelicopter)){
                playerHelicopter = (Helicopter) currentObject;
                break;
            }
        }
    }

    @Override
    public void invokeStrategy() {
        Vector<Double> playerHelicopterCoordinates =
                                        playerHelicopter.getCoordinates();
        Vector<Double> nonPlayerHelicopterCoordinates =
                                        nonPlayerHelicopter.getCoordinates();

        double xCoordDifference = playerHelicopterCoordinates.elementAt(0)
                            - nonPlayerHelicopterCoordinates.elementAt(0);

        double yCoordDifference = playerHelicopterCoordinates.elementAt(1)
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
