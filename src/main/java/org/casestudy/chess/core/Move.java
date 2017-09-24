package org.casestudy.chess.core;

import org.casestudy.chess.constants.MoveType;

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
