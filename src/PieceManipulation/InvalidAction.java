package PieceManipulation;

import Board.ChessBoard;

public class InvalidAction extends ChessAction {
	
	public boolean executeAction(ChessBoard board) {
		System.err.println("Invalid command entered.");
		return false;
	}
	
}
