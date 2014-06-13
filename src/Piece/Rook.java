package Piece;

import GameLogic.Team;
import PieceManipulation.*;

import java.awt.*;


public class Rook extends Piece {

    private static final int ROOK_PIECE_VALUE = 5;
	
	public Rook(Team team, Location location) {
		super(team, location, ROOK_PIECE_VALUE);
        setImage(getFilePath());
	}
	
	@Override
	public boolean isValidMove(Movement move) {
        int distance = Math.abs(move.getInitialLocation().getIntX() - move.getEndLocation().getIntX());
        if(distance == 0) {
            distance = Math.abs(move.getInitialLocation().getArrayY() - move.getEndLocation().getArrayY());
        }
        return distance > 0 && (isValidNorthMovement(distance, move) || isValidEastMovement(distance, move) || isValidSouthMovement(distance, move)
                || isValidWestMovement(distance, move));
	}

    @Override
    public Piece makePieceCopy() {
        return new Rook(this.team, this.getLocation());
    }

    @Override
	public boolean isValidCapture(Capture capture) {
		return isValidMove(new Movement(capture.getInitialLocation(), capture.getEndLocation()));
	}
	
	public String getPieceName() {
		return "Rook";
	}

    @Override
    protected String getFilePath() {
        return this.getColor() == Color.WHITE ? "ChessPieceImages/WHITE_ROOK.PNG" : "ChessPieceImages/BLACK_ROOK.PNG";
    }
	
	@Override
	public String toString() {
		return "r";
	}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Rook && super.equals(obj);
    }

}
