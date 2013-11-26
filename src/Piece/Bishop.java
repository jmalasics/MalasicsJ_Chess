package Piece;

import java.awt.Color;

import PieceManipulation.*;


public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
	}
	
	@Override
	public boolean isValidMove(Movement move) {
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
	}
	
	@Override
	public boolean isValidCapture(Capture capture) {
		boolean isValid = false;
		int distance = Math.abs(capture.getInitialLocation().getIntX() - capture.getEndLocation().getIntX());
		if(capture.getInitialLocation().getIntX() + distance == capture.getEndLocation().getIntX()) {
			if(capture.getInitialLocation().getArrayY() + distance == capture.getEndLocation().getArrayY()) {
				isValid = true;
			} else if(capture.getInitialLocation().getArrayY() - distance == capture.getEndLocation().getArrayY()) {
				isValid = true;
			}
		} else if(capture.getInitialLocation().getIntX() - distance == capture.getEndLocation().getIntX()) {
			if(capture.getInitialLocation().getArrayY() + distance == capture.getEndLocation().getArrayY()) {
				isValid = true;
			} else if(capture.getInitialLocation().getArrayY() - distance == capture.getEndLocation().getArrayY()) {
				isValid = true;
			}
		}
		return isValid;
	}

	@Override
	public String toString() {
		return "b";
	}
	
}
