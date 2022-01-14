package _1_adaptersPackage;

import _2_domainPackage.Board;
import _2_domainPackage.Location;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class GameFrame extends JFrame {

    private final Board board;

    public GameFrame(Board board) throws HeadlessException {
        this.board = board;
        //this.init();
    }

    public void init() {
        this.setLayout(new GridLayout(9, 9));
        this.setSize(new Dimension(650, 650));

        this.fillTiles();

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void fillTiles() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                this.add(this.board.getSquareFromLocation(new Location(row, col)));
            }
        }
    }

    //TODO: Place in right spot
    public void play(){
        boolean gameRunning = true;
        Scanner s = new Scanner(System.in);
        int oldY, oldX, newY, newX;

        Location oldLocation;
        Location newLocation;

        while (gameRunning){
            System.out.println("Enter old piece row value");
            oldY = Integer.parseInt(s.nextLine());
            System.out.println("Enter old piece column value");
            oldX = Integer.parseInt(s.nextLine());

            oldLocation = new Location(oldY,oldX);

            System.out.println("Enter new piece row value");
            newY = Integer.parseInt(s.nextLine());
            System.out.println("Enter new piece column value");
            newX = Integer.parseInt(s.nextLine());

            newLocation = new Location(newY, newX);

            if(board.movePiece(oldLocation, newLocation)){
                System.out.println("Move was correct!");
                continue;
            }
            System.out.println("Sorry, your move isn't correct...");
        }
    }
}
