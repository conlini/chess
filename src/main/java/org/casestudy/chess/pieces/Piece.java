package org.casestudy.chess.pieces;

import org.casestudy.chess.constants.PieceColor;
import org.casestudy.chess.constants.PieceType;
import org.casestudy.chess.core.ILayoutOwner;
import org.casestudy.chess.core.Square;

/**
 * Created by adityabhasin on 23/09/17.
 */

public abstract class Piece {

    protected final PieceType pieceType;
    private final PieceColor pieceColor;

    protected boolean hasMoved;
    protected Square currentPlace;
    private boolean isCaptured;

    public Piece(PieceType pieceType, PieceColor pieceColor, Square current) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        this.currentPlace = current;
        this.currentPlace.setOccupiedPiece(this);
    }

    public abstract boolean canMoveToSquare(ILayoutOwner layoutOwner, Square targetSquare);

    public void markCaptured() {
        this.isCaptured = true;
        this.currentPlace = null;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public Square getCurrentPlace() {

        return currentPlace;
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

    public void moveTo(Square targetSquare) {
        this.currentPlace = targetSquare;
        this.hasMoved = true;
    }

    public String getPieceTypeCode() {
        if (this.pieceColor == PieceColor.Black) {
            return this.pieceType.getShortname().toLowerCase();
        } else {
            return this.pieceType.getShortname();
        }
    }
}
