package org.casestudy.chess.pieces;

import org.casestudy.chess.constants.PieceColor;
import org.casestudy.chess.constants.PieceType;
import org.casestudy.chess.core.Square;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class Knight extends Piece {
    public Knight(PieceColor pieceColor, Square current) {
        super(PieceType.Knight, pieceColor, current);
    }

    public boolean canMoveToSquare(Square targetSquare) {
        int[] xmoves = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] ymoves = new int[]{1, -1, 2, -2, 2, -2, 1, -1};
        for (int i = 0; i < xmoves.length; i++) {
            if (new Square(currentPlace.getX() + xmoves[i], currentPlace.getY() + ymoves[i]).equals(targetSquare)) {
                return true;
            }
        }
        return false;
    }
}