package PieceManipulation;


/**
 * Super class for all types of movement and placement classes.
 * 
 * @author jmalasics
 *
 */
public abstract class MovePlace {

	protected Location initialLocation;
	protected Location endLocation;
	
	public MovePlace(Location initial, Location end) {
		initialLocation = initial;
		endLocation = end;
	}
	
	public MovePlace(Location location) {
		initialLocation = location;
	}
	
}
