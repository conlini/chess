package org.casestudy.chess.pieces;

import org.casestudy.chess.constants.PieceColor;
import org.casestudy.chess.constants.PieceType;
import org.casestudy.chess.constants.RookLocation;
import org.casestudy.chess.core.Square;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class Rook extends Piece {
    private final RookLocation rookLocation;

    public Rook(PieceColor pieceColor, Square current, RookLocation rookLocation) {
        super(PieceType.Rook, pieceColor, current);
        this.rookLocation = rookLocation;
    }

    public boolean canMoveToSquare(Square targetSquare) {
        return MoveUtil.checkMoveLateral(currentPlace, targetSquare);
    }

    public RookLocation getRookLocation() {
        return rookLocation;
    }
}
