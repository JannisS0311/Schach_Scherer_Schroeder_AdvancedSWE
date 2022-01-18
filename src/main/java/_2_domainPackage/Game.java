package _2_domainPackage;

import java.awt.*;

public class Game {

    private Color turn;
    private Boolean running;

    /*
    Bool turn:
    True = Player 1 (White) has to move
    False= Player 2 (Black) has to move
     */


    public Game(){
        this.running = true;
        this.turn = Color.WHITE;
    }

    public Boolean getRunning() {
        return running;
    }

    public Color getTurn(){
        return turn;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public void setTurn(Color turn) {
        this.turn = turn;
    }
}
