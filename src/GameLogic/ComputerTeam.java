package GameLogic;

import Board.ChessBoard;
import PieceManipulation.Capture;
import PieceManipulation.ChessAction;
import PieceManipulation.Location;
import Exception.*;

import java.awt.*;
import java.util.Random;

/**
 * Created by jmalasics on 6/10/14.
 */
public class ComputerTeam extends Team {

    private static final int MAX_MOVES_AHEAD = 4;
    private ChessBoard copyBoard;

    public ComputerTeam(Color color) {
        super(color);
    }

    @Override
    public boolean performAction(ChessAction action, ChessBoard board, Team enemyTeam) {
        return false;
    }

    private int determineMoveValue(ChessAction action, ChessBoard board, Team enemyTeam, int currentValue, int moveNumber) {
        if(moveNumber == 0) {
            return currentValue;
        }
        if(action instanceof Capture) {
            try {
                currentValue += determinePieceValue(action.getEndLocation(), board);
                board.capture((Capture) action);
                currentValue -= determineMoveValue(enemyTeam.getBestMove(enemyTeam, board), board, this, currentValue, moveNumber - 1);
            } catch(CaptureException ce) {
                System.out.println("Something went seriously wrong.");
            }

        }
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result
                + ((teamColor == null) ? 0 : teamColor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if(obj instanceof ComputerTeam) {
            ComputerTeam otherTeam = (ComputerTeam) obj;
            isEqual = this.teamColor == otherTeam.getColor();
        }
        return isEqual;
    }

}
