package Piece;

import java.awt.Color;

import GameLogic.Team;
import PieceManipulation.*;


public abstract class Piece {

	protected Team team;
	
	public Piece(Team team) {
		this.team = team;
	}
	
	public Color getColor() {
		return team.getColor();
	}
	
	public Team getTeam() {
		return team;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj && this.team == ((Piece) obj).team;
	}
	
	
	
}
