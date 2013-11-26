package Piece;

import java.awt.Color;

import PieceManipulation.*;


public class Queen extends Piece {

	public Queen(Color color) {
		super(color);
	}
	
	@Override
	public boolean isValidMove(Movement move) {
		boolean isValid = false;
		int distance = Math.abs(move.getInitialLocation().getIntX()
				- move.getEndLocation().getIntX());
		if (distance == 0) {
			distance = Math.abs(move.getInitialLocation().getY()
					- move.getEndLocation().getY());
		}
		if (distance > 0) {
			if (move.getInitialLocation().getY() == move.getEndLocation()
					.getY()) {
				if (move.getInitialLocation().getIntX() + distance == move
						.getEndLocation().getIntX()) {
					isValid = true;
				} else if (move.getInitialLocation().getIntX() - distance == move
						.getEndLocation().getIntX()) {
					isValid = true;
				}
			} else if (move.getInitialLocation().getIntX() == move
					.getEndLocation().getIntX()) {
				if (move.getInitialLocation().getY() + distance == move
						.getEndLocation().getY()) {
					isValid = true;
				} else if (move.getInitialLocation().getY() - distance == move
						.getEndLocation().getY()) {
					isValid = true;
				}
			} else if (move.getInitialLocation().getY() + distance == move
					.getEndLocation().getY()) {
				if (move.getInitialLocation().getIntX() + distance == move
						.getEndLocation().getIntX()) {
					isValid = true;
				} else if (move.getInitialLocation().getIntX() - distance == move
						.getEndLocation().getIntX()) {
					isValid = true;
				}
			} else if (move.getInitialLocation().getY() - distance == move
					.getEndLocation().getY()) {
				if (move.getInitialLocation().getIntX() + distance == move
						.getEndLocation().getIntX()) {
					isValid = true;
				} else if (move.getInitialLocation().getIntX() - distance == move
						.getEndLocation().getIntX()) {
					isValid = true;
				}
			}
		}
		return isValid;
	}
	
	@Override
	public boolean isValidCapture(Capture capture) {
		boolean isValid = false;
		int distance = Math.abs(capture.getInitialLocation().getIntX()
				- capture.getEndLocation().getIntX());
		if (distance == 0) {
			distance = Math.abs(capture.getInitialLocation().getY()
					- capture.getEndLocation().getY());
		}
		if (distance > 0) {
			if (capture.getInitialLocation().getY() == capture.getEndLocation()
					.getY()) {
				if (capture.getInitialLocation().getIntX() + distance == capture
						.getEndLocation().getIntX()) {
					isValid = true;
				} else if (capture.getInitialLocation().getIntX() - distance == capture
						.getEndLocation().getIntX()) {
					isValid = true;
				}
			} else if (capture.getInitialLocation().getIntX() == capture
					.getEndLocation().getIntX()) {
				if (capture.getInitialLocation().getY() + distance == capture
						.getEndLocation().getY()) {
					isValid = true;
				} else if (capture.getInitialLocation().getY() - distance == capture
						.getEndLocation().getY()) {
					isValid = true;
				}
			} else if (capture.getInitialLocation().getY() + distance == capture
					.getEndLocation().getY()) {
				if (capture.getInitialLocation().getIntX() + distance == capture
						.getEndLocation().getIntX()) {
					isValid = true;
				} else if (capture.getInitialLocation().getIntX() - distance == capture
						.getEndLocation().getIntX()) {
					isValid = true;
				}
			} else if (capture.getInitialLocation().getY() - distance == capture
					.getEndLocation().getY()) {
				if (capture.getInitialLocation().getIntX() + distance == capture
						.getEndLocation().getIntX()) {
					isValid = true;
				} else if (capture.getInitialLocation().getIntX() - distance == capture
						.getEndLocation().getIntX()) {
					isValid = true;
				}
			}
		}
		return isValid;
	}
	
	@Override
	public String toString() {
		return "q";
	}
	
}
