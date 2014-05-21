package UI;

import Board.ChessBoard;
import PieceManipulation.*;

/**
 * Created by jmalasics on 5/14/14.
 */
public interface UI {

    public void displayBoard(ChessBoard board);

    public void displayExceptionMessage(Exception exception);

    public void displayMessage(String string);

    public void displayLogMessage(String string);

}
