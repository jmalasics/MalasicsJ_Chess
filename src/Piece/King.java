package Piece;

import GameLogic.Team;
import PieceManipulation.*;


public class King extends Piece {
	
	private static final int MAX_MOVE_DISTANCE = 1;
	
	public King(Team team) {
		super(team);
	}
	
	@Override
	public boolean isValidMove(Movement move) {
		boolean isValid = false;
		if(move.getInitialLocation().getY() == move.getEndLocation().getY()) {
			if(move.getInitialLocation().getIntX() + MAX_MOVE_DISTANCE == move.getEndLocation().getIntX()) {
				isValid = true;
			} else if(move.getInitialLocation().getIntX() - MAX_MOVE_DISTANCE == move.getEndLocation().getIntX()) {
				isValid = true;
			}
		} else if(move.getInitialLocation().getIntX() == move.getEndLocation().getIntX()) {
			if(move.getInitialLocation().getY() + MAX_MOVE_DISTANCE == move.getEndLocation().getY()) {
				isValid = true;
			} else if(move.getInitialLocation().getY() - MAX_MOVE_DISTANCE == move.getEndLocation().getY()) {
				isValid = true;
			}
		} else if(move.getInitialLocation().getY() + MAX_MOVE_DISTANCE == move.getEndLocation().getY()) {
			if(move.getInitialLocation().getIntX() + MAX_MOVE_DISTANCE == move.getEndLocation().getIntX()) {
				isValid = true;
			} else if(move.getInitialLocation().getIntX() - MAX_MOVE_DISTANCE == move.getEndLocation().getIntX()) {
				isValid = true;
			}
		} else if(move.getInitialLocation().getY() - MAX_MOVE_DISTANCE == move.getEndLocation().getY()) {
			if(move.getInitialLocation().getIntX() + MAX_MOVE_DISTANCE == move.getEndLocation().getIntX()) {
				isValid = true;
			} else if(move.getInitialLocation().getIntX() - MAX_MOVE_DISTANCE == move.getEndLocation().getIntX()) {
				isValid = true;
			}
		}
		return isValid;
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

}
