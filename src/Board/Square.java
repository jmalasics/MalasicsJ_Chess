package Board;

import java.awt.Color;

import Piece.*;
import PieceManipulation.Location;

public class Square {

	private Location location;
	private Piece piece;
	
	public Square(Location loc) {
		location = loc;
	}
	
	/**
	 * Gets the location of the square.
	 * 
	 * @return the location of the square
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * Sets the piece that is on the square.
	 * 
	 * @param p the piece you are placing on the square
	 */
	public void setPiece(Piece p) {
		piece = p;
	}
	
	/**
	 * Gets the piece that is on the square.
	 * 
	 * @return the piece that is on the square
	 */
	public Piece getPiece() {
		return piece;
	}

	@Override
	public String toString() {
		String s = "";
		if(piece == null) {
			s = "[ - ]"; 
		} else if(piece.getColor().equals(Color.WHITE)) {
			s = "[ " + piece.toString() + " ]";
		} else if(piece.getColor().equals(Color.BLACK)) {
			s = "[ " + piece.toString().toUpperCase() + " ]";
		}
		return s;
	}
	
	
	
}
