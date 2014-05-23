package Piece;

import GameLogic.Team;
import PieceManipulation.*;


public class Bishop extends Piece {

	public Bishop(Team team) {
		super(team);
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
	
}
