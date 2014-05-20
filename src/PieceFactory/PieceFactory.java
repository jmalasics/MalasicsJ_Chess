package PieceFactory;

import java.awt.*;
import Piece.*;

/**
 * Created by jmalasics on 5/14/14.
 */
public abstract class PieceFactory {

    public abstract Piece createPiece(char color);

    public Color getColor(char color) {
        return color == 'l' ? Color.WHITE : Color.BLACK;
    }

}
