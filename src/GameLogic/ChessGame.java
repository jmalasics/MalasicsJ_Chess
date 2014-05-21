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
	private Team whiteTeam;
	private Team blackTeam;
	private boolean isWhiteTurn = true;
	
	public ChessGame(String path) throws FileNotFoundException {
        ui = new Console();
		fileIo = new FileIO(path, ui);
		board = new ChessBoard();
		whiteTeam = new Team(Color.WHITE);
        whiteTeam.setUI(ui);
		blackTeam = new Team(Color.BLACK);
        blackTeam.setUI(ui);
	}
	
	public void setUp() {
		boolean running = true;
		while(running) {
			try{
				ChessAction action = fileIo.readLine();
				if(action == null) {
					running = false;
				} else {
					if(action.executeAction(board)) {
						Team team = ((Placement) action).getPiece().getTeam().equals(whiteTeam) ? whiteTeam : blackTeam;
						team.addPiece(((Placement) action).getPiece(), board);
					}
					System.out.flush();
					System.err.flush();
				}
			} catch(IOException e) {
                ui.displayExceptionMessage(new IOException("IO error"));
			} catch(Exception exception) {
                ui.displayExceptionMessage(exception);
            }
		}
		ui.displayBoard(board);
	}
	
	/**
	 * Runs the program and passes the actions from the IO to the board to be performed then displays the new state 
	 * of the board.
	 * @throws FileNotFoundException 
	 * 
	 */
	public void run() {
		boolean running = true;
		while(running) {
			try{
				ChessAction action = fileIo.readLine();
				if(action == null) {
					running = false;
				} else {
                    if(!(action instanceof InvalidAction)) {
                        action.executeAction(board);
                        ui.displayBoard(board);
                    }

                    /**
                    if(!(action instanceof InvalidAction)) {
					    Team currentTeam = isWhiteTurn ? whiteTeam : blackTeam;
					    Team otherTeam = isWhiteTurn ? blackTeam : whiteTeam;
					    if(currentTeam.performAction(action, board, otherTeam)) {
						    if(otherTeam.isInCheck(board, currentTeam.getMoves())) {
							    otherTeam.displayCheckMessage();
						    }
						    isWhiteTurn = !isWhiteTurn;
						    ui.displayBoard(board);
					    }
				    }
                    */
                }
			} catch(IOException e) {
                ui.displayExceptionMessage(new IOException("IO error"));
            } catch (Exception exception) {
                ui.displayExceptionMessage(exception);
                ui.displayBoard(board);
            }
		}
	}
	
	public void setFileIo(String file) throws FileNotFoundException {
		fileIo = new FileIO(file, ui);
	}
	
}
