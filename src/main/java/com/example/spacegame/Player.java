package com.example.spacegame;

public class Player extends Shootables{
    public int playerSize;
    public int playerSpeed;
    public int health;

    public Player(int posX, int poxY) {
        super(10, 10, posX, poxY, 5, 5);
        this.playerSize = 10;
        this.playerSpeed = 5;
    }

    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }


}
