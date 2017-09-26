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

    public CastlingMove(String move) {
        super(MoveType.Castling);
        if (move.equals("0-0")) {
            this.castlingSide = CastlingSide.KingSide;
        } else if (move.equals("0-0-0")) {
            this.castlingSide = CastlingSide.QueenSide;
        }
    }

    public CastlingSide getCastlingSide() {
        return castlingSide;
    }

}
