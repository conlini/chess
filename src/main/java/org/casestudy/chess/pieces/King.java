package org.casestudy.chess.pieces;

import org.casestudy.chess.constants.PieceColor;
import org.casestudy.chess.constants.PieceType;
import org.casestudy.chess.core.Square;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class King extends Piece {
    public King(PieceColor pieceColor, Square current) {
        super(PieceType.King, pieceColor, current);
    }

    public boolean canMoveToSquare(Square targetSquare) {
        int[] xmoves = new int[]{-1, 0, -1, -1, 0, -1, -1, 0, -1};
        int[] ymoves = new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1};
        for (int i = 0; i < xmoves.length; i++) {
            Square square = new Square(currentPlace.getX() + xmoves[i], currentPlace.getY() + ymoves[i]);
            if (MoveUtil.isValidSquare(square) && square.equals(targetSquare)) {
                return true;
            }
        }
        return false;
    }
}