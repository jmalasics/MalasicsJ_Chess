package Piece;

import java.awt.Color;

import PieceManipulation.Location;

public class Knight extends Piece {
	
	public Knight(Location location, Color color) {
		super(location, color);
	}
	
	@Override
	public String toString() {
		return "n";
	}

}
