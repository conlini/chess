package org.casestudy.chess.core;

import org.casestudy.chess.constants.MoveType;
import org.casestudy.chess.constants.PieceType;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class NormalMove extends Move {
    /*
Move has
- Name of piece[if missing then assume pawn [ KQRBN]
- capture(optional) - x
- file [a - h]
- rank [1 - 8]
- Disambugating move // phase 2
- Promotion // phase 2
- check (+)

 */


    private PieceType pieceType;
    private Square targetSquare;
    private boolean isAttemptToCapture;
    private boolean isAttemptToCheck;


    public NormalMove() {
        super(MoveType.Normal);
    }


    public Square getTargetSquare() {
        return targetSquare;
    }


    public boolean isAttemptToCapture() {
        return isAttemptToCapture;
    }

    public boolean isAttemptToCheck() {
        return isAttemptToCheck;
    }

    public PieceType getPieceType() {
        return pieceType;
    }


    public NormalMove setAttemptToCheck(String attemptToCheck) {
        if (attemptToCheck != null && attemptToCheck == "+") {
            this.isAttemptToCheck = true;
        } else {
            this.isAttemptToCheck = false;
        }

        return this;
    }

    public NormalMove setAttemptToCapture(String attemptToCapture) {
        if (attemptToCapture != null && attemptToCapture == "x") {
            this.isAttemptToCapture = true;
        } else {
            this.isAttemptToCapture = false;
        }

        return this;
    }

    public NormalMove setPieceType(String pieceType) {
        this.pieceType = PieceType.decode(pieceType);
        return this;
    }

    public NormalMove setTargetSquare(String file, String rank) {
        this.targetSquare = new Square(Integer.parseInt(rank), file.toCharArray()[0] - 'a');
        return this;
    }
}
