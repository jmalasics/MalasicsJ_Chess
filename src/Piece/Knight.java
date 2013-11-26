package Piece;

import java.awt.Color;

import PieceManipulation.*;


public class Knight extends Piece {
	
	public static final int INITIAL_MOVE = 2;
	public static final int SECOND_MOVE = 1;
	
	public Knight(Color color) {
		super(color);
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
		boolean isValid = false;
		if (capture.getInitialLocation().getY() + INITIAL_MOVE == capture
				.getEndLocation().getY()) {
			if (capture.getInitialLocation().getIntX() + SECOND_MOVE == capture
					.getEndLocation().getIntX()) {
				isValid = true;
			} else if (capture.getInitialLocation().getIntX()
					- SECOND_MOVE == capture.getEndLocation().getIntX()) {
				isValid = true;
			}
		} else if (capture.getInitialLocation().getY() - INITIAL_MOVE == capture
				.getEndLocation().getY()) {
			if (capture.getInitialLocation().getIntX() + SECOND_MOVE == capture
					.getEndLocation().getIntX()) {
				isValid = true;
			} else if (capture.getInitialLocation().getIntX()
					- SECOND_MOVE == capture.getEndLocation().getIntX()) {
				isValid = true;
			}
		} else if (capture.getInitialLocation().getIntX() + INITIAL_MOVE == capture
				.getEndLocation().getIntX()) {
			if (capture.getInitialLocation().getY() + SECOND_MOVE == capture
					.getEndLocation().getY()) {
				isValid = true;
			} else if (capture.getInitialLocation().getY() - INITIAL_MOVE == capture
					.getEndLocation().getY()) {
				isValid = true;
			}
		} else if (capture.getInitialLocation().getIntX() - INITIAL_MOVE == capture
				.getEndLocation().getIntX()) {
			if (capture.getInitialLocation().getY() + SECOND_MOVE == capture
					.getEndLocation().getY()) {
				isValid = true;
			} else if (capture.getInitialLocation().getY() - INITIAL_MOVE == capture
					.getEndLocation().getY()) {
				isValid = true;
			}
		}
		return isValid;
	}
	
	@Override
	public String toString() {
		return "n";
	}

}
