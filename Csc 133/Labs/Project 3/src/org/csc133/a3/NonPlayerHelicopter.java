package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.Vector;

public class NonPlayerHelicopter extends Helicopter implements IDrawable{


    private IStrategy currentStrategy;
    private double originalXCord;
    private double originalYCord;
    private static int amountOfNpcInWorld = 0;

    private NonPlayerHelicopter(double xCord, double yCord, int heliMaximumSpeed)
    {
        super(xCord, yCord, heliMaximumSpeed, ColorUtil.CYAN);
        originalXCord = xCord;
        originalYCord = yCord;
    }

    public static NonPlayerHelicopter addNpcHelicopter(double xCord,
                                                       double yCord,
                                                       int heliMaximumSpeed){
        if(amountOfNpcInWorld < 3){
            if(amountOfNpcInWorld == 0)
                amountOfNpcInWorld = 1;
            else
                amountOfNpcInWorld++;
            NonPlayerHelicopter npcHolder = new NonPlayerHelicopter(xCord,yCord,
                    heliMaximumSpeed);
            return npcHolder;
        }
        return null;
    }

    public void npcLostLife(){
        super.npcLostLife(originalXCord, originalYCord);
    }

    public void resetFuel(){
        if(super.getFuelLevel() <= 5)
            super.setFuelLevel(50);
    }

    public void setStrategy(IStrategy newStrategy){
        currentStrategy = newStrategy;
    }

    public String getStrategy(){
        if(currentStrategy instanceof AttackStrategy)
            return "Attack Strategy";
        else if(currentStrategy instanceof ResourceStarveStrategy)
            return "Resource Starve Strategy";
        else
            return "Objective Strategy";
    }

    public void enactStrategy (){
        currentStrategy.invokeStrategy();
    }

    @Override
    public void draw(Graphics g, Point point) {
        Vector<Double> coordinates = getCoordinates();
        try {
            int xCord = (int)(coordinates.get(0) * 1);
            int yCord = (int)(coordinates.get(1) * 1);
            g.drawImage(Image.createImage("/NPC.png"),
                    xCord - getSize(),
                    yCord + (getMapViewYOrigin() - getSize()),
                    getSize() + 20,
                    getSize());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString(){
        Vector<Double> coordinates = super.getCoordinates();
        double xValue = coordinates.elementAt(0);
        double yValue = coordinates.elementAt(1);

        return "NPC Helicopter: " +
                "loc=" + (Math.round(xValue * 10.0) / 10.0) + "," +
                (Math.round(yValue * 10.0) / 10.0) +
                " color=" + super.toStringForGetColor() +
                "heading=" + super.getHeading() +
                " speed=" + super.getSpeed() +
                " size=" + super.getSize() +
                " maxSpeed=" + this.getCurrentMaxSpeed() +
                " fuelLevel=" + this.getFuelLevel() +
                " damageLevel=" + this.getDamageLevel() +
                " strategy=" + getStrategy();
    }

    @Override
    public void handleCollision(PlayerHelicopter helicopter) {
        if(HasItAlreadyCollided(helicopter)) return;
        GameWorld currentWorld = super.getGw();

        if(currentWorld.getSoundToggle())
            currentWorld.playCollisionSound();

        this.setDamageLevel(50);
         helicopter.setDamageLevel(50);

        currentWorld.updateHelicopterColorOnDamageTaken(this);
        currentWorld.updateHelicopterColorOnDamageTaken(
                                                 helicopter);

        addCollision(helicopter);
        helicopter.addCollision(this);
        currentWorld.checkIfHelicopterDied(this);

        currentWorld.checkIfHelicopterDied(helicopter);

    }

    @Override
    public void handleCollision(NonPlayerHelicopter nonPlayerHelicopter) {
        if(HasItAlreadyCollided(nonPlayerHelicopter)) return;
        GameWorld currentWorld = super.getGw();

        if(currentWorld.getSoundToggle())
            currentWorld.playCollisionSound();

        this.setDamageLevel(50);
        nonPlayerHelicopter.setDamageLevel(50);

        currentWorld.updateHelicopterColorOnDamageTaken(this);
        currentWorld.updateHelicopterColorOnDamageTaken(
                nonPlayerHelicopter);

        addCollision(nonPlayerHelicopter);
        nonPlayerHelicopter.addCollision(this);
        currentWorld.checkIfHelicopterDied(this);


        //needed to fix collision error
        currentWorld.checkIfHelicopterDied(nonPlayerHelicopter);

    }
}
