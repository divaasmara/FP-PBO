package application;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;


public class Ghost extends Rectangle implements Runnable {

    String direction;
    GameManager gameManager;
    Maze maze;
    AnimationTimer animation;
    int timesWalked;

    public Ghost(double x, double y, Color color, Maze maze, GameManager gameManager) {
        this.setX(x);
        this.setY(y);
        this.maze = maze;
        this.gameManager = gameManager;
        this.setHeight(50);
        this.setWidth(50);
        this.setFill(color);
        this.timesWalked = 0;
        this.direction = "down";
        this.createAnimation();
    }

    private String getRandomDirection(String exclude1, String exclude2) {
        String[] directions = {"left", "right", "up", "down"};
        int rnd = new Random().nextInt(directions.length);
        while (directions[rnd].equals(exclude1) || directions[rnd].equals(exclude2)) {
            rnd = new Random().nextInt(directions.length);
        }
        return directions[rnd];
    }

    /**
     * Gets the animation for the ghost
     * @return
     */
    public AnimationTimer getAnimation() {
        return animation;
    }

    /**
     *
     * @param direction
     */
    private void checkIftheresPathToGo(String direction) {
        double rightEdge, leftEdge, topEdge, bottomEdge;
        switch (direction) {
            case "down":
                leftEdge = getX() - 10;
                bottomEdge = getY() + getHeight() + 15;
                rightEdge = getX() + getWidth() + 10;
                if (!maze.hasObstacle(leftEdge, rightEdge, bottomEdge - 1, bottomEdge)) {
                    this.direction = direction;
                }
                break;
            case "up":
                leftEdge = getX() - 10;
                rightEdge = getX() + getWidth() + 10;
                topEdge = getY() - 15;
                if (!maze.hasObstacle(leftEdge, rightEdge, topEdge - 1, topEdge)) {
                    this.direction = direction;
                }
                break;
            case "left":
                leftEdge = getX() - 15;
                bottomEdge = getY() + getHeight() + 10;
                topEdge = getY() - 10;
                if (!maze.hasObstacle(leftEdge - 1, leftEdge, topEdge, bottomEdge)) {
                    this.direction = direction;
                }
                break;
            case "right":
                bottomEdge = getY() + getHeight() + 10;
                rightEdge = getX() + getWidth() + 15;
                topEdge = getY() - 10;
                if (!maze.hasObstacle(rightEdge - 1, rightEdge, topEdge, bottomEdge)) {
                    this.direction = direction;
                }
                break;
        }
    }

 
}