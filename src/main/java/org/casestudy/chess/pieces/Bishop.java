package org.casestudy.chess.pieces;

import org.casestudy.chess.constants.PieceColor;
import org.casestudy.chess.constants.PieceType;
import org.casestudy.chess.core.Square;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class Bishop extends Piece {
    public Bishop(PieceColor pieceColor, Square current) {
        super(PieceType.Bishop, pieceColor, current);
    }

    public boolean canMoveToSquare(Square targetSquare) {
        return MoveUtil.checkMoveDiagonal(currentPlace, targetSquare);
    }
}
