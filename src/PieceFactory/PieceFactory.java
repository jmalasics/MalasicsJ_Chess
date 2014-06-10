package PieceFactory;

import java.awt.*;

import GameLogic.Team;
import Piece.*;
import PieceManipulation.Location;

/**
 * Created by jmalasics on 5/14/14.
 */
public abstract class PieceFactory {

    protected Team whiteTeam;
    protected Team blackTeam;

    public PieceFactory(Team whiteTeam, Team blackTeam) {
        this.whiteTeam = whiteTeam;
        this.blackTeam = blackTeam;
    }

    public abstract Piece createPiece(char color, Location location);

    protected Team getTeam(char color) {
        return color == 'l' ? whiteTeam : blackTeam;
    }

}
