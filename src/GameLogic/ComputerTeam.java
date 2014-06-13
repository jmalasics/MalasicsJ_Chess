package GameLogic;

import Board.ChessBoard;
import PieceManipulation.Capture;
import PieceManipulation.ChessAction;
import Piece.*;
import Exception.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jmalasics on 6/10/14.
 */
public class ComputerTeam extends Team {

    private static final int MAX_MOVES_AHEAD = 3;
    private ChessBoard copyBoard;

    public ComputerTeam(Color color) {
        super(color);
    }

    @Override
    public boolean performAction(ChessAction action, ChessBoard board, Team enemyTeam) throws Exception {
        ArrayList<ChessAction> bestActions = new ArrayList<ChessAction>();
        copyBoard = new ChessBoard();
        copyBoard(board);
        int currentBestValue = 0;
        for(ChessAction chessAction : getMoves()) {
            int currentValue = determineMoveValue(chessAction, copyBoard, enemyTeam, 0, MAX_MOVES_AHEAD);
            if(currentValue > currentBestValue) {
                bestActions = new ArrayList<ChessAction>();
                bestActions.add(chessAction);
                currentBestValue = currentValue;
            } else if(currentValue == currentBestValue) {
                bestActions.add(chessAction);
            }
        }
        Random rand = new Random();
        return bestActions.get(rand.nextInt(bestActions.size())).executeAction(board);
    }

    private void copyBoard(ChessBoard board) {
        for(int i = 0; i < ChessBoard.BOARD_ROWS; i++) {
            for(int j = 0; j < ChessBoard.BOARD_COLUMNS; j++) {
                Piece piece = board.getSquareAt(i, j).getPiece();
                copyBoard.getSquareAt(i, j).setPiece(piece.makePieceCopy());
                copyBoard.getSquareAt(i, j).setPiece(board.getSquareAt(i, j).getPiece()));
            }
        }
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
        return currentValue;
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
