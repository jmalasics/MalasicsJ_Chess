package PieceManipulation;

/**
 * Stores the information needed for capturing pieces.
 * 
 * @author jmalasics
 *
 */
public class Capture extends MovePlace {
	
	public Capture(Location initial, Location end) {
		super(initial, end);
	}
	
	/**
	 * Gets the initial location of the piece that is capturing another piece.
	 * 
	 * @return
	 */
	public Location getInitialLocation() {
		return initialLocation;
	}
	
	/**
	 * Gets the location that the piece is capturing another piece at.
	 * 
	 * @return
	 */
	public Location getEndLocation() {
		return endLocation;
	}

}
