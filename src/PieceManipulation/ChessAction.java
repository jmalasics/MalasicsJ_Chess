package PieceManipulation;

import Board.ChessBoard;


/**
 * Super class for all types of movement and placement classes.
 * 
 * @author jmalasics
 *
 */
public abstract class ChessAction {

	protected Location initialLocation;
	protected Location endLocation;
	
	public ChessAction() {
		
	}
	
	public ChessAction(Location initial, Location end) {
		initialLocation = initial;
		endLocation = end;
	}
	
	public ChessAction(Location location) {
		initialLocation = location;
	}
	
	public Location getInitialLocation() {
		return initialLocation;
	}
	
	public Location getEndLocation() {
		return endLocation;
	}
	
	public boolean executeAction(ChessBoard board) {
		return false;
	}
	
}
