package Main;

public class KingedPiece extends Piece {

	
	private int team; // 3(top) or 4(bottom)
	private int row, col;
	
	public KingedPiece(int team, int i, int j) {
		super(team, i, j);
	}
	
	public void setLoc(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public int getTeam() {
		return this.team;
	}
	
	public void setTeam(int t) {
		this.team = t;
	}
	
	public Cell[] checkSkips(Piece[][] pieces, Cell[][] board) { // some elements may be null so use try/catch
		
		int i = 0;
		Cell[] moves = new Cell[4];
		
		//  check 4 corners   || Player 1, non-kinged pieces can only move lower left and lower right.
		// ================== || Player 2, non-kinged pieces can only move upper left and upper right.
		//   1  |     |  2    ||
		// ------------------ || Kinged(subclass) pieces can move any direction.
		//      |  P  |       ||
		// ------------------ ||
		//   3  |     |  4    ||
		// ================== ||
		
		// each of the following try/catch blocks checks a corner TO SEE IF THERE'S A PIECE
		// if so, check the spot behind it to see if it's an empty open spot
		try {
			if(pieces[this.row - 1][this.col - 1] != null && pieces[this.row - 1][this.col - 1].getTeam() != this.getTeam()) { // upper left (1)
				if(pieces[this.row - 2][this.col - 2] == null && board[this.row - 2][this.col - 2].getState() == 1) {
					moves[i] = board[this.row - 2][this.col - 2];
					i++;
				}
			} else {
				moves[i] = null;
				i++;
			}
		} catch(Exception e) {}
		
		try {
			if(pieces[this.row - 1][this.col + 1] != null && pieces[this.row - 1][this.col + 1].getTeam() != this.getTeam()) { // upper right (2)
				if(pieces[this.row - 2][this.col + 2] == null && board[this.row - 2][this.col + 2].getState() == 1) {
					moves[i] = board[this.row - 2][this.col + 2];
					i++;
				}
			} else {
				moves[i] = null;
				i++;
			}
		} catch(Exception e) {}
		
		try {
			if(pieces[this.row + 1][this.col - 1] != null && pieces[this.row + 1][this.col - 1].getTeam() != this.getTeam()) { //lower left (3)
				if(pieces[this.row + 2][this.col - 2] == null && board[this.row + 2][this.col - 2].getState() == 1) {
					moves[i] = board[this.row + 2][this.col - 2];
					i++;
				}
			} else {
				moves[i] = null;
				i++;
			}
		} catch(Exception e) {}
		
		try {
			if(pieces[this.row + 1][this.col + 1] != null && pieces[this.row + 1][this.col + 1].getTeam() != this.getTeam()) { // lower right (4)
				if(pieces[this.row + 2][this.col + 2] == null && board[this.row + 2][this.col + 2].getState() == 1) {
					moves[i] = board[this.row + 2][this.col + 2];
				}
			} else {
				moves[i] = null;
			}
		} catch(Exception e) {}
		
		return moves;
	}

	
	/**
	 * This method finds all of the possible moves for a given piece.
	 * @return Cell[] of possible moves
	 */
	public Cell[] checkMoves(Piece[][] pieces, Cell[][] board) { // some elements may be null so use try/catch
		
		int i = 0;
		Cell[] moves = new Cell[4];
		
		//  check 4 corners   || Player 1, non-kinged pieces can only move lower left and lower right.
		// ================== || Player 2, non-kinged pieces can only move upper left and upper right.
		//   1  |     |  2    ||
		// ------------------ || Kinged(subclass) pieces can move any direction.
		//      |  P  |       ||
		// ------------------ ||
		//   3  |     |  4    ||
		// ================== ||
		
		// each of the following try/catch blocks checks a corner(see above)
		// if there is no piece there AND it's a light spot, add that CELL to the Cell[].
		try {
			if(pieces[this.row - 1][this.col - 1] == null && board[this.row - 1][this.col - 1].getState() == 1) { // upper left (1)
				moves[i] = board[this.row - 1][this.col - 1];
				i++;
			} else {
				moves[i] = null;
				i++;
			}
		} catch(Exception e) {}
		
		try {
			if(pieces[this.row - 1][this.col + 1] == null && board[this.row - 1][this.col + 1].getState() == 1) { // upper right (2)
				moves[i] = board[this.row - 1][this.col + 1];
				i++;
			} else {
				moves[i] = null;
				i++;
			}
		} catch(Exception e) {}
		
		try {
			if(pieces[this.row + 1][this.col - 1] == null && board[this.row + 1][this.col - 1].getState() == 1) { // lower left (3)
				moves[i] = board[this.row + 1][this.col - 1];
				i++;
			} else {
				moves[i] = null;
				i++;
			}
		} catch(Exception e) {}
		
		try {
			if(pieces[this.row + 1][this.col + 1] == null && board[this.row + 1][this.col + 1].getState() == 1) { // lower right (4)
				moves[i] = board[this.row + 1][this.col + 1];
			} else {
				moves[i] = null;
			}
		} catch(Exception e) {}
		
		
		return moves;
	}
}
