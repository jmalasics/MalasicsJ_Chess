package Board;

import java.awt.Color;

import Piece.Piece;
import Piece.Pawn;
import PieceManipulation.*;
import Exception.*;

public class ChessBoard implements Cloneable {
	
	private Square[][] board;
	
	public static final int BOARD_ROWS = 8;
	public static final int BOARD_COLUMNS = 8;
	
	public ChessBoard() {
		board = new Square[BOARD_ROWS][BOARD_COLUMNS];
		createBoard();
	}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public ChessBoard getClone() {
        ChessBoard clone = null;
        try {
            clone = (ChessBoard) clone();
        } catch(CloneNotSupportedException e) {

        }
        return clone;
    }

    /**
	 * Adds all the squares to the 2 dimensional array that makes up the board itself. Every Square has a color that alternates and 
	 * also has the location of where the square is.
	 * 
	 */
	private void createBoard() {
		for (int i = 0; i < BOARD_ROWS; i++) {
			for (int j = 0; j < BOARD_COLUMNS; j++) {
				board[i][j] = new Square(getColor(i, j), new Location(j, i));
			}
		}
	}
	
	/**
	 * Determines what the color will be for the squares that are added to the board, based off of where the square is 
	 * located in the array.
	 * 
	 * @param x the x location of a square
	 * @param y the y location of a square
	 * @return the color that the square
	 */
	private Color getColor(int x, int y) {
		return (x + y) % 2 == 0 ? Color.WHITE : Color.GRAY;
	}
	
	/**
	 * Places a piece on the board at a location if that location is unoccupied. If it is occupied it displays an error message.
	 * 
	 * @param place the placement action you wish to perform
	 * @return a boolean representing if the placement was successful or not
	 */
	public boolean placePiece(Placement place) throws PlacementException {
		boolean actionCompleted = false;
		if(!isPieceAt(place.getLocation())) {
			getSquareAt(place.getLocation()).setPiece(place.getPiece());
			actionCompleted = true;
		} else {
            throw new PlacementException("There is already a piece in that location.");
		}
		return actionCompleted;
	}

    /**
     * Validates if the movement is valid or not.
     *
     * @param move the movement you are trying to perform
     * @return a boolean representing if the movement is valid or not
     */
	public boolean canMove(Movement move) {
		boolean isValidMove = false;
		if(!isPieceAt(move.getEndLocation()) && isPieceAt(move.getInitialLocation())) {
			if(getPieceAt(move.getInitialLocation()).isValidMove(move)) {
				isValidMove = true;
			}
		}

		return isValidMove;
	}
	
	/**
	 * Moves a piece on the board if no piece is on the square it is moving to and there is a piece on the starting location for 
	 * the move. If not an error message displaying what the problem is with the move.
	 * 
	 * @param move the movement you are trying to perform
	 * @return a boolean representing if the movement was completed or not
	 */
	public boolean move(Movement move) throws MovementException {
		boolean actionCompleted = false;
        if(canMove(move)) {
            movePiece(move.getInitialLocation(), move.getEndLocation());
            actionCompleted = true;
        } else {
            throw new MovementException("The move is invalid.");
        }
        return actionCompleted;
	}

    /**
     * Validates if the capture is valid or not.
     *
     * @param capture the capture you are trying to perform
     * @return a boolean representing if the capture is valid or not
     */
	public boolean canCapture(Capture capture) {
		boolean isValidCapture = false;
		if(isPieceAt(capture.getEndLocation()) && isPieceAt(capture.getInitialLocation())) {
            Piece capturingPiece = getPieceAt(capture.getInitialLocation());
            Piece capturedPiece = getPieceAt(capture.getEndLocation());
			if(!capturingPiece.isOnSameTeam(capturedPiece)) {
                isValidCapture = capturingPiece.isValidCapture(capture);
			}
		}
		return isValidCapture;
	}
	
	/**
	 * Captures a piece on the board if there is a piece on the captured location and if there is a piece to be captured with. If 
	 * not an error message displaying what the problem is with the capture.
	 * 
	 * @param capture the capture you are trying to perform
	 * @return a boolean representing if the capture was successful or not
	 */
	public boolean capture(Capture capture) throws CaptureException {
		boolean actionCompleted = false;
        if(canCapture(capture)) {
            movePiece(capture.getInitialLocation(), capture.getEndLocation());
            actionCompleted = true;
        } else {
            throw new CaptureException("The capture is invalid.");
        }
        return actionCompleted;
	}

    /**
     * Moves the piece at the initial location to the end location.
     *
     * @param initial the initial location of a move or capture
     * @param end the end location of a move or capture
     */
	public void movePiece(Location initial, Location end) {
		if(getPieceAt(initial) instanceof Pawn) {
			((Pawn) getPieceAt(initial)).pawnHasMoved();
		}
		getSquareAt(end).setPiece(getPieceAt(initial));
		getSquareAt(initial).setPiece(null);
	}
	
	/**
	 * Castles if there are pieces on the starting locations and if the squares they are moving to are empty. If not an error 
	 * message display what the problem is with the castle.
	 * 
	 * @param multimove the castling action you are trying to perform
	 * @return a boolean representing if you completed the castling movement or not
	 */
	public boolean castle(Multimovement multimove) throws CastlingException {
		boolean actionCompleted = false;
		if(isPieceAt(multimove.getKingInitialLocation()) && isPieceAt(multimove.getRookInitialLocation())) {
			if(!isPieceAt(multimove.getKingEndLocation()) && !isPieceAt(multimove.getRookEndLocation())) {
                try {
				    actionCompleted = move(new Movement(multimove.getKingInitialLocation(), multimove.getKingEndLocation()));
                    if(actionCompleted) {
				        actionCompleted = move(new Movement(multimove.getRookInitialLocation(), multimove.getRookEndLocation()));
                    }
                } catch(MovementException movementException) {
                    throw new CastlingException(movementException.getMessage());
                }
			} else if(isPieceAt(multimove.getKingEndLocation()) || isPieceAt(multimove.getRookEndLocation())) {
				throw new CastlingException("There is a piece blocking the castle.");
			}
		} else if(!isPieceAt(multimove.getKingInitialLocation()) || !isPieceAt(multimove.getRookInitialLocation())) {
			throw new CastlingException("There isn't a piece at one of the locations.");
		}
		return actionCompleted;
	}
	
	/**
	 * Returns the square at the index of the 2 dimensional array.
	 * 
	 * @param i the x location of the square you are getting
	 * @param j the y location of the square you are getting
	 * @return the square at the i (x) and j (y) location
	 */
	public Square getSquareAt(int i, int j) {
		return board[i][j];
	}
	
	/**
	 * Returns the square at the specified location.
	 * 
	 * @param location the location of of which you want to get a square
	 * @return the square at the specified location
	 */
	private Square getSquareAt(Location location) {
        return board[location.getArrayY()][location.getIntX()];
	}
	
	/**
	 * Returns if there is a piece at the specified location or not.
	 * 
	 * @param location the location you are searching for a piece
	 * @return a boolean representing if there is a square at the specified location or not
	 */
	public boolean isPieceAt(Location location) {
		return getSquareAt(location).getPiece() != null;
	}
	
	/**
	 * Returns the piece at the specified location.
	 * 
	 * @param location the location you are searching for a piece
	 * @return the piece at the specified location
	 */
	public Piece getPieceAt(Location location) {
		return getSquareAt(location).getPiece();
	}
	
	public Location getPieceLocation(Piece piece) {
		Location pieceLocation = null;
		for(int i = 0; i < BOARD_ROWS; i++) {
			for(int j = 0; j < BOARD_COLUMNS; j++) {
				if(board[i][j].getPiece() != null) {
					if(board[i][j].getPiece().equals(piece)) {
						pieceLocation = board[i][j].getLocation();
					}
				}
			}
		}
		return pieceLocation;
	}
	
}
