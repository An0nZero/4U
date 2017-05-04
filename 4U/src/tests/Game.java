package tests;

// import static org.junit.Assert.*;
import org.junit.Test;

import exceptions.CellAlreadyOccupied;
import game.Checker;
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
    
    @Test
    public void addPiece() {
        
        Board b = new Board();
        
        assert !b.hasPiece( 0, 0 );
        assert !b.hasPiece( Board.DEFAULT_ROWS - 1, Board.DEFAULT_COLUMNS - 1 );
        
        b.setPiece( new Player(), 0, 0 );
        
        assert b.hasPiece( 0, 0 );
        
        b.resetBoard();
        
        assert !b.hasPiece( 0, 0 );
        
        b.setPiece( new Player(), Board.DEFAULT_ROWS - 1, Board.DEFAULT_COLUMNS - 1 );
        
        assert b.hasPiece( Board.DEFAULT_ROWS - 1, Board.DEFAULT_COLUMNS - 1 );
        
        try {
            b.setPiece( new Player(), Board.DEFAULT_ROWS - 1, Board.DEFAULT_COLUMNS - 1 );
            assert false;
        } catch ( CellAlreadyOccupied e ) {
            assert true;
        }
        
    }
    
    /* Our Board
     * _____________________
     * | | | | | | | | | | |
     * | | | | | | | | | | |
     * | | | | | | | | | | |
     * | | | | | | | | | | |
     * | | | | | | | | | | |
     * | | | | | | | | | | |
     * ------------------------> j
     *                     |
     *                     V
     *                     i
     */
    
    @Test
    public void checker() {
        
        Board b = new Board();
        Checker c = new Checker( b );
        
        Player p1 = new Player(), p2 = new Player();
        
        // Placing 4 pieces horizontally...
        
        b.setPiece( p2, 4, 0 );
        b.setPiece( p1, 4, 1 );
        b.setPiece( p1, 4, 2 );
        b.setPiece( p1, 4, 3 );
        
        assert !c.check( b.getPiece( 4, 3 ) );
        
        b.setPiece( p1, 4, 4 );
        
        assert c.check( b.lastPiece() );
        
        // Placing 4 pieces vertically...
        b.resetBoard();
        
        b.setPiece( p2, 4, 0 );
        b.setPiece( p1, 3, 0 );
        b.setPiece( p1, 2, 0 );
        b.setPiece( p1, 1, 0 );
        
        assert !c.check( b.getPiece( 1, 0 ) );
        
        b.setPiece( p1, 0, 0 );
        
        assert c.check( b.lastPiece() );
        
        // TODO add more tests...
        
        
    }

}
