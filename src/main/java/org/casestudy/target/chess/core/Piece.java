package org.casestudy.target.chess.core;

import java.util.List;

/**
 * Created by adityabhasin on 23/09/17.
 */
public abstract class Piece {

    private final PieceType pieceType;
    private final PieceColor pieceColor;

    private boolean hasMoved;
    private Square currentPlace;

    public Piece(PieceType pieceType, PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public abstract boolean canMoveToSquare(Square targetSquare);
}
