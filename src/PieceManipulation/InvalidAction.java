package PieceManipulation;

import Board.ChessBoard;
import Exception.InvalidActionException;

public class InvalidAction extends ChessAction {
	
	public boolean executeAction(ChessBoard board) throws InvalidActionException {
        throw new InvalidActionException("Invalid command entered.");
	}
	
}
