package org.casestudy.target.chess.core;

import org.casestudy.target.chess.constants.CastlingSide;
import org.casestudy.target.chess.constants.MoveType;
import org.casestudy.target.chess.constants.PieceType;

/**
 * Created by adityabhasin on 23/09/17.
 */
public class Move {


    private final MoveType moveType;


    public Move(MoveType moveType) {
        this.moveType = moveType;
    }


    public MoveType getMoveType() {
        return moveType;
    }
}
