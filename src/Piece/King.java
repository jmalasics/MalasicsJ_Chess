package Piece;

import GameLogic.Team;
import PieceManipulation.*;


public class King extends Piece {
	
	public King(Team team, Location location) {
		super(team, location);
	}

    @Override
	public boolean isValidMove(Movement move) {
        int distance = Math.abs(move.getInitialLocation().getIntX() - move.getEndLocation().getIntX());
        if(distance == 0) {
            distance = Math.abs(move.getInitialLocation().getArrayY() - move.getEndLocation().getArrayY());
        }
        return distance == 1 && (isValidNorthMovement(distance, move) || isValidNorthEastMovement(distance, move) || isValidEastMovement(distance, move)
                || isValidSouthEastMovement(distance, move) || isValidSouthMovement(distance, move) || isValidSouthWestMovement(distance, move)
                || isValidWestMovement(distance, move) || isValidNorthWestMovement(distance, move));
	}
	
	@Override
	public boolean isValidCapture(Capture capture) {
		return isValidMove(new Movement(capture.getInitialLocation(), capture.getEndLocation()));
	}
	
	public String getPieceName() {
		return "King";
	}
	
	@Override
	public String toString() {
		return "k";
	}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof King && super.equals(obj);
    }

}
