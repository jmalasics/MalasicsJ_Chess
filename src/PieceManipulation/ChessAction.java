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
	
	public int getXDirection() {
		return 0;
	}
	
	public int getYDirection() {
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endLocation == null) ? 0 : endLocation.hashCode());
		result = prime * result
				+ ((initialLocation == null) ? 0 : initialLocation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		ChessAction action = (ChessAction) obj;
		return this == action && this.initialLocation.equals(action.getInitialLocation()) && this.endLocation.equals(action.getEndLocation());
	}
	
	
	
}
