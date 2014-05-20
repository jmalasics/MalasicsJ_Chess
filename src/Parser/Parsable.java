package Parser;

import PieceManipulation.ChessAction;

/**
 * Created by jmalasics on 5/13/14.
 */
public interface Parsable {

    public ChessAction parse(String input);

    public ChessAction createAction(String input);

    public void printAction(ChessAction action);

}
