package game;

import game.entities.Board;
import game.entities.Piece;

public class Checker {

	private Board board;
	
	public Checker(Board board){
		this.board = board;
	}
	
	public boolean check(Piece p){
		return checkHorizontaly(p) && checkVerticaly(p) && checkDiagonaly(p) && checkOtherDiagonaly(p);
	}
	
	public boolean checkHorizontaly(Piece p){
		int col = p.getCol();
		int row = p.getRow();
		
		int countRight = 0;
		int countLeft = 0;
		
		for(int auxCol = col + 1; board.getPiece(row, auxCol).getOwner().equals(p.getOwner()) && auxCol < board.getColumns() ; auxCol++){
			countRight = board.getPiece(row, auxCol).getOwner().equals(p.getOwner()) ? countRight + 1 : countRight;
		}
		
		for(int auxCol = col - 1; board.getPiece(row, auxCol).getOwner().equals(p.getOwner()) && auxCol >= 0; auxCol--){
			countLeft = board.getPiece(row, auxCol).getOwner().equals(p.getOwner()) ? countLeft + 1 : countLeft;
		}
		
		return (countLeft + countRight + 1) >= 4;
	}
	
	public boolean checkVerticaly(Piece p){
		int col = p.getCol();
		int row = p.getRow();
		
		int countDown = 0;
		
		for(int auxRow = row + 1; board.getPiece(auxRow, col).getOwner().equals(p.getOwner()) && auxRow < board.getRows(); auxRow++){
			countDown = board.getPiece(auxRow, col).getOwner().equals(p.getOwner()) ? countDown + 1 : countDown;
		}
		
		return (countDown + 1) >= 4;
	}
	
	public boolean checkDiagonaly(Piece p){
		int col = p.getCol();
		int row = p.getRow();
		
		int countUp = 0;
		int countDown = 0;
		
		for(int auxCol = col - 1, auxRow = row - 1; board.getPiece(auxRow, auxCol).getOwner().equals(p.getOwner()) && auxCol >= 0 && auxRow >= 0; auxCol--, auxRow--){
			countUp = board.getPiece(auxRow, auxCol).getOwner().equals(p.getOwner()) ? countUp + 1 : countUp;
		}
		
		for(int auxCol = col + 1, auxRow = row + 1; board.getPiece(row, auxCol).getOwner().equals(p.getOwner()) && auxCol < board.getColumns() && auxRow < board.getRows(); auxCol++, auxRow++){
			countDown = board.getPiece(auxRow, auxCol).getOwner().equals(p.getOwner()) ? countDown + 1 : countDown;
		}
		
		return (countDown + countUp + 1) >= 4;
	}
	
	public boolean checkOtherDiagonaly(Piece p){
		int col = p.getCol();
		int row = p.getRow();
		
		int countUp = 0;
		int countDown = 0;
		
		for(int auxCol = col + 1, auxRow = row - 1; board.getPiece(auxRow, auxCol).getOwner().equals(p.getOwner()) && auxCol < board.getColumns() && auxRow >= 0; auxCol++, auxRow--){
			countUp = board.getPiece(auxRow, auxCol).getOwner().equals(p.getOwner()) ? countUp + 1 : countUp;
		}
		
		for(int auxCol = col - 1, auxRow = row + 1; board.getPiece(row, auxCol).getOwner().equals(p.getOwner()) && auxCol >= 0 && auxRow < board.getRows(); auxCol--, auxRow++){
			countDown = board.getPiece(auxRow, auxCol).getOwner().equals(p.getOwner()) ? countDown + 1 : countDown;
		}
		
		return (countDown + countUp + 1) >= 4;
	}
	
}
