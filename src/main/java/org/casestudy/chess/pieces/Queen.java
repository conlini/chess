package org.casestudy.chess.pieces;

import org.casestudy.chess.constants.PieceColor;
import org.casestudy.chess.constants.PieceType;
import org.casestudy.chess.core.ILayoutOwner;
import org.casestudy.chess.core.Square;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class Queen extends Piece {
    public Queen(PieceColor pieceColor, Square current) {
        super(PieceType.Queen, pieceColor, current);
    }

    public boolean canMoveToSquare(ILayoutOwner layoutOwner, Square targetSquare) {
        return MoveUtil.checkMoveDiagonal(layoutOwner, currentPlace, targetSquare) ||
                MoveUtil.checkMoveLateral(layoutOwner, currentPlace, targetSquare);
    }
}