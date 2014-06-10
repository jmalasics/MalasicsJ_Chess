package Parser;

import PieceManipulation.ChessAction;

/**
 * Created by jmalasics on 5/13/14.
 */
public interface Parsable {

    /**
     * Parses the input string.
     *
     * @param input the input string
     * @return the action that is parsed out
     */
    public ChessAction parse(String input);

    /**
     * Creates the action that is parsed.
     *
     * @param input the input string
     * @return the action that is parsed out
     */
    public ChessAction createAction(String input);

    /**
     * Prints the action out to whatever medium you wish to use.
     *
     * @param action the action that is created from the string input
     */
    public void printAction(ChessAction action);

}
