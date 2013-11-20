package Board;

import java.awt.Color;

import Piece.Piece;
import PieceManipulation.*;

public class ChessBoard {
	
	private Square[][] board;
	
	public static final int BOARD_ROWS = 8;
	public static final int BOARD_COLUMNS = 8;
	
	public ChessBoard() {
		board = new Square[BOARD_ROWS][BOARD_COLUMNS];
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
		return (x + y) % 2 == 0 ? Color.WHITE : Color.GRAY;
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
			board[place.getLocation().getArrayY()][place.getLocation().getIntX()].setPiece(place.getPiece());
			actionCompleted = true;
		} else {
			System.err.println("There is already a piece in that location.");
		}
		return actionCompleted;
	}
	
	private boolean movePiece(Movement move) {
		boolean actionCompleted = false;
		if(!isPieceAt(move.getEndLocation()) && isPieceAt(move.getInitialLocation())) {
			board[move.getEndLocation().getArrayY()][move.getEndLocation().getIntX()].setPiece(getPieceAt(move.getInitialLocation()));
			board[move.getInitialLocation().getArrayY()][move.getInitialLocation().getIntX()].setPiece(null);
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
			board[capture.getEndLocation().getArrayY()][capture.getEndLocation().getIntX()].setPiece(getPieceAt(capture.getInitialLocation()));
			board[capture.getInitialLocation().getArrayY()][capture.getInitialLocation().getIntX()].setPiece(null);
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
				actionCompleted = movePiece(new Movement(multimove.getKingInitialLocation(), multimove.getKingEndLocation()));
				actionCompleted = movePiece(new Movement(multimove.getRookInitialLocation(), multimove.getRookEndLocation()));
			} else if(isPieceAt(multimove.getKingEndLocation()) || isPieceAt(multimove.getRookEndLocation())) {
				System.err.println("There is a piece blocking the castle.");
			}
		} else if(!isPieceAt(multimove.getKingInitialLocation()) || !isPieceAt(multimove.getRookInitialLocation())) {
			System.err.println("There isn't a piece at one of the locations.");
		}
		return actionCompleted;
	}
	
	public Square getSquareAt(int i, int j) {
		return board[i][j];
	}
	
	private boolean isPieceAt(Location location) {
		return board[location.getArrayY()][location.getIntX()].getPiece() != null;
	}
	
	private Piece getPieceAt(Location location) {
		return board[location.getArrayY()][location.getIntX()].getPiece();
	}
	
}
