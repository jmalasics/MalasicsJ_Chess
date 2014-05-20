package PieceManipulation;

import Board.ChessBoard;


/**
 * Stores all the information needed when moving a piece on the board.
 * 
 * @author jmalasics
 *
 */
public class Movement extends ChessAction {
	
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
	
	public boolean executeAction(ChessBoard board) {
		return board.move(this);
	}
	
	public int getXDirection() {
		int xDirection = 0;
		if (initialLocation.getIntX()
				- endLocation.getIntX() > 0) {
			xDirection = -1;
		} else if (initialLocation.getIntX()
				- endLocation.getIntX() < 0) {
			xDirection = 1;
		}
		return xDirection;
	}

	public int getYDirection() {
		int yDirection = 0;
		if (initialLocation.getY()
				- endLocation.getY() > 0) {
			yDirection = -1;
		} else if (initialLocation.getY()
				- endLocation.getY() < 0) {
			yDirection = 1;
		}
		return yDirection;
	}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Movement && this.getInitialLocation().equals(((Movement) obj).getInitialLocation()) && this.getEndLocation().equals(((Movement) obj).getEndLocation());
    }

}
