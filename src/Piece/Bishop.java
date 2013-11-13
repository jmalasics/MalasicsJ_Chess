package Piece;

import java.awt.Color;

import PieceManipulation.Location;

public class Bishop extends Piece {

	public Bishop(Location location, Color color) {
		super(location, color);
	}

	@Override
	public String toString() {
		return "Bishop";
	}
	
}
