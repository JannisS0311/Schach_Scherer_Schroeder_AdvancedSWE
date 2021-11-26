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
        this.setLayout(new GridLayout(8, 8));
        this.setSize(new Dimension(500, 500));

        this.fillTiles();

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void fillTiles() {
        for (int i = 0; i < 64; i++) {
            int row = i / 8;
            int col = i % 8;
            this.add(this.board.getTile(row, col));
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
            System.out.println("Enter old piece y value");
            oldY = Integer.parseInt(s.nextLine());
            System.out.println("Enter old piece x value");
            oldX = Integer.parseInt(s.nextLine());

            oldLocation = new Location(oldY,oldX);

            System.out.println("Enter new piece y value");
            newY = Integer.parseInt(s.nextLine());
            System.out.println("Enter new piece x value");
            newX = Integer.parseInt(s.nextLine());

            newLocation = new Location(newY, newX);

            try {
                board.movePiece(oldLocation, newLocation);
                System.out.println("Move was correct!");
            }
            catch (Exception e){
                System.out.println("Sorry, your move isn't correct...");
            }
        }
    }
}
