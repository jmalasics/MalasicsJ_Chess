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
		return checkUpMove(move) || checkDownMove(move) || checkRightMove(move) || checkLeftMove(move);
	}
	
	private boolean checkUpMove(Movement move) {
		return move.getInitialLocation().getY() + INITIAL_MOVE == move
				.getEndLocation().getY() && (move.getInitialLocation().getIntX() + SECOND_MOVE == move
				.getEndLocation().getIntX() || move.getInitialLocation().getIntX()
				- SECOND_MOVE == move.getEndLocation().getIntX());
	}
	
	private boolean checkDownMove(Movement move) {
		return move.getInitialLocation().getY() - INITIAL_MOVE == move
				.getEndLocation().getY() && (move.getInitialLocation().getIntX() + SECOND_MOVE == move
					.getEndLocation().getIntX() || move.getInitialLocation().getIntX()
					- SECOND_MOVE == move.getEndLocation().getIntX());
	}
	
	private boolean checkRightMove(Movement move) {
		return move.getInitialLocation().getIntX() + INITIAL_MOVE == move
				.getEndLocation().getIntX() && (move.getInitialLocation().getY() + SECOND_MOVE == move
				.getEndLocation().getY() || move.getInitialLocation().getY() - SECOND_MOVE == move
				.getEndLocation().getY());
	}
	
	private boolean checkLeftMove(Movement move) {
		return move.getInitialLocation().getIntX() - INITIAL_MOVE == move
				.getEndLocation().getIntX() && (move.getInitialLocation().getY() + SECOND_MOVE == move
					.getEndLocation().getY() || move.getInitialLocation().getY() + SECOND_MOVE == move
					.getEndLocation().getY());
	}
	
	@Override
	public boolean isValidCapture(Capture capture) {
		return isValidMove(new Movement(capture.getInitialLocation(), capture.getEndLocation()));
	}
	public String getPieceName() {
		return "Knight";
	}
	
	@Override
	public String toString() {
		return "n";
	}

}
