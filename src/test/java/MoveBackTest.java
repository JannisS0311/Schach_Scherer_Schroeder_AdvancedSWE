import _1_adaptersPackage.GameFrame;
import _2_domainPackage.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class MoveBackTest {
    Board board, comparationBoard;
    Game game;
    GameFrame gameFrame;
    Game comparationGame;
    GameFrame comparationFrame;
    int oldRow;
    int oldCol;
    int newRow;
    int newCol;

    @BeforeEach
    public void beforeTest(){
        board = new Board();

        comparationBoard = board;
        comparationGame = new Game();
        comparationFrame = new GameFrame(comparationBoard, comparationGame);
        comparationFrame.init();

        oldRow = (int) (Math.random()*2 + 1);
        oldCol = (int) (Math.random()*8 + 1);
        newRow = (int) (Math.random()*8 + 1);
        newCol = (int) (Math.random()*8 + 1);

    }

    // Test doesn't work yet, working on it...
    @RepeatedTest(30)
    @DisplayName("Test if the board is moved back correctly after a random move.")
    void moveBackTest() {
        //comparationBoard.changeBoard(new Location(7,1), new Location(6,1));
        //newBoard.moveBack(new Location(oldRow,oldCol), new Location(newRow,newCol));

        //assertEquals(comparationBoard.getSquareFromLocation(new Location(7,1)).getTile().getPiece(), board.getSquareFromLocation(new Location(7,1)).getTile().getPiece());
    }

    @AfterEach
    public void afterTest(){

    }

}
