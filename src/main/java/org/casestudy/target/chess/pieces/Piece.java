package org.casestudy.target.chess.pieces;

import org.casestudy.target.chess.constants.PieceColor;
import org.casestudy.target.chess.constants.PieceType;
import org.casestudy.target.chess.core.Square;

/**
 * Created by adityabhasin on 23/09/17.
 */

// TODO : add concrete classes for each type
public abstract class Piece {

    private final PieceType pieceType;
    // TODO: does Piece care for PieceColor??
    private final PieceColor pieceColor;

    private boolean hasMoved;
    private Square currentPlace;
    private boolean isCaptured;

    public Piece(PieceType pieceType, PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public abstract boolean canMoveToSquare(Square targetSquare);

    public void markCaptured() {
        this.isCaptured = true;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public Square getCurrentPlace() {

        return currentPlace;
    }

    public void setCurrentPlace(Square currentPlace) {
        this.currentPlace = currentPlace;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    public void setCaptured(boolean captured) {
        isCaptured = captured;
    }
}
