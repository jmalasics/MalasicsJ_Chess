MalasicsJ_Chess
===============

public void promptForMove() {
		while(true)
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("Make a move:");
			String move = scan.nextLine();
			
			MultimoveParser mm = new MultimoveParser(ui);
			ChessAction cm = mm.parse(move);
			if(cm != null)
			{
				run(cm);
			} else
			{
				CaptureParser cp = new CaptureParser(ui);
				ChessAction c = cp.parse(move);
				if(c != null) {
					run(c);
				} else {
					MoveParser mp = new MoveParser(ui);
					ChessAction m = mp.parse(move);
					run(m);
				}
			} 
		}	
	}
