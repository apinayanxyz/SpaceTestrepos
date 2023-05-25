package com.example.spacegame;

import javafx.scene.image.Image;

public class Asteroid extends NormalEnemies{

    /*
    * Constructor for asteroid class
    * */
    public Asteroid(int posX, int poxY) {
        super(posX, poxY, false);
        super.setImage(new Image("file:Images/Asteroid.png"));
    }
}
