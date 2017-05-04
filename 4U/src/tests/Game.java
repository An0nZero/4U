package tests;

// import static org.junit.Assert.*;
import org.junit.Test;

import exceptions.ColumnFullException;
import game.Checker;
import game.entities.Board;
import game.entities.Player;

public class Game {

	private void set(Board b, Player p, int col) {
		try {
			b.setPiece(p, col);
		} catch (ColumnFullException e) {
			assert false;
		}
	}

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

		assert !b.hasPiece(0, 0);
		assert !b.hasPiece(b.getRows() - 1, b.getColumns() - 1);

		set(b, new Player("p"), 0);

		assert !b.hasPiece(0, 0);
		assert b.hasPiece(b.getRows() - 1, 0);

		assert b.lastPiece() != null;

		b.resetBoard();

		assert b.lastPiece() == null;

		assert !b.hasPiece(0, 0);

		set(b, new Player("p"), b.getColumns() - 1);

		assert b.hasPiece(b.getRows() - 1, b.getColumns() - 1);

		set(b, new Player("p"), b.getColumns() - 1);
		set(b, new Player("p"), b.getColumns() - 1);
		set(b, new Player("p"), b.getColumns() - 1);
		set(b, new Player("p"), b.getColumns() - 1);
		set(b, new Player("p"), b.getColumns() - 1);

		try {
			b.setPiece(new Player("p"), b.getColumns() - 1);
			assert false;
		} catch (ColumnFullException e) {
			assert true;
		}

	}

	//
	// Our Board
	// _____________________
	// | | | | | | | | | | |
	// | | | | | | | | | | |
	// | | | | | | | | | | |
	// | | | | | | | | | | |
	// | | | | | | | | | | |
	// | | | | | | | | | | |
	// -----------------------> j
	// |
	// V
	// i
	//

	@Test
	public void checker() {

		Board b = new Board();
		Checker c = new Checker(b);

		Player p1 = new Player("p1"), p2 = new Player("p2");

		// ## The last piece inserted is on the end of the line

		// Placing 4 pieces horizontally...

		set(b, p2, 0);
		set(b, p1, 1);
		set(b, p1, 2);
		set(b, p1, 3);

		assert !c.check(b.lastPiece());

		set(b, p1, 4);

		assert c.check(b.lastPiece());

		// Placing 4 pieces vertically...
		b.resetBoard();

		set(b, p2, 0);
		set(b, p1, 0);
		set(b, p1, 0);
		set(b, p1, 0);

		assert !c.check(b.lastPiece());

		set(b, p1, 0);

		assert c.check(b.lastPiece());

		// Placing 4 pieces diagonally...
		b.resetBoard();

		set(b, p2, 0);
		set(b, p2, 0);
		set(b, p2, 0);
		set(b, p2, 0);
		set(b, p1, 0);

		set(b, p2, 1);
		set(b, p2, 1);
		set(b, p2, 1);
		set(b, p1, 1);

		set(b, p2, 2);
		set(b, p2, 2);
		set(b, p1, 2);

		assert !c.check(b.lastPiece());

		set(b, p2, 3);
		set(b, p1, 3);

		assert c.check(b.lastPiece());

		// Placing 4 pieces in the other diagonal...
		b.resetBoard();

		set(b, p2, 4);
		set(b, p2, 4);
		set(b, p2, 4);
		set(b, p2, 4);
		set(b, p1, 4);

		set(b, p2, 3);
		set(b, p2, 3);
		set(b, p2, 3);
		set(b, p1, 3);

		set(b, p2, 2);
		set(b, p2, 2);
		set(b, p1, 2);

		assert !c.check(b.lastPiece());

		set(b, p2, 1);
		set(b, p1, 1);

		assert c.check(b.lastPiece());

		// ## The last piece inserted is on the middle of the line

		// Placing 4 pieces horizontally...
		b.resetBoard();

		set(b, p2, 0);
		set(b, p1, 1);
		set(b, p1, 2);
		set(b, p1, 4);

		assert !c.check(b.lastPiece());

		set(b, p1, 3);

		assert c.check(b.lastPiece());

		// Placing 4 pieces diagonally...
		b.resetBoard();

		set(b, p1, 0);
		set(b, p1, 0);
		set(b, p1, 0);
		set(b, p1, 0);
		set(b, p2, 0);

		set(b, p1, 1);
		set(b, p1, 1);
		set(b, p1, 1);

		set(b, p1, 2);
		set(b, p1, 2);
		set(b, p2, 2);

		set(b, p1, 3);
		set(b, p2, 3);

		assert !c.check(b.lastPiece());

		set(b, p2, 1);

		assert c.check(b.lastPiece());

		// Placing 4 pieces in the other diagonal...
		b.resetBoard();

		set(b, p1, 4);
		set(b, p1, 4);
		set(b, p1, 4);
		set(b, p1, 4);
		set(b, p2, 4);

		set(b, p1, 3);
		set(b, p1, 3);
		set(b, p1, 3);

		set(b, p1, 2);
		set(b, p1, 2);
		set(b, p2, 2);

		set(b, p1, 1);
		set(b, p2, 1);

		assert !c.check(b.lastPiece());

		set(b, p2, 3);

		assert c.check(b.lastPiece());

		// ## The last piece inserted is on the start of the line

		// Placing 4 pieces horizontally...
		b.resetBoard();

		set(b, p2, 0);
		set(b, p1, 2);
		set(b, p1, 3);
		set(b, p1, 4);

		assert !c.check(b.lastPiece());

		set(b, p1, 1);

		assert c.check(b.lastPiece());

		// Placing 4 pieces diagonally...
		b.resetBoard();

		set(b, p2, 0);
		set(b, p2, 0);
		set(b, p2, 0);
		set(b, p2, 0);

		set(b, p2, 1);
		set(b, p2, 1);
		set(b, p2, 1);
		set(b, p1, 1);

		set(b, p2, 2);
		set(b, p2, 2);
		set(b, p1, 2);

		set(b, p2, 3);
		set(b, p1, 3);

		assert !c.check(b.lastPiece());

		set(b, p1, 0);

		assert c.check(b.lastPiece());

		// Placing 4 pieces in the other diagonal...
		b.resetBoard();

		set(b, p2, 4);
		set(b, p2, 4);
		set(b, p2, 4);
		set(b, p2, 4);

		set(b, p2, 3);
		set(b, p2, 3);
		set(b, p2, 3);
		set(b, p1, 3);

		set(b, p2, 2);
		set(b, p2, 2);
		set(b, p1, 2);

		set(b, p2, 1);
		set(b, p1, 1);

		assert !c.check(b.lastPiece());

		set(b, p1, 4);

		assert c.check(b.lastPiece());

	}

}
