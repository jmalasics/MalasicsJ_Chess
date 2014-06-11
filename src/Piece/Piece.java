package Piece;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

import GameLogic.PlayerTeam;
import GameLogic.Team;
import PieceManipulation.*;


public abstract class Piece {

	protected Team team;
	private ArrayList<ChessAction> possibleMoves;
    private Location location;
    private Image pieceImage;
    private int pieceValue;
	
	public Piece(Team team, Location location, int pieceValue) {
		this.team = team;
		possibleMoves = new ArrayList<ChessAction>();
        this.location = location;
        this.pieceValue = pieceValue;
	}

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
	
    public int getPieceValue() {
        return pieceValue;
    }

	public void addMove(ChessAction action) {
		possibleMoves.add(action);
	}
	
	public void clearMoves() {
		possibleMoves = new ArrayList<ChessAction>();
	}

    public boolean isOnSameTeam(Piece piece) {
        return this.getColor() == piece.getColor();
    }
	
	public ArrayList<ChessAction> getMoves() {
		return possibleMoves;
	}
	
	public Color getColor() {
		return team.getColor();
	}
	
	public Team getTeam() {
		return team;
	}
	
    public Image getPieceImage() {
        return pieceImage;
    }

    protected void setImage(String filePath) {
        URL imageURL = this.getClass().getResource(filePath);
        pieceImage = Toolkit.getDefaultToolkit().getImage(imageURL);
    }

	/**
	 * Returns if the capture is valid for the piece.
	 * 
	 * @param capture
	 * @return
	 */
	public abstract boolean isValidCapture(Capture capture);
	
	/**
	 * Returns if the movement is valid for the piece.
	 * 
	 * @param move
	 * @return
	 */
	public abstract boolean isValidMove(Movement move);
	
	public abstract String getPieceName();

    protected abstract String getFilePath();

    protected boolean isValidNorthMovement(int distance, ChessAction action) {
        return action.getInitialLocation().getArrayY() - distance == action.getEndLocation().getArrayY() && action.getInitialLocation().getIntX() == action.getEndLocation().getIntX();
    }

    protected boolean isValidSouthMovement(int distance, ChessAction action) {
        return action.getInitialLocation().getArrayY() + distance == action.getEndLocation().getArrayY() && action.getInitialLocation().getIntX() == action.getEndLocation().getIntX();
    }

    protected boolean isValidEastMovement(int distance, ChessAction action) {
        return action.getInitialLocation().getIntX() + distance == action.getEndLocation().getIntX() && action.getInitialLocation().getArrayY() == action.getEndLocation().getArrayY();
    }

    protected boolean isValidWestMovement(int distance, ChessAction action) {
        return action.getInitialLocation().getIntX() - distance == action.getEndLocation().getIntX() && action.getInitialLocation().getArrayY() == action.getEndLocation().getArrayY();
    }

    protected boolean isValidNorthEastMovement(int distance, ChessAction action) {
        return action.getInitialLocation().getIntX() + distance == action.getEndLocation().getIntX() && action.getInitialLocation().getArrayY() - distance == action.getEndLocation().getArrayY();
    }

    protected boolean isValidNorthWestMovement(int distance, ChessAction action) {
        return action.getInitialLocation().getIntX() - distance == action.getEndLocation().getIntX() && action.getInitialLocation().getArrayY() - distance == action.getEndLocation().getArrayY();
    }

    protected boolean isValidSouthEastMovement(int distance, ChessAction action) {
        return action.getInitialLocation().getIntX() + distance == action.getEndLocation().getIntX() && action.getInitialLocation().getArrayY() + distance == action.getEndLocation().getArrayY();
    }

    protected boolean isValidSouthWestMovement(int distance, ChessAction action) {
        return action.getInitialLocation().getIntX() - distance == action.getEndLocation().getIntX() && action.getInitialLocation().getArrayY() + distance == action.getEndLocation().getArrayY();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
        boolean isEqual = false;
        if(obj != null && obj instanceof Piece) {
            Piece piece = (Piece) obj;
            isEqual = piece.getLocation().equals(this.getLocation()) && piece.getTeam().equals(this.getTeam());
        }
		return isEqual;
	}
	
	
	
}
