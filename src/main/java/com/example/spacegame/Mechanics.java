package com.example.spacegame;

import javafx.scene.input.KeyCode;

import javafx.scene.shape.Rectangle;

import static javafx.scene.input.KeyCode.W;
import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.D;

public class Mechanics {
    //For collisions , out of bounds, spawning waves


    public static void movePlayer(KeyCode code, Player player){
        if (code.equals(W)){
            if (player.getPosY()>0){
                moveY(player,player.getPosY(),-player.playerSpeed);
            }
        }
        if (code.equals(A)){
            if (player.getPosX()>0){
                moveX(player,player.getPosX(),-player.playerSpeed);
            }
        }
        if (code.equals(D)){
            if (player.getPosX()<MainGame.GAMEWIDTH-player.getPlayerSize()){
                moveX(player,player.getPosX(),player.playerSpeed);
            }
        }
        if (code.equals(S)){
            if (player.getPosY()<MainGame.GAMEHEIGHT-player.getPlayerSize()){
                moveY(player,player.getPosY(),player.playerSpeed);
            }
        }
    }
    public static void moveX(BaseEntity entity, int position, int displacement){
        position = position + displacement;
        entity.setPosX(position);
    }
    public static void moveY(BaseEntity entity, int position, int displacement){
        position = position + displacement;
        entity.setPosY(position);
    }
}
