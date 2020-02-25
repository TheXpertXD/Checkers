
package Main;
import java.util.ArrayList;

public class Checkers extends BoardGame {
	public Cell[][] board;    //the board, without pieces | Cell object with state 0 or 1
	   //every element in the 2D array will be initialized at setup

	public Piece[][] pieces;  //the pieces, matches up with board, null = empty | Piece object with team 3 or 4
	   //spot is null if no piece holds it

	public static ArrayList<Piece[][]> snapshots;  //ArrayList of every "snapshot" of the board after each move.
	public static int numTurns;                //Number of turns at any certain time.

	// To Do:
	// - Implement board with buttons as opposed to ints, same thing to the pieces.


	public Checkers(int x, int y) { //standard game is 8x8
		board = new Cell[x][y];
		pieces = new Piece[x][y];
		snapshots = new ArrayList<Piece[][]>();
	}


	/**
	 * This method setups the checkered board to play on (light and dark spaces).
	 * Each line is offset using count, giving it a checkered look.
	 */
	public void setupBoard() {                       

		int count = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				count++;
				if(count % 2 == 0) {
					board[i][j] = new Cell(1,i,j);
				} else {
					board[i][j] = new Cell(0,i,j);
				}
			}
			count++;
		}
	}


	/**
	 * This method sets up the pieces for each player.
	 */
	public void setupPieces() {

		//standard number of pieces for standard 8x8 board, we can mess with this in the future.
		int numPieces1 = 12;
		int numPieces2 = 12;

		//setup pieces for player 1 (top)
		//these pieces will be represented as 3's

		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j].getState() == 1 && numPieces1 > 0) {
					pieces[i][j] = new Piece(3,i,j);
					numPieces1--; //essentially sets a piece down on each open spot
				}
			}
		}

		//setup pieces for player 2 (bottom)
		//these pieces will be represented as 4's

		for(int i = board.length - 1; i >= 0; i--) {
			for(int j = board.length - 1; j >= 0; j--) {
				if(board[i][j].getState() == 1 && numPieces2 > 0) {
					pieces[i][j] = new Piece(4,i,j);
					numPieces2--;
				}
			}
		}
	}


	public void goBack(Piece[][] pieces, ArrayList<Piece[][]> snapshots, int numTurns) {

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				pieces[i][j] = snapshots.get(numTurns - 2)[i][j];
			}
		}
	}



	/**
	 * This method displays the current board to the screen.
	 * @param p if true, print pieces, else, print board.
	 */
	public void display(boolean p) {

		for (int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(p) {
					try {  //catch null exception
						System.out.print(pieces[i][j].getTeam() + "    "); // prints the piece
					} catch(Exception e) {                                 //   if no piece,
						System.out.print(board[i][j].getState() + "    "); // prints open or closed space
					}
				} else {
					System.out.print(board[i][j].getState() + "    ");
				}
			}
			System.out.println();
			System.out.println();
		}
		snapshots.add(pieces);
		numTurns++;
	}

	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////// /
	//
	// - checkMoves and checkSkips are executed when a piece is selected (clicked),
	//   and available moves or skips are highlighted (board piece changes color)
	// - if a highlighted spot is clicked, movePiece or skipPiece is executed.
	// - every time movePiece or skipPiece is executed, the board must be displayed again.
	// - 
	// 
	////////////////////////////////////////////////////////////////////////// /
	////////////////////////////////////////////////////////////////////////////////////
	//
	public static void main(String[] args) {

		/**
		Checkers game = new Checkers(8,8);
		game.setupBoard();
		game.setupPieces();
		game.display(new Boolean(true));

		//test possible moves
		game.pieces[2][3].displayMoves(game.pieces, game.board);

		game.pieces[2][3].movePiece(game.board[3][4], game.pieces);
		game.display(true);


		for(@SuppressWarnings("unused") Piece[][] p : snapshots) {
			System.out.println("Snap!");
		}
		game.goBack(game.pieces, snapshots, numTurns);
		System.out.print("\n\nLast Snapshot:\n");
		game.display(true);

		*/

}
}
