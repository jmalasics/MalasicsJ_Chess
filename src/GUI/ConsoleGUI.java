package GUI;

import Board.ChessBoard;

public class ConsoleGUI {
	
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
	}
	
}

