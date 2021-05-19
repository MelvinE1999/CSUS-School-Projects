package org.csc133.a2;

import java.util.Vector;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public abstract class GameObject {
    private Vector<Double> coordinates;
    private int color;
    private int size;

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
    }

    public GameObject(int color, int size){
        this.color = color;
        this.size = size;
        this.coordinates = new Vector<>();
        this.coordinates.setSize(2);
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
}
