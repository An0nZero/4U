package game.entities;

import java.util.Iterator;


/**
 * This iterator iterates through the pieces of a certain board from an initial
 * position with a given step.
 * 
 * @author MrLipdx
 *
 */
public class PieceIterator implements Iterator<Piece> {

	private Piece next;// next piece to return, null if no more pieces
	private Board board;// board to analise
	private int stepRow;// increment in the row to the next piece
	private int stepCol;// increment in the col to the next piece

	public PieceIterator(Board board, int startRow, int startCol, int stepRow, int stepCol){

		this.board = board;
		this.stepRow = stepRow;
		this.stepCol = stepCol;
		
		if (board.validPosition(startRow, startRow))
			this.next = board.getPiece(startRow, startCol);
		else
			this.next = null;
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public Piece next() {
		Piece res = next;
		int nextRow = next.getRow() + stepRow;
		int nextCol = next.getCol() + stepCol;

		if (board.validPosition(nextRow, nextCol))
			next = board.getPiece(nextRow, nextCol);
		else
			next = null;

		return res;
	}

}
