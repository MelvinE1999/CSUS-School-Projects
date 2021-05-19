package org.csc133.a2;

import java.util.ArrayList;

public class GameWorldCollection implements ICollection{
    private ArrayList<GameObject> gameObjects;

    public GameWorldCollection(){
        gameObjects = new ArrayList<GameObject>();
    }


    @Override
    public void add(GameObject object) {
        gameObjects.add(object);
    }

    @Override
    public void remove(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    @Override
    public IIterator getIterator() {
        return new GameObjectIterator();
    }

    private class GameObjectIterator implements  IIterator{

        private int index;

        public GameObjectIterator(){
            this.index = -1; // -1 instead of 0 to cut out a method
        }

        @Override
        public boolean hasNext() {
            if(index == gameObjects.size() - 1)
                return false;

            return true;
        }

        @Override
        public GameObject getNext() {
            index++;
            return getCurrent();
        }

        @Override
        public GameObject getCurrent() {
            return gameObjects.get(index);
        }
    }
}
