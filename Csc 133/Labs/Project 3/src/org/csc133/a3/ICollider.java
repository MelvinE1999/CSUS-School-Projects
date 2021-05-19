package org.csc133.a3;

public interface ICollider {
    boolean collidesWith(GameObject otherObject);

    void handleCollision(GameObject otherObject);
    void handleCollision(PlayerHelicopter helicopter);
    void handleCollision(NonPlayerHelicopter nonPlayerHelicopter);
}
