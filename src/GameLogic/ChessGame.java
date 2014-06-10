package GameLogic;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Board.ChessBoard;
import Piece.Piece;
import PieceManipulation.Location;
import IO.FileIO;
import PieceManipulation.ChessAction;
import PieceManipulation.Placement;
import UI.*;
import Piece.*;


public class ChessGame implements IUserInterfaceObserver, ITeamObservable {

	public static void main(String[] args) {
		//boolean running = true;
		//while(running) {
			try {
				ChessGame game = new ChessGame("Chess.txt");
				game.setUp();
				//game.run();
				//running = false;
			} catch (FileNotFoundException e) {
				System.err.println("File was not found.");
			}
		//}
	}
	
	private FileIO fileIo;
	private UI ui;
	private ChessBoard board;
    private Team currentTeam;
    private Team otherTeam;
    private ArrayList<ITeamObserver> observers;
	
	public ChessGame(String path) throws FileNotFoundException {
		board = new ChessBoard();
        observers = new ArrayList<ITeamObserver>();
		currentTeam = new Team(Color.WHITE);
		otherTeam = new Team(Color.BLACK);
        ui = new GUI(board, currentTeam, otherTeam, this);
        registerObserver((GUI) ui);
        fileIo = new FileIO(path, ui, currentTeam, otherTeam);
	}

    /**
     * Sets up the board before the program prompts the user for the file for the moves.
     *
     */
	public void setUp() {
		boolean running = true;
		while(running) {
			try{
				ChessAction action = fileIo.readLine();
				if(action == null) {
					running = false;
				} else {
					if(action.executeAction(board)) {
						Team team = ((Placement) action).getPiece().getTeam().equals(currentTeam) ? currentTeam : otherTeam;
						team.addPiece(((Placement) action).getPiece(), board);
					}
				}
			} catch(IOException e) {
                ui.displayErrorMessage(new IOException("IO error"));
			} catch(Exception exception) {
                ui.displayErrorMessage(exception);
                ui.displayBoard();
            }
		}
		ui.displayBoard();
	}

	/**
	 * Runs the program and passes the actions from the IO to the board to be performed then displays the new state 
	 * of the board.
	 * 
	 */
	public void run(ChessAction action) {
        try {
            if(action != null) {
                if(currentTeam.performAction(action, board, otherTeam)) {
                    if(otherTeam.isInCheck(currentTeam.getMoves())) {
                        if(otherTeam.isInCheckmate(currentTeam)) {
                            ui.displayBoard();
                            ui.displayCheckOrCheckmateMessage(otherTeam.checkmateMessage());
                            System.exit(0);
                        } else {
                            ui.displayCheckOrCheckmateMessage(otherTeam.checkMessage());
                        }
                    } else if(otherTeam.isStalemate(currentTeam)) {
                        ui.displayBoard();
                        ui.displayMessage(otherTeam.toString() + " is unable to move. Stalemate!");
                        System.exit(0);
                    }
                    Pawn pawn = board.pawnPromotionCheck(action);
                    if(pawn != null) {
                        pawnPromotion(pawn);
                    }
                    ui.displayBoard();
                    changeTurn();
                }
            }
        } catch(Exception e) {
            ui.displayErrorMessage(e);
            ui.displayBoard();
        }
	}

    /**
     * Toggles the boolean used to represent who's turn it is.
     *
     */
    public void changeTurn() {
        Team tempTeam = currentTeam;
        currentTeam = otherTeam;
        otherTeam = tempTeam;
        notifyObservers();
    }

    /**
     * Takes in a FileIO object and stores it for later use.
     *
     * @param file the string path to the file you wish to read
     * @throws FileNotFoundException
     */
	public void setFileIo(String file) throws FileNotFoundException {
		fileIo = new FileIO(file, ui, currentTeam, otherTeam);
	}

    /**
     * Sends a message to the UI displaying who's turn it is.
     *
     */
    private void displayTurnMessage() {
        ui.displayMessage(currentTeam.toString() + "'s turn:");
    }

    /**
     * Promotes the pawn to a queen when it reaches the opposite side of the board.
     *
     * @param pawn the pawn that is being promoted
     */
    private void pawnPromotion(Pawn pawn) {
        board.promotePawn(pawn, new Queen(pawn.getTeam(), pawn.getLocation()));
        ui.displayMessage("Pawn has been promoted.");
    }

    @Override
    public void update(ChessAction action) {
        run(action);
    }

    @Override
    public void registerObserver(ITeamObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ITeamObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(ITeamObserver observer : observers) {
            observer.update(currentTeam, otherTeam);
        }
    }

}
