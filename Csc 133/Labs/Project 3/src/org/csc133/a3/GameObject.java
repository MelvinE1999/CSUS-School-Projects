package org.csc133.a3;

import java.util.ArrayList;
import java.util.Vector;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public abstract class GameObject implements ICollider{
    private final Vector<Double> coordinates;
    private int color;
    private int size;
    private static GameWorld gw;

    private ArrayList<GameObject> collisions;

    private static final int WORLD_WIDTH = 1020; //to give the bord dimensions
    private static final int WORLD_HEIGHT = 440; //gives bord dimensions

    private static MapView mapView;
    public GameObject(int color){
        this.color = color;
        Random randomCoordinates = new Random(); // used to set object cords
                              //due to bird and blimp being randomly spawned
        this.coordinates = new Vector<>();
        this.coordinates.setSize(2);
        this.coordinates.set(0, randomCoordinates.nextDouble() * WORLD_WIDTH);
        this.coordinates.set(1, randomCoordinates.nextDouble() * WORLD_HEIGHT);
        collisions = new ArrayList<GameObject>();
    }

    public GameObject(int color, int size){
        this.color = color;
        this.size = size;
        this.coordinates = new Vector<>();
        this.coordinates.setSize(2);
        collisions = new ArrayList<GameObject>();
    }

    public void addCollision(GameObject collidedWith){
        if(collisions.contains(collidedWith)) return;

        collisions.add(collidedWith);
    }

    public boolean HasItAlreadyCollided(GameObject objectToCheck){
        return collisions.contains(objectToCheck);
    }

    public ArrayList<GameObject> getCollisions(){
        return collisions;
    }

    public boolean isCollisionsEmpty(){
        return collisions.isEmpty();
    }

    public static void setGw(GameWorld newGw){
        gw = newGw;
    }

    public GameWorld getGw(){
        return gw;
    }

    public static  void setMapView(MapView mapViewParameter){
        mapView = mapViewParameter;
    }

    public int getMapViewYOrigin(){
        return mapView.getY();
    }

    public int getMapViewHeight(){
        return mapView.getMapHeight();
    }

    public int getMapViewLength(){
        return mapView.getMapLength();
    }

    public int getSize(){
        return this.size;
    }

    protected void setSize(int size){
        this.size = size;
    }

    public int getColor(){
        return this.color;
    }

    //GameObject is added as param so only certain classes can change color
    protected void setColor(int pickedColor, GameObject checker){
        if(checker instanceof Helicopter || checker instanceof RefuelingBlimp) {
            this.color = pickedColor;
        }
    }

    public Vector<Double> getCoordinates() {
        return this.coordinates;
    }

    protected void  setCoordinates(double pickedX, double pickedY){
        this.coordinates.set(0, pickedX);
        this.coordinates.set(1, pickedY);
    }

    public String toStringForGetColor(){
        return "[" + ColorUtil.red(this.getColor()) + "," +
                ColorUtil.green(this.getColor()) + "," +
                ColorUtil.blue(this.getColor()) + "]";
    }

    @Override
    public boolean collidesWith(GameObject otherObject) {
        boolean result = false;
        double thisCenterX = this.coordinates.elementAt(0) +
                            (otherObject.size/2);
        double thisCenterY = this.coordinates.elementAt(1) +
                            (otherObject.size/2);
        int thisRadius = this.getSize()/2;

        double otherCenterX = otherObject.coordinates.elementAt(0);
        double otherCenterY = otherObject.coordinates.elementAt(1);
        int otherRadius = otherObject.getSize()/2;

        double dx = thisCenterX - otherCenterX;
        double dy = thisCenterY - otherCenterY;

        double distanceBetweenCentersSquared = (Math.pow(dx, 2) +
                                                Math.pow(dy, 2));
        int radiusSquared = (thisRadius * thisRadius +
                      2 * thisRadius * otherRadius + otherRadius * otherRadius);

        if(distanceBetweenCentersSquared <= radiusSquared)
            result = true;

        return result;
    }

    public void handleCollision(GameObject otherObject){}
    public void handleCollision(PlayerHelicopter helicopter){}
    public void handleCollision(NonPlayerHelicopter nonPlayerHelicopter){}
}
