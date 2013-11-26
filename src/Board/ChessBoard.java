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

	/**
	 * Adds all the squares to the 2 dimensional array that makes up the board itself. Every Square has a color that alternates and 
	 * also has the location of where the square is.
	 * 
	 */
	private void createBoard() {
		for (int i = 0; i < BOARD_ROWS; i++) {
			for (int j = 0; j < BOARD_COLUMNS; j++) {
				board[i][j] = new Square(getColor(i, j), new Location(i, j));
			}
		}
	}
	
	/**
	 * Determines what the color will be for the squares that are added to the board, based off of where the square is 
	 * located in the array.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private Color getColor(int x, int y) {
		return (x + y) % 2 == 0 ? Color.WHITE : Color.GRAY;
	}
	
	/**
	 * Places a piece on the board at a location if that location is unoccupied. If it is occupied it displays an error message.
	 * 
	 * @param place
	 * @return
	 */
	public boolean placePiece(Placement place) {
		boolean actionCompleted = false;
		if(!isPieceAt(place.getLocation())) {
			getSquareAt(place.getLocation()).setPiece(place.getPiece());
			actionCompleted = true;
		} else {
			System.err.println("There is already a piece in that location.");
		}
		return actionCompleted;
	}
	
	/**
	 * Moves a piece on the board if no piece is on the square it is moving to and there is a piece on the starting location for 
	 * the move. If not an error message displaying what the problem is with the move.
	 * 
	 * @param move
	 * @return
	 */
	public boolean move(Movement move) {
		boolean actionCompleted = false;
		if(!isPieceAt(move.getEndLocation()) && isPieceAt(move.getInitialLocation())) {
			if(getPieceAt(move.getInitialLocation()).isValidMove(move)) {
				movePiece(move.getInitialLocation(), move.getEndLocation());
				actionCompleted = true;
			} else {
				System.err.println("Invalid move for that piece.");
			}
		} else if(isPieceAt(move.getEndLocation())) {
			System.err.println("There is a piece in the location you are trying to move to.");
		} else if(!isPieceAt(move.getInitialLocation())) {
			System.err.println("There is no piece at that location.");
		}
		return actionCompleted;
	}
	
	/**
	 * Captures a piece on the board if there is a piece on the captured location and if there is a piece to be captured with. If 
	 * not an error message displaying what the problem is with the capture.
	 * 
	 * @param capture
	 * @return
	 */
	public boolean capture(Capture capture) {
		boolean actionCompleted = false;
		if(isPieceAt(capture.getEndLocation()) && isPieceAt(capture.getInitialLocation())) {
			if(!getPieceAt(capture.getInitialLocation()).getColor().equals(getPieceAt(capture.getEndLocation()).getColor())) {
				if(getPieceAt(capture.getInitialLocation()).isValidCapture(capture)) {
					movePiece(capture.getInitialLocation(), capture.getEndLocation());
					actionCompleted = true;
				} else {
					System.err.println("Invalid capture for that piece.");
				}
			} else {
				System.err.println("Cannot capture your own piece.");
			}
		} else if(!isPieceAt(capture.getEndLocation())) {
			System.err.println("There is no piece to capture at that location.");
		} else if(!isPieceAt(capture.getInitialLocation())) {
			System.err.println("There is no piece to capture with.");
		}
		return actionCompleted;
	}
	
	private void movePiece(Location initial, Location end) {
		getSquareAt(end).setPiece(getPieceAt(initial));
		getSquareAt(initial).setPiece(null);
	}
	
	/**
	 * Castles if there are pieces on the starting locations and if the squares they are moving to are empty. If not an error 
	 * message display what the problem is with the castle.
	 * 
	 * @param multimove
	 * @return
	 */
	public boolean castle(Multimovement multimove) {
		boolean actionCompleted = false;
		if(isPieceAt(multimove.getKingInitialLocation()) && isPieceAt(multimove.getRookInitialLocation())) {
			if(!isPieceAt(multimove.getKingEndLocation()) && !isPieceAt(multimove.getRookEndLocation())) {
				actionCompleted = move(new Movement(multimove.getKingInitialLocation(), multimove.getKingEndLocation()));
				actionCompleted = move(new Movement(multimove.getRookInitialLocation(), multimove.getRookEndLocation()));
			} else if(isPieceAt(multimove.getKingEndLocation()) || isPieceAt(multimove.getRookEndLocation())) {
				System.err.println("There is a piece blocking the castle.");
			}
		} else if(!isPieceAt(multimove.getKingInitialLocation()) || !isPieceAt(multimove.getRookInitialLocation())) {
			System.err.println("There isn't a piece at one of the locations.");
		}
		return actionCompleted;
	}
	
	/**
	 * Returns the square at the index of the 2 dimensional array.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public Square getSquareAt(int i, int j) {
		return board[i][j];
	}
	
	/**
	 * Returns the square at the specified location.
	 * 
	 * @param location
	 * @return
	 */
	private Square getSquareAt(Location location) {
		return board[location.getArrayY()][location.getIntX()];
	}
	
	/**
	 * Returns if there is a piece at the specified location or not.
	 * 
	 * @param location
	 * @return
	 */
	private boolean isPieceAt(Location location) {
		return getSquareAt(location.getArrayY(), location.getIntX()).getPiece() != null;
	}
	
	/**
	 * Returns the piece at the specified location.
	 * 
	 * @param location
	 * @return
	 */
	private Piece getPieceAt(Location location) {
		return getSquareAt(location.getArrayY(), location.getIntX()).getPiece();
	}
	
}
