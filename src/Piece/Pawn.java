package Piece;

import java.awt.Color;

import GameLogic.Team;
import PieceManipulation.*;


public class Pawn extends Piece {
	
	public static final int MAX_INITIAL_MOVE = 2;
	public static final int MAX_NORMAL_MOVE = 1;
	
	private boolean hasMoved;
	
	public Pawn(Team team, Location location) {
		super(team, location);
		hasMoved = false;
	}
	
	@Override
	public boolean isValidMove(Movement move) {
        int distance = Math.abs(move.getInitialLocation().getArrayY() - move.getEndLocation().getArrayY());
        boolean isValid = false;
        if(!hasMoved) {
            isValid = isValidInitialMove(move, distance);
        } else if(distance == MAX_NORMAL_MOVE) {
            if(this.getColor() == Color.BLACK) {
                isValid = isValidSouthMovement(distance, move);
            } else if(this.getColor() == Color.WHITE)
                isValid = isValidNorthMovement(distance, move);
        }
        return isValid;
	}
	
	private boolean isValidInitialMove(Movement move, int distance) {
        boolean isValid = false;
        if(distance <= MAX_INITIAL_MOVE) {
            if(this.getColor() == Color.BLACK) {
                isValid = isValidSouthMovement(distance, move);
            } else if(this.getColor() == Color.WHITE) {
                isValid = isValidNorthMovement(distance, move);
            }
        }
        return isValid;
	}
	
	@Override
	public boolean isValidCapture(Capture capture) {
        int distance = Math.abs(capture.getInitialLocation().getArrayY() - capture.getEndLocation().getArrayY());
        boolean isValid = false;
        if(distance == MAX_NORMAL_MOVE) {
            if(this.getColor() == Color.BLACK) {
                isValid = isValidSouthEastMovement(distance, capture) || isValidSouthWestMovement(distance, capture);
            } else if(this.getColor() == Color.WHITE) {
                isValid = isValidNorthEastMovement(distance, capture) || isValidNorthWestMovement(distance, capture);
            }
        }
        return isValid;
	}
	
	public void pawnHasMoved() {
		hasMoved = true;
	}

    public void resetHasMoved() {
        hasMoved = false;
    }

	public String getPieceName() {
		return "Pawn";
	}
	
	@Override
	public String toString() {
		return "p";
	}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if(obj != null && obj instanceof Pawn) {
            Pawn pawn = (Pawn) obj;
            isEqual = pawn.hasMoved == this.hasMoved && super.equals(pawn);
        }
        return isEqual;
    }

}
