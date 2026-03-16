package com.soa;

abstract class BaseEntity {
    float x;
    float y;
    float velocityX;
    float velocityY;
    boolean isDead = false;

    BaseEntity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void markDead() {
        isDead = true;
    }

    public void updatePosition() {
        this.x += velocityX;
        this.y += velocityY;
    }

}
