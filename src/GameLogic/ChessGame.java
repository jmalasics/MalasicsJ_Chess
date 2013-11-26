package GameLogic;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Board.ChessBoard;
import GUI.ConsoleGUI;
import IO.FileIO;
import PieceManipulation.ChessAction;
import PieceManipulation.InvalidAction;


public class ChessGame {

	public static void main(String[] args) {
		boolean running = true;
		while(running) {
			try {
				FileIO fileIO = new FileIO("Chess.txt");
				ChessGame game = new ChessGame(fileIO);
				game.run();
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
	private ConsoleGUI gui;
	private ChessBoard board;
	private boolean isWhiteTurn = true;
	
	public ChessGame(FileIO io) {
		fileIo = io;
		gui = new ConsoleGUI();
		board = new ChessBoard();
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
				} else if(action instanceof InvalidAction) {
					System.err.println("Invalid command entered.");
				} else {
					if(action.executeAction(board)) {
						gui.displayBoard(board);	
					}
				}
			} catch (IOException e) {
				System.err.println("IO error.");
			}
		}
	}
	
	public void setFileIo(String file) throws FileNotFoundException {
		fileIo = new FileIO(file);
	}
	
}
