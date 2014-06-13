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
        int currentBestValue = Integer.MIN_VALUE;
        for(ChessAction chessAction : getMoves()) {
            copyBoard = new ChessBoard();
            copyBoard(board);
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
        ChessAction performedAction = bestActions.get(rand.nextInt(bestActions.size()));
        if(performedAction != null) {
            Pawn pawn = board.pawnPromotionCheck(performedAction);
            if(pawn != null) {
                pawnPromotion(pawn, board);
            }
        }
        return performedAction.executeAction(board);
    }

    private void copyBoard(ChessBoard board) {
        for(int i = 0; i < ChessBoard.BOARD_ROWS; i++) {
            for(int j = 0; j < ChessBoard.BOARD_COLUMNS; j++) {
                Piece piece = board.getSquareAt(i, j).getPiece();
                if(piece != null) {
                    copyBoard.getSquareAt(i, j).setPiece(piece.makePieceCopy());
                }
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
