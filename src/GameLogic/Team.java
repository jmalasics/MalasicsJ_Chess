package GameLogic;

import java.awt.Color;
import java.util.ArrayList;

import Piece.*;
import PieceManipulation.*;
import Board.*;

public class Team {

	private Color teamColor;
	private ArrayList<Piece> pieces;
	
	private Location kingLocation;
	
	public Team(Color color) {
		teamColor = color;
		pieces = new ArrayList<Piece>();
	}
	
	public Color getColor() {
		return teamColor;
	}
	
	public void addPiece(Piece piece, ChessBoard board) {
		pieces.add(piece);
		if(piece instanceof King && piece.getTeam().equals(this)) {
			kingLocation = board.getPieceLocation(piece);
		}
	}
	
	public void removePiece(Piece piece) {
		pieces.remove(piece);
	}
	
	public boolean performAction(ChessAction action, ChessBoard board, Team otherTeam) {
		boolean actionCompleted = false;
        if(!isInCheckmate(board, otherTeam)) {
		    findAllAvailableMoves(board, otherTeam);
		    if(isTeamPiece(action.getInitialLocation(), board)) {
		        if(containsMove(action, board.getPieceAt(action.getInitialLocation()))) {
			        board.movePiece(action.getInitialLocation(), action.getEndLocation());
			        actionCompleted = true;
			        getKingLocation(board);
		        }
		    } else {
			    System.err.println("Cannot move a piece that isn't yours.");
			    System.err.flush();
		    }
		    findAllAvailableMoves(board, otherTeam);
        } else {
            printCheckmateMessage();
        }
		return actionCompleted;
	}
	
	public boolean containsMove(ChessAction action, Piece piece) {
		boolean isContained = false;
		for(ChessAction act : piece.getMoves()) {
			if(act.equals(action)) {
				isContained = true;
			}
		}
		return isContained;
	}
	
	/**
	 * Returns if move puts team in check or not.
	 * 
	 * @param action
	 * @param board
	 * @return
	 */
	public boolean isMovingIntoCheck(ChessAction action, ChessBoard board, Team otherTeam) {
		boolean isMovingIntoCheck = false;
        Piece capturedPiece = null;
        if(action instanceof Capture) {
            capturedPiece = board.getPieceAt(action.getEndLocation());
        }
        board.movePiece(action.getInitialLocation(), action.getEndLocation());
        otherTeam.findAllAvailableMoves(board, this);
        isMovingIntoCheck = isInCheck(board, otherTeam.getMoves());
        board.movePiece(action.getEndLocation(), action.getInitialLocation());
        board.placePiece(new Placement(action.getEndLocation(), capturedPiece));
        otherTeam.findAllAvailableMoves(board, this);
        return isMovingIntoCheck;
	}
	
	private void findAllAvailableMoves(ChessBoard board, Team otherTeam) {
		for(Piece piece : pieces) {
			piece.clearMoves();
			addPossibleMoves(piece, allAvailableMovesForPiece(board, piece));
			if(!(piece instanceof Knight)) {
				removeBlockedActions(board, piece);
			}
			removeIntoCheckMoves(piece, board, otherTeam);
		}
	}
	
	private void removeIntoCheckMoves(Piece piece, ChessBoard board, Team otherTeam) {
		for(int i = 0; i < piece.getMoves().size(); i++) {
			if(isMovingIntoCheck(piece.getMoves().get(i), board, otherTeam)) {
				piece.getMoves().remove(piece.getMoves().get(i));
			}
		}
	}
	
	private ArrayList<ChessAction> allAvailableMovesForPiece(ChessBoard board, Piece piece) {
		ArrayList<ChessAction> actions = new ArrayList<ChessAction>();
		for(int i = 0; i < ChessBoard.BOARD_ROWS; i++) {
			for(int j = 0; j < ChessBoard.BOARD_COLUMNS; j++) {
				if(board.getPieceAt(new Location(j, i)) == null) {
					Movement move = new Movement(board.getPieceLocation(piece), new Location(j, i));
					if(board.canMove(move)) {
						actions.add(move);
					}
				} else if(board.getPieceAt(new Location(j, i)).getColor() != piece.getColor()){
					Capture capture = new Capture(board.getPieceLocation(piece), new Location(j, i));
					if(board.canCapture(capture)) {
						actions.add(capture);
					}
				}
			}
		}
		return actions;
	}
	
	private void addPossibleMoves(Piece piece, ArrayList<ChessAction> actions) {
		for(ChessAction action : actions) {
			piece.addMove(action);
		}
	}
	
	private boolean isTeamPiece(Location location, ChessBoard board) {
		return board.getPieceAt(location) != null ? board.getPieceAt(location).getColor() == this.teamColor : false;
	}
	
	public boolean isInCheck(ChessBoard board, ArrayList<ChessAction> enemyActions) {
		boolean isInCheck = false;
		for(ChessAction action : enemyActions) {
			isInCheck = action.getEndLocation().equals(kingLocation) ? true : isInCheck;
		}
		return isInCheck;
	}

    public boolean isInCheckmate(ChessBoard board, Team otherTeam) {
        return isInCheck(board, otherTeam.getMoves()) && otherTeam.getMoves().size() == 0;
    }
	
	public void printCheckMessage() {
		String teamColorString = teamColor == Color.WHITE ? "White" : "Black";
		System.out.println("The " + teamColorString +"'s king is in check.");
	}
	
	public void printCheckmateMessage() {
		String teamColorString = teamColor == Color.WHITE ? "White" : "Black";
		System.out.println(teamColorString + " has been checkmated.");
	}
	
	private void getKingLocation(ChessBoard board) {
		for(Piece piece : pieces) {
			if(piece instanceof King && piece.getTeam().equals(this)) {
				kingLocation = board.getPieceLocation(piece);
			}
		}
	}
	
	private boolean isBlockedAction(ChessAction action, ChessBoard board) {
		boolean isBlocked = false;
		int xDirection = getXDirection(action);
        int yDirection = getYDirection(action);
        int xValue = action.getInitialLocation().getIntX() + xDirection;
		int yValue = action.getInitialLocation().getArrayY() + yDirection;
		while(!reachedActionEndLocation(xValue, yValue, action) && !isBlocked) {
            if(board.isPieceAt(new Location(xValue, yValue))) {
                isBlocked = true;
            }
            xValue += xDirection;
            yValue += yDirection;
        }
		return isBlocked;
	}

    private int getYDirection(ChessAction action) {
        int yDirection = 0;
        if(action.getEndLocation().getArrayY() - action.getInitialLocation().getArrayY() != 0) {
            yDirection = action.getEndLocation().getArrayY() - action.getInitialLocation().getArrayY() > 0 ? 1 : -1;
        }
        return yDirection;
    }

    private int getXDirection(ChessAction action) {
        int xDirection = 0;
        if(action.getEndLocation().getIntX() - action.getInitialLocation().getIntX() != 0) {
            xDirection = action.getEndLocation().getIntX() - action.getInitialLocation().getIntX() > 0 ? 1 : -1;
        }
        return xDirection;
    }

    private boolean reachedActionEndLocation(int xLocation, int yLocation, ChessAction action) {
        return xLocation == action.getEndLocation().getIntX() && yLocation == action.getEndLocation().getArrayY();
    }
	
	private void removeBlockedActions(ChessBoard board, Piece piece) {
        int index = 0;
        while(index != piece.getMoves().size()) {
            ChessAction action = piece.getMoves().get(index);
            if(isBlockedAction(action, board)) {
                piece.getMoves().remove(piece.getMoves().get(index));
            } else {
                index++;
            }
        }
	}
	
	public ArrayList<ChessAction> getMoves() {
		ArrayList<ChessAction> allPossibleMoves = new ArrayList<ChessAction>();
		for(Piece piece : pieces) {
			for(ChessAction action : piece.getMoves()) {
				allPossibleMoves.add(action);
			}
		}
		return allPossibleMoves;
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
		return this.teamColor == otherTeam.getColor();
	}
	
	
	
}
