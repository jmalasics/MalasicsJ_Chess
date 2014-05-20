package Parser;

import PieceManipulation.Capture;
import PieceManipulation.ChessAction;
import PieceManipulation.Location;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jmalasics on 5/13/14.
 */
public class CaptureParser implements Parsable {

    Pattern capturePattern;

    public static final int FIRST_X_COORD = 0;
    public static final int FIRST_Y_COORD = 1;
    public static final int SECOND_X_COORD = 3;
    public static final int SECOND_Y_COORD = 4;

    public CaptureParser() {
        capturePattern = Pattern
                .compile("([a-h][1-8])\\s([a-h][1-8])([*])");
    }

    @Override
    public ChessAction parse(String input) {
        Matcher captureMatcher = capturePattern.matcher(input);
        if(captureMatcher.find()) {
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
        Capture capture = new Capture(new Location(xOne, yOne), new Location(xTwo, yTwo));
        printAction(capture);
        return capture;
    }

    @Override
    public void printAction(ChessAction action) {
        Capture capture = (Capture) action;
        System.out.println("The piece at " + capture.getInitialLocation() + " captures the piece at " + capture.getEndLocation() + ".");
    }

}
