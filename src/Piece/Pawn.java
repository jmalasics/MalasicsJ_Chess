package Piece;

import java.awt.Color;

import PieceManipulation.Location;

public class Pawn extends Piece {
	
	public Pawn(Location location, Color color) {
		super(location, color);
	}
	
	@Override
	public String toString() {
		return "Pawn";
	}

}
