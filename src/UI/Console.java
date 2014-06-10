package UI;

import Board.ChessBoard;
import GameLogic.Team;
import Piece.Piece;
import PieceManipulation.ChessAction;

import java.util.ArrayList;
import java.util.Scanner;

public class Console implements UI {

    private ChessBoard board;

    public Console(ChessBoard board) {
        this.board = board;
    }

    @Override
    public ChessAction getAction(Team currentTeam, Team otherTeam) {
        return promptForAction(promptForPiece(currentTeam, otherTeam), currentTeam, otherTeam);
    }

    @Override
	public void displayBoard() {
		System.out.println("K : Black");
		System.out.println("k : White");
		System.out.println("- : Empty");
		for(int i = 0; i < ChessBoard.BOARD_ROWS; i++) {
			for(int j = 0; j < ChessBoard.BOARD_COLUMNS; j++) {
				if(j < ChessBoard.BOARD_COLUMNS - 1) {
					System.out.print(board.getSquareAt(i, j).toString());
				} else {
					System.out.println(board.getSquareAt(i, j).toString());
				}
			}
		}
	}

    @Override
    public void displayErrorMessage(Exception exception) {
        System.out.println("*** " + exception.getMessage() + " ***");
        exception.printStackTrace();
    }

    @Override
    public void displayMessage(String string) {
        System.out.println(string);
    }

    @Override
    public void displayLogMessage(String string) {
        System.out.println(string);
    }

    @Override
    public void displayCheckOrCheckmateMessage(String string) {
        System.out.println("!!! " + string + " !!!");
    }

    public String promptUser(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Prompts the user for the piece that they wish to move.
     *
     * @return the piece that the user wishes to move
     */
    private Piece promptForPiece(Team currentTeam, Team otherTeam) {
        Piece piece = null;
        ArrayList<Piece> movablePieces = currentTeam.getMovablePieces(board, otherTeam);
        displayMessage("Which piece do you wish to move?");
        displayMessage(movablePiecesString(movablePieces));
        String input = promptUser("Enter piece number: ");
        try {
            int pieceNumber = Integer.parseInt(input);
            piece = movablePieces.get(pieceNumber);
        } catch(NumberFormatException nfe) {
            displayMessage("Invalid input.");
            piece = promptForPiece(currentTeam, otherTeam);
        } catch(IndexOutOfBoundsException ioobe) {
            displayMessage("Not a valid number.");
            piece = promptForPiece(currentTeam, otherTeam);
        }
        return piece;
    }

    /**
     * Takes in a list of movable pieces for a team and generates a string to be used when prompting a user for a piece they want to move.
     *
     * @param movablePieces the list of movable pieces for a team
     * @return a string for the user to see when prompted for movable pieces.
     */
    private String movablePiecesString(ArrayList<Piece> movablePieces) {
        StringBuilder sBuilder = new StringBuilder();
        int pieceIndex = 0;
        for(Piece piece : movablePieces) {
            sBuilder.append(" " + pieceIndex + ": " + piece.getPieceName() + " at " + piece.getLocation() + "\n");
            pieceIndex++;
        }
        return sBuilder.toString();
    }

    /**
     * Prompts user for the action they wish to perform on the piece they have selected.
     *
     * @param piece the selected piece to perform an action on
     * @return the action the user wishes to perform on the selected piece
     */
    private ChessAction promptForAction(Piece piece, Team currentTeam, Team otherTeam) {
        ChessAction action = null;
        ArrayList<ChessAction> availableActions = piece.getMoves();
        displayMessage("Where to you wish to move?");
        displayMessage(pieceActionString(piece.getMoves()));
        String input = promptUser("Enter action number: ");
        try {
            int actionNumber = Integer.parseInt(input);
            if(actionNumber < availableActions.size()) {
                action = availableActions.get(actionNumber);
            } else if(actionNumber == availableActions.size()) {
                action = promptForAction(promptForPiece(currentTeam, otherTeam), currentTeam, otherTeam);
            }
        } catch(NumberFormatException nfe) {
            displayMessage("Invalid Input");
            action = promptForAction(piece, currentTeam, otherTeam);
        } catch(IndexOutOfBoundsException ioobe) {
            displayMessage("Not a valid number.");
            action = promptForAction(piece, currentTeam, otherTeam);
        }
        return action;
    }

    /**
     * Takes in a list of available actions for a piece and returns a string used for prompting the user.
     *
     * @param actions the available actions for a piece
     * @return the string representing the available actions for a piece
     */
    private String pieceActionString(ArrayList<ChessAction> actions) {
        StringBuilder sBuilder = new StringBuilder();
        int actionIndex = 0;
        for(ChessAction action : actions) {
            sBuilder.append(" " + actionIndex + ": " + action.getEndLocation().toString() + "\n");
            actionIndex++;
        }
        sBuilder.append(" " + actionIndex + ": Unselect Piece");
        return sBuilder.toString();
    }
	
}

