package IO;
import java.awt.Color;
import java.io.*;
import java.util.HashMap;
import java.util.regex.*;

import GameLogic.Team;
import Parser.*;
import Piece.*;
import PieceManipulation.*;


public class FileIO {

	private String filePath;
	private FileReader reader;
	private BufferedReader textReader;
    private Parsable[] parsables;

    private static final int NUM_PARSABLES = 5;
	
	public FileIO(String file) throws FileNotFoundException {
		filePath = file;
		reader = new FileReader(filePath);
		textReader = new BufferedReader(reader);
        createParserArray();
	}

    private void createParserArray() {
        parsables = new Parsable[NUM_PARSABLES];
        parsables[0] = new PlaceParser();
        parsables[1] = new CaptureParser();
        parsables[2] = new MultimoveParser();
        parsables[3] = new MoveParser();
        parsables[4] = new InvalidActionParser();
    }
	
	/**
	 * Reads each line from the file and returns an object containing the information for the action.
	 * 
	 * @return
	 * @throws IOException
	 */
	public ChessAction readLine() throws IOException {
		ChessAction action = null;
		String input = textReader.readLine();
		if (input != null) {
			input.toLowerCase();
            int index = 0;
            while(index < parsables.length) {
                if(action == null) {
                    action = parsables[index].parse(input);
                }
                index++;
            }
		}
		System.out.flush();
		return action;
    }
	
}