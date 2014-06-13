package Piece;

import GameLogic.PlayerTeam;
import GameLogic.Team;
import PieceManipulation.*;

import java.awt.*;


public class Bishop extends Piece {

    private static final int BISHOP_PIECE_VALUE = 4;

	public Bishop(Team team, Location location) {
		super(team, location, BISHOP_PIECE_VALUE);
        setImage(getFilePath());
	}
	
	@Override
	public boolean isValidMove(Movement move) {
        int distance = Math.abs(move.getInitialLocation().getIntX() - move.getEndLocation().getIntX());
        return distance > 0 && (isValidNorthEastMovement(distance, move) || isValidNorthWestMovement(distance, move) || isValidSouthEastMovement(distance, move)
                || isValidSouthWestMovement(distance, move));
	}
	
	public String getPieceName() {
		return "Bishop";
	}

    @Override
    protected String getFilePath() {
        return this.getColor() == Color.WHITE ? "ChessPieceImages/WHITE_BISHOP.PNG" : "ChessPieceImages/BLACK_BISHOP.PNG";
    }

    @Override
    public Piece makePieceCopy() {
        return new Bishop(this.team, this.getLocation());
    }

    @Override
	public boolean isValidCapture(Capture capture) {
		return isValidMove(new Movement(capture.getInitialLocation(), capture.getEndLocation()));
	}

	@Override
	public String toString() {
		return "b";
	}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Bishop && super.equals(obj);
    }
}
