package _2_domainPackage;

import java.awt.*;

public class Player {

    private Color color;
    private Integer score;

    public Player(Color color){
        this.color = color;
        this.score = 0;
    }

    public Integer getScore() {
        return score;
    }

    public Color getColor(){
        return color;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
