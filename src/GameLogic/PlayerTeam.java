package GameLogic;

import java.awt.Color;
import java.util.ArrayList;

import Piece.*;
import PieceManipulation.*;
import Board.*;
import Exception.*;

public class PlayerTeam extends Team {

	public PlayerTeam(Color color) {
		super(color);
	}

    @Override
	public boolean performAction(ChessAction action, ChessBoard board, Team enemyTeam) throws Exception {
		boolean actionCompleted = false;
        if(action != null) {
            if (action.executeAction(board)) {
                getKingLocation();
                board.getPieceAt(action.getEndLocation()).setLocation(action.getEndLocation());
                if (action instanceof Capture) {
                    enemyTeam.addCapturedPiece(enemyTeam.getPieceAt(action.getEndLocation()));
                }
                actionCompleted = true;
                this.findAllAvailableMoves(board);
                this.removeIntoCheckMoves(board, enemyTeam);
                enemyTeam.findAllAvailableMoves(board);
                enemyTeam.removeIntoCheckMoves(board, this);
            }
        }
        Pawn pawn = board.pawnPromotionCheck(action);
        if(pawn != null) {
            pawnPromotion(pawn, board);
        }
		return actionCompleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((teamColor == null) ? 0 : teamColor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
        boolean isEqual = false;
        if(obj instanceof PlayerTeam) {
		    PlayerTeam otherTeam = (PlayerTeam) obj;
		    isEqual = this.teamColor == otherTeam.getColor();
        }
        return isEqual;
	}
	
}
