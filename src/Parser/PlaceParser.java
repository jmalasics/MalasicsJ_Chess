package Parser;

import GameLogic.Team;
import Piece.*;
import PieceFactory.*;
import PieceManipulation.ChessAction;
import PieceManipulation.Location;
import PieceManipulation.Placement;

import java.awt.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import UI.*;

/**
 * Created by jmalasics on 5/13/14.
 */
public class PlaceParser implements Parsable {

    private Pattern placePattern;
    private HashMap<Character, PieceFactory> pieceFactories;
    private UI ui;

    public static final int PIECE_TYPE = 0;
    public static final int PIECE_COLOR = 1;
    public static final int PLACEMENT_X_COORD = 2;
    public static final int PLACEMENT_Y_COORD = 3;

    public PlaceParser(UI ui, Team whiteTeam, Team blackTeam) {
        this.ui = ui;
        placePattern = Pattern.compile("([bknpqr])([dl])([a-h][1-8])");
        createPieceFactories(whiteTeam, blackTeam);
    }

    /**
     * Creates the hashmap that contains the character for the piece as the key and the factory to make the piece as the value.
     *
     */
    private void createPieceFactories(Team whiteTeam, Team blackTeam) {
        pieceFactories = new HashMap<Character, PieceFactory>();
        pieceFactories.put('p', new PawnFactory(whiteTeam, blackTeam));
        pieceFactories.put('r', new RookFactory(whiteTeam, blackTeam));
        pieceFactories.put('n', new KnightFactory(whiteTeam, blackTeam));
        pieceFactories.put('b', new BishopFactory(whiteTeam, blackTeam));
        pieceFactories.put('q', new QueenFactory(whiteTeam, blackTeam));
        pieceFactories.put('k', new KingFactory(whiteTeam, blackTeam));
    }

    @Override
    public ChessAction parse(String input) {
        Matcher placeMatcher = placePattern.matcher(input);
        if(placeMatcher.find()) {
            return createAction(input);
        }
        return null;
    }

    @Override
    public ChessAction createAction(String input) {
        int y = Integer.parseInt("" + input.charAt(PLACEMENT_Y_COORD));
        Location location = new Location(input.charAt(PLACEMENT_X_COORD), y);
        Piece piece = determinePiece(input.charAt(PIECE_TYPE),
                input.charAt(PIECE_COLOR), location);
        Placement place = new Placement(location, piece);
        printAction(place);
        return place;
    }

    @Override
    public void printAction(ChessAction action) {
        Placement placement = (Placement) action;
        ui.displayLogMessage("A " + placement.getPiece().getTeam().toString() + " " + placement.getPiece().getPieceName() + " is placed at " + placement.getLocation().toString() + ".");
    }

    /**
     * Gets the string for the color of the piece.
     *
     * @param piece the piece you wish to get the color string for
     * @return the string of what color the piece is
     */
    private String getPieceColorString(Piece piece) {
        return piece.getColor().equals(Color.WHITE) ? "White" : "Black";
    }

    /**
     * Determines what piece is from the character, location, and color from the place command.
     *
     * @param charForPiece the character of the piece you wish to create
     * @param color the character of the color for the piece you wish to create
     * @param location the location of the piece you wish to create
     * @return the piece that is created
     */
    private Piece determinePiece(char charForPiece, char color, Location location) {
        return pieceFactories.get(charForPiece).createPiece(color, location);
    }

}
