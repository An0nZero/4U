package game;

import game.entities.Board;
import game.entities.Piece;
import game.entities.PieceIterator;
import game.exceptions.InvalidBoardPositionException;

public class Checker {

	private static final int CONSECUTIVE_PIECES = 4;

	// Matrix notation
	private static final int I = 0;
	private static final int J = 1;

	// Directions as vectors
	private static final int[] UP = { 0, -1 };
	private static final int[] DOWN = { 0, 1 };
	private static final int[] LEFT = { -1, 0 };
	private static final int[] RIGHT = { 1, 0 };
	private static final int[] UPLEFT = { -1, -1 };
	private static final int[] UPRIGHT = { 1, -1 };
	private static final int[] DOWNLEFT = { -1, 1 };
	private static final int[] DOWNRIGHT = { 1, 1 };

	// Orientations
	private static final int[][] HORIZONTAL = { LEFT, RIGHT };
	private static final int[][] VERTICAL = { UP, DOWN };
	// diagonal that goes like / 
	private static final int[][] DIAGONAL_UPLEFT = { DOWNRIGHT, UPLEFT }; 
	// diagonal that goes like \
	private static final int[][] DIAGONAL_UPRIGHT = { DOWNLEFT, UPRIGHT }; 

	// All Orientations
	private static final int[][][] ALL_ORIENTATIONS = {HORIZONTAL,VERTICAL,DIAGONAL_UPLEFT,DIAGONAL_UPRIGHT};

	private Board board;

	public Checker(Board board) {
		this.board = board;
	}

	/**
	 * Checks if a given Piece gave a winging condition
	 * @param p piece that could give a wining condition
	 * @return true if p fulfils the wining condition
	 * 		   false if p does not fulfil the wining condition
	 */
	public boolean check(Piece p) {
		int orientationSum;

		for (int[][] orientation : ALL_ORIENTATIONS) {
			orientationSum = 0;

			for (int[] direction : orientation) {
				PieceIterator it = null;

				try {
					it = new PieceIterator(board, p.getRow(), p.getCol(), direction[I], direction[J]);
				} catch (InvalidBoardPositionException e) {
					// This piece of code is unreachable as long as the piece is
					// correctly created
					e.printStackTrace();
					System.exit(1);
				}

				while (it.hasNext()) {
					if (it.next().getOwner() == p.getOwner()) {
						orientationSum++;
						// while counting orientation sum piece p is couned
						// twice so that requiers a +1 to correct it
						if (orientationSum >= CONSECUTIVE_PIECES + 1)
							return true;
					} else {
						break;
					}
				}
			}
		}
		return false;
	}
}