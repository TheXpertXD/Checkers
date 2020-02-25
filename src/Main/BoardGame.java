package Main;

import java.util.ArrayList;

/**
 * This abstract class serves as a blueprint for any board game. In our instance, we are creating Checkers.
 * The board can be any size, and every game has the ability to capture a "snapshot" of each turn.
 * 
 * @author Owen Moreau, Andrew Zemko, Alyssa Lohr
 *
 */
public abstract class BoardGame {
	
	public static Cell[][] board;
	public static Piece[][] pieces;
	public static ArrayList<Piece[][]> snapshots;
	public static int numTurns;

	public abstract void setupBoard();
	public abstract void setupPieces();
	public abstract void display(boolean p);
	
}
