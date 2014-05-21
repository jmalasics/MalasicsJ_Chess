package UI;

import Board.ChessBoard;

public class Console implements UI {
	
	/**
	 * Displays an ASCII representation of the current state of the chess board into the console.
	 * 
	 * @param board
	 */
	public void displayBoard(ChessBoard board) {
		System.out.println("K : Black");
		System.out.println("k : White");
		System.out.println("- : Empty");
		for(int i = 0; i < ChessBoard.BOARD_ROWS; i++) {
			for(int j = 0; j < ChessBoard.BOARD_COLUMNS; j++) {
				if(j < ChessBoard.BOARD_COLUMNS - 1) {
					System.out.print(board.getSquareAt(i, j).toString());
				} else {
					System.out.println(board.getSquareAt(i, j).toString());
				}
			}
		}
		System.out.flush();
	}

    @Override
    public void displayExceptionMessage(Exception exception) {
        System.err.println(exception.getMessage());
        System.err.flush();
        System.out.flush();
    }

    @Override
    public void displayMessage(String string) {
        System.out.println(string);
        System.out.flush();
        System.err.flush();
    }

    @Override
    public void displayLogMessage(String string) {
        System.out.println(string);
        System.out.flush();
        System.err.flush();
    }
	
}

