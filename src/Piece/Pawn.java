package Piece;

import java.awt.Color;

import GameLogic.Team;
import PieceManipulation.*;


public class Pawn extends Piece {
	
	public static final int MAX_INITIAL_MOVE = 2;
	public static final int MAX_NORMAL_MOVE = 1;
	
	private boolean hasMoved = false;
	
	public Pawn(Team team) {
		super(team);
	}
	
	@Override
	public boolean isValidMove(Movement move) {
		boolean isValid = false;
		if(!hasMoved) {
			isValid = isValidInitialMove(move);
		} else if (move.getInitialLocation().getY() + MAX_NORMAL_MOVE == move
				.getEndLocation().getY()
				&& move.getInitialLocation().getIntX() == move
						.getEndLocation().getIntX() && team.getColor() == Color.BLACK) {
			isValid = true;
		} else if (move.getInitialLocation().getY() - MAX_NORMAL_MOVE == move
				.getEndLocation().getY()
				&& move.getInitialLocation().getIntX() == move
						.getEndLocation().getIntX() && team.getColor() == Color.WHITE) {
			isValid = true;
		}
		return isValid;
	}
	
	private boolean isValidInitialMove(Movement move) {
		boolean isValid = false;
		if (move.getInitialLocation().getIntX() == move.getEndLocation()
				.getIntX() && team.getColor() == Color.BLACK) {
			if (move.getInitialLocation().getY() + MAX_INITIAL_MOVE == move
					.getEndLocation().getY()
					|| move.getInitialLocation().getY() + MAX_NORMAL_MOVE == move
							.getEndLocation().getY()) {
				isValid = true;
			}
		} else if (move.getInitialLocation().getIntX() == move
				.getEndLocation().getIntX() && team.getColor() == Color.WHITE) {
			if (move.getInitialLocation().getY() - MAX_INITIAL_MOVE == move
					.getEndLocation().getY()
					|| move.getInitialLocation().getY() - MAX_NORMAL_MOVE == move
							.getEndLocation().getY()) {
				isValid = true;
			}
		}
		return isValid;
	}
	
	@Override
	public boolean isValidCapture(Capture capture) {
		boolean isValid = false;
		if ((capture.getInitialLocation().getIntX() + MAX_NORMAL_MOVE == capture
				.getEndLocation().getIntX() || capture.getInitialLocation()
				.getIntX() - MAX_NORMAL_MOVE == capture.getEndLocation()
				.getIntX())
				&& capture.getInitialLocation().getY() + MAX_NORMAL_MOVE == capture
						.getEndLocation().getY() && team.getColor() == Color.BLACK) {
			isValid = true;
		} else if ((capture.getInitialLocation().getIntX() + MAX_NORMAL_MOVE == capture
				.getEndLocation().getIntX() || capture.getInitialLocation()
				.getIntX() - MAX_NORMAL_MOVE == capture.getEndLocation()
				.getIntX())
				&& capture.getInitialLocation().getY() - MAX_NORMAL_MOVE == capture
						.getEndLocation().getY() && team.getColor() == Color.WHITE) {
			isValid = true;
		}
		return isValid;
	}
	
	@Override
	public String toString() {
		return "p";
	}

}
