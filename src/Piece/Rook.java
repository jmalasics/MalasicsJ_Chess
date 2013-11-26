package Piece;

import java.awt.Color;

import PieceManipulation.*;


public class Rook extends Piece {
	
	public Rook(Color color) {
		super(color);
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
		boolean isValid = false;
		int distance = 0;
		if(capture.getInitialLocation().getIntX() == capture.getEndLocation().getIntX()) {
			distance = Math.abs(capture.getInitialLocation().getY() - capture.getEndLocation().getY());
			if(distance > 0) {
				isValid = true;
			}
		} else if(capture.getInitialLocation().getY() == capture.getEndLocation().getY()) {
			distance = Math.abs(capture.getInitialLocation().getIntX() - capture.getEndLocation().getIntX());
			if(distance > 0) {
				isValid = true;
			}
		}
		return isValid;
	}
	
	@Override
	public String toString() {
		return "r";
	}

}
