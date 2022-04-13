import _2_domainPackage.Board;
import _2_domainPackage.Location;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TilesBetweenEmptyTest {

    @BeforeEach
    public void beforeTest(){
    }

    @Test
    @DisplayName("Test if Locations are empty with not initialized array (expected: true)")
    void areLocationsEmptyWithEmptyArrayListTest(){
        Board board = new Board();
        assertEquals(board.areLocationsEmpty(new ArrayList<>()), true);
    }

    @Test
    @DisplayName("Test if Locations are empty with null array (expected: Exception)")
    void areLocationsEmptyWithNoArrayListTest(){
        Board board = new Board();
        Throwable exception = assertThrows(NullPointerException.class, () -> board.areLocationsEmpty(null));
        assertEquals("Cannot invoke \"java.util.ArrayList.size()\" because \"locations\" is null", exception.getMessage());
    }

    @Test
    @DisplayName("Test if Locations are empty with array with one occupied tile (expected: false)")
    void areLocationsNotEmptyWithOneOccupiedTileBetweenTest(){
        Board board = new Board();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Location(1,1));

        assertEquals(false, board.areLocationsEmpty(arrayList));
    }

    @Test
    @DisplayName("Test if Locations are empty with array with one free tile (expected: true)")
    void areLocationsNotEmptyWithOneFreeTileBetweenTest(){
        Board board = new Board();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Location(3,3));

        assertEquals(true, board.areLocationsEmpty(arrayList));
    }

    @AfterEach
    public void afterTest(){
    }
}
