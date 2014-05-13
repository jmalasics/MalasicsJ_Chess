package Piece;

import GameLogic.Team;
import PieceManipulation.*;


public class Queen extends Piece {

	public Queen(Team team) {
		super(team);
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
		return isValidMove(new Movement(capture.getInitialLocation(), capture.getEndLocation()));
	}
	
	public String getPieceName() {
		return "Queen";
	}
	
	@Override
	public String toString() {
		return "q";
	}
	
}
