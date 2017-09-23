package org.casestudy.target.chess.core;

import org.casestudy.target.chess.pieces.Piece;

/**
 * Created by adityabhasin on 23/09/17.
 */
public class Square {
    private int x,y;
    private Piece occupiedPiece;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Piece getOccupiedPiece() {
        return occupiedPiece;
    }

    public void setOccupiedPiece(Piece occupiedPiece) {
        this.occupiedPiece = occupiedPiece;
    }
}
