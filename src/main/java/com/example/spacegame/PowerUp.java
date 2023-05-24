package com.example.spacegame;

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

    private void addImages() {
        switch (type) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }
}
