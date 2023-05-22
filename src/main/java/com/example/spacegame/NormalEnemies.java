package com.example.spacegame;

public class NormalEnemies extends BaseEntity{

    private boolean canMoveVertical;

    public NormalEnemies(int posX, int poxY, boolean canMoveVertical) {
        super(50, 50, posX, poxY, 5, 5);
        this.canMoveVertical = canMoveVertical;
        super.setDown(true);
    }

    public void hit(Projectile projectile){
        MainGame.RemoveItem(super.getEntity());
    }
}
