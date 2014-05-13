package IO;
import java.awt.Color;
import java.io.*;
import java.util.regex.*;

import GameLogic.Team;
import Piece.*;
import PieceManipulation.*;


public class FileIO {

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
	
	/**
	 * Reads each line from the file and returns an object containing the information for the action.
	 * 
	 * @return
	 * @throws IOException
	 */
	public ChessAction readLine() throws IOException {
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
				action = new InvalidAction();
			}
		}
		System.out.flush();
		return action;
	}
	
	/**
	 * Checks to see if the command is a move command. If the command is a move command it sends the input to be parsed. If it is not a move command it returns null.
	 * 
	 * @param input
	 * @return
	 */
	private Movement parseMove(String input) {
		Pattern movePattern = Pattern
				.compile("(([a-h][1-8])\\s([a-h][1-8]))");
		Matcher moveMatcher = movePattern.matcher(input);
		if(moveMatcher.find()) {
			return CreateMove(input);
		}
		return null;
	}
	
	/**
	 * Parses out the information needed to move a piece and packages it into an object.
	 * 
	 * @param input
	 * @return
	 */
	private Movement CreateMove(String input) {
		int yOne = Integer.parseInt("" + input.charAt(FIRST_Y_COORD));
		int yTwo = Integer.parseInt("" + input.charAt(SECOND_Y_COORD));
		char xOne = input.charAt(FIRST_X_COORD);
		char xTwo = input.charAt(SECOND_X_COORD);
		Movement move = new Movement(new Location(xOne, yOne), new Location(xTwo, yTwo));
		printMovement(move);
		return move;
	}
	
	/**
	 * Prints out a message displaying from where to where a movement occurred.
	 * 
	 * @param move
	 */
	private void printMovement(Movement move) {
		System.out.println("The piece at " + move.getInitialLocation().toString() + " moves to " + move.getEndLocation().toString() + ".");
	}
	
	/**
	 * Checks to see if the command is a placement command. If it is then it sends the input to be parsed. If not a placement command it returns null.
	 * 
	 * @param input
	 * @return
	 */
	private Placement parsePlace(String input) {
		Pattern placePattern = Pattern.compile("([bknpqr])([dl])([a-h][1-8])");
		Matcher placeMatcher = placePattern.matcher(input);
		if(placeMatcher.find()) {
			return CreatePlacement(input);
		}
		return null;
	}
	
	/**
	 * Parses out the information needed to place a piece and packages it into an object.
	 * 
	 * @param input
	 * @return
	 */
	private Placement CreatePlacement(String input) {
		int y = Integer.parseInt("" + input.charAt(PLACEMENT_Y_COORD));
		Location location = new Location(input.charAt(2), y);
		Piece piece = determinePiece(input.charAt(PIECE_TYPE),
			determinePieceColor(input.charAt(PIECE_COLOR)));
		Placement place = new Placement(location, piece);
		printPlacement(place);
		return place;
	}
	
	/**
	 * Prints out a message displaying what piece was placed and where it was placed.
	 * 
	 * @param placement
	 */
	private void printPlacement(Placement placement) {
		System.out.println("A " + printPieceColor(placement.getPiece()) + " " + printPiece(placement.getPiece().toString()) + " is placed at " + placement.getLocation().toString() + ".");
	}
	
	private String printPieceColor(Piece piece) {
		return piece.getColor().equals(Color.WHITE) ? "White" : "Black";
	}
	
	/**
	 * Checks to see if the command is a multimove command. If it is it sends the command to be parsed. If it is not it returns null.
	 * 
	 * @param input
	 * @return
	 */
	private Multimovement parseMultimove(String input) {
		Pattern multimovePattern = Pattern
				.compile("(([a-h][1-8])\\s([a-h][1-8]))\\s(([a-h][1-8])\\s([a-h][1-8]))");
		Matcher multimoveMatcher = multimovePattern.matcher(input);
		if(multimoveMatcher.find()) {
			return createMultimove(input);
		}
		return null;
	}
	
	/**
	 * Parses out the information needed to move multiple pieces in a single move and packages it in an object.
	 * 
	 * @param input
	 * @return
	 */
	private Multimovement createMultimove(String input) {
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
	
	/**
	 * Prints out a message displaying from where to where the two pieces moved.
	 * 
	 * @param multimovement
	 */
	private void printMultimove(Multimovement multimovement) {
		System.out.println("The king at " + multimovement.getKingInitialLocation().toString() + " moves to " 
										+ multimovement.getKingEndLocation().toString() + " and the rook at " 
										+ multimovement.getRookInitialLocation().toString() + " moves to " 
										+ multimovement.getRookEndLocation().toString() + ".");
	}
	
	/**
	 * Checks to see if the command is a command for a capture. If it is a capture command it sends it off to be parsed. If it is not then it returns null.
	 * 
	 * @param input
	 * @return
	 */
	private Capture parseCapture(String input) {
		Pattern capturePattern = Pattern
				.compile("([a-h][1-8])\\s([a-h][1-8])([*])");
		Matcher captureMatcher = capturePattern.matcher(input);
		if(captureMatcher.find()) {
			return createCapture(input);
		}
		return null;
	}
	
	/**
	 * Parses out the information needed to perform a capture and packages it into an object.
	 * 
	 * @param input
	 * @return
	 */
	private Capture createCapture(String input) {
		int yOne = Integer.parseInt("" + input.charAt(FIRST_Y_COORD));
		int yTwo = Integer.parseInt("" + input.charAt(SECOND_Y_COORD));
		char xOne = input.charAt(FIRST_X_COORD);
		char xTwo = input.charAt(SECOND_X_COORD);
		Capture capture = new Capture(new Location(xOne, yOne), new Location(xTwo, yTwo));
		printCapture(capture);
		return capture;
	}
	
	/**
	 * Prints out a message displaying where the capturing piece originated and where the piece captured another piece.
	 * 
	 * @param capture
	 */
	private void printCapture(Capture capture) {
		System.out.println("The piece at " + capture.getInitialLocation() + " captures the piece at " + capture.getEndLocation() + ".");
	}
	
	/**
	 * Determines what color the piece will be from the character in the place command.
	 * 
	 * @param color
	 * @return
	 */
	private Color determinePieceColor(char color) {
		return color == 'l' ? Color.WHITE : Color.BLACK;
	}
	
	/**
	 * Determines what piece is from the character, location, and color from the place command.
	 * 
	 * @param charForPiece
	 * @param location
	 * @param color
	 * @return
	 */
	private Piece determinePiece(char charForPiece, Color color) {
		Piece piece = new Pawn(new Team(color));
		if(charForPiece == 'k') {
			piece = new King(new Team(color));
		} else if(charForPiece == 'q') {
			piece = new Queen(new Team(color));
		} else if(charForPiece == 'b') {
			piece = new Bishop(new Team(color));
		} else if(charForPiece == 'n') {
			piece = new Knight(new Team(color));
		} else if(charForPiece == 'r') {
			piece = new Rook(new Team(color));
		}
		return piece;
	}
	
	/**
	 * Prints out the name of the piece for the message displaying the placement command.
	 * 
	 * @param piece
	 * @return
	 */
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