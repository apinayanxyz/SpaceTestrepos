package com.example.spacegame;

import javafx.scene.image.Image;

public class ShootingEnemies extends Shootables{

    private int bulletSpawnX;

    public ShootingEnemies(int posX, int poxY) {
        super(80, 80, posX, poxY, 1, 1, 1);
        super.setDown(true);
        super.setImage(new Image("file:Images/Player.png"));
        bulletSpawnX = posX + 80/2;
    }
    public int getBulletSpawnX() {
        return bulletSpawnX;
    }
}
