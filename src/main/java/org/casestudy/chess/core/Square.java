package org.casestudy.chess.core;

import org.casestudy.chess.pieces.Piece;

/**
 * Created by adityabhasin on 23/09/17.
 */
public class Square {
    private int row, column;
    private Piece occupiedPiece;

    public Square(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
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

        if (row != square.row) return false;
        return column == square.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
