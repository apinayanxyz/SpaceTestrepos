package com.example.spacegame;

public class NormalEnemies extends BaseEntity{

    private boolean canMoveVertical;

    private boolean isAlive;

    public boolean isAlive() {return isAlive;}
    public void setAlive(boolean alive) {isAlive = alive;}

    public NormalEnemies(int posX, int poxY, boolean canMoveVertical) {
        super(50, 50, posX, poxY, 1, 1);
        this.canMoveVertical = canMoveVertical;
        isAlive=true;
        super.setDown(true);
    }

    public void hit(Projectile projectile){
        MainGame.RemoveItem(super.getEntity());
        isAlive=false;
    }

    public int ifGone(int destroyPlus){
        if (getPosY()>MainGame.GAMEHEIGHT+getSizeY()) {
            MainGame.RemoveItem(super.getEntity());
            isAlive = false;
            System.out.printf("Gone");
            return 1;
        }
        return 0;
    }
}
