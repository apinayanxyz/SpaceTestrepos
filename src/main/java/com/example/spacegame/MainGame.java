package com.example.spacegame;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Group;

import java.io.IOException;

public class MainGame {
    public final static int SCREENWIDTH= (int) Screen.getPrimary().getBounds().getWidth() ;
    public final static int GAMEWIDTH= SCREENWIDTH/2;
    public final static int SCREENHEIGHT= (int) Screen.getPrimary().getBounds().getHeight() ;
    public final static int GAMEHEIGHT= SCREENHEIGHT;

    public Stage stage;
    //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    public static Pane root = new Pane();
    //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    public Scene scene = new Scene(root, GAMEWIDTH, GAMEHEIGHT);
    public Player player = new Player(GAMEWIDTH/2 -25, GAMEHEIGHT/2);
    public MainGame(Stage stage) {
    this.stage = stage;
    stage.setResizable(false);
    stage.setMaximized(false);
    stage.setFullScreen(false);
    }

    public void startGame() throws IOException {

        System.out.println(SCREENWIDTH);
        System.out.println(SCREENHEIGHT);
        Rectangle test = new Rectangle(20,20);
        //AddItem(player.getEntity());
        //root.getChildren().add(player.getEntity());
        //root.getChildren().add(test);
        scene.setOnKeyPressed((KeyEvent e) -> {
            KeyCode code = e.getCode();
            Mechanics.movePlayer(code, player);
        });
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        startGameLoop();
    }

    private void startGameLoop() {
        new AnimationTimer(){
            @Override
            public void handle(long now){
                player.update();
                //System.out.println(player.getPosX());
            }
        }.start();
    }

    public static void AddItem(Rectangle item){
        root.getChildren().add(item);
    }
}
