package Piece;

import GameLogic.Team;
import PieceManipulation.*;


public class Bishop extends Piece {

    private static final int MAX_MOVE_DISTANCE = 8;

	public Bishop(Team team) {
		super(team);
	}
	
	@Override
	public boolean isValidMove(Movement move) {
        int distance = Math.abs(move.getInitialLocation().getIntX() - move.getEndLocation().getIntX());
        return distance > 0 && (isValidNorthEastMovement(distance, MAX_MOVE_DISTANCE, move) || isValidNorthWestMovement(distance, MAX_MOVE_DISTANCE, move) || isValidSouthEastMovement(distance, MAX_MOVE_DISTANCE, move)
                || isValidSouthWestMovement(distance, MAX_MOVE_DISTANCE, move));

        /**
        boolean isValid = false;
		int distance = Math.abs(move.getInitialLocation().getIntX() - move.getEndLocation().getIntX());
		if(distance > 0) {
			if(move.getInitialLocation().getIntX() + distance == move.getEndLocation().getIntX()) {
				if(move.getInitialLocation().getArrayY() + distance == move.getEndLocation().getArrayY()) {
					isValid = true;
				} else if(move.getInitialLocation().getArrayY() - distance == move.getEndLocation().getArrayY()) {
					isValid = true;
				}
			} else if(move.getInitialLocation().getIntX() - distance == move.getEndLocation().getIntX()) {
				if(move.getInitialLocation().getArrayY() + distance == move.getEndLocation().getArrayY()) {
					isValid = true;
				} else if(move.getInitialLocation().getArrayY() - distance == move.getEndLocation().getArrayY()) {
					isValid = true;
				}
			}
		}
		return isValid;
        */
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
	
}
