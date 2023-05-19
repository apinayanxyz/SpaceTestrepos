package com.example.spacegame;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BaseEntity {
    private Rectangle Entity;
    private int sizeX;
    private int sizeY;
    private int posX;
    private int poxY;
    private int speedX;
    private int speedY;

    public BaseEntity(int sizeX, int sizeY, int posX, int poxY, int speedX, int speedY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posX = posX;
        this.poxY = poxY;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    //Sprite method
    //Disappear method

    //Move method

    public void update(){

    }

    public void setImage(Image image){
        Entity.setFill(new ImagePattern(image));
    }
    /*
     * Returns the hitbox(Rectangle) of the item
     */
    public Rectangle getEntity() {
        return Entity;
    }
    /*
     * Returns the X value of the item
     */
    public int getPosX() {
        return posX;
    }
    /*
     * Sets the X value of the item
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }
    /*
     * Returns the Y value of the item
     */
    public int getPoxY() {
        return poxY;
    }
    /*
     * Sets the Y value of the item
     */
    public void setPoxY(int poxY) {
        this.poxY = poxY;
    }
    /*
     * Returns the X speed value of the item
     */
    public int getSpeedX() {
        return speedX;
    }
    /*
     * Sets the Y speed value of the item
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
    /*
     * Returns the Y speed value of the item
     */
    public int getSpeedY() {
        return speedY;
    }
    /*
     * Sets the Y speed value of the item
     */
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
}
