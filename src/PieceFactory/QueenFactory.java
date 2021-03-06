package PieceFactory;

import GameLogic.PlayerTeam;
import GameLogic.Team;
import Piece.*;
import PieceManipulation.Location;

/**
 * Created by jmalasics on 5/14/14.
 */
public class QueenFactory extends PieceFactory {

    public QueenFactory(Team whiteTeam, Team blackTeam) {
        super(whiteTeam, blackTeam);
    }

    @Override
    public Piece createPiece(char color, Location location) {
        return new Queen(getTeam(color), location);
    }

}
