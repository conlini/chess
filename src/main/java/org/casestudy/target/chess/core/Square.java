package org.casestudy.target.chess.core;

import org.casestudy.target.chess.pieces.Piece;

/**
 * Created by adityabhasin on 23/09/17.
 */
public class Square {
    private int x,y;
    private Piece occupiedPiece;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Square square = (Square) o;

        if (x != square.x) return false;
        return y == square.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
