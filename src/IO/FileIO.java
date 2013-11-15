package IO;
import java.awt.Color;
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

import Piece.*;
import PieceManipulation.*;


public class FileIO {
	
	public static void main(String [] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the path of the file you wish to use.");
		boolean running = true;
		while(running) {
			try {
				FileIO fileIO = new FileIO(scanner.nextLine());
				fileIO.run();
				running = false;
			} catch (FileNotFoundException e) {
				System.err.println("File was not found.");
			} catch (IOException ex) {
				System.err.println("IO error.");
			}
		}
	}

	private String filePath;
	private FileReader reader;
	private BufferedReader textReader;
	
	public static final int FIRST_X_COORD = 0;
	public static final int KING_X_COORD_FIRST = 0;
	public static final int PIECE_TYPE = 0;
	public static final int FIRST_Y_COORD = 1;
	public static final int KING_Y_COORD_FIRST = 1;
	public static final int PIECE_COLOR = 1;
	public static final int PLACEMENT_X_COORD = 2;
	public static final int SECOND_X_COORD = 3;
	public static final int KING_X_COORD_SECOND = 3;
	public static final int PLACEMENT_Y_COORD = 3;
	public static final int SECOND_Y_COORD = 4;
	public static final int KING_Y_COORD_SECOND = 4;
	public static final int ROOK_X_FIRST = 6;
	public static final int ROOK_Y_FIRST = 7;
	public static final int ROOK_X_SECOND = 9;
	public static final int ROOK_Y_SECOND = 10;
	
	public FileIO(String file) throws FileNotFoundException {
		filePath = file;
		reader = new FileReader(filePath);
		textReader = new BufferedReader(reader);
	}
	
	public void run() throws IOException {
		boolean running = true;
		while(running) {
			if(parseFile() == null) {
				running = false;
			}
		}
	}
	
	private ChessAction parseFile() throws IOException {
		ChessAction action = null;
		String input = textReader.readLine();
		if (input != null) {
			input.toLowerCase();
			action = parsePlace(input);
			if (action == null) {
				action = parseCapture(input);
			}
			if (action == null) {
				action = parseMultimove(input);
			}
			if (action == null) {
				action = parseMove(input);
			} 
			if (action == null) {
				System.err.println("Invalid command entered.");
			}
		}
		return action;
	}
	
	private Movement parseMove(String input) {
		Pattern movePattern = Pattern
				.compile("(([a-h][1-8])\\s([a-h][1-8]))");
		Matcher moveMatcher = movePattern.matcher(input);
		if(moveMatcher.find()) {
			return CreateMove(input);
		}
		return null;
	}
	
	private Movement CreateMove(String input) {
		int yOne = Integer.parseInt("" + input.charAt(FIRST_Y_COORD));
		int yTwo = Integer.parseInt("" + input.charAt(SECOND_Y_COORD));
		char xOne = input.charAt(FIRST_X_COORD);
		char xTwo = input.charAt(SECOND_X_COORD);
		Movement move = new Movement(new Location(xOne, yOne), new Location(xTwo, yTwo));
		printMovement(move);
		return move;
	}
	
	private void printMovement(Movement move) {
		System.out.println("The piece at " + move.getInitialLocation().toString() + " moves to " + move.getEndLocation().toString() + ".");
	}
	
	private Placement parsePlace(String input) {
		Pattern placePattern = Pattern.compile("([bknpqr])([dl])([a-h][1-8])");
		Matcher placeMatcher = placePattern.matcher(input);
		if(placeMatcher.find()) {
			return CreatePlacement(input);
		}
		return null;
	}
	
	private Placement CreatePlacement(String input) {
		int y = Integer.parseInt("" + input.charAt(PLACEMENT_Y_COORD));
		Location location = new Location(input.charAt(2), y);
		Piece piece = determinePiece(input.charAt(PIECE_TYPE), location,
			determinePieceColor(input.charAt(PIECE_COLOR)));
		Placement place = new Placement(location, piece);
		printPlacement(place);
		return place;
	}
	
	private void printPlacement(Placement placement) {
		System.out.println("A " + placement.getPiece().getColor() + " " + printPiece(placement.getPiece().toString()) + " is placed at " + placement.getLocation().toString() + ".");
	}
	
	private Multimovement parseMultimove(String input) {
		Pattern multimovePattern = Pattern
				.compile("(([a-h][1-8])\\s([a-h][1-8]))\\s(([a-h][1-8])\\s([a-h][1-8]))");
		Matcher multimoveMatcher = multimovePattern.matcher(input);
		if(multimoveMatcher.find()) {
			int kingYOne = Integer.parseInt("" + input.charAt(KING_Y_COORD_FIRST));
			int kingYTwo = Integer.parseInt("" + input.charAt(KING_Y_COORD_SECOND));
			int rookYOne = Integer.parseInt("" + input.charAt(ROOK_Y_FIRST));
			int rookYTwo = Integer.parseInt("" + input.charAt(ROOK_Y_SECOND));
			char kingXOne = input.charAt(KING_X_COORD_FIRST);
			char kingXTwo = input.charAt(KING_X_COORD_SECOND);
			char rookXOne = input.charAt(ROOK_X_FIRST);
			char rookXTwo = input.charAt(ROOK_X_SECOND);
			Multimovement multimove = new Multimovement(new Location(kingXOne, kingYOne),
				new Location(kingXTwo, kingYTwo), new Location(
				rookXOne, rookYOne), new Location(rookXTwo, rookYTwo));
			printMultimove(multimove);
			return multimove;
		}
		return null;
	}
	
	private void printMultimove(Multimovement multimovement) {
		System.out.println("The king at " + multimovement.getKingInitialLocation().toString() + " moves to " 
										+ multimovement.getKingEndLocation().toString() + " and the rook at " 
										+ multimovement.getRookInitialLocation().toString() + " moves to " 
										+ multimovement.getRookEndLocation().toString() + ".");
	}
	
	private Capture parseCapture(String input) {
		Pattern capturePattern = Pattern
				.compile("([a-h][1-8])\\s([a-h][1-8])([*])");
		Matcher captureMatcher = capturePattern.matcher(input);
		if(captureMatcher.find()) {
			int yOne = Integer.parseInt("" + input.charAt(FIRST_Y_COORD));
			int yTwo = Integer.parseInt("" + input.charAt(SECOND_Y_COORD));
			char xOne = input.charAt(FIRST_X_COORD);
			char xTwo = input.charAt(SECOND_X_COORD);
			Capture capture = new Capture(new Location(xOne, yOne), new Location(xTwo, yTwo));
			printCapture(capture);
			return capture;
		}
		return null;
	}
	
	private void printCapture(Capture capture) {
		System.out.println("The piece at " + capture.getInitialLocation() + " captures the piece at " + capture.getEndLocation() + ".");
	}
	
	private Color determinePieceColor(char color) {
		Color pieceColor = Color.BLACK;
		if(color == 'l') {
			pieceColor = Color.WHITE;
		}
		return pieceColor;
	}
	
	private Piece determinePiece(char charForPiece, Location location, Color color) {
		Piece piece = new Pawn(location, color);
		if(charForPiece == 'k') {
			piece = new King(location, color);
		} else if(charForPiece == 'q') {
			piece = new Queen(location, color);
		} else if(charForPiece == 'b') {
			piece = new Bishop(location, color);
		} else if(charForPiece == 'n') {
			piece = new Knight(location, color);
		} else if(charForPiece == 'r') {
			piece = new Rook(location, color);
		}
		return piece;
	}
	
	private String printPiece(String piece) {
		String pieceName = "Pawn";
		if(piece.equals("k")) {
			pieceName = "King";
		} else if(piece.equals("q")) {
			pieceName = "Queen";
		} else if(piece.equals("b")) {
			pieceName = "Bishop";
		} else if(piece.equals("n")) {
			pieceName = "Knight";
		} else if(piece.equals("r")) {
			pieceName = "Rook";
		}
		return pieceName;
	}
	
}