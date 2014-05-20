package PieceFactory;

import GameLogic.Team;
import Piece.*;

/**
 * Created by jmalasics on 5/14/14.
 */
public class PawnFactory extends PieceFactory {

    @Override
    public Piece createPiece(char color) {
        return new Pawn(new Team(getColor(color)));
    }

}
