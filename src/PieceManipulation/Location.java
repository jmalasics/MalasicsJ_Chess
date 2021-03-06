package PieceManipulation;

/**
 * Stores all the information needed when locating where a piece or square is on the board.
 * 
 * @author jmalasics
 *
 */
public class Location {

	private char xLocation;
	private int yLocation;
	
	/**
	 * Used when parsing strings from file.
	 * 
	 * @param x
	 * @param y
	 */
	public Location(char x, int y) {
		xLocation = x;
		yLocation = y;
	}
	
	/**
	 * Used when using values from a two dimensional array to create a location.
	 * 
	 * @param x
	 * @param y
	 */
	public Location(int x, int y) {
		xLocation = convertIntToChar(x);
		yLocation = y + 1;
	}
	
	/**
	 * Converts a integer value (from an array) to the correct character value for the column.
	 * 
	 * @param x
	 * @return
	 */
	private char convertIntToChar(int x) {
		char xCharacter = 'a';
		if(x == 1) {
			xCharacter = 'b';
		} else if(x == 2) {
			xCharacter = 'c';
		} else if(x == 3) {
			xCharacter = 'd';
		} else if(x == 4) {
			xCharacter = 'e';
		} else if(x == 5) {
			xCharacter = 'f';
		} else if(x == 6) {
			xCharacter = 'g';
		} else if(x == 7) {
			xCharacter = 'h';
		}
		return xCharacter;
	}
	
	/**
	 * Gets the character value for the X value of the location.
	 * 
	 * @return
	 */
	public char getX() {
		return xLocation;
	}
	
	/**
	 * Gets the integer value for the X value of the location.
	 * 
	 * @return
	 */
	public int getIntX() {
		return xLocation - 'a';
	}
	
	/**
	 * Gets the Y value of the location.
	 * 
	 * @return
	 */
	public int getY() {
		return yLocation;
	}
	
	public int getArrayY() {
		return yLocation - 1;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xLocation;
		result = prime * result + yLocation;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null) && obj instanceof Location && this.getIntX() == ((Location) obj).getIntX() && (this.getY() == ((Location) obj).getY());
	}

	@Override
	public String toString() {
		return "" + xLocation + yLocation;
	}
	
	
	
}
