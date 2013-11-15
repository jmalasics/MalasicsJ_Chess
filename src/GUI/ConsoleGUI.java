package GUI;

import java.awt.Color;

import Piece.Piece;
import PieceManipulation.*;

public class ConsoleGUI {

	private Square[][] board;
	
	public final static int BOARD_ROWS = 8;
	public final static int BOARD_COLUMNS = 8;
	
	public ConsoleGUI() {
		createBoard();
	}
	
	private void createBoard() {
		for (int i = 0; i < BOARD_ROWS; i++) {
			for (int j = 0; j < BOARD_COLUMNS; j++) {
				board[i][j] = new Square(getColor(i, j), new Location(i, j));
			}
		}
	}
	
	private Color getColor(int x, int y) {
		Color color = Color.GRAY;
		if ((x + y) % 2 == 0) {
			color = Color.WHITE;
		}
		return color;
	}
	
	public Square[][] getBoard() {
		return board;
	}
	
	public void displayBoard() {
		for(int i = 0; i < BOARD_ROWS; i++) {
			for(int j = 0; j < BOARD_COLUMNS; j++) {
				if(j < BOARD_COLUMNS - 1) {
					System.out.println(board[i][j].toString());
				} else {
					System.out.println(board[i][j].toString() + "\n");
				}
			}
		}
	}
	
	public boolean performAction(ChessAction action) {
		boolean actionCompleted = false;
		if(action instanceof Placement) {
			actionCompleted = placePiece((Placement) action);
		} else if(action instanceof Movement) {
			actionCompleted = movePiece((Movement) action);
		} else if(action instanceof Capture) {
			actionCompleted = capturePiece((Capture) action);
		} else if(action instanceof Multimovement) {
			actionCompleted = castle((Multimovement) action);
		}
		return actionCompleted;
	}
	
	private boolean placePiece(Placement place) {
		boolean actionCompleted = false;
		if(!isPieceAt(place.getLocation())) {
			board[place.getLocation().getIntX()][place.getLocation().getY()].setPiece(place.getPiece());
			actionCompleted = true;
		} else {
			System.err.println("There is already a piece in that location.");
		}
		return actionCompleted;
	}
	
	private boolean movePiece(Movement move) {
		boolean actionCompleted = false;
		if(!isPieceAt(move.getEndLocation()) && isPieceAt(move.getInitialLocation())) {
			board[move.getEndLocation().getIntX()][move.getEndLocation().getY()].setPiece(getPieceAt(move.getInitialLocation()));
			board[move.getInitialLocation().getIntX()][move.getInitialLocation().getY()].setPiece(null);
			actionCompleted = true;
		} else if(isPieceAt(move.getEndLocation())) {
			System.err.println("There is a piece in the location you are trying to move to.");
		} else if(!isPieceAt(move.getInitialLocation())) {
			System.err.println("There is no piece at that location.");
		}
		return actionCompleted;
	}
	
	private boolean capturePiece(Capture capture) {
		boolean actionCompleted = false;
		if(isPieceAt(capture.getEndLocation()) && isPieceAt(capture.getInitialLocation())) {
			board[capture.getEndLocation().getIntX()][capture.getEndLocation().getY()].setPiece(getPieceAt(capture.getInitialLocation()));
			board[capture.getInitialLocation().getIntX()][capture.getInitialLocation().getY()].setPiece(null);
			actionCompleted = true;
		} else if(!isPieceAt(capture.getEndLocation())) {
			System.err.println("There is no piece to capture at that location.");
		} else if(!isPieceAt(capture.getInitialLocation())) {
			System.err.println("There is no piece to capture with.");
		}
		return actionCompleted;
	}
	
	private boolean castle(Multimovement multimove) {
		boolean actionCompleted = false;
		if(isPieceAt(multimove.getKingInitialLocation()) && isPieceAt(multimove.getRookInitialLocation())) {
			if(!isPieceAt(multimove.getKingEndLocation()) && !isPieceAt(multimove.getRookEndLocation())) {
				board[multimove.getKingEndLocation().getIntX()][multimove.getKingEndLocation().getY()].setPiece(getPieceAt(multimove.getKingInitialLocation()));
				board[multimove.getKingInitialLocation().getIntX()][multimove.getKingInitialLocation().getY()].setPiece(null);
				board[multimove.getRookEndLocation().getIntX()][multimove.getRookEndLocation().getY()].setPiece(getPieceAt(multimove.getRookInitialLocation()));
				board[multimove.getRookInitialLocation().getIntX()][multimove.getRookInitialLocation().getY()].setPiece(null);
				actionCompleted = true;
			} else if(isPieceAt(multimove.getKingEndLocation()) || isPieceAt(multimove.getRookEndLocation())) {
				System.err.println("There is a piece blocking the castle.");
			}
		} else if(!isPieceAt(multimove.getKingInitialLocation()) || !isPieceAt(multimove.getRookInitialLocation())) {
			System.err.println("There isn't a piece at one of the locations.");
		}
		return actionCompleted;
	}
	
	private boolean isPieceAt(Location location) {
		return board[location.getIntX()][location.getY()].getPiece() != null;
	}
	
	private Piece getPieceAt(Location location) {
		return board[location.getIntX()][location.getY()].getPiece();
	}
	
}
