package com.example.spacegame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Shootables extends BaseEntity{

    private boolean fired;
    private boolean firing;
    private Timeline cooldown = new Timeline();

    private int bulletSpawnX;

    public void setBulletSpawnX(int bulletSpawnX) {
        this.bulletSpawnX = bulletSpawnX;
    }
    public int getBulletSpawnX() {
        return bulletSpawnX;
    }
    private int direction;
    private float timeSeconds = 0;
    /*
     * Main Constructor used for items that can shoot
     * creates a cooldown
     * */
    public Shootables(int sizeX, int sizeY, int posX, int poxY, int speedX, int speedY, int direction) {
        super(sizeX, sizeY, posX, poxY, speedX, speedY);
        this.direction = direction;
        Timeline cooldown1 = new Timeline();
        cooldown1.setCycleCount(3);
        cooldown1.getKeyFrames().add(
                new KeyFrame(Duration.seconds(ProjectileStats.getFireCooldown()),
                        new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                timeSeconds++;
                                if (timeSeconds >= 1) {
                                    timeSeconds = 0;
                                    fired=false;
                                }
                            }
                        }));
        cooldown=cooldown1;
    }


    /*
    * creates a projetile if not on cooldown
     */
    public void Fire(int direction){
        if (!fired){
            MainGame.AddProjectile(direction,bulletSpawnX,getPosY());
            fired=true;
            cooldown.playFromStart();
        }

    }

    /*
    * Fires projectile as well as normal movemnt updates
    * */
    public void Update(){
        if (firing) {
            Fire(direction);
        }
        super.update();
    }

    public boolean isFiring() {
        return firing;
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }
    //Shoot

    /*
    * updates timer with new value for cooldown
    * */
    public void updateTimer(){
        Timeline cooldown1 = new Timeline();
        cooldown1.setCycleCount(3);
        cooldown1.getKeyFrames().add(
                new KeyFrame(Duration.seconds(ProjectileStats.getFireCooldown()),
                        new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                timeSeconds++;
                                if (timeSeconds >= 1) {
                                    timeSeconds = 0;
                                    fired=false;
                                }
                            }
                        }));
        cooldown=cooldown1;
    }
}
