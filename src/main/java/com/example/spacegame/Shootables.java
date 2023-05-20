package com.example.spacegame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Shootables extends BaseEntity{

    private boolean fired;
    private Timeline cooldown = new Timeline();
    private float timeSeconds = 0;
    public Shootables(int sizeX, int sizeY, int posX, int poxY, int speedX, int speedY) {
        super(sizeX, sizeY, posX, poxY, speedX, speedY);
        cooldown.setCycleCount(1);
        cooldown.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                timeSeconds++;
                                if (timeSeconds >= ProjectileStats.getFireCooldown()) {
                                    timeSeconds = 0;
                                    fired=false;
                                }
                            }
                        }));
    }

    public void Fire(int direction){
        if (!fired){
            Projectile bullet = new Projectile(Shootables.super.getPosX(), Shootables.super.getPosY(),direction);
            fired=true;
            cooldown.playFromStart();
        }

    }
//Shoot
}
