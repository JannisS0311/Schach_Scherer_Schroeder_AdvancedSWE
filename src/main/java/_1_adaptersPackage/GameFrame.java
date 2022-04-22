package _1_adaptersPackage;

import _2_domainPackage.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {

    private Board board;
    private Game game;
    private final Player playerOne;
    private final Player playerTwo;

    private JTextArea loggingFrame = new JTextArea("Logged moves");
    private JList<BoardState> boardStateJList = new JList();
    private ListSelectionModel listSelectionModel;
    private DefaultListModel listModel;
    private JButton saveBoardStateButton;

    private final JPanel mainPanel = new JPanel();
    private final JPanel sidePanel = new JPanel();


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

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(sidePanel, BorderLayout.EAST);

        this.fillTiles();
        this.fillSide();


        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void fillSide(){
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

        submit.addActionListener(e -> {
            int oldY, oldX, newY, newX;

            oldY = Integer.parseInt(oldRowT.getText());
            oldX = Integer.parseInt(oldColT.getText());
            newY = Integer.parseInt(newRowT.getText());
            newX = Integer.parseInt(newColT.getText());

            oldRowT.setText("");
            oldColT.setText("");
            newRowT.setText("");
            newColT.setText("");

            oldRowT.requestFocusInWindow();

            move(oldY, oldX, newY, newX);
        });

        sideCenterPanel.add(submit);

        JPanel sideBottomPanel = new JPanel();
        sideBottomPanel.setLayout(new GridLayout(4,1));
        sideBottomPanel.setPreferredSize(new Dimension(150, 400));

        sideBottomPanel.add(loggingFrame);
        loggingFrame.setEditable(false);

        listModel = new DefaultListModel();

        boardStateJList = new JList(this.board.getBoardStates().toArray());
        boardStateJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        boardStateJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        boardStateJList.setVisibleRowCount(-1);

        boardStateJList.setModel(listModel);

        listSelectionModel = boardStateJList.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (boardStateJList.getSelectedIndex() != -1) {
                    board.setBoardState(boardStateJList.getSelectedIndex());
                    updateTiles();
                    game.setTurn(board.getBoardStates().get(boardStateJList.getSelectedIndex()).getTurn());
                    boardStateJList.clearSelection();
                }
            }
        });

        JScrollPane listScroller = new JScrollPane(boardStateJList);
        listScroller.setPreferredSize(new Dimension(250, 80));
        sideBottomPanel.add(listScroller);

        saveBoardStateButton = new JButton("Save Board State");
        saveBoardStateButton.addActionListener(this);
        sideBottomPanel.add(saveBoardStateButton);

        JPanel sideBottomBottomPanel = new JPanel();
        sideBottomBottomPanel.setLayout(new GridLayout(2,2));
        sideBottomBottomPanel.setSize(new Dimension(150, 100));
        sideBottomBottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel scorePlayerOne =  new JLabel(playerOne.getScore().toString());
        scorePlayerOne.setBackground(Color.WHITE);
        scorePlayerOne.setOpaque(true);
        JLabel scorePlayerTwo = new JLabel(playerTwo.getScore().toString());
        scorePlayerTwo.setBackground(Color.BLACK);
        scorePlayerTwo.setForeground(Color.WHITE);
        scorePlayerTwo.setOpaque(true);
        JButton resetGame = new JButton("New Game");
        JButton moveBack = new JButton("Back");

        resetGame.setMargin(new Insets(0,0,0,0));
        resetGame.addActionListener(e -> this.resetGame());

        moveBack.setMargin(new Insets(0,0,0,0));
        moveBack.addActionListener(e -> this.moveBack());

        sideBottomBottomPanel.add(scorePlayerOne);
        sideBottomBottomPanel.add(scorePlayerTwo);
        sideBottomBottomPanel.add(resetGame);
        sideBottomBottomPanel.add(moveBack);

        sideBottomPanel.add(sideBottomBottomPanel, BorderLayout.SOUTH);

        sidePanel.add(sideTopPanel, BorderLayout.NORTH);
        sidePanel.add(sideCenterPanel, BorderLayout.CENTER);
        sidePanel.add(sideBottomPanel, BorderLayout.SOUTH);

        this.getRootPane().setDefaultButton(submit);
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

    private void updateTilesFromBoardState(int index){
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        mainPanel.setLayout(new GridLayout(9, 9));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                mainPanel.add(board.getBoardStates().get(index).getSquare(row, col));
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

    private void checkRunning() {
        if (!game.getRunning()){
            this.enterScore();
        }
    }

    private void enterScore(){
        if (game.getTurn() == Color.WHITE){
            playerTwo.setScore(playerTwo.getScore()+1);
        }else {
            playerOne.setScore(playerOne.getScore()+1);
        }
        sidePanel.removeAll();
        sidePanel.revalidate();
        sidePanel.repaint();
        this.fillSide();
    }

    private void resetGame(){
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        sidePanel.removeAll();
        sidePanel.revalidate();
        sidePanel.repaint();
        this.game = new Game();
        this.board = new Board();
        this.loggingFrame = new JTextArea("Logged moves");
        this.fillTiles();
        this.fillSide();
    }

    private void moveBack(){
        Location origin = this.game.getLastMoveOrigin();
        Location target = this.game.getLastMoveTarget();

        board.moveBack(target, origin);

        this.game.setLastMoveOrigin(target);
        this.game.setLastMoveTarget(origin);
        this.setTurn();
        this.updateTiles();
    }

    private void move(Integer oldY, Integer oldX, Integer newY, Integer newX){
        Location oldLocation = new Location(oldY,oldX);
        Location newLocation = new Location(newY, newX);

        if(board.movePiece(oldLocation, newLocation, this.game)){
            loggingFrame.append("\nMove: " + oldY + "," + oldX + " > " + newY + "," + newX);
            this.game.setLastMoveOrigin(oldLocation);
            this.game.setLastMoveTarget(newLocation);
            this.updateTiles();
            this.setTurn();
            this.checkRunning();
        }else {
            loggingFrame.append("\nYour move isn't correct...");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.saveBoardStateButton){
            this.listModel.addElement(board.saveBoardState(game.getTurn()) + Integer.toString(this.board.getBoardStates().size()));
            this.boardStateJList.setModel(listModel);
            this.boardStateJList.updateUI();
        }
    }
}
