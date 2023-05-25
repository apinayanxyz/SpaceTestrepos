package com.example.spacegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.WHITE;

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

    /*
    * Main constructor used for all entities
    * */
    public BaseEntity(int sizeX, int sizeY, int posX, int poxY, int speedX, int speedY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posX = posX;
        this.posY = poxY;
        this.speedX = speedX;
        this.speedY = speedY;
        Entity = new Rectangle(posX, poxY, sizeY, sizeX);
        Entity.setFill(WHITE);
        MainGame.AddItem(getEntity());
    }

    /*
    * Method to be used in main gameloop
    * updates the position of the entity
    * */
    public void update(){
        moveAll();
        Entity.setX(posX);
        Entity.setY(posY);
    }

    /*
     * Moves entity based on boolean values
     *  */
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

    /*
    * Moves the entities X position
    * */
    public void moveX(int speed){
        posX = posX+ speed;
        setPosX(posX);
    }
    /*
     * Moves the entities Y position
     * */
    public void moveY(int speed){
        posY = posY + speed;
        setPosY(posY);
    }

    /*
    * Sets image of entity
    * */
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
    /*
    * Getter for Left variable
    * */
    public boolean isLeft() {
        return left;
    }
    /*
     * Setter for Left variable
     * */
    public void setLeft(boolean left) {
        this.left = left;
    }
    /*
     * Getter for Right variable
     * */
    public boolean isRight() {
        return right;
    }
    /*
     * Setter for Right variable
     * */
    public void setRight(boolean right) {
        this.right = right;
    }
    /*
     * Getter for Up variable
     * */
    public boolean isUp() {
        return up;
    }
    /*
     * Setter for Up variable
     * */
    public void setUp(boolean up) {
        this.up = up;
    }
    /*
     * Getter for Down variable
     * */
    public boolean isDown() {
        return down;
    }
    /*
     * Setter for Down variable
     * */
    public void setDown(boolean down) {
        this.down = down;
    }
    /*
     * Getter for sizeX variable
     * */
    public int getSizeX() {
        return sizeX;
    }
    /*
     * Setter for sizeX variable
     * */
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }
    /*
     * Getter for sizeY variable
     * */
    public int getSizeY() {
        return sizeY;
    }
    /*
     * Setter for sizeY variable
     * */
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
}
