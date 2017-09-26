package org.casestudy.chess.core;

import org.casestudy.chess.pieces.Piece;

/**
 * Created by adityabhasin on 26/09/17.
 */
public interface ILayoutOwner {

    boolean isOccupied(int row, int column);

    Piece getOccupant(int row, int column);

    Square getSquare(int row, int column);
}
