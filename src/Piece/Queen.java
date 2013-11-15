package Piece;

import java.awt.Color;

import PieceManipulation.Location;

public class Queen extends Piece {

	public Queen(Location location, Color color) {
		super(location, color);
	}
	
	@Override
	public String toString() {
		return "q";
	}
	
}
