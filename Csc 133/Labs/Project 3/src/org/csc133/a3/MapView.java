package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;

public class MapView extends Container {
    private static int height;
    private static int length;
    private Point mapOrigin;
    private final GameWorld gw;

    public MapView(GameWorld gameWorld){
        gw = gameWorld;
        Container mapView = new Container(new BorderLayout());
        mapOrigin = new Point(this.getX(), this.getY());
        MapView.height = this.getHeight();
        MapView.length = this.getWidth();
        this.getAllStyles().setBgColor(ColorUtil.WHITE);
        this.getAllStyles().setBgTransparency(255);
    }

    public void setMapOrigin(Point origin){
        mapOrigin = origin;
    }

    public static void setMapHeight(int newHeight){
        height = newHeight;
    }

    public static void setMapLength(int newLength){
        length = newLength;
    }

    public int getMapHeight(){
        return height;
    }

    public int getMapLength(){
        return length;
    }

    public void update(){
        this.repaint();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Point point = mapOrigin;
        IIterator objectIterator = gw.getGameObjects().getIterator();
        while(objectIterator.hasNext()){
            GameObject currentObject = objectIterator.getNext();
            if(currentObject instanceof IDrawable) {
                ((IDrawable) currentObject).draw(g, point);
            }
    }

}}
