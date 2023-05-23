package com.example.spacegame;

public class PowerUp extends BaseEntity{

    private int type;
    public PowerUp(int posX, int poxY) {
        super(30, 30, posX, poxY, 3, 3);
        super.setDown(true);
    }
}
