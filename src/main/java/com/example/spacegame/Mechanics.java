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
            System.out.println("A");
            if (player.getPosY()>0){
                player.setUp(true);
                player.setDown(false);
            }
        }
        if (code.equals(A)){
            if (player.getPosX()>0){
                player.setLeft(true);
                player.setRight(false);
            }
        }
        if (code.equals(D)){
            if (player.getPosX()<MainGame.GAMEWIDTH-player.getPlayerSize()){
                player.setRight(true);
                player.setLeft(false);
            }
        }
        if (code.equals(S)){
            if (player.getPosY()<MainGame.GAMEHEIGHT-player.getPlayerSize()){
                player.setDown(true);
                player.setUp(false);
            }
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
        /*if (event.getCode() == SPACE) {
            space = false;
        }*/
    }

}
