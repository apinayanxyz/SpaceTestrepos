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
import java.util.ArrayList;

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
    public static Player player = new Player(GAMEWIDTH/2 -25, GAMEHEIGHT/2);
    public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public static ArrayList<BaseEntity> enemies = new ArrayList<BaseEntity>();
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
        scene.setOnKeyReleased((KeyEvent e) -> {
            KeyCode code = e.getCode();
            Mechanics.stopMovement(code, player);
        });
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        startGameLoop();
    }

    public ArrayList<NormalEnemies> enemyList = new ArrayList<NormalEnemies>();
    public int enemyCount = 0;
    public int destroyed = 0;
    public int waveNum;
    private void startGameLoop() {
        new AnimationTimer(){
            @Override
            public void handle(long now){
                player.update();
                System.out.println("count:"+enemyCount);
                System.out.println("destroyed:"+destroyed);
                if (enemyCount == destroyed){
                    waveNum = 1;
                    CreateWave();
                    System.out.printf("a");
                }
                for (NormalEnemies enemy : enemyList) {
                    if (enemy.isAlive()){
                        destroyed =destroyed + enemy.ifGone(destroyed);
                        enemy.update();
                    }
                }
                for (Projectile projectile:projectiles) {
                    projectile.update();
                    for (NormalEnemies enemy : enemyList) {
                        if (projectile.getEntity().getBoundsInParent().intersects(enemy.getEntity().getBoundsInParent())){
                            if (enemy.isAlive()){
                                enemy.hit(projectile);
                                RemoveItem(projectile.getEntity());
                                destroyed++;
                            }

                        }
                    }
                    
                }

            }
        }.start();
    }

    private void CreateWave() {
        for (int i = 0; i < 5; i++) {
            NormalEnemies test = new NormalEnemies((GAMEWIDTH/6) * (i+1), 200,false );
            enemyList.add(test);
            enemyCount = enemyCount + 1;
        }

    }

    public static void AddItem(Rectangle item){root.getChildren().add(item);}

    public static void AddProjectile(){
        Projectile tempProj = new Projectile(player.getBulletSpawnX(), player.getPosY(),-1);
        projectiles.add(tempProj);
    }
    public static void RemoveItem(Rectangle item){ root.getChildren().remove(item); }
}
