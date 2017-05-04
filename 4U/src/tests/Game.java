package tests;

// import static org.junit.Assert.*;
import org.junit.Test;
import game.entities.Board;
import game.entities.Player;

public class Game {

    @Test
    public void initWorks() {
        Board b1 = new Board();
        Board b2 = new Board(5, 5);
        assert b1.getRows() == 6;
        assert b1.getColumns() == 10;
        assert b2.getRows() == 5;
        assert b2.getColumns() == 5;
    }
    
    /* Our Board
     * _____________________
     * | | | | | | | | | | |
     * | | | | | | | | | | |
     * | | | | | | | | | | |
     * | | | | | | | | | | |
     * | | | | | | | | | | |
     * | | | | | | | | | | |
     * ---------------------
     */
    
    @Test
    public void addPiece() {
        
        Board b = new Board();
        
        assert !b.hasPiece( 0, 0 );
        assert !b.hasPiece( Board.DEFAULT_ROWS - 1, Board.DEFAULT_COLUMNS - 1 );
        
        b.setPiece( new Player(), 0, 0 );
        
        assert b.hasPiece( 0, 0 );
        
        b.resetBoard();
        
        assert !b.hasPiece( 0, 0 );
        
    }

}
