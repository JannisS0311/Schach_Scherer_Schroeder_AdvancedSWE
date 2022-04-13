import _2_domainPackage.*;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

public class BoardFilledCorrectlyTest {

    @BeforeEach
    public void beforeTest(){
    }

    @RepeatedTest(50)
    @DisplayName("Check two random tiles of two initialized board if the pieces are the same (expected: equal)")
    void compareRandomValidTilesOfBoardsIfEqual(){
        Board board1 = new Board();
        Board board2 = new Board();

        int row = (int) (Math.random()*8 + 1);
        int col = (int) (Math.random()*8 + 1);

        String pieceType1 = board1.getSquareFromLocation(new Location(row, col)).getTile().getPieceType();
        String pieceType2 = board2.getSquareFromLocation(new Location(row,col)).getTile().getPieceType();

        String pieceColor1 = board1.getSquareFromLocation(new Location(row, col)).getTile().getPieceColorAsString();
        String pieceColor2 = board1.getSquareFromLocation(new Location(row, col)).getTile().getPieceColorAsString();

        assertEquals(pieceType1, pieceType2, "Right pieceType");
        assertEquals(pieceColor1, pieceColor2, "Right pieceColor");
    }

    @Test
    @DisplayName("Test if the two locations for queens on the board are occupied with queens (expected: Queen)")
    void checkIfQueensLocationsContainQueens(){
        Board board = new Board();

        String pieceType1 = board.getSquareFromLocation(new Location(1,4)).getTile().getPieceType();
        String pieceType2 = board.getSquareFromLocation(new Location(8,4)).getTile().getPieceType();

        assertEquals("Queen", pieceType1);
        assertEquals("Queen", pieceType2);
        }

    @Test
    @DisplayName("Check if the getter of a Piece at the location of the boards labeling throws an exception.")
    void checkGetPieceAtLabelingLocationThrowsException(){
        Board board = new Board();

        Throwable exception = assertThrows(NullPointerException.class, () -> board.getSquareFromLocation(new Location(0, 0)).getTile().getPieceType());
        assertEquals("Cannot invoke \"_2_domainPackage.Tile.getPieceType()\" because the return value of \"_1_adaptersPackage.Square.getTile()\" is null", exception.getMessage());
    }

    @Test
    @DisplayName("Check if the getter of a Piece at an invalid location throws an exception.")
    void checkGetPieceAtInvalidRowLocationThrowsException(){
        Board board = new Board();

        int row = (int) (Math.random()*8 + 9);
        int col = (int) (Math.random()*8 + 1);

        Throwable exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.getSquareFromLocation(new Location(row, col)).getTile().getPieceType());
        assertEquals(String.format("Index %d out of bounds for length 9", row), exception.getMessage());
    }

    @Test
    @DisplayName("Check if the getter of a Piece at an invalid location throws an exception.")
    void checkGetPieceAtInvalidColLocationThrowsException(){
        Board board = new Board();

        int row = (int) (Math.random()*8 + 1);
        int col = (int) (Math.random()*8 + 9);

        Throwable exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.getSquareFromLocation(new Location(row, col)).getTile().getPieceType());
        assertEquals(String.format("Index %d out of bounds for length 9", col), exception.getMessage());
    }

    @Test
    @DisplayName("Check if the getter of a Piece at a null location throws an exception.")
    void checkGetPieceAtLocationNullThrowsException(){
        Board board = new Board();

        Throwable exception = assertThrows(NullPointerException.class, () -> board.getSquareFromLocation(null).getTile().getPieceType());
        assertEquals("Cannot invoke \"_2_domainPackage.Location.getRowCoordinate()\" because \"location\" is null", exception.getMessage());
    }

    @AfterEach
    public void afterTest(){
    }
}
