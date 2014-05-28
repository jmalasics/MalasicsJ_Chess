package PieceFactory;

import GameLogic.Team;
import Piece.*;
import PieceManipulation.Location;

/**
 * Created by jmalasics on 5/14/14.
 */
public class PawnFactory extends PieceFactory {

    @Override
    public Piece createPiece(char color, Location location) {
        return new Pawn(new Team(getColor(color)), location);
    }

}
