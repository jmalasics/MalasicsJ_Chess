package PieceManipulation;

import Board.ChessBoard;


/**
 * Stores the information needed for castling.
 * 
 * @author jmalasics
 *
 */
public class Multimovement extends ChessAction {

	private Location secondaryInitialLocation;
	private Location secondaryEndLocation;
	
	public Multimovement(Location initial, Location end, Location secondaryInitial, Location secondaryEnd) {
		super(initial, end);
		secondaryInitialLocation = secondaryInitial;
		secondaryEndLocation = secondaryEnd;
	}
	
	/**
	 * Gets the initial location of the king.
	 * 
	 * @return
	 */
	public Location getKingInitialLocation() {
		return initialLocation;
	}
	
	/**
	 * Gets the end location of the king.
	 * 
	 * @return
	 */
	public Location getKingEndLocation() {
		return endLocation;
	}
	
	/**
	 * Gets the initial location of the rook.
	 * 
	 * @return
	 */
	public Location getRookInitialLocation() {
		return secondaryInitialLocation;
	}
	
	/**
	 * Gets the end location of the rook.
	 * 
	 * @return
	 */
	public Location getRookEndLocation() {
		return secondaryEndLocation;
	}
	
	public boolean executeAction(ChessBoard board) {
		return board.castle(this);
	}
	
}
