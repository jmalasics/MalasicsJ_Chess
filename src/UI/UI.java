package UI;

import Board.ChessBoard;
import GameLogic.Team;
import PieceManipulation.ChessAction;

/**
 * Created by jmalasics on 5/14/14.
 */
public interface UI {

    /**
     * Displays the board using whatever the medium the UI uses.
     *
     */
    public void displayBoard();

    /**
     * Displays an error message.
     *
     * @param exception the exception that is thrown
     */
    public void displayErrorMessage(Exception exception);

    /**
     * Displays a message for the user to see.
     *
     * @param string the message you wish to display
     */
    public void displayMessage(String string);

    /**
     * Writes a message to the log.
     *
     * @param string the message you wish to write in the log
     */
    public void displayLogMessage(String string);

    /**
     * Displays a message showing that a team is currently in check or checkmate.
     *
     * @param string the check or checkmate message
     */
    public void displayCheckOrCheckmateMessage(String string);

    public ChessAction getAction(Team currentTeam, Team otherTeam);

}
