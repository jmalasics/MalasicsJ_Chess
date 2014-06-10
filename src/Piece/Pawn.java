package Piece;

import java.awt.*;

import Board.ChessBoard;
import GameLogic.Team;
import PieceManipulation.*;


public class Pawn extends Piece {
	
	public static final int MAX_INITIAL_MOVE = 2;
	public static final int MAX_NORMAL_MOVE = 1;
	
	private boolean hasMoved;
	
	public Pawn(Team team, Location location) {
		super(team, location);
		hasMoved = false;
        setImage(getFilePath());
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

	public String getPieceName() {
		return "Pawn";
	}

    @Override
    protected String getFilePath() {
        return this.getColor() == Color.WHITE ? "ChessPieceImages/WHITE_PAWN.PNG" : "ChessPieceImages/BLACK_PAWN.PNG";
    }

    /**
     * Removes itself from the team and adds the new piece when promoting a pawn.
     *
     * @param newPiece the new piece that is replacing the pawn
     * @param board the board you currently are playing on
     */
    public void promote(Piece newPiece, ChessBoard board) {
        team.addCapturedPiece(this);
        team.addPiece(newPiece, board);
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
