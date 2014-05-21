package PieceManipulation;

import Board.ChessBoard;
import Exception.CaptureException;

/**
 * Stores the information needed for capturing pieces.
 * 
 * @author jmalasics
 *
 */
public class Capture extends ChessAction {
	
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
	
	public boolean executeAction(ChessBoard board) throws CaptureException {
		return board.capture(this);
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
        return obj instanceof Capture && this.getInitialLocation().equals(((Capture) obj).getInitialLocation()) && this.getEndLocation().equals(((Capture) obj).getEndLocation());
    }

}
