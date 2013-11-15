package Piece;

import java.awt.Color;

import PieceManipulation.Location;

public class Rook extends Piece {
	
	public Rook(Location location, Color color) {
		super(location, color);
	}
	
	@Override
	public String toString() {
		return "r";
	}

}
