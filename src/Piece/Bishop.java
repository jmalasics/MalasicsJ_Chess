package Piece;

import GameLogic.Team;
import PieceManipulation.*;


public class Bishop extends Piece {

	public Bishop(Team team, Location location) {
		super(team, location);
	}
	
	@Override
	public boolean isValidMove(Movement move) {
        int distance = Math.abs(move.getInitialLocation().getIntX() - move.getEndLocation().getIntX());
        return distance > 0 && (isValidNorthEastMovement(distance, move) || isValidNorthWestMovement(distance, move) || isValidSouthEastMovement(distance, move)
                || isValidSouthWestMovement(distance, move));
	}
	
	public String getPieceName() {
		return "Bishop";
	}
	
	@Override
	public boolean isValidCapture(Capture capture) {
		return isValidMove(new Movement(capture.getInitialLocation(), capture.getEndLocation()));
	}

	@Override
	public String toString() {
		return "b";
	}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Bishop && super.equals(obj);
    }
}
