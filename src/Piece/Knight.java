package Piece;

import GameLogic.Team;
import PieceManipulation.*;


public class Knight extends Piece {
	
	public static final int INITIAL_MOVE = 2;
	public static final int SECOND_MOVE = 1;
	
	public Knight(Team team) {
		super(team);
	}
	
	@Override
	public boolean isValidMove(Movement move) {
		boolean isValid = false;
		if (move.getInitialLocation().getY() + INITIAL_MOVE == move
				.getEndLocation().getY()) {
			if (move.getInitialLocation().getIntX() + SECOND_MOVE == move
					.getEndLocation().getIntX()) {
				isValid = true;
			} else if (move.getInitialLocation().getIntX()
					- SECOND_MOVE == move.getEndLocation().getIntX()) {
				isValid = true;
			}
		} else if (move.getInitialLocation().getY() - INITIAL_MOVE == move
				.getEndLocation().getY()) {
			if (move.getInitialLocation().getIntX() + SECOND_MOVE == move
					.getEndLocation().getIntX()) {
				isValid = true;
			} else if (move.getInitialLocation().getIntX()
					- SECOND_MOVE == move.getEndLocation().getIntX()) {
				isValid = true;
			}
		} else if (move.getInitialLocation().getIntX() + INITIAL_MOVE == move
				.getEndLocation().getIntX()) {
			if (move.getInitialLocation().getY() + SECOND_MOVE == move
					.getEndLocation().getY()) {
				isValid = true;
			} else if (move.getInitialLocation().getY() - INITIAL_MOVE == move
					.getEndLocation().getY()) {
				isValid = true;
			}
		} else if (move.getInitialLocation().getIntX() - INITIAL_MOVE == move
				.getEndLocation().getIntX()) {
			if (move.getInitialLocation().getY() + SECOND_MOVE == move
					.getEndLocation().getY()) {
				isValid = true;
			} else if (move.getInitialLocation().getY() - INITIAL_MOVE == move
					.getEndLocation().getY()) {
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
		return "n";
	}

}
