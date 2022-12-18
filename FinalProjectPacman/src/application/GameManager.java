package application;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GameManager {

    private Pacman pacman;
    private Group root;
    private Set<Cookie> cookieSet;
    private Set<Ghost> ghosts;
    private AnimationTimer leftPacmanAnimation;
    private AnimationTimer rightPacmanAnimation;
    private AnimationTimer upPacmanAnimation;
    private AnimationTimer downPacmanAnimation;
    private Maze maze;
    private int lifes;
    private int score;
    private Score scoreBoard;
    private boolean gameEnded;
    private int cookiesEaten;

    /**
     * Constructor
     */
    GameManager(Group root) {
        this.root = root;
        this.maze = new Maze();
        this.pacman = new Pacman(2.5 * BarObstacle.THICKNESS, 2.5 * BarObstacle.THICKNESS);
        this.cookieSet = new HashSet<>();
        this.ghosts = new HashSet<>();
        this.leftPacmanAnimation = this.createAnimation("left");
        this.rightPacmanAnimation = this.createAnimation("right");
        this.upPacmanAnimation = this.createAnimation("up");
        this.downPacmanAnimation = this.createAnimation("down");
        this.lifes = 3;
        this.score = 0;
        this.cookiesEaten = 0;
    }

    /**
     * Set one life less
     */
    private void lifeLost() {
        this.leftPacmanAnimation.stop();
        this.rightPacmanAnimation.stop();
        this.upPacmanAnimation.stop();
        this.downPacmanAnimation.stop();
        for (Ghost ghost : ghosts) {
            ghost.getAnimation().stop();
        }
        this.pacman.setCenterX(2.5 * BarObstacle.THICKNESS);
        this.pacman.setCenterY(2.5 * BarObstacle.THICKNESS);
        lifes--;
        score -= 10;
        this.scoreBoard.lifes.setText("Lifes: " + this.lifes);
        this.scoreBoard.score.setText("Score: " + this.score);
        if (lifes == 0) {
            this.endGame();
        }
    }

    /**
     * Ends the game
     */
    private void endGame() {
        this.gameEnded = true;
        root.getChildren().remove(pacman);
        for (Ghost ghost : ghosts) {
            root.getChildren().remove(ghost);
        }
        javafx.scene.text.Text endGame = new javafx.scene.text.Text("       GAME OVER\n press ESC to restart");
        endGame.setX(BarObstacle.THICKNESS * 15);
        endGame.setY(BarObstacle.THICKNESS * 12);
        endGame.setFont(Font.font("Arial", 50));
        endGame.setFill(Color.RED);
        root.getChildren().remove(this.scoreBoard.score);
        root.getChildren().remove(this.scoreBoard.lifes);
        root.getChildren().add(endGame);
    }
    
    private void winGame() {
        this.gameEnded = true;
        root.getChildren().remove(pacman);
        for (Ghost ghost : ghosts) {
            root.getChildren().remove(ghost);
        }
        javafx.scene.text.Text winGame = new javafx.scene.text.Text("        YOU WIN\n press ESC to restart");
        winGame.setX(BarObstacle.THICKNESS * 15);
        winGame.setY(BarObstacle.THICKNESS * 12);
        winGame.setFont(Font.font("Arial", 50));
        winGame.setFill(Color.GREENYELLOW);
        root.getChildren().remove(this.scoreBoard.score);
        root.getChildren().remove(this.scoreBoard.lifes);
        root.getChildren().add(winGame);
    }
    
    /**
     * Restart the game
     * @param event
     */
    public void restartGame(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE && gameEnded) {
            root.getChildren().clear();
            this.cookieSet.clear();
            this.ghosts.clear();
            this.drawBoard();
            this.pacman.setCenterX(2.5 * BarObstacle.THICKNESS);
            this.pacman.setCenterY(2.5 * BarObstacle.THICKNESS);
            this.lifes = 3;
            this.score = 0;
            this.cookiesEaten = 0;
            gameEnded = false;
        }
    }
    
    /**
     * Draws the board of the game with the cookies and the Pacman
     */
    public void drawBoard() {
        this.maze.CreateMaze(root);
        // 1st line
        Integer skip[] = {5, 17};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, 2.5 * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 2nd line
        skip = new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, 4.5 * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 3rd line
        skip = new Integer[]{1, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, 6.5 * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 4th line
        skip = new Integer[]{1, 3, 4, 5, 7, 9, 10, 11, 12, 13, 15, 17, 18, 19, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 8.5 * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 5th line
        skip = new Integer[]{1, 7, 9, 10, 11, 12, 13, 15, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, 10.5 * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 6th line
        skip = new Integer[]{3, 4, 5, 7, 9, 10, 11, 12, 13, 15, 17, 18, 19};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 7th line
        skip = new Integer[]{1, 7, 9, 10, 11, 12, 13, 15, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 14.5 * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 8th line
        skip = new Integer[]{1, 3, 4, 5, 7, 9, 10, 11, 12, 13, 15, 17, 18, 19, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 16.5 * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 9th line
        skip = new Integer[]{1, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 18.5 * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 10th line
        skip = new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, 20.5 * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 11th line
        skip = new Integer[]{5, 17};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 22.5 * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        root.getChildren().add(this.pacman);
        this.generateGhosts();
        root.getChildren().addAll(this.ghosts);
        this.scoreBoard = new Score(root);
    }
    
    /**
     * Generates the ghosts for the pacman!
     */
    public void generateGhosts() {
        this.ghosts.add(new Ghost(22 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, Color.DEEPPINK, maze, this));
        this.ghosts.add(new Ghost(25 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, Color.GREENYELLOW, maze, this));
        this.ghosts.add(new Ghost(22 * BarObstacle.THICKNESS, 13.5 * BarObstacle.THICKNESS, Color.BLACK, maze, this));
        this.ghosts.add(new Ghost(25 * BarObstacle.THICKNESS, 13.5 * BarObstacle.THICKNESS, Color.SPRINGGREEN, maze, this));
    }

    /**
     * Moves the pacman
     * @param event
     */
    public void movePacman(KeyEvent event) {
        for (Ghost ghost : this.ghosts) {
            ghost.run();
        }
        switch(event.getCode()) {
            case RIGHT:
                this.rightPacmanAnimation.start();
                break;
            case LEFT:
                this.leftPacmanAnimation.start();
                break;
            case UP:
                this.upPacmanAnimation.start();
                break;
            case DOWN:
                this.downPacmanAnimation.start();
                break;
        }
    }
    
}
