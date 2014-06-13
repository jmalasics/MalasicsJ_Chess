package UI;

import Board.ChessBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import GameLogic.ChessGame;
import GameLogic.ITeamObserver;
import GameLogic.PlayerTeam;
import GameLogic.Team;
import Piece.Piece;
import PieceManipulation.ChessAction;
import PieceManipulation.Location;

/**
 * Created by jmalasics on 5/15/14.
 */
public class GUI implements UI, ISquareObserver, KeyListener, IUserInterfaceObservable, ITeamObserver {

    private static final int NUM_ROWS_COLS = 8;
    private static final int ESC_KEYCODE = 27;

    private JFrame window;
    private ChessBoard board;
    private Square[][] visualBoard;
    private Piece selectedPiece;
    private ChessAction currentAction;
    private Team currentTeam;
    private Team otherTeam;
    private ArrayList<IUserInterfaceObserver> observers;

    public GUI(ChessBoard board, Team currentTeam, Team otherTeam, ChessGame game) {
        this.board = board;
        createWindow();
        observers = new ArrayList<IUserInterfaceObserver>();
        registerObserver(game);
        window.addKeyListener(this);
        this.currentTeam = currentTeam;
        this.otherTeam = otherTeam;
    }

    private void createWindow() {
        window = new JFrame();
        window.setBounds(100, 100, 500, 500);
        window.setTitle("Chess Champions : White Turn");
        window.setLayout(new GridLayout(NUM_ROWS_COLS, NUM_ROWS_COLS));
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fillBoard();
        window.setVisible(true);
    }

    private void fillBoard() {
        visualBoard = new Square[NUM_ROWS_COLS][NUM_ROWS_COLS];
        for(int i = 0; i < NUM_ROWS_COLS; i++) {
            for(int j = 0; j < NUM_ROWS_COLS; j++) {
                Square square = new Square(determineBackgroundColor(i, j), board.getSquareAt(i, j).getLocation());
                square.setBackground(square.getColor());
                square.registerObserver(this);
                visualBoard[i][j] = square;
                window.add(square);
            }
        }
        window.validate();
    }

    /**
     * Returns the square at the specified location.
     *
     * @param location the location of of which you want to get a square
     * @return the square at the specified location
     */
    private Square getSquareAt(Location location) {
        return visualBoard[location.getArrayY()][location.getIntX()];
    }

    /**
     * Determines what the color will be for the squares that are added to the board, based off of where the square is
     * located in the array.
     *
     * @param x the x location of a square
     * @param y the y location of a square
     * @return the color that the square
     */
    private Color determineBackgroundColor(int x, int y) {
        return (x + y) % 2 == 0 ? Color.WHITE : Color.GRAY;
    }

    @Override
    public void displayBoard() {
        clearBoard();
        for(int i = 0; i < NUM_ROWS_COLS; i++) {
            for(int j = 0; j < NUM_ROWS_COLS; j++) {
                visualBoard[i][j].setPieceImage(board.getSquareAt(i, j).getPiece());
                visualBoard[i][j].validate();
            }
        }
        window.validate();
    }

    private void clearBoard() {
        for(int i = 0; i < NUM_ROWS_COLS; i++) {
            for(int j = 0; j < NUM_ROWS_COLS; j++) {
                visualBoard[i][j].removePiece();
            }
        }
    }

    @Override
    public void displayErrorMessage(Exception exception) {
        exception.printStackTrace();
        JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void displayMessage(String string) {
        System.out.println(string);
    }

    @Override
    public void displayLogMessage(String string) {
        System.out.println(string);
    }

    @Override
    public void displayCheckOrCheckmateMessage(String string) {
        if(otherTeam.getMoves().size() == 0) {
            displayCheckmateMessage(string);
        } else {
            displayCheckMessage(string);
        }
    }

    public void displayCheckMessage(String string) {
        JOptionPane.showMessageDialog(null, string, "Check", JOptionPane.WARNING_MESSAGE);
    }

    public void displayCheckmateMessage(String string) {
        JOptionPane.showMessageDialog(null, string, "Checkmate", JOptionPane.WARNING_MESSAGE);
        JOptionPane.showMessageDialog(null, currentTeam.toString() + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public ChessAction getAction(PlayerTeam currentTeam, PlayerTeam otherTeam) {
        this.currentTeam = currentTeam;
        this.otherTeam = otherTeam;
        return currentAction;
    }

    @Override
    public void update(Square square) {
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
            currentAction = findAction(square, selectedPiece);
            if(currentAction != null) {
                revertBackgroundColors();
                notifyObservers();
                currentAction = null;
                selectedPiece = null;
            }
        }
    }

    private ChessAction findAction(Square square, Piece piece) {
        ChessAction action = null;
        for(ChessAction ca : piece.getMoves()) {
            if(ca.getEndLocation().equals(square.getSquareLocation())) {
                action = ca;
            }
        }
        return action;
    }

    private void revertBackgroundColors() {
        for(int i = 0; i < NUM_ROWS_COLS; i++) {
            for(int j = 0; j < NUM_ROWS_COLS; j++) {
                visualBoard[i][j].resetBackground();
            }
        }
    }

    @Override
    public void registerObserver(IUserInterfaceObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IUserInterfaceObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for(IUserInterfaceObserver observer : observers) {
            observer.update(currentAction);
        }
    }

    @Override
    public void update(Team currentTeam, Team otherTeam) {
        this.currentTeam = currentTeam;
        this.otherTeam = otherTeam;
        window.setTitle("Chess Champions : " + currentTeam.toString() + "'s Turn");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == ESC_KEYCODE) {
            selectedPiece = null;
            revertBackgroundColors();
            displayBoard();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

