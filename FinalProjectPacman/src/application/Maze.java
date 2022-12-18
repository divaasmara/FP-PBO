package application;

import javafx.scene.Group;

import java.util.HashSet;
import java.util.Set;

public class Maze {

    public Set<BarObstacle> obstacles;

    Maze() {
        obstacles = new HashSet<>();
    }

    /**
     * Checks if point is touching obstacles
     * @param x
     * @param y
     * @return
     */
    public Boolean isTouching(double x, double y, double padding) {
        for (BarObstacle barObstacle:obstacles) {
            if (
                    x >= barObstacle.getX() - padding &&
                            x <= barObstacle.getX() + padding + barObstacle.getWidth() &&
                            y >= barObstacle.getY() - padding &&
                            y <= barObstacle.getY() + padding + barObstacle.getHeight())
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * lets you know if there's an obstacle in the current coordinate
     * @param fromX
     * @param toX
     * @param fromY
     * @param toY
     * @return
     */
    public Boolean hasObstacle(double fromX,  double toX, double fromY, double toY) {
        boolean isTouching = false;
        for (double i = fromX; i < toX; i++) {
            for (double j = fromY; j < toY; j++) {
                if (this.isTouching(i, j, 0)) isTouching = true;
            }
        }
        return isTouching;
    }

}
