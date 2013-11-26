package Piece;

import java.awt.Color;

import PieceManipulation.*;


public class King extends Piece {
	
	private static final int MAX_MOVE_DISTANCE = 1;
	
	public King(Color color) {
		super(color);
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
		boolean isValid = false;
		if(capture.getInitialLocation().getY() == capture.getEndLocation().getY()) {
			if(capture.getInitialLocation().getIntX() + MAX_MOVE_DISTANCE == capture.getEndLocation().getIntX()) {
				isValid = true;
			} else if(capture.getInitialLocation().getIntX() - MAX_MOVE_DISTANCE == capture.getEndLocation().getIntX()) {
				isValid = true;
			}
		} else if(capture.getInitialLocation().getIntX() == capture.getEndLocation().getIntX()) {
			if(capture.getInitialLocation().getY() + MAX_MOVE_DISTANCE == capture.getEndLocation().getY()) {
				isValid = true;
			} else if(capture.getInitialLocation().getY() - MAX_MOVE_DISTANCE == capture.getEndLocation().getY()) {
				isValid = true;
			}
		} else if(capture.getInitialLocation().getY() + MAX_MOVE_DISTANCE == capture.getEndLocation().getY()) {
			if(capture.getInitialLocation().getIntX() + MAX_MOVE_DISTANCE == capture.getEndLocation().getIntX()) {
				isValid = true;
			} else if(capture.getInitialLocation().getIntX() - MAX_MOVE_DISTANCE == capture.getEndLocation().getIntX()) {
				isValid = true;
			}
		} else if(capture.getInitialLocation().getY() - MAX_MOVE_DISTANCE == capture.getEndLocation().getY()) {
			if(capture.getInitialLocation().getIntX() + MAX_MOVE_DISTANCE == capture.getEndLocation().getIntX()) {
				isValid = true;
			} else if(capture.getInitialLocation().getIntX() - MAX_MOVE_DISTANCE == capture.getEndLocation().getIntX()) {
				isValid = true;
			}
		}
		return isValid;
	}
	
	@Override
	public String toString() {
		return "k";
	}

}
