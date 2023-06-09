package com.example.spacegame;

public class Projectile extends BaseEntity{
    public int direction;

    private boolean hasShot = false;

    public boolean isHasShot() { return hasShot; }

    public void setHasShot(boolean hasShot) { this.hasShot = hasShot; }
    private boolean isEnemyBullet;

    public boolean isEnemyBullet() { return isEnemyBullet; }

    public void setEnemyBullet(boolean enemyBullet) { isEnemyBullet = enemyBullet; }
    /*
     * Main Constructor used for projectiles
     * projetiles move automatically
     * */
    public Projectile(int posX, int poxY, int direction) {
        super(ProjectileStats.size, ProjectileStats.size, posX, poxY, ProjectileStats.speed, ProjectileStats.speed);
        this.direction = direction;
    }

    @Override
    public void update(){
        super.moveY(super.getSpeedY()*direction);
        if (checkIfBounds()){
            MainGame.RemoveItem(super.getEntity());
        }
        super.update();
    }
    /*
    * Checks if projectile has left screen, removing if it has
    * */
    public boolean checkIfBounds(){
        if (super.getPosY()< 0 - ProjectileStats.size){
            return true;
        } else if (super.getPosY()>MainGame.GAMEHEIGHT){
            return true;
        }
        return false;
    }
}
