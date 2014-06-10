package UI;

import PieceManipulation.ChessAction;

/**
 * Created by jmalasics on 6/6/14.
 */
public interface IUserInterfaceObserver {

    public void update(ChessAction action);

}
