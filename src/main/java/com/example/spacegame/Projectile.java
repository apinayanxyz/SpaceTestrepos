package com.example.spacegame;

public class Projectile extends BaseEntity{
    public int direction;

    public Projectile(int posX, int poxY, int direction) {
        super(ProjectileStats.size, ProjectileStats.size, posX, poxY, ProjectileStats.speed, ProjectileStats.speed);
        this.direction = direction;
    }

    @Override
    public void update(){
        Mechanics.moveX(this ,super.getPosX(),super.getSpeedX()*direction);
        super.update();
    }
}