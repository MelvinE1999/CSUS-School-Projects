package org.csc133.a2;

public interface ICollection {

    public void add(GameObject object);

    public void remove(GameObject object);

    IIterator getIterator();
}
