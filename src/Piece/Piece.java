package Piece;

import java.awt.Color;

import PieceManipulation.*;


public abstract class Piece {

	protected Color color;
	
	public Piece(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	/**
	 * Returns if the capture is valid for the piece.
	 * 
	 * @param capture
	 * @return
	 */
	public boolean isValidCapture(Capture capture) {
		return false;
	}
	
	/**
	 * Returns if the movement is valid for the piece.
	 * 
	 * @param move
	 * @return
	 */
	public boolean isValidMove(Movement move) {
		return false;
	}
	
}
