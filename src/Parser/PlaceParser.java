package Parser;

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

    public PlaceParser(UI ui) {
        this.ui = ui;
        placePattern = Pattern.compile("([bknpqr])([dl])([a-h][1-8])");
        createPieceFactories();
    }

    private void createPieceFactories() {
        pieceFactories = new HashMap<Character, PieceFactory>();
        pieceFactories.put('p', new PawnFactory());
        pieceFactories.put('r', new RookFactory());
        pieceFactories.put('n', new KnightFactory());
        pieceFactories.put('b', new BishopFactory());
        pieceFactories.put('q', new QueenFactory());
        pieceFactories.put('k', new KingFactory());
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
        ui.displayLogMessage("A " + printPieceColor(placement.getPiece()) + " " + placement.getPiece().getPieceName() + " is placed at " + placement.getLocation().toString() + ".");
    }

    private String printPieceColor(Piece piece) {
        return piece.getColor().equals(Color.WHITE) ? "White" : "Black";
    }

    /**
     * Determines what piece is from the character, location, and color from the place command.
     *
     * @param charForPiece
     * @param color
     * @return
     */
    private Piece determinePiece(char charForPiece, char color, Location location) {
        return pieceFactories.get(charForPiece).createPiece(color, location);
    }

}
