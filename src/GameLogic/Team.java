package GameLogic;

import java.awt.Color;
import java.util.ArrayList;

import Piece.*;
import PieceManipulation.*;
import Board.*;
import Exception.*;

public class Team {

	private Color teamColor;
	private ArrayList<Piece> pieces;
    private ArrayList<Piece> capturedPieces;
	
	private Location kingLocation;
	
	public Team(Color color) {
		teamColor = color;
		pieces = new ArrayList<Piece>();
        capturedPieces = new ArrayList<Piece>();
	}

    /**
     * Gets the color of the team.
     *
     * @return the color for the team
     */
	public Color getColor() {
		return teamColor;
	}

    /**
     * Adds a piece to the team.
     *
     * @param piece the piece being added to the team
     * @param board the board that the piece was added on
     */
	public void addPiece(Piece piece, ChessBoard board) {
		pieces.add(piece);
		if(piece instanceof King && piece.getTeam().equals(this)) {
			kingLocation = board.getPieceLocation(piece);
		}
	}

    /**
     * Removes the piece from the team's active pieces.
     *
     * @param piece the piece you wish to remove from the team
     */
	public void addCapturedPiece(Piece piece) {
		pieces.remove(piece);
        capturedPieces.add(piece);
	}

    public Piece getPieceAt(Location location) {
        Piece targetPiece = null;
        for(Piece piece : pieces) {
            if(piece.getLocation().equals(location)) {
                targetPiece = piece;
            }
        }
        return targetPiece;
    }

    /**
     * Takes in an action and after determining if the move is allowed it executes the action.
     *
     * @param action the action you wish the team to perform
     * @param board the board that you are performing the action on
     * @param otherTeam the enemy team of this team
     * @return a boolean representing if the action was successful or not
     * @throws Exception
     */
	public boolean performAction(ChessAction action, ChessBoard board, Team otherTeam) throws Exception {
		boolean actionCompleted = false;
        if(action.executeAction(board)) {
            getKingLocation();
            board.getPieceAt(action.getEndLocation()).setLocation(action.getEndLocation());
            if(action instanceof Capture) {
                otherTeam.addCapturedPiece(otherTeam.getPieceAt(action.getEndLocation()));
            }
            actionCompleted = true;
            this.findAllAvailableMoves(board);
            this.removeIntoCheckMoves(board, otherTeam);
            otherTeam.findAllAvailableMoves(board);
            otherTeam.removeIntoCheckMoves(board, this);
        }
		return actionCompleted;
	}

    /**
     * Checks to see if the action is a contained in the valid moves for the piece.
     *
     * @param action the action you wish the piece to perform
     * @param piece the piece you wish to perform the action
     * @return a boolean representing if the action is in the list of its valid moves
     */
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
	 * @param action the action that you wish to perform
	 * @param board the board that the action is being performed on
	 * @return a boolean representing if the team is in check after the move
	 */
	public boolean isMovingIntoCheck(ChessAction action, ChessBoard board, Team otherTeam) throws PlacementException {
		boolean isMovingIntoCheck;
        Piece capturedPiece = null;
        if(action instanceof Capture) {
            capturedPiece = board.getPieceAt(action.getEndLocation());
        }
        board.movePiece(action.getInitialLocation(), action.getEndLocation());
        board.getPieceAt(action.getEndLocation()).setLocation(action.getEndLocation());
        getKingLocation();
        otherTeam.findAllAvailableMoves(board);
        isMovingIntoCheck = isInCheck(otherTeam.getMoves());
        board.movePiece(action.getEndLocation(), action.getInitialLocation());
        board.placePiece(new Placement(action.getEndLocation(), capturedPiece));
        board.getPieceAt(action.getInitialLocation()).setLocation(action.getInitialLocation());
        getKingLocation();
        otherTeam.findAllAvailableMoves(board);
        return isMovingIntoCheck;
	}

    /**
     * Finds all the available moves for the team.
     *
     * @param board the board you are currently playing on
     */
	public void findAllAvailableMoves(ChessBoard board) {
		for(Piece piece : pieces) {
			piece.clearMoves();
			addPossibleMoves(piece, allAvailableMovesForPiece(board, piece));
			if(!(piece instanceof Knight)) {
				removeBlockedActions(board, piece);
			}
		}
	}

    /**
     * Removes moves that result in your king being in check.
     *
     * @param board the board that you are playing on
     * @param otherTeam the enemy team of this team
     */
	public void removeIntoCheckMoves(ChessBoard board, Team otherTeam) {
        for(Piece piece : pieces) {
		    for(int i = piece.getMoves().size() - 1; i > -1; i--) {
                try {
			        if(isMovingIntoCheck(piece.getMoves().get(i), board, otherTeam)) {
				        piece.getMoves().remove(piece.getMoves().get(i));
			        }
                } catch(PlacementException pe) {
                    pe.printStackTrace();
                }
		    }
        }
	}

    /**
     * Gets all the available moves for the piece.
     *
     * @param board the board you are currently playing on
     * @param piece the piece you are getting the valid moves for
     * @return a list of valid moves for the piece
     */
	private ArrayList<ChessAction> allAvailableMovesForPiece(ChessBoard board, Piece piece) {
		ArrayList<ChessAction> actions = new ArrayList<ChessAction>();
		for(int i = 0; i < ChessBoard.BOARD_ROWS; i++) {
			for(int j = 0; j < ChessBoard.BOARD_COLUMNS; j++) {
				if(board.getPieceAt(new Location(j, i)) == null) {
					Movement move = new Movement(piece.getLocation(), new Location(j, i));
					if(board.canMove(move)) {
						actions.add(move);
					}
				} else if(board.getPieceAt(new Location(j, i)).getColor() != piece.getColor()){
					Capture capture = new Capture(piece.getLocation(), new Location(j, i));
					if(board.canCapture(capture)) {
						actions.add(capture);
					}
				}
			}
		}
		return actions;
	}

    /**
     * Adds the possible moves of a piece to the piece.
     *
     * @param piece the piece you are adding the possible actions to
     * @param actions the list of the actions that you are adding to the piece
     */
	private void addPossibleMoves(Piece piece, ArrayList<ChessAction> actions) {
		for(ChessAction action : actions) {
			piece.addMove(action);
		}
	}

    /**
     * Checks to see if the piece at the location is on this team or not.
     *
     * @param location the location of the piece
     * @param board the board you are currently playing on
     * @return a boolean representing if the piece at the location is on your team or not
     */
	public boolean isTeamPiece(Location location, ChessBoard board) {
        boolean isTeamPiece = false;
        if(board.getPieceAt(location) != null) {
            isTeamPiece = board.getPieceAt(location).getColor() == this.teamColor;
        }
		return isTeamPiece;
	}

    /**
     * Checks to see if the king is in check.
     *
     * @param enemyActions the actions of the enemy team
     * @return a boolean representing if you are in check or not
     */
	public boolean isInCheck(ArrayList<ChessAction> enemyActions) {
		boolean isInCheck = false;
		for(ChessAction action : enemyActions) {
            if(action instanceof Capture && !isInCheck) {
                Capture possibleCheckCapture = (Capture) action;
                isInCheck = possibleCheckCapture.getEndLocation().equals(kingLocation);
            }

		}
		return isInCheck;
	}

    /**
     * Checks to see if the king is in checkmate.
     *
     * @param otherTeam the enemy team of this team
     * @return a boolean representing if you are in checkmate or not
     */
    public boolean isInCheckmate(Team otherTeam) {
        return isInCheck(otherTeam.getMoves()) && this.getMoves().size() == 0;
    }

    /**
     * Checks to see if the game is in a stalemate
     *
     * @param otherTeam the enemy team of this team
     * @return a boolean representing if the game is in a stalemate or not
     */
    public boolean isStalemate(Team otherTeam) {
        return !isInCheck(otherTeam.getMoves()) && this.getMoves().size() == 0;
    }

    /**
     * Sends the check message to the UI to be displayed.
     *
     */
	public String checkMessage() {
        return "The " + toString() +"'s king is in check.";
	}

    /**
     * Sends the checkmate message to the UI to be displayed.
     *
     */
	public String checkmateMessage() {
        return toString() + " has been checkmated.";
	}

    /**
     * Gets the location of the team's king.
     *
     */
	private void getKingLocation() {
		for(Piece piece : pieces) {
			if(piece instanceof King && piece.getTeam().equals(this)) {
				kingLocation = piece.getLocation();
			}
		}
	}

    /**
     * Checks to see if the action is blocked by a piece or not.
     *
     * @param action the action you are checking if it is blocked or not
     * @param board the board you are currently playing on
     * @return a boolean representing if the action if blocked or not
     */
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

    /**
     * Gets the y direction of the action.
     *
     * @param action the action you are getting the y direction for
     * @return an integer representing which y direction you are traveling, 1 for down, -1 for up, or 0 for neither
     */
    private int getYDirection(ChessAction action) {
        int yDirection = 0;
        if(action.getEndLocation().getArrayY() - action.getInitialLocation().getArrayY() != 0) {
            yDirection = action.getEndLocation().getArrayY() - action.getInitialLocation().getArrayY() > 0 ? 1 : -1;
        }
        return yDirection;
    }

    /**
     * Gets the x direction of the action.
     *
     * @param action the action you are getting the x direction for
     * @return an integer representing which x direction you are traveling, 1 for right, -1 for left, or 0 for neither
     */
    private int getXDirection(ChessAction action) {
        int xDirection = 0;
        if(action.getEndLocation().getIntX() - action.getInitialLocation().getIntX() != 0) {
            xDirection = action.getEndLocation().getIntX() - action.getInitialLocation().getIntX() > 0 ? 1 : -1;
        }
        return xDirection;
    }

    /**
     * Checks to see if the the current location that is being checked is the end location or not.
     *
     * @param xLocation the current x location you are at
     * @param yLocation the current y location you are at
     * @param action the action you are checking if it is blocked or not
     * @return a boolean representing if you have reached the end location of the action
     */
    private boolean reachedActionEndLocation(int xLocation, int yLocation, ChessAction action) {
        return xLocation == action.getEndLocation().getIntX() && yLocation == action.getEndLocation().getArrayY();
    }

    /**
     * Removes all the moves that are blocked by another piece when the piece is not a knight.
     *
     * @param board the board you are currently playing on
     * @param piece the piece that you are removing blocked actions for
     */
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

    /**
     * Gets all the possible moves for the team.
     *
     * @return a list of all the possible moves for the team
     */
	public ArrayList<ChessAction> getMoves() {
		ArrayList<ChessAction> allPossibleMoves = new ArrayList<ChessAction>();
		for(Piece piece : pieces) {
			for(ChessAction action : piece.getMoves()) {
				allPossibleMoves.add(action);
			}
		}
		return allPossibleMoves;
	}

    /**
     * Gets a list of the movable pieces for the team.
     *
     * @param board the board you are currently playing on
     * @param otherTeam the enemy team of this team
     * @return the list of movable pieces for the team
     */
    public ArrayList<Piece> getMovablePieces(ChessBoard board, Team otherTeam) {
        findAllAvailableMoves(board);
        removeIntoCheckMoves(board, otherTeam);
        ArrayList<Piece> movablePieces = new ArrayList<Piece>();
        for(Piece piece : pieces) {
            if(piece.getMoves().size() > 0) {
                movablePieces.add(piece);
            }
        }
        return movablePieces;
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
        boolean isEqual = false;
        if(obj instanceof Team) {
		    Team otherTeam = (Team) obj;
		    isEqual = this.teamColor == otherTeam.getColor();
        }
        return isEqual;
	}
	
	@Override
    public String toString() {
        String teamColor = "White";
        if(this.getColor() == Color.BLACK) {
            teamColor = "Black";
        }
        return teamColor;
    }
	
}
