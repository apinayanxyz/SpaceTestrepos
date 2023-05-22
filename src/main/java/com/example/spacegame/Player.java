package com.example.spacegame;

public class Player extends Shootables{
    public int playerSize;
    public int playerSpeed;

    private int bulletSpawnX;
    public int health;

    public int getPlayerSize() {
        return playerSize;
    }

    public Player(int posX, int poxY) {
        super(50, 50, posX, poxY, 5, 5,-1);
        this.playerSize = 50;
        this.playerSpeed = 5;
        this.health = 30;
        bulletSpawnX = posX + playerSize/2;
    }
    public int getBulletSpawnX() {
        return bulletSpawnX;
    }

    @Override
    public void update(){
        bulletSpawnX = getPosX() + playerSize/2;
        super.Update();
    }

    public void hit(){
        health--;
    }
}
