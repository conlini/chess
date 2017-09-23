package org.casestudy.target.chess.core;

/**
 * Created by adityabhasin on 23/09/17.
 */
public abstract class Player {
    private final PieceColor color;

    public Player(PieceColor pieceColor) {
        this.color= pieceColor;
    }

    public abstract Move makeMove();
}
