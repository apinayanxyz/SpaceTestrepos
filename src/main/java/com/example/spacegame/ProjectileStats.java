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
    public static void setSpeed(int speed1) {
        speed = speed1;
    }
    public static void setSize(int size1) {
        size = size1;
    }
    public static float getFireCooldown() { return fireCooldown; }
    public static void setFireCooldown(float fireCooldown1) { fireCooldown = fireCooldown1; }

}
