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

    public boolean canMoveToSquare(Square[][] places, Square targetSquare) {
        int[] columnMoves = new int[]{-1, 0, 1, -1, 1, -1, 0, -1};
        int[] rowMoves = new int[]{1, 1, 1, 0, 0, -1, -1, -1};
        for (int i = 0; i < columnMoves.length; i++) {
            Square square = new Square(currentPlace.getRow() + rowMoves[i], currentPlace.getColumn() + columnMoves[i]);
            if (MoveUtil.isValidSquare(square) && square.equals(targetSquare)) {
                return true;
            }
        }
        return false;
    }
}