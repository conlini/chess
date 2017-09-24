package org.casestudy.chess.core;

import org.casestudy.chess.constants.CastlingSide;
import org.casestudy.chess.constants.MoveType;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class CastlingMove extends Move {
    /*
        0-0 {kingside castle}
    0-0-0 {queenside castle}
    */
    private CastlingSide castlingSide;

    public CastlingMove() {
        super(MoveType.Castling);
    }

    public CastlingSide getCastlingSide() {
        return castlingSide;
    }

    public CastlingMove setCastlingSide(String castlingSide) {
        return this;
    }
}
