package tests;

// import static org.junit.Assert.*;
import org.junit.Test;

import exceptions.CellAlreadyOccupiedException;
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

		assert !b.hasPiece(0, 0);
		assert !b.hasPiece(b.getRows() - 1, b.getColumns() - 1);

		try {
			b.setPiece(new Player(), 0);
		} catch (CellAlreadyOccupiedException e1) {
			assert false;
		}

		assert !b.hasPiece(0, 0);
		assert b.hasPiece(b.getRows() - 1, 0);

		assert b.lastPiece() != null;

		b.resetBoard();

		assert b.lastPiece() == null;

		assert !b.hasPiece(0, 0);

		try {
			b.setPiece(new Player(), b.getColumns() - 1);
		} catch (CellAlreadyOccupiedException e1) {
			assert false;
		}

		assert b.hasPiece(b.getRows() - 1, b.getColumns() - 1);

		try {
			b.setPiece(new Player(), b.getColumns() - 1);
			b.setPiece(new Player(), b.getColumns() - 1);
			b.setPiece(new Player(), b.getColumns() - 1);
			b.setPiece(new Player(), b.getColumns() - 1);
			b.setPiece(new Player(), b.getColumns() - 1);
		} catch (CellAlreadyOccupiedException e1) {
			assert false;
		}

		try {
			b.setPiece(new Player(), b.getColumns() - 1);
			assert false;
		} catch (CellAlreadyOccupiedException e) {
			assert true;
		}

	}

	/*
	 * Our Board _____________________ | | | | | | | | | | | | | | | | | | | | |
	 * | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | |
	 * | | | | | | | | ------------------------> j | V i
	 */

	@Test
	public void checker() {

		Board b = new Board();
		Checker c = new Checker(b);

		Player p1 = new Player(), p2 = new Player();

		// Placing 4 pieces horizontally...

		try {
			b.setPiece(p2, 0);
			b.setPiece(p1, 1);
			b.setPiece(p1, 2);
			b.setPiece(p1, 3);
		} catch (CellAlreadyOccupiedException e) {
			assert false;
		}

		assert !c.check(b.lastPiece());

		try {
			b.setPiece(p1, 4);
		} catch (CellAlreadyOccupiedException e) {
			assert false;
		}

		assert c.check(b.lastPiece());

		// Placing 4 pieces vertically...
		b.resetBoard();

		try {
			b.setPiece(p2, 0);
			b.setPiece(p1, 0);
			b.setPiece(p1, 0);
			b.setPiece(p1, 0);
		} catch (CellAlreadyOccupiedException e) {
			assert false;
		}

		assert !c.check(b.lastPiece());

		try {
			b.setPiece(p1, 0);
		} catch (CellAlreadyOccupiedException e) {
			assert false;
		}

		assert c.check(b.lastPiece());

		// TODO add more tests...

	}

}
