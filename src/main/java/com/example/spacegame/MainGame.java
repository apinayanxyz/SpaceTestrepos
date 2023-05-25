package com.example.spacegame;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

import static javafx.scene.paint.Color.RED;
public class MainGame {

    //
    public final static int SCREENWIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    public final static int GAMEWIDTH = SCREENWIDTH / 2;
    public final static int SCREENHEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    public final static int GAMEHEIGHT = SCREENHEIGHT;

    // Game items
    public Stage stage;
    // FXMLLoader fxmlLoader = new
    // FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    public static Pane root = new Pane();
    // Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    public Scene scene = new Scene(root, GAMEWIDTH, GAMEHEIGHT);
    public static Player player = new Player(GAMEWIDTH / 2 - 25, GAMEHEIGHT / 2);
    public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public static ArrayList<Projectile> enemyProjectiles = new ArrayList<Projectile>();
    public static ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
    public static ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();

    public ArrayList<NormalEnemies> enemyList = new ArrayList<NormalEnemies>();
    public ArrayList<ShootingEnemies> enemyList2 = new ArrayList<ShootingEnemies>();
    //Wave variables
    public int enemyCount = 0;
    public int destroyed = 0;
    public int waveNum;

    public int roundCount = 1;
    //Round variables
    public int roundWaveCount = 0;
    public int roundWaveMax = 0;
    public Label roundText = new Label();;

    // Miscellanous

    private Timeline roundCooldown = new Timeline();
    public int timeSeconds = 0;
    public boolean roundBreak = false;

    /*
     * Constructer for this class
     * takes stage from main class and changes it to be fixed
     * */
    public MainGame(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.setFullScreen(false);
    }

    /*
     * Main method used to start game
     * creates timers for cooldowns
     * sets up stage to be used for game
     * */
    public void startGame() throws IOException {

        roundCooldown.setCycleCount(1);
        roundCooldown.getKeyFrames().add(
                new KeyFrame(Duration.seconds(5),
                        new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                timeSeconds++;
                                if (timeSeconds == 1) {
                                    timeSeconds = 0;
                                    roundBreak = false;
                                    root.getChildren().remove(roundText);
                                }
                            }
                        }));
        System.out.println(SCREENWIDTH);
        System.out.println(SCREENHEIGHT);
        Rectangle test = new Rectangle(20, 20);
        // AddItem(player.getEntity());
        // root.getChildren().add(player.getEntity());
        // root.getChildren().add(test);
        scene.setOnKeyPressed((KeyEvent e) -> {
            KeyCode code = e.getCode();
            Mechanics.movePlayer(code, player);
        });
        scene.setOnKeyReleased((KeyEvent e) -> {
            KeyCode code = e.getCode();
            Mechanics.stopMovement(code, player);
        });
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        roundText.setLayoutX(GAMEWIDTH/2 - 50);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        scene.setFill(BLACK);
        stage.show();

        startGameLoop();
        Timeline cooldown2 = new Timeline();
        cooldown2.setCycleCount(1);
        cooldown2.getKeyFrames().add(
                new KeyFrame(Duration.seconds(2),
                        new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                timeSeconds1++;

                                if (timeSeconds1 >= 1) {
                                    timeSeconds1 = 0;
                                    playerInvincible = false;
                                }
                            }
                        }));
        invincibleCoolDown = cooldown2;
        invincibleCoolDown.playFromStart();
    }

    private boolean asteroidPlaying = true;
    private Timeline asteroidCooldown;
    private int timeSeconds1 = 0;

    /*
     * Creates asteroids at a certain interval
     * */
    private void createAsteroid() {
        if (asteroidPlaying){
            Random rand = new Random();
            asteroidPlaying = false;
            int randNum = rand.nextInt(5) + 1;
            int randXNum = rand.nextInt(GAMEWIDTH-80) + 1;
            Timeline cooldown1 = new Timeline();
            cooldown1.setCycleCount(1);
            cooldown1.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(10),
                            new EventHandler() {
                                @Override
                                public void handle(Event event) {
                                    System.out.println(timeSeconds);
                                    timeSeconds1++;
                                    if (timeSeconds1 >= 1) {
                                        timeSeconds1 = 0;
                                        Asteroid asteroid = new Asteroid(randXNum,-80);
                                        asteroids.add(asteroid);
                                        asteroidPlaying=true;
                                    }
                                }
                            }));
        }

    }
    private boolean paused = false;
    private boolean playerInvincible = false;

    private Timeline invincibleCoolDown;

    /*
     * Animation timer method where game is run
     * Moves items, checks if collision happens
     * */
    private void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!paused) {
                    createAsteroid();
                    player.update();
                    roundText.setTextFill(WHITE);
                    roundText.setLayoutY(200);
                    if (player.health <= 0) {
                        RemoveItem(player.getEntity());
                        roundText.setText("You Lose");
                        root.getChildren().add(roundText);
                        paused = true;
                        ;
                    }
                    if (!roundBreak) {
                        if (roundWaveMax < 5) {
                            if (enemyCount == destroyed) {
                                if (roundWaveCount <= roundWaveMax) {
                                    waveNum = 1;
                                    CreateWave();
                                    roundWaveCount++;
                                } else {
                                    //ProjectileStats.setFireCooldown(ProjectileStats.getFireCooldown()/2);
                                    //player.updateTimer();
                                    roundWaveCount = 0;
                                    roundWaveMax++;
                                    roundBreak = true;

                                    roundText.setText("Round " + (roundWaveMax + 1) + " start");
                                    roundText.setTextFill(WHITE);
                                    //System.out.println(roundText.getAccessibleText());
                                    //scene.setFill(BLACK);
                                    root.getChildren().add(roundText);
                                    roundCooldown.playFromStart();
                                }
                            }
                        }
                        else {
                            roundText.setText("You Win");
                            root.getChildren().add(roundText);
                            paused = true;
                        }
                    }
                    for (NormalEnemies enemy : enemyList) {
                        if (enemy.isAlive()) {
                            destroyed = destroyed + enemy.ifGone(destroyed);
                            if (enemy.getEntity().getBoundsInParent().intersects(player.getEntity().getBoundsInParent())) {
                                if (!playerInvincible) {
                                    player.health--;
                                    playerInvincible = true;
                                    invincibleCoolDown.playFromStart();
                                }
                            }
                            enemy.update();
                        }
                    }
                    for (ShootingEnemies enemy : enemyList2) {
                        if (enemy.isAlive()) {
                            destroyed = destroyed + enemy.ifGone(destroyed);
                            if (enemy.getEntity().getBoundsInParent().intersects(player.getEntity().getBoundsInParent())) {
                                if (!playerInvincible) {
                                    player.health--;
                                    playerInvincible = true;
                                    invincibleCoolDown.playFromStart();
                                }
                            }
                            enemy.update();
                        }
                    }
                    for (Projectile projectile : enemyProjectiles) {
                        projectile.update();
                        if (!projectile.isHasShot()) {
                            if (projectile.getEntity().getBoundsInParent().intersects(player.getEntity().getBoundsInParent())) {
                                if (!playerInvincible) {
                                    player.health--;
                                    playerInvincible = true;
                                    invincibleCoolDown.playFromStart();
                                    projectile.setHasShot(true);
                                }

                            }
                        }
                    }
                    for (Asteroid asteroid : asteroids) {
                        asteroid.update();
                    }
                    for (Projectile projectile : projectiles) {
                        projectile.update();
                        for (NormalEnemies enemy : enemyList) {
                            if (projectile.getEntity().getBoundsInParent()
                                    .intersects(enemy.getEntity().getBoundsInParent())) {
                                if (!projectile.isHasShot()) {
                                    if (enemy.isAlive()) {
                                        enemy.hit(projectile);
                                        projectile.setHasShot(true);
                                        RemoveItem(projectile.getEntity());
                                        destroyed++;
                                    }
                                }
                            }
                        }
                        for (ShootingEnemies enemy : enemyList2) {
                            if (projectile.getEntity().getBoundsInParent()
                                    .intersects(enemy.getEntity().getBoundsInParent())) {
                                if (!projectile.isHasShot()) {
                                    if (enemy.isAlive()) {
                                        enemy.hit(projectile);
                                        projectile.setHasShot(true);
                                        RemoveItem(projectile.getEntity());
                                        destroyed++;
                                    }
                                }
                            }
                        }
                        for (PowerUp powerup : powerUps) {
                            //powerup.update();
                            if (powerup.getEntity().getBoundsInParent().intersects(player.getEntity().getBoundsInParent())) {
                                if (!powerup.isObtained()) {
                                    addPower(powerup.getType());

                                    addPower(5);
                                    powerup.setObtained(true);
                                    RemoveItem(powerup.getEntity());
                                }
                            }
                        }
                        for (Asteroid asteroid : asteroids) {
                            if (asteroid.getPosY() > 0) {
                                if (projectile.getEntity().getBoundsInParent()
                                        .intersects(asteroid.getEntity().getBoundsInParent())) {
                                    if (!projectile.isHasShot()) {
                                        if (asteroid.isAlive()) {
                                            PowerUp test = new PowerUp(asteroid.getPosX() + (asteroid.getSizeX() / 2) - 15, asteroid.getPosY());
                                            test.getSizeX();
                                            powerUps.add(test);
                                            asteroid.hit(projectile);
                                            projectile.setHasShot(true);
                                            RemoveItem(projectile.getEntity());
                                        }
                                    }
                                }
                            }
                            if (asteroid.getPosY() > GAMEHEIGHT) {
                                asteroid.hit(projectile);
                                RemoveItem(asteroid.getEntity());
                            }
                        }
                    }
                }
        }
        }.start();
    }

    /*
    * Creates wave that appears on screen
    * randomly selects a pattern for a wave
    * */
    private void CreateWave() {
        Random rand = new Random();
        int randNum = rand.nextInt(5) + 1;
        switch (randNum) {
            case 1:
                for (int i = 1; i < 8; i++) {
                    NormalEnemies test = new NormalEnemies((GAMEWIDTH / 8) * (i) - 40, -80, false);
                    enemyList.add(test);
                    enemyCount = enemyCount + 1;
                }
                for (int i = 1; i < 8; i++) {
                    NormalEnemies test = new NormalEnemies((GAMEWIDTH / 8) * (i) - 40, -350, false);
                    enemyList.add(test);
                    enemyCount = enemyCount + 1;
                }
                for (int i = 1; i < 4; i++) {
                    ShootingEnemies test = new ShootingEnemies((GAMEWIDTH/4)*i,-200);
                    enemyList2.add(test);
                    enemyCount = enemyCount + 1;
                }
                break;
            case 2:
                for (int i = 1; i < 8; i++) {
                    NormalEnemies test;
                    if (i > 1) {
                        test = new NormalEnemies((GAMEWIDTH / 8) * (i) - 40, -50, true, i % ((i / 2) * 2));
                    } else {
                        test = new NormalEnemies((GAMEWIDTH / 8) * (i) - 40, -50, true, i);
                    }
                    enemyList.add(test);
                    enemyCount = enemyCount + 1;
                }
                for (int i = 1; i < 8; i++) {
                    NormalEnemies test;
                    if (i > 1) {
                        test = new NormalEnemies((GAMEWIDTH / 8) * (i) - 40, -350, true, i % ((i / 2) * 2));
                    } else {
                        test = new NormalEnemies((GAMEWIDTH / 8) * (i) - 40, -350, true, i);
                    }
                    enemyList.add(test);
                    enemyCount = enemyCount + 1;
                }
                for (int i = 1; i < 3; i++) {
                    ShootingEnemies test = new ShootingEnemies((GAMEWIDTH/3)*i,-200);
                    enemyList2.add(test);
                    enemyCount = enemyCount + 1;
                }
                break;
            case 3:
                for (int i = 1; i < 8; i++) {
                    NormalEnemies test = new NormalEnemies((GAMEWIDTH / 8) * (i) - 40, -50, true);
                    enemyList.add(test);
                    enemyCount = enemyCount + 1;
                }
                for (int i = 1; i < 8; i++) {
                    NormalEnemies test = new NormalEnemies((GAMEWIDTH / 8) * (i) - 40, -350, true);
                    enemyList.add(test);
                    enemyCount = enemyCount + 1;
                }
                for (int i = 1; i < 4; i++) {
                    ShootingEnemies test = new ShootingEnemies((GAMEWIDTH/4)*i,-200);
                    enemyList2.add(test);
                    enemyCount = enemyCount + 1;
                }
                break;
            case 4:
                for (int i = 1; i < 8; i++) {
                    NormalEnemies test = new NormalEnemies((GAMEWIDTH / 8) * (i) - 40, -50, true, 1);
                    enemyList.add(test);
                    enemyCount = enemyCount + 1;
                }
                for (int i = 1; i < 8; i++) {
                    NormalEnemies test = new NormalEnemies((GAMEWIDTH / 8) * (i) - 40, -350, true, 0);
                    enemyList.add(test);
                    enemyCount = enemyCount + 1;
                }
                break;
            case 5:
                for (int i = 1; i < 8; i++) {
                    NormalEnemies test = new NormalEnemies((GAMEWIDTH / 8) * (i) - 40, -50, true, 0);
                    enemyList.add(test);
                    enemyCount = enemyCount + 1;
                }
                for (int i = 1; i < 8; i++) {
                    NormalEnemies test = new NormalEnemies((GAMEWIDTH / 8) * (i) - 40, -350, true, 1);
                    enemyList.add(test);
                    enemyCount = enemyCount + 1;
                }
                for (int i = 1; i < 2; i++) {
                    ShootingEnemies test = new ShootingEnemies((GAMEWIDTH/2)*i,-200);
                    enemyList2.add(test);
                    enemyCount = enemyCount + 1;
                }
                break;
        }

    }

    /*
     * Adds items to game
     *
     * */
    public static void AddItem(Rectangle item) {
        root.getChildren().add(item);
    }

    private static boolean isDouble = false;
    private static boolean isTriple = false;
    private static boolean isVertical = false;
    private static boolean isMulti = false;

    /*
    * Adds projectiles to game based on where they were created and their direction
    * also checks based on power ups obtained
    * */
    public static void AddProjectile(int direction , int bulletSpawn, int yPos) {
        if (isMulti) {
            if (isDouble) {
                for (int i = 1; i <= 2; i++) {
                    if (direction == -1) {
                        Projectile tempProj = new Projectile((bulletSpawn - 90) + 60 * i, yPos, direction);
                        projectiles.add(tempProj);
                    } else {
                        Projectile tempProj = new Projectile((bulletSpawn - 90) + 60 * i, yPos + 70, direction);
                        enemyProjectiles.add(tempProj);
                    }
                }
            }
                if (isTriple) {
                    for (int i = 1; i <= 3; i++) {
                        if (direction == -1) {
                            Projectile tempProj = new Projectile((bulletSpawn - 150) + 75 * i, yPos, direction);
                            projectiles.add(tempProj);
                        } else {
                            Projectile tempProj = new Projectile((bulletSpawn - 150) + 75 * i, yPos + 70, direction);
                            enemyProjectiles.add(tempProj);
                        }
                    }

                }

            } else {
                if (direction == -1) {
                    Projectile tempProj = new Projectile(bulletSpawn, yPos, direction);
                    projectiles.add(tempProj);
                } else {
                    Projectile tempProj = new Projectile(bulletSpawn, yPos + 70, direction);
                    enemyProjectiles.add(tempProj);
                }
            }
        if (isVertical){
            Projectile tempProj1 = new Projectile((bulletSpawn - 75), yPos, direction);
            tempProj1.setLeft(true);
            projectiles.add(tempProj1);
            Projectile tempProj2 = new Projectile((bulletSpawn + 75), yPos, direction);
            tempProj2.setRight(true);
            projectiles.add(tempProj2);
        }

        }

    /**
     * Removes items from the game
     */

    public static void RemoveItem(Rectangle item) {
        root.getChildren().remove(item);
    }

    /*
    * Adds powerups effects
    * */
    public void addPower(int type){
        switch (type) {
            case 1:
                isDouble=true;
                isMulti=true;
                break;
            case 2:
                isTriple=true;
                isMulti=true;
                break;
            case 3:
                isVertical=true;
                break;
            case 4:
                ProjectileStats.setFireCooldown(ProjectileStats.getFireCooldown()/2);
                player.updateTimer();
                break;
            case 5:
                ProjectileStats.setSpeed(ProjectileStats.speed * 2);
                player.updateTimer();
                break;
        }
    }
}
