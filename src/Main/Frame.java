package Main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.awt.event.*;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author Owen Moreau, Andrew Zemko, Alyssa Lohr
 * This class serves as the GUI component of the game. Has the methods to display the pieces and the board.
 *
 */

public class Frame extends Application implements MouseListener{
	
	public static void Main(String[] args) {
		launch(args);
	}// end Main

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		Checkers game = new Checkers(8,8);
		game.setupBoard();
		game.setupPieces();
		
		
		Pane pane = new Pane();
		int team = 0;
		int x = 0;
		int y = 0;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				x = j*100;
				y = i*100;
				Rectangle rec = new Rectangle(x, y, 100, 100);
				if((i + j) % 2 == 1) {
					rec.setFill(Color.rgb(104, 75, 29));
				}
				else {
					rec.setFill(Color.rgb(198, 152, 97));
				}
				pane.getChildren().add(rec);
				if(i <= 2) {
					team = 3;
				}
				else if(i == 3 || i == 4) {
					team = 0;
				}
				else {
					team = 4;
				}
				setUpBoard(pane, i, j, x, y, team, primaryStage);
			}
		}
		
		Scene scene = new Scene(pane, 800, 800);
		primaryStage.setTitle("Checkers");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//Mouse Event
		EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
			
			  double gridX = e.getSceneX();
			  double gridY = e.getSceneY();
				
			  int newGridX = (int) (gridX / 100);
			  int newGridY = (int) (gridY / 100);
			
			  //erase all previously highlighted spots
			  
			  if(game.pieces[newGridY][newGridX] != null) {
				final Circle c = new Circle();
				c.setCenterX(newGridX * 100 + 50);
				c.setCenterY(newGridY * 100 + 50);
				c.setRadius(40);
				c.setFill(Color.AQUA);
				pane.getChildren().add(c);
				
				Piece piece = game.pieces[newGridY][newGridX];
				//System.out.printf("Row: %d, Col: %d",game.pieces[newGridY][newGridX].getRow(),game.pieces[newGridY][newGridX].getCol());
				
				Cell[] moves = piece.checkMoves(game.pieces, game.board);
				
				for (Cell d : moves) {
					try {
						int locY = d.getRow();
						int locX = d.getCol();
						Rectangle rec = new Rectangle(locX*100, locY*100, 100, 100);		//fix
						rec.setFill(Color.AQUA);
						pane.getChildren().add(rec);
					} catch(Exception f) {}
				}
				EventHandler<MouseEvent> click2 = new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						  System.out.println("Click");
						  double gridX2 = e.getSceneX();
						  double gridY2 = e.getSceneY();
							
						  int newGridX2 = (int) (gridX2 / 100);
						  int newGridY2 = (int) (gridY2 / 100);
						  
						  Cell moveTo = game.board[newGridY2][newGridX2];
						  boolean inMoves = false;
						  for(Cell c : moves) {
							  if(moveTo.getRow() == c.getRow() && moveTo.getCol()==c.getCol()) {
								  inMoves = true;
							  }
						  }
						  if(inMoves) {
							  game.pieces = piece.movePiece(moveTo, game.pieces);
						  }
						  final Circle newCircle = new Circle();
						  newCircle.setCenterX(newGridX2 * 100 + 50);
						  newCircle.setCenterY(newGridY2 * 100 + 50);
						  newCircle.setRadius(40);
						  if(piece.getTeam() == 3) {
							  newCircle.setFill(Color.rgb(173, 12, 12));
						  } 
						  else {
							  newCircle.setFill(Color.rgb(30, 30, 30));
						  }
						  pane.getChildren().add(newCircle);
						  primaryStage.show();
						  
						  for (Cell d : moves) {
								try {
									int locY = d.getRow();
									int locX = d.getCol();
									Rectangle rec = new Rectangle(locX*100, locY*100, 100, 100);		//fix
									rec.setFill(Color.rgb(104,75,29));
									pane.getChildren().add(rec);
									primaryStage.show();
								} catch(Exception f) {}
						  }
								 
						  
						  pane.getChildren().remove(c);
						  primaryStage.show();
					}
				};			
				primaryStage.addEventHandler(MouseEvent.MOUSE_RELEASED, click2);
			  }
		};};
		primaryStage.addEventHandler(MouseEvent.MOUSE_PRESSED, click);
	}
		
		
	
	public void setUpBoard(Pane pane, int row, int col, int x, int y, int team, Stage primaryStage)
	{		
		int x1 = x;
		int y1 = y;
		Piece piece = new Piece(team, row, col);
		
		final Circle c = new Circle();
		c.setCenterX(x+50);
		c.setCenterY(y+50);
		c.setRadius(40);
		
		//final Circle newC = new Circle();
		//c.setCenterX(x+50);
		//c.setCenterY(y+50);
		//c.setRadius(40);
		
		if(piece.getTeam() == 3 && (col + row) % 2 != 0)
		{
			c.setStroke(Color.rgb(173,12,12));
			c.setFill(Color.rgb(173,12,12));
			pane.getChildren().add(c);
		}
		if(piece.getTeam() == 4 && (col + row) % 2 != 0)
		{
			c.setStroke(Color.rgb(30,30,30));
			c.setFill(Color.rgb(30,30,30));
			pane.getChildren().add(c);
		}
		primaryStage.show();
		
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}// end mouseClicked

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}// end mouseEntered

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}// end mouseExited

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}// end mousePressed

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}// end mouseReleased

}// end class Frame
