package GameLogic;

import java.awt.Color;
import java.util.ArrayList;

import Piece.King;
import Piece.Piece;
import PieceManipulation.*;
import Board.*;

public class Team {

	private Color teamColor;
	private ArrayList<Piece> pieces;
	private ArrayList<ChessAction> possibleMoves;
	
	public Team(Color color) {
		teamColor = color;
		possibleMoves = new ArrayList<ChessAction>();
		pieces = new ArrayList<Piece>();
	}
	
	public Color getColor() {
		return teamColor;
	}
	
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	
	public void addPiece(Piece piece) {
		pieces.add(piece);
	}
	
	public void removePiece(Piece piece) {
		pieces.remove(piece);
	}
	
	public boolean performAction(ChessAction action, ChessBoard board) {
		boolean actionCompleted = false;
		if(isTeamPiece(action.getInitialLocation(), board)) {
			actionCompleted = action.executeAction(board);
			findAllAvailableMoves(board);
		} else {
			System.err.println("Cannot move a piece that isn't yours.");
		}
		return actionCompleted;
	}
	
	private void findAllAvailableMoves(ChessBoard board) {
		possibleMoves = new ArrayList<ChessAction>();
		for(Piece piece : pieces) {
			addPossibleMoves(allAvailableMovesForPiece(board, piece));
		}
	}
	
	private ArrayList<ChessAction> allAvailableMovesForPiece(ChessBoard board, Piece piece) {
		ArrayList<ChessAction> actions = new ArrayList<ChessAction>();
		for(int i = 0; i < ChessBoard.BOARD_ROWS; i++) {
			for(int j = 0; j < ChessBoard.BOARD_COLUMNS; j++) {
				Movement move = new Movement(board.getPieceLocation(piece), new Location(i, j));
				Capture capture = new Capture(board.getPieceLocation(piece), new Location(i, j));
				if(piece.isValidMove(move)) {
					actions.add(move);
				}
				if(piece.isValidCapture(capture)) {
					actions.add(capture);
				}
			}
		}
		return actions;
	}
	
	private void addPossibleMoves(ArrayList<ChessAction> actions) {
		for(ChessAction action : actions) {
			possibleMoves.add(action);
		}
	}
	
	private boolean isTeamPiece(Location location, ChessBoard board) {
		return board.getPieceAt(location).getColor() == this.teamColor;
	}
	
	public boolean isInCheck(ChessBoard board, ArrayList<ChessAction> enemyActions) {
		boolean isInCheck = false;
		Location kingLocation = board.getPieceLocation(new King(this));
		for(ChessAction action : enemyActions) {
			isInCheck = action.getEndLocation().equals(kingLocation) ? true : isInCheck;
		}
		return isInCheck;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((teamColor == null) ? 0 : teamColor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Team otherTeam = (Team) obj;
		return this.teamColor == otherTeam.getColor() && this.pieces == otherTeam.getPieces();
	}
	
	
	
}
