package GameLogic;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Board.ChessBoard;
import GUI.ConsoleGUI;
import IO.FileIO;
import PieceManipulation.ChessAction;
import PieceManipulation.Placement;


public class ChessGame {

	public static void main(String[] args) {
		boolean running = true;
		while(running) {
			try {
				ChessGame game = new ChessGame(new FileIO("Chess.txt"));
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
	private ConsoleGUI gui;
	private ChessBoard board;
	private Team whiteTeam;
	private Team blackTeam;
	//private boolean isWhiteTurn = true;
	
	public ChessGame(FileIO io) {
		fileIo = io;
		gui = new ConsoleGUI();
		board = new ChessBoard();
		whiteTeam = new Team(Color.WHITE);
		blackTeam = new Team(Color.BLACK);
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
						team.addPiece(((Placement) action).getPiece());
					}
				}
			} catch(IOException e) {
				System.err.println("IO error");
			}
		}
		gui.displayBoard(board);
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
					action.executeAction(board);
					gui.displayBoard(board);
				}
			} catch (IOException e) {
				System.err.println("IO error.");
			}
		}
	}
	
	public void setFileIo(String file) throws FileNotFoundException {
		fileIo = new FileIO(file);
	}
	
	private void printCheckMessage(Team team) {
		String teamColor = team.getColor() == Color.WHITE ? "White" : "Black";
		System.out.println("The " + teamColor +"'s king is in check.");
	}
	
}
