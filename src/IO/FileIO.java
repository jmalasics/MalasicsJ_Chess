package IO;
import java.io.*;

import GameLogic.PlayerTeam;
import GameLogic.Team;
import Parser.*;
import PieceManipulation.*;
import UI.*;


public class FileIO {

	private String filePath;
	private FileReader reader;
	private BufferedReader textReader;
    private Parsable[] parsables;
    private UI ui;

    private static final int NUM_PARSABLES = 5;
	
	public FileIO(String file, UI ui, Team whiteTeam, Team blackTeam) throws FileNotFoundException {
		filePath = file;
		reader = new FileReader(filePath);
		textReader = new BufferedReader(reader);
        this.ui = ui;
        createParserArray(whiteTeam, blackTeam);
	}

    /**
     * Creates the array containing all the parsers for inputted actions.
     *
     */
    private void createParserArray(Team whiteTeam, Team blackTeam) {
        parsables = new Parsable[NUM_PARSABLES];
        parsables[0] = new PlaceParser(ui, whiteTeam, blackTeam);
        parsables[1] = new CaptureParser(ui);
        parsables[2] = new MultimoveParser(ui);
        parsables[3] = new MoveParser(ui);
        parsables[4] = new InvalidActionParser(ui);
    }
	
	/**
	 * Reads each line from the file and returns an object containing the information for the action.
	 * 
	 * @return an action that is parsed out from the line
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
		return action;
    }
	
}