package com.example.spacegame;

import javafx.scene.image.Image;

import java.util.Random;

public class PowerUp extends BaseEntity{

    private int type;
    public PowerUp(int posX, int poxY) {
        super(30, 30, posX, poxY, 3, 3);
        super.setDown(true);
        Random rand = new Random();
        type = rand.nextInt(5) + 1;
        addImages();
    }

    public void setType(int type) {
        this.type = type;
    }

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
