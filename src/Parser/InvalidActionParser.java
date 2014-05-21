package Parser;

import PieceManipulation.ChessAction;
import PieceManipulation.InvalidAction;
import UI.*;

/**
 * Created by jmalasics on 5/13/14.
 */
public class InvalidActionParser implements Parsable {

    private String input;
    private UI ui;

    public InvalidActionParser(UI ui) {
        this.ui = ui;
    }

    @Override
    public ChessAction parse(String input) {
        this.input = input;
        return createAction(input);
    }

    @Override
    public ChessAction createAction(String input) {
        return new InvalidAction();
    }

    @Override
    public void printAction(ChessAction action) {
        ui.displayLogMessage("Invalid action input: " + input);
    }

}
