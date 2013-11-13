package Piece;

import java.awt.Color;

import PieceManipulation.Location;

public abstract class Piece {

	protected Location location;
	protected Color color;
	
	public Piece(Location loc, Color color) {
		location = loc;
		this.color = color;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String getColor() {
		String pieceColor = "Black";
		if(color == Color.WHITE) {
			pieceColor = "White";
		}
		return pieceColor;
	}
	
}
