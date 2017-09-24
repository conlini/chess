package org.casestudy.target.chess.pieces;

import org.casestudy.target.chess.constants.PieceColor;
import org.casestudy.target.chess.constants.PieceType;
import org.casestudy.target.chess.core.Square;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class Rook extends Piece {

    public Rook( PieceColor pieceColor, Square current) {
        super(PieceType.Rook, pieceColor, current);
    }

    public boolean canMoveToSquare(Square targetSquare) {
        return MoveUtil.checkMoveLateral(currentPlace, targetSquare);
    }
}
