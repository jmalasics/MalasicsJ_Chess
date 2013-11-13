package Piece;

import java.awt.Color;

import PieceManipulation.Location;

public class King extends Piece {
	
	public King(Location location, Color color) {
		super(location, color);
	}
	
	@Override
	public String toString() {
		return "King";
	}

}
