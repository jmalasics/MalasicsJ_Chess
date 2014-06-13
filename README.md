MalasicsJ_Chess
===============

 @Override
    public void update(Square square) {
    	//Build the output string
    	StringBuilder move = new StringBuilder();
   
        if(selectedPiece == null && square.isOccupied()) {
            currentTeam.findAllAvailableMoves(board);
            currentTeam.removeIntoCheckMoves(board, otherTeam);
            selectedPiece = square.getPiece();
            if(selectedPiece != null) {
                if(currentTeam.isTeamPiece(selectedPiece.getLocation(), board)) {
                    for(ChessAction action : selectedPiece.getMoves()) {
                        Square endSquare = getSquareAt(action.getEndLocation());
                        if(endSquare.isOccupied()) {
                            endSquare.setBackground(Color.RED);
                            endSquare.validate();
                        } else {
                            endSquare.setBackground(Color.GREEN);
                            endSquare.validate();
                        }
                    }
                } else {
                    selectedPiece = null;
                }
            }
        } else if(selectedPiece != null) {
        	move.append(selectedPiece.getLocation().getX());
        	move.append(((this.NUM_ROWS_COLS + 1) - selectedPiece.getLocation().getY()));
        	move.append(" ");
            currentAction = findAction(square, selectedPiece);
            if(currentAction != null) {
            	String kill = ((square.isOccupied()) ? "*" : "");
                revertBackgroundColors();
                notifyObservers();
                move.append(selectedPiece.getLocation().getX());
                move.append(((this.NUM_ROWS_COLS + 1) - selectedPiece.getLocation().getY()));
                move.append(kill);
                System.out.println(move.toString());
                currentAction = null;
                selectedPiece = null;
            }
        }
    }
