package com.example.spacegame;

public class ProjectileStats {

    //Projectile stats
    private static final int initialSpeed = 3;
    public static int speed = initialSpeed;
    private static final int initialSize = 10;
    public static int size = initialSize;

    //Added effects
    private boolean isTripled;
    private static float fireCooldown=3;

    //Methods
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public static float getFireCooldown() { return fireCooldown; }
    public void setFireCooldown(float fireCooldown) { this.fireCooldown = fireCooldown; }

}
