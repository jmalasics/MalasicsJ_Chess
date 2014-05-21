package IO;
import java.io.*;
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
	
	public FileIO(String file, UI ui) throws FileNotFoundException {
		filePath = file;
		reader = new FileReader(filePath);
		textReader = new BufferedReader(reader);
        this.ui = ui;
        createParserArray();
	}

    private void createParserArray() {
        parsables = new Parsable[NUM_PARSABLES];
        parsables[0] = new PlaceParser(ui);
        parsables[1] = new CaptureParser(ui);
        parsables[2] = new MultimoveParser(ui);
        parsables[3] = new MoveParser(ui);
        parsables[4] = new InvalidActionParser(ui);
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
		return action;
    }
	
}