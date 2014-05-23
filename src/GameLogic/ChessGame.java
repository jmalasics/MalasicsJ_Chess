package GameLogic;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Board.ChessBoard;
import UI.Console;
import IO.FileIO;
import PieceManipulation.ChessAction;
import PieceManipulation.InvalidAction;
import PieceManipulation.Placement;
import UI.UI;


public class ChessGame {

	public static void main(String[] args) {
		boolean running = true;
		while(running) {
			try {
				ChessGame game = new ChessGame("Chess.txt");
				game.setUp();
				Scanner scanner = new Scanner(System.in);
				System.out.println("Please enter the path of the file you wish to use.");
				game.setFileIo(scanner.nextLine());
				game.run();
				running = false;
			} catch (FileNotFoundException e) {
				System.err.println("File was not found.");
			}
		}
	}
	
	private FileIO fileIo;
	private UI ui;
	private ChessBoard board;
    private Team currentTeam;
    private Team otherTeam;
	
	public ChessGame(String path) throws FileNotFoundException {
        ui = new Console();
		fileIo = new FileIO(path, ui);
		board = new ChessBoard();
		currentTeam = new Team(Color.WHITE);
		otherTeam = new Team(Color.BLACK);
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
                ui.displayBoard(board);
            }
		}
		ui.displayBoard(board);
	}
	
	/**
	 * Runs the program and passes the actions from the IO to the board to be performed then displays the new state 
	 * of the board.
	 * 
	 */
	public void run() {
		boolean running = true;
		while(running) {
			try{
                displayTurnMessage();
				ChessAction action = fileIo.readLine();
				if(action == null) {
					running = false;
				} else {
                    if(!(action instanceof InvalidAction)) {
                        if(currentTeam.performAction(action, board, otherTeam)) {
                            if(otherTeam.isInCheck(currentTeam.getMoves())) {
                                ui.displayCheckOrCheckmateMessage(otherTeam.checkMessage());
                            }
                            changeTurn();
                            ui.displayBoard(board);
                        }
                    }
                }
			} catch(IOException e) {
                ui.displayErrorMessage(new IOException("IO error"));
            } catch (Exception exception) {
                ui.displayErrorMessage(exception);
                ui.displayBoard(board);
            }
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
    }

    /**
     * Takes in a FileIO object and stores it for later use.
     *
     * @param file the string path to the file you wish to read
     * @throws FileNotFoundException
     */
	public void setFileIo(String file) throws FileNotFoundException {
		fileIo = new FileIO(file, ui);
	}

    /**
     * Sends a message to the UI displaying who's turn it is.
     *
     */
    private void displayTurnMessage() {
        ui.displayMessage(currentTeam.toString() + "'s turn:");
    }
	
}
