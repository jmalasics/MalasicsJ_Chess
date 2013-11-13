package PieceManipulation;


/**
 * Stores all the information needed when moving a piece on the board.
 * 
 * @author jmalasics
 *
 */
public class Movement extends MovePlace {
	
	public Movement(Location initial, Location end) {
		super(initial, end);
	}
	
	/**
	 * Gets the initial location of the piece being moved.
	 * 
	 * @return
	 */
	public Location getInitialLocation() {
		return initialLocation;
	}
	
	/**
	 * Gets the location where the piece is being moved.
	 * 
	 * @return
	 */
	public Location getEndLocation() {
		return endLocation;
	}
	
}
