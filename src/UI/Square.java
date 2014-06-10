package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import Piece.*;
import PieceManipulation.Location;

/**
 * Created by jmalasics on 6/2/14.
 */
public class Square extends JPanel implements MouseListener, ISquareObservable {

    private Color backgroundColor;
    private Piece piece;
    private JLabel pieceImage;
    private ArrayList<ISquareObserver> observers;
    private Location location;

    public Square(Color backgroundColor, Location location) {
        this.backgroundColor = backgroundColor;
        observers = new ArrayList<ISquareObserver>();
        this.location = location;
        addMouseListener(this);
    }

    public Color getColor() {
        return backgroundColor;
    }

    public void setPieceImage(Piece piece) {
        if(piece != null) {
            this.piece = piece;
            pieceImage = new JLabel(new ImageIcon(piece.getPieceImage()));
            this.add(pieceImage);
            this.repaint();
        }
    }

    public void removePiece() {
        piece = null;
        if(pieceImage != null) {
            this.remove(pieceImage);
            pieceImage = null;
        }
        this.repaint();
    }

    public void setBackgroundColor(Color color) {
        this.setBackground(color);
    }

    public void resetBackground() {
        this.setBackground(backgroundColor);
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public Location getSquareLocation() {
        return location;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.notifyObservers();
    }

    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void registerObserver(ISquareObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ISquareObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(ISquareObserver observer : observers) {
            observer.update(this);
        }
    }

}
