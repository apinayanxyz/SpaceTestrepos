package com.example.spacegame;

public class ProjectileStats {

    //Projectile stats
    private static int initialSpeed = 5;
    public static int speed = initialSpeed;
    private static int initialSize = 10;
    public static int size = initialSize;

    //Added effects
    private boolean isTripled;
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSize(int size) {
        this.size = size;
    }


}
