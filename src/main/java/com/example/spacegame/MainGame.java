package com.example.spacegame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Group;

import java.io.IOException;

public class MainGame {
    public final static int SCREENWIDTH= (int) Screen.getPrimary().getBounds().getWidth() ;
    public final static int GAMEWIDTH= SCREENWIDTH/2;
    public final static int SCREENHEIGHT= (int) Screen.getPrimary().getBounds().getWidth() ;
    public final static int GAMEHEIGHT= SCREENHEIGHT;

    public Stage stage;
    public MainGame(Stage stage) {
    this.stage = stage;
    stage.setResizable(false);
    stage.setMaximized(false);
    stage.setFullScreen(false);
    }

    public void startGame() throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Pane root = new Pane();
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
