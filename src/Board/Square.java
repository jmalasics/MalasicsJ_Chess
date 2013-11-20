package Board;

import java.awt.Color;

import Piece.*;
import PieceManipulation.Location;

public class Square {

	private Color color;
	private Location location;
	private Piece piece;
	
	public Square(Color c, Location loc) {
		color = c;
		location = loc;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setPiece(Piece p) {
		piece = p;
	}
	
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
