package com.example.spacegame;

import javafx.scene.image.Image;

public class ShootingEnemies extends Shootables{

    private boolean isAlive;
    public boolean isAlive() {return isAlive;}
    public ShootingEnemies(int posX, int poxY) {
        super(80, 80, posX, poxY, 1, 1, 1);
        super.setDown(true);
        super.setImage(new Image("file:Images/ShootingEnemies.png"));
        super.setFiring(true);
        isAlive = true;
        super.setBulletSpawnX(posX + 80/2);
    }

    public int ifGone(int destroyPlus){
        if (getPosY()>MainGame.GAMEHEIGHT+getSizeY()) {
            MainGame.RemoveItem(super.getEntity());
            isAlive = false;
            super.setFiring(false);
            return 1;
        }
        return 0;
    }
    public void hit(Projectile projectile){
        MainGame.RemoveItem(super.getEntity());
        isAlive=false;
        super.setFiring(false);
    }

    @Override
    public void update(){
        super.setBulletSpawnX(getPosX() + 35);
        if (isAlive){
            setFiring(true);
        }
        super.Update();
    }
}
