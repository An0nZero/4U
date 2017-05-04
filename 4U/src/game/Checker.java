package game;

import game.entities.Board;
import game.entities.Piece;
import game.entities.Player;

public class Checker {

	private Board board;
	
	public Checker(Board board){
		this.board = board;
	}
	
	public boolean check(Piece p){
		return checkHorizontaly(p) || checkVerticaly(p) || checkDiagonaly(p) || checkOtherDiagonaly(p);
	}
	
	private boolean checkOwner( int row, int col, Player owner ) {
	    Piece p = board.getPiece(row, col);
	    return p != null && p.getOwner().equals(owner);
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
	
	/*
	 * 1 X 2
	 */
	private boolean checkHorizontaly(Piece p){
		int col = p.getCol();
		int row = p.getRow();
		
		Player owner = p.getOwner();
		
		int count = 1;
		
		for(int auxCol = col - 1; auxCol >= 0 && checkOwner(row, auxCol, owner); auxCol--){
		    count++;
		}
		
		for(int auxCol = col + 1; auxCol < board.getColumns() && checkOwner(row, auxCol, owner); auxCol++){
		    count++;
		}
		
		return count >= 4;
	}
	
	/*
	 * X
	 * 1
	 */
	private boolean checkVerticaly(Piece p){
		int col = p.getCol();
		int row = p.getRow();
		
		Player owner = p.getOwner();
		
		int count = 1;
		
		for(int auxRow = row + 1; auxRow < board.getRows() && checkOwner(auxRow, col, owner); auxRow++){
		    count++;
		}
		
		return count >= 4;
	}
	
	/*
	 * 1
	 *  X
	 *   2
	 */
	private boolean checkDiagonaly(Piece p){
		int col = p.getCol();
		int row = p.getRow();
		
		Player owner = p.getOwner();
		
		int count = 1;
		
		for(int auxCol = col - 1, auxRow = row - 1; auxCol >= 0 && auxRow >= 0 && checkOwner(auxRow, auxCol, owner); auxCol--, auxRow--){
		    count++;
		}
		
		for(int auxCol = col + 1, auxRow = row + 1; auxCol < board.getColumns() && auxRow < board.getRows() && checkOwner(auxRow, auxCol, owner); auxCol++, auxRow++){
		    count++;
		}
		
		return count >= 4;
	}
	
	/*
	 *   1
	 *  X
	 * 2
	 */
	private boolean checkOtherDiagonaly(Piece p){
		int col = p.getCol();
		int row = p.getRow();
		
		Player owner = p.getOwner();
		
		int count = 1;
		
		for(int auxCol = col + 1, auxRow = row - 1; auxCol < board.getColumns() && auxRow >= 0 && checkOwner(auxRow, auxCol, owner); auxCol++, auxRow--){
			count++;
		}
		
		for(int auxCol = col - 1, auxRow = row + 1; auxCol >= 0 && auxRow < board.getRows() && checkOwner(auxRow, auxCol, owner); auxCol--, auxRow++){
		    count++;
		}
		
		return count >= 4;
	}
	
}
