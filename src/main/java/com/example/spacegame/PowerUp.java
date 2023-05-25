package com.example.spacegame;

import javafx.scene.image.Image;

import java.util.Random;

public class PowerUp extends BaseEntity{

    private int type;
    private boolean obtained;

    /*
     * Main Constructor used for power ups
     * power ups are randomised on creation
     * */
    public PowerUp(int posX, int poxY) {
        super(30, 30, posX, poxY, 1, 1);
        super.setDown(true);
        obtained = false;
        Random rand = new Random();
        type = rand.nextInt(5) + 1;
        addImages();
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
    /*
     * Shows if power up is obtained
     * */
    public boolean isObtained() {
        return obtained;
    }
    /*
     * Changes if power up is obtained
     * */
    public void setObtained(boolean obtained) {
        this.obtained = obtained;
    }


    /*
    * Adds images based on what type of power up it is
    * */
    private void addImages() {
        switch (type) {
            case 1:
                super.setImage(new Image("file:Images/DoublePowerUp.png"));
                break;
            case 2:
                super.setImage(new Image("file:Images/TriplePoweUp.png"));
                break;
            case 3:
                super.setImage(new Image("file:Images/VerticalPowerUp.png"));
                break;
            case 4:
                super.setImage(new Image("file:Images/CoolDownPowerUp.png"));
                break;
            case 5:
                super.setImage(new Image("file:Images/ProjectileSpeed.png"));
                break;
        }
    }
}
