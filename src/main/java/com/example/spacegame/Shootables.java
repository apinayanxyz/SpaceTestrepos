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

    private int direction;
    private float timeSeconds = 0;
    public Shootables(int sizeX, int sizeY, int posX, int poxY, int speedX, int speedY, int direction) {
        super(sizeX, sizeY, posX, poxY, speedX, speedY);
        this.direction = direction;
        cooldown.setCycleCount(3);
        cooldown.getKeyFrames().add(
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
    }

    public void Fire(int direction){
        if (!fired){
            MainGame.AddProjectile();
            fired=true;
            cooldown.playFromStart();
        }

    }
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
}
