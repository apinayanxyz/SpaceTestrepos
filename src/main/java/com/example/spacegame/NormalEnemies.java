package com.example.spacegame;

import java.util.Random;

public class NormalEnemies extends BaseEntity{

    private boolean canMoveVertical;

    private boolean isAlive;
    private int startDirection;
    public boolean isAlive() {return isAlive;}
    public void setAlive(boolean alive) {isAlive = alive;}

    public NormalEnemies(int posX, int poxY, boolean canMoveVertical) {
        super(50, 50, posX, poxY, 1, 1);
        this.canMoveVertical = canMoveVertical;
        isAlive=true;
        moveVertical(-1);
        super.setDown(true);
    }
    public NormalEnemies(int posX, int poxY, boolean canMoveVertical,int startDirection) {
        super(50, 50, posX, poxY, 1, 1);
        this.canMoveVertical = canMoveVertical;
        this.startDirection = startDirection;
        moveVertical(startDirection);
        isAlive=true;
        super.setDown(true);
    }

    private void moveVertical(int direction) {
        if (canMoveVertical) {
            switch (direction) {
                case -1:
                    Random rand = new Random();
                    int randNum = rand.nextInt(2);
                    switch (randNum) {
                        case 0:
                            super.setLeft(true);
                            break;
                        case 1:
                            super.setRight(true);
                            break;
                    }
                    break;
                case 0:
                    super.setLeft(true);
                    break;
                case 1:
                    super.setRight(true);
                    break;
            }
        }
    }

    public void hit(Projectile projectile){
        MainGame.RemoveItem(super.getEntity());
        isAlive=false;
    }

    public int ifGone(int destroyPlus){
        if (getPosY()>MainGame.GAMEHEIGHT+getSizeY()) {
            MainGame.RemoveItem(super.getEntity());
            isAlive = false;
            return 1;
        }
        return 0;
    }

    @Override
    public void update(){
        checkIfBounds();
        super.update();
    }

    private void checkIfBounds() {
        if (getPosX() <= 0 ){
            super.setLeft(false);
            super.setRight(true);
        }
        if (getPosX() >= MainGame.GAMEWIDTH - getSizeX()) {
            super.setRight(false);
            super.setLeft(true);
        }
    }
}
