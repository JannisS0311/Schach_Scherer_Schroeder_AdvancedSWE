package _1_adaptersPackage;

import _2_domainPackage.Board;
import _2_domainPackage.Game;
import _2_domainPackage.Location;
import _2_domainPackage.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class GameFrame extends JFrame {

    private final Board board;
    private final Game game;
    private Player playerOne;
    private Player playerTwo;

    private final JTextArea loggingFrame = new JTextArea("Logged moves");
    private JPanel mainPanel = new JPanel();

    public GameFrame(Board board, Game game) throws HeadlessException {
        this.board = board;
        this.game = game;
        this.playerOne = new Player(Color.WHITE);
        this.playerTwo = new Player(Color.BLACK);
    }

    public void init() {
        //this.setLayout(new GridLayout(9, 9));
        this.setSize(new Dimension(750, 650));
        this.setLayout(new BorderLayout());

        JPanel sidePanel = new JPanel();

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(sidePanel, BorderLayout.EAST);

        this.fillTiles();
        this.fillSide(sidePanel);


        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void fillSide(JPanel sidePanel){
        sidePanel.setLayout(new BorderLayout());

        JPanel sideTopPanel = new JPanel();
        sideTopPanel.setLayout(new GridLayout(4,2));
        sideTopPanel.setPreferredSize(new Dimension(150, 150));
        JLabel oldRowL = new JLabel("Old row:");
        JLabel oldColL = new JLabel("Old col:");
        JLabel newRowL = new JLabel("New row:");
        JLabel newColL = new JLabel("New col:");

        JTextField oldRowT = new JTextField(2);
        JTextField oldColT = new JTextField(2);
        JTextField newRowT = new JTextField(2);
        JTextField newColT = new JTextField(2);

        sideTopPanel.add(oldRowL);
        sideTopPanel.add(oldColL);
        sideTopPanel.add(oldRowT);
        sideTopPanel.add(oldColT);
        sideTopPanel.add(newRowL);
        sideTopPanel.add(newColL);
        sideTopPanel.add(newRowT);
        sideTopPanel.add(newColT);

        JPanel sideCenterPanel = new JPanel();
        sideCenterPanel.setPreferredSize(new Dimension(150, 100));
        JButton submit = new JButton("Move");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldY, oldX, newY, newX;

                oldY = Integer.parseInt(oldRowT.getText());
                oldX = Integer.parseInt(oldColT.getText());
                newY = Integer.parseInt(newRowT.getText());
                newX = Integer.parseInt(newColT.getText());

                move(oldY, oldX, newY, newX);

                oldRowT.setText("");
                oldColT.setText("");
                newRowT.setText("");
                newColT.setText("");
            }
        });

        sideCenterPanel.add(submit);

        JPanel sideBottomPanel = new JPanel();
        sideBottomPanel.setPreferredSize(new Dimension(150, 400));
        sideBottomPanel.add(loggingFrame);
        loggingFrame.setEditable(false);


        sidePanel.add(sideTopPanel, BorderLayout.NORTH);
        sidePanel.add(sideCenterPanel, BorderLayout.CENTER);
        sidePanel.add(sideBottomPanel, BorderLayout.SOUTH);
    }

    private void fillTiles() {
        mainPanel.setLayout(new GridLayout(9, 9));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                mainPanel.add(this.board.getSquareFromLocation(new Location(row, col)));
            }
        }
    }

    private void updateTiles(){
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        mainPanel.setLayout(new GridLayout(9, 9));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                mainPanel.add(this.board.getSquareFromLocation(new Location(row, col)));
            }
        }
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void setTurn() {
        if (game.getTurn() == Color.WHITE){
            game.setTurn(Color.BLACK);
        }else {
            game.setTurn(Color.WHITE);
        }
    }

    private void move(Integer oldY, Integer oldX, Integer newY, Integer newX){
        Location oldLocation = new Location(oldY,oldX);
        Location newLocation = new Location(newY, newX);

        if(board.movePiece(oldLocation, newLocation, this.game)){
            loggingFrame.append("\nMove: " + oldY + "," + oldX + " > " + newY + "," + newX);
            this.updateTiles();
            this.setTurn();
        }else {
            loggingFrame.append("\nYour move isn't correct...");
        }
    }
}
