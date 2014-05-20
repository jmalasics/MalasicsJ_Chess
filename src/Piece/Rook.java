package Piece;

import GameLogic.Team;
import PieceManipulation.*;


public class Rook extends Piece {

    private static final int MAX_MOVE_DISTANCE = 8;
	
	public Rook(Team team) {
		super(team);
	}
	
	@Override
	public boolean isValidMove(Movement move) {
        int distance = Math.abs(move.getInitialLocation().getIntX() - move.getEndLocation().getIntX());
        if(distance == 0) {
            distance = Math.abs(move.getInitialLocation().getArrayY() - move.getEndLocation().getArrayY());
        }
        return distance > 0 && (isValidNorthMovement(distance, MAX_MOVE_DISTANCE, move) || isValidEastMovement(distance, MAX_MOVE_DISTANCE, move) || isValidSouthMovement(distance, MAX_MOVE_DISTANCE, move)
                || isValidWestMovement(distance, MAX_MOVE_DISTANCE, move));

        /**
		boolean isValid = false;
		int distance = 0;
		if(move.getInitialLocation().getIntX() == move.getEndLocation().getIntX()) {
			distance = Math.abs(move.getInitialLocation().getY() - move.getEndLocation().getY());
			if(distance > 0) {
				isValid = true;
			}
		} else if(move.getInitialLocation().getY() == move.getEndLocation().getY()) {
			distance = Math.abs(move.getInitialLocation().getIntX() - move.getEndLocation().getIntX());
			if(distance > 0) {
				isValid = true;
			}
		}
		return isValid;
         */
	}
	
	@Override
	public boolean isValidCapture(Capture capture) {
		return isValidMove(new Movement(capture.getInitialLocation(), capture.getEndLocation()));
	}
	
	public String getPieceName() {
		return "Rook";
	}
	
	@Override
	public String toString() {
		return "r";
	}

}
