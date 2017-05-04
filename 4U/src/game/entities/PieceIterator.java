package game.entities;

import java.util.Iterator;

import game.exceptions.InvalidBoardPositionException;

/**
 * This iterator iterates through the pieces of a certain board
 * from an initial position with a given step.
 * @author MrLipdx
 *
 */
public class PieceIterator implements Iterator<Piece> {

	private Piece next;//next piece to return, null if no more pieces
	private Board board;//board to analise
	private int stepRow;//increment in the row to the next piece
	private int stepCol;//increment in the col to the next piece

	public PieceIterator(Board board, int startRow, int startCol, int stepRow, int stepCol) throws InvalidBoardPositionException {
		
		this.board = board;
		this.stepRow = stepRow;
		this.stepCol = stepCol;
		this.next = board.getPiece(startRow, startCol);
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public Piece next() {
		Piece res = next;
		
		try {
			next = board.getPiece(next.getRow() + stepRow, next.getCol() + stepCol);
		} catch (InvalidBoardPositionException e) {
			next = null;
		}
		
		return res;
	}

}
