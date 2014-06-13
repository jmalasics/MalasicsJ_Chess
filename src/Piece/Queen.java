package Piece;

import GameLogic.PlayerTeam;
import GameLogic.Team;
import PieceManipulation.*;

import java.awt.*;


public class Queen extends Piece {

    private static final int QUEEN_PIECE_VALUE = 10;

	public Queen(Team team, Location location) {
		super(team, location, QUEEN_PIECE_VALUE);
        setImage(getFilePath());
	}
	
	@Override
	public boolean isValidMove(Movement move) {
        int distance = Math.abs(move.getInitialLocation().getIntX()
                - move.getEndLocation().getIntX());
        if (distance == 0) {
            distance = Math.abs(move.getInitialLocation().getY()
                    - move.getEndLocation().getY());
        }
        return distance > 0 && (isValidNorthMovement(distance, move) || isValidNorthEastMovement(distance, move) || isValidEastMovement(distance, move)
                || isValidSouthEastMovement(distance, move) || isValidSouthMovement(distance, move) || isValidSouthWestMovement(distance, move)
                    || isValidWestMovement(distance, move) || isValidNorthWestMovement(distance, move));
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

    @Override
    protected String getFilePath() {
        return this.getColor() == Color.WHITE ? "ChessPieceImages/WHITE_QUEEN.PNG" : "ChessPieceImages/BLACK_QUEEN.PNG";
    }

    @Override
    public void setLocation(Location location) {
        super.setLocation(location);
    }

    @Override
    public Piece makePieceCopy() {
        return new Queen(this.team, this.getLocation());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Queen && super.equals(obj);
    }

}
