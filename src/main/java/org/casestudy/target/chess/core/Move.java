package org.casestudy.target.chess.core;

/**
 * Created by adityabhasin on 23/09/17.
 */
public class Move {
/*
Move has
- Name of piece[if missing then assume pawn [ KQRBN]
- capture(optional) - x
- file [a - h]
- rank [1 - 8]
- Disambugating move // phase 2
- Promotion // phase 2
- check (+)

or castling
0-0 {kingside castle}
0-0-0 {queenside castle}

 */

    private PieceType pieceType;
    private Square targetSquare;

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public Square getTargetSquare() {
        return targetSquare;
    }

    public void setTargetSquare(Square targetSquare) {
        this.targetSquare = targetSquare;
    }
}
