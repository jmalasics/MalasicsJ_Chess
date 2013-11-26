package PieceManipulation;

import Board.ChessBoard;
import Piece.Piece;

/**
 * Stores all the information needed for placing a piece on the board.
 * 
 * @author jmalasics
 *
 */
public class Placement extends ChessAction {

	private Piece piece;
	
	public Placement(Location location, Piece piece) {
		super(location);
		this.piece = piece;
	}
	
	/**
	 * Gets the piece that is being placed.
	 * 
	 * @return
	 */
	public Piece getPiece() {
		return piece;
	}
	
	/**
	 * Gets the location where the piece is being placed.
	 * 
	 * @return
	 */
	public Location getLocation() {
		return initialLocation;
	}
	
	public boolean executeAction(ChessBoard board) {
		return board.placePiece(this);
	}
	
}
