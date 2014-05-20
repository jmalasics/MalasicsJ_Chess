package Parser;

import PieceManipulation.ChessAction;
import PieceManipulation.Location;
import PieceManipulation.Multimovement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jmalasics on 5/13/14.
 */
public class MultimoveParser implements Parsable {

    private Pattern multimovePattern;

    public static final int KING_X_COORD_FIRST = 0;
    public static final int KING_Y_COORD_FIRST = 1;
    public static final int KING_X_COORD_SECOND = 3;
    public static final int KING_Y_COORD_SECOND = 4;
    public static final int ROOK_X_FIRST = 6;
    public static final int ROOK_Y_FIRST = 7;
    public static final int ROOK_X_SECOND = 9;
    public static final int ROOK_Y_SECOND = 10;

    public MultimoveParser() {
        multimovePattern = Pattern
                .compile("(([a-h][1-8])\\s([a-h][1-8]))\\s(([a-h][1-8])\\s([a-h][1-8]))");
    }

    @Override
    public ChessAction parse(String input) {
        Matcher multimoveMatcher = multimovePattern.matcher(input);
        if(multimoveMatcher.find()) {
            return createAction(input);
        }
        return null;
    }

    @Override
    public ChessAction createAction(String input) {
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
        printAction(multimove);
        return multimove;
    }

    @Override
    public void printAction(ChessAction action) {
        Multimovement multimovement = (Multimovement) action;
        System.out.println("The king at " + multimovement.getKingInitialLocation().toString() + " moves to "
                + multimovement.getKingEndLocation().toString() + " and the rook at "
                + multimovement.getRookInitialLocation().toString() + " moves to "
                + multimovement.getRookEndLocation().toString() + ".");
    }

}
