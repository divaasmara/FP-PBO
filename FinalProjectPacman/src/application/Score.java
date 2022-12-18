package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score {
    public Text score;
    public Text lifes;

    Score(Group root) {
        this.score = new Text(BarObstacle.THICKNESS * 4, BarObstacle.THICKNESS * 26.5, "Score: ");
        this.lifes = new Text(BarObstacle.THICKNESS * 20, BarObstacle.THICKNESS * 26.5,"Lifes: 3");
        score.setFill(Color.MAROON);
        score.setFont(Font.font("Arial", 30));
}
