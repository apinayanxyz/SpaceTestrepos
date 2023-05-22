package com.example.spacegame;

import javafx.scene.input.KeyCode;

import javafx.scene.shape.Rectangle;

import static javafx.scene.input.KeyCode.*;

public class Mechanics {
    //For collisions , out of bounds, spawning waves


    public static void movePlayer(KeyCode code, Player player){
        if (code.equals(W)){
            if (player.getPosY()>0){
                player.setUp(true);
                player.setDown(false);
            }
            else{
                player.setUp(false);
                player.setDown(false);
                player.setPosY(1);
            }
        }
        if (code.equals(D)){
            if (player.getPosX()<MainGame.GAMEWIDTH-player.getPlayerSize()){
                player.setRight(true);
                player.setLeft(false);
            }
            else{
                player.setRight(false);
                player.setLeft(false);
                player.setPosX(MainGame.GAMEWIDTH-(player.getPlayerSize()+1));
            }
        }

        if (code.equals(A)) {
            if (player.getPosX() > 0) {
                player.setLeft(true);
                player.setRight(false);
            } else {
                player.setLeft(false);
                player.setRight(false);
                player.setPosX(1);
            }
        }
        if (code.equals(S)){
            if (player.getPosY()<MainGame.GAMEHEIGHT-player.getPlayerSize()){
                player.setDown(true);
                player.setUp(false);
            }
            else{
                player.setUp(false);
                player.setDown(false);
                player.setPosY(MainGame.GAMEHEIGHT-player.getPlayerSize());
            }
        }
        if (code.equals(SPACE)){
            player.setFiring(true);
        }
    }

    public static void stopMovement(KeyCode code, Player player){
        if (code == A) {
            player.setLeft(false);

        } else if (code == D) {
            player.setRight(false);
        }
        if (code == W) {
            player.setUp(false);
        } else if (code == S) {
            player.setDown(false);
        }
        if (code == SPACE) {
            player.setFiring(false);
        }
    }

    public static void CreateWavePattern1(){

    }

}
