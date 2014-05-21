package Parser;

import PieceManipulation.ChessAction;
import PieceManipulation.Location;
import PieceManipulation.Movement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import UI.*;

/**
 * Created by jmalasics on 5/13/14.
 */
public class MoveParser implements Parsable {

    private Pattern movePattern;
    private UI ui;

    public static final int FIRST_X_COORD = 0;
    public static final int FIRST_Y_COORD = 1;
    public static final int SECOND_X_COORD = 3;
    public static final int SECOND_Y_COORD = 4;

    public MoveParser(UI ui) {
        this.ui = ui;
        movePattern = Pattern
                .compile("(([a-h][1-8])\\s([a-h][1-8]))");
    }

    @Override
    public ChessAction parse(String input) {
        Matcher moveMatcher = movePattern.matcher(input);
        if(moveMatcher.find()) {
            return createAction(input);
        }
        return null;
    }

    @Override
    public ChessAction createAction(String input) {
        int yOne = Integer.parseInt("" + input.charAt(FIRST_Y_COORD));
        int yTwo = Integer.parseInt("" + input.charAt(SECOND_Y_COORD));
        char xOne = input.charAt(FIRST_X_COORD);
        char xTwo = input.charAt(SECOND_X_COORD);
        Movement move = new Movement(new Location(xOne, yOne), new Location(xTwo, yTwo));
        printAction(move);
        return move;
    }

    @Override
    public void printAction(ChessAction action) {
        Movement move = (Movement) action;
        ui.displayLogMessage("The piece at " + move.getInitialLocation().toString() + " moves to " + move.getEndLocation().toString() + ".");
    }

}
