package org.csc133.a3;

public interface ICollection {

    void add(GameObject object);

    void remove(GameObject object);

    IIterator getIterator();
}
