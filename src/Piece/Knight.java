package Piece;

import GameLogic.PlayerTeam;
import GameLogic.Team;
import PieceManipulation.*;

import java.awt.*;


public class Knight extends Piece {
	
	public static final int INITIAL_MOVE = 2;
	public static final int SECOND_MOVE = 1;

    private static final int KNIGHT_PIECE_VALUE = 3;
	
	public Knight(Team team, Location location) {
		super(team, location, KNIGHT_PIECE_VALUE);
        setImage(getFilePath());
	}

    @Override
    protected String getFilePath() {
        return this.getColor() == Color.WHITE ? "ChessPieceImages/WHITE_KNIGHT.PNG" : "ChessPieceImages/BLACK_KNIGHT.PNG";
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
					.getEndLocation().getY() || move.getInitialLocation().getY() - SECOND_MOVE == move
					.getEndLocation().getY());
	}

    @Override
    public Piece makePieceCopy() {
        return new Knight(this.team, this.getLocation());
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Knight && super.equals(obj);
    }

}
