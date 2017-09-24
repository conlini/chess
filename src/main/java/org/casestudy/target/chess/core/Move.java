package org.casestudy.target.chess.core;

import org.casestudy.target.chess.constants.CastlingSide;
import org.casestudy.target.chess.constants.PieceType;

/**
 * Created by adityabhasin on 23/09/17.
 */
// TODO: maybe have this as abstract and then subclasses for type of Move???
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
    private boolean isAttemptToCapture;
    private boolean isAttemptToCheck;
    private boolean isCastling;
    private CastlingSide castlingSide;

    public boolean isCastling() {
        return isCastling;
    }

    public void setCastling(boolean castling) {
        isCastling = castling;
    }

    public CastlingSide getCastlingSide() {
        return castlingSide;
    }

    public void setCastlingSide(CastlingSide castlingSide) {
        this.castlingSide = castlingSide;
    }

    public boolean isAttemptToCheck() {
        return isAttemptToCheck;
    }

    public void setAttemptToCheck(boolean attemptToCheck) {
        isAttemptToCheck = attemptToCheck;
    }

    public boolean isAttemptToCapture() {
        return isAttemptToCapture;
    }

    public void setAttemptToCapture(boolean attemptToCapture) {
        isAttemptToCapture = attemptToCapture;
    }

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
