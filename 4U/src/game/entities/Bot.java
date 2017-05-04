package game.entities;

import java.util.Random;

import game.exceptions.ColumnFullException;

public class Bot extends Player {

	private Board board;
	private int columns;
	private Random rand;

	public Bot(String name, Board board) {
		super(name);
		this.board = board;
		this.columns = board.getColumns();
		this.rand = new Random();
	}

	private int findClosestLeft(int col) {
		for (int i = col - 1; i >= 0; i--) {
			if (!board.colIsFull(i)) {
				return i;
			}
		}
		return -1;
	}

	private int findClosestRight(int col) {
		for (int i = col + 1; i < columns; i++) {
			if (!board.colIsFull(i)) {
				return i;
			}
		}
		return -1;
	}

	// Usage: bot.doPlay(bot.nextPlay())

	public void doPlay(int col) throws ColumnFullException {
		board.setPiece(this, col);
	}

	public int nextPlay() {

		// Random integer in [0, columns[
		int randCol = rand.nextInt(columns);

		if (!board.colIsFull(randCol)) {
			return randCol;
		}

		// If the column number 'randCol' is full, we use this to decide from
		// which side we will try to look for a non-full column: left or right
		boolean leftFirst = rand.nextInt(2) == 0;

		int col = leftFirst ? findClosestLeft(randCol) : findClosestRight(randCol);
		return col > -1 ? col : (leftFirst ? findClosestRight(randCol) : findClosestLeft(randCol));

	}

}
