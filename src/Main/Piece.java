package Main;

public class Piece {
	private int team; // 3(top) or 4(bottom)
	private int row, col;
	
	public Piece(int team, int i, int j) {
		this.team = team;
		row = i;
		col = j;
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
		
		//remove illegal moves depending on the pieces team
		// *** THE KINGED PIECE CLASS SHOULD OVERRIDE THIS PART ***
		if(this.getTeam() == 3) {
			moves[0] = null;
			moves[1] = null;
		} else if(this.getTeam() == 4) {
			moves[2] = null;
			moves[3] = null;
		}
		
		Cell[] goodMoves = new Cell[2];
		int j = 0;
		for(Cell c : moves) {
			if (c == null) {
				
			} else {
				goodMoves[j] = c;
				j++;
			}
		}
		return goodMoves;
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
		
		//remove illegal moves depending on the pieces team
		// *** THE KINGED PIECE CLASS SHOULD OVERRIDE THIS PART ***
		if(this.getTeam() == 3) {
			moves[0] = null;
			moves[1] = null;
		} else if(this.getTeam() == 4) {
			moves[2] = null;
			moves[3] = null;
		}
		
		return moves;
	}
	
	
	/**
	 * This method is used strictly for debugging purposes.
	 * @param pieces
	 * @param board
	 */
	public Integer[] displayMoves(Piece[][] pieces, Cell[][] board) {
		
		Cell[] moves = this.checkMoves(pieces, board);
		Integer [] move = null;
		
		System.out.printf("Moves for Piece[%d][%d]: %n", this.getRow(), this.getCol());
		for(Cell c : moves) {
			
			try {
				//System.out.printf("  Row: %d, Col: %d%n",c.getRow(),c.getCol());
				move[0] = c.getRow();
				move[1] = c.getCol();
			}catch(Exception e) {
				//System.out.println("  null lol");
				move[0] = null;
				move[1] = null;
			}
			
		}
		return move;
		//System.out.print("\n\n");
	}
	
	
	/**
	 * This method is used strictly for debugging purposes.
	 * @param pieces
	 * @param board
	 */
	public void displaySkips(Piece[][] pieces, Cell[][] board) {
		
		Cell[] moves = this.checkSkips(pieces, board);
		
		System.out.printf("Skips for Piece[%d][%d]: %n", this.getRow(), this.getCol());
		for(Cell c : moves) {
			
			try {
				System.out.printf("  Row: %d, Col: %d%n",c.getRow(),c.getCol());
			}catch(Exception e) {
				System.out.println("  null lol");
			}
			
		}
		System.out.print("\n\n");
	}

	
	
	public Piece[][] movePiece(Cell dest, Piece[][] pieces) {
		
		int currentRow = this.row;
		int currentCol = this.col;
		
		int team = this.getTeam();
		
		int newRow = dest.getRow();
		int newCol = dest.getCol();
		this.setLoc(newRow, newCol);
		
		//first, remove piece from pieces array
		pieces[currentRow][currentCol] = null;
		
		//second, add piece to new destination
		pieces[newRow][newCol] = new Piece(team, newRow, newCol);
		
		//finally, return new board
		return pieces;
	}
	
	
	
	public Piece[][] skipPiece(Cell dest, Piece skipped, Piece[][] pieces) {
		
		int currentRow = this.row;
		int currentCol = this.col;
		
		int team = this.getTeam();
		
		int newRow = dest.getRow();
		int newCol = dest.getCol();
		this.setLoc(newRow, newCol);
		
		//first, remove piece from pieces array
		pieces[currentRow][currentCol] = null;
		
		//second, add piece to new destination
		pieces[newRow][newCol] = new Piece(team, newRow, newCol);
		
		//third, remove the skipped piece
		pieces[skipped.getRow()][skipped.getCol()] = null;
		
		//finally, return new board
		return pieces;
	}
	
	
	// This was a previous method we used, just keeping it incase :)
		//
		/**
		public Piece[] possibleMoves(Piece[] moves, Piece[][] pieces) {
			
			//we assume moves[0] = upper left, moves[1] = upper right, and so on.
			//determine length
			int len = 0;
			for(Piece p : moves) { //this loop determines the length of the new array
				if(this.getTeam() == 3) {
					if(moves[0] != null) {
						len++;
					}
					if(moves[1] != null) {
						len++;
					}
				}
				if(this.getTeam() == 4) {
					if(moves[2] != null) {
						len++;
					}
					if(moves[3] != null) {
						len++;
					}
				}
			}//end of for loop
			
			int i = 0;
			Piece[] goodMoves = new Piece[len];
			
			for(Piece p : moves) {
				if(this.getTeam() == 3) {            //if top team
					if(isPossible(p,pieces)) {
						goodMoves[i] = moves[0];
						i++;
					}
					if(isPossible(p,pieces)) {
						goodMoves[i] = moves[1];
						i++;
					}
				}
				if(this.getTeam() == 4) {           //if bottom team
					if(isPossible(p,pieces)) {
						goodMoves[i] = moves[2];
						i++;
					}
					if(isPossible(p,pieces)) {
						goodMoves[i] = moves[3];
						i++;
					}
				}
			}//end of for loop
			return goodMoves;
		}
		*/
}
