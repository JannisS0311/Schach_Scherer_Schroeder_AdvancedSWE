import _2_domainPackage.*;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GeneratePieceTest {

    @Mock
    private Tile tile;

    @BeforeEach
    public void beforeTest(){
    }

    @Test
    @DisplayName("Get Black Queen Of a board and check PieceType and PieceColor (expected: Queen.class and Color.Black")
    void generateNewQueenOnBoardAndCheckPiece(){
        //
        // Given
        //
        Board board = new Board();
        tile = org.mockito.Mockito.mock(Tile.class);
        Piece piece = board.getSquareFromLocation(new Location(1,4)).getTile().getPiece();
        when(tile.getPiece()).thenReturn(piece);

        //
        // When
        //
        Class expectedClass = Queen.class;

        //
        // Then
        //
        Queen queen = (Queen) tile.getPiece();
        assertInstanceOf(expectedClass, queen);
        assertEquals(Color.BLACK, queen.getPieceColor());
    }

    @Test
    @DisplayName("Generate New Black Queen and check PieceType (expected: Queen.class)")
    void generateNewQueenAndCheckPieceType(){
        Tile tile = new Tile("Queen", "black", new Board(), new Location(1,6));
        Queen queen = (Queen) tile.getPiece();

        assertInstanceOf(Queen.class, queen);
    }

    @Test
    @DisplayName("Generate New Black Queen and check PieceColor (expected: Color.Black)")
    void generateNewQueenAndCheckPieceColor(){
        Tile tile = new Tile("Queen", "black", new Board(), new Location(1,6));
        Queen queen = (Queen) tile.getPiece();

        assertEquals(Color.BLACK, queen.getPieceColor());
    }

    @AfterEach
    public void afterTest(){
    }
}
