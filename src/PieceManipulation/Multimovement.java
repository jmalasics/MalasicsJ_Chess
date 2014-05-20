package PieceManipulation;

import Board.ChessBoard;


/**
 * Stores the information needed for castling.
 * 
 * @author jmalasics
 *
 */
public class Multimovement extends ChessAction {

	private Location secondaryInitialLocation;
	private Location secondaryEndLocation;
	
	public Multimovement(Location initial, Location end, Location secondaryInitial, Location secondaryEnd) {
		super(initial, end);
		secondaryInitialLocation = secondaryInitial;
		secondaryEndLocation = secondaryEnd;
	}
	
	/**
	 * Gets the initial location of the king.
	 * 
	 * @return
	 */
	public Location getKingInitialLocation() {
		return initialLocation;
	}
	
	/**
	 * Gets the end location of the king.
	 * 
	 * @return
	 */
	public Location getKingEndLocation() {
		return endLocation;
	}
	
	/**
	 * Gets the initial location of the rook.
	 * 
	 * @return
	 */
	public Location getRookInitialLocation() {
		return secondaryInitialLocation;
	}
	
	/**
	 * Gets the end location of the rook.
	 * 
	 * @return
	 */
	public Location getRookEndLocation() {
		return secondaryEndLocation;
	}
	
	public boolean executeAction(ChessBoard board) {
		return board.castle(this);
	}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Multimovement && this.getInitialLocation().equals(((Multimovement) obj).getKingInitialLocation()) && this.getEndLocation().equals(((Multimovement) obj).getKingEndLocation())
                && this.getRookInitialLocation().equals(((Multimovement) obj).getRookInitialLocation()) && this.getRookEndLocation().equals(((Multimovement) obj).getRookEndLocation());
    }
	
}
