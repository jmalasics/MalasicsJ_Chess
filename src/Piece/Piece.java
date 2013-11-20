package Piece;

import java.awt.Color;


public abstract class Piece {

	protected Color color;
	
	public Piece(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
}
