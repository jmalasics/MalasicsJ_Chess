package PieceFactory;

import GameLogic.Team;
import Piece.*;
import PieceManipulation.Location;

/**
 * Created by jmalasics on 5/14/14.
 */
public class QueenFactory extends PieceFactory {

    @Override
    public Piece createPiece(char color, Location location) {
        return new Queen(new Team(getColor(color)), location);
    }

}
