package game.entities;

import exceptions.ColumnFullException;

public class Board {

	private static final int DEFAULT_ROWS = 6;
	private static final int DEFAULT_COLUMNS = 10;

	private final int rows; // number of rows on the board
	private final int columns; // number of columns on the board

	private int[] pieceCount; // number of pieces on each column
	private Piece[][] board; // array of pieces representing a game board
	private Piece lastPiece; // last piece that was played

	public Board() {
		this(DEFAULT_ROWS, DEFAULT_COLUMNS);
	}

	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.board = new Piece[rows][columns];
		this.pieceCount = new int[columns];
	}

	/**
	 * Sets a Piece on the board in the first available row of a given column
	 * 
	 * @param owner
	 *            owner of the Piece
	 * @param col
	 *            column index on where to put the Piece
	 * @throws CellAlreadyOccupiedException
	 * @throws ColumnFullException
	 *             thrown if the column index references a full column
	 */
	public void setPiece(Player owner, int col) throws ColumnFullException {

		if (colIsFull(col))
			throw new ColumnFullException();

		pieceCount[col]++;

		int row = rows - pieceCount[col];

		lastPiece = board[row][col] = new Piece(owner, row, col);

	}

	/**
	 * Returns the last piece played.
	 * 
	 * @return last piece played
	 */
	public Piece lastPiece() {
		return this.lastPiece;
	}

	public boolean hasPiece(int row, int col) {
		return board[row][col] != null;
	}

	public Piece getPiece(int row, int col) {
		return board[row][col];
	}

	public int getRows() {
		return this.rows;
	}

	public int getColumns() {
		return this.columns;
	}

	public void resetBoard() {
		this.lastPiece = null;
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				board[i][j] = null;
			}
		}

		for (int i = 0; i < columns; i++) {
			pieceCount[i] = 0;
		}
	}

	public static boolean validRowsColumns(int rows, int columns) {
		return rows > 4 && columns > 4;
	}

	/**
	 * Verifies if the column with a given index is full or not.
	 * 
	 * @param col
	 *            index of the column to verify
	 */
	public boolean colIsFull(int col) {
		return pieceCount[col] >= rows;
	}

}
