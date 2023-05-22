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
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

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
        moveAll();
        Entity.setX(posX);
        Entity.setY(posY);
    }

    public void moveAll(){
        if (isUp()) {
            moveY(-getSpeedY());
        }
        else if (isDown()) {
            moveY(+getSpeedY());
        }
        if (isLeft()) {
            moveX(-getSpeedX());
        }
        else if (isRight()) {
            moveX(+getSpeedX());
        }
    }
    public void moveX(int speed){
        posX = posX+ speed;
        setPosX(posX);
    }
    public void moveY(int speed){
        posY = posY + speed;
        setPosY(posY);
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

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
}
