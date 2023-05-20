package com.example.spacegame;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BaseEntity {
    private Rectangle Entity;
    private int sizeX;
    private int sizeY;
    private int posX;
    private int posY;
    private int speedX;
    private int speedY;

    public BaseEntity(int sizeX, int sizeY, int posX, int poxY, int speedX, int speedY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posX = posX;
        this.posY = poxY;
        this.speedX = speedX;
        this.speedY = speedY;
        Entity = new Rectangle(posX, poxY, sizeY, sizeX);
        MainGame.AddItem(getEntity());
    }

    //Sprite method
    //Disappear method

    //Move method

    public void update(){
        Entity.setX(posX);
        Entity.setY(posY);
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
    public int getPosY() {
        return posY;
    }
    /*
     * Sets the Y value of the item
     */
    public void setPosY(int posY) {
        this.posY = posY;
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
