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
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		while(running) {
			try {
				System.out.println("Please enter the path of the file you wish to use.");
				FileIO fileIO = new FileIO(scanner.nextLine());
				ChessGame game = new ChessGame(fileIO);
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
	
	public ChessGame(FileIO io) {
		fileIo = io;
		gui = new ConsoleGUI();
		board = new ChessBoard();
	}
	
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
					board.performAction(action);
					gui.displayBoard(board);
				}
			} catch (IOException e) {
				System.err.println("IO error.");
			}
		}
	}
	
}
