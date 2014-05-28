package PieceFactory;

import GameLogic.Team;
import Piece.*;
import PieceManipulation.Location;

/**
 * Created by jmalasics on 5/14/14.
 */
public class RookFactory extends PieceFactory {

    @Override
    public Piece createPiece(char color, Location location) {
        return new Rook(new Team(getColor(color)), location);
    }

}
