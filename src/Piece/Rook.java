package Piece;

import GameLogic.Team;
import PieceManipulation.*;


public class Rook extends Piece {
	
	public Rook(Team team) {
		super(team);
	}
	
	@Override
	public boolean isValidMove(Movement move) {
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
	}
	
	@Override
	public boolean isValidCapture(Capture capture) {
		return isValidMove(new Movement(capture.getInitialLocation(), capture.getEndLocation()));
	}
	
	@Override
	public String toString() {
		return "r";
	}

}
