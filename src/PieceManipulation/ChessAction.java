package PieceManipulation;


/**
 * Super class for all types of movement and placement classes.
 * 
 * @author jmalasics
 *
 */
public abstract class ChessAction {

	protected Location initialLocation;
	protected Location endLocation;
	
	public ChessAction(Location initial, Location end) {
		initialLocation = initial;
		endLocation = end;
	}
	
	public ChessAction(Location location) {
		initialLocation = location;
	}
	
}
