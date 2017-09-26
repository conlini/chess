package org.casestudy.chess.pieces;

import org.casestudy.chess.constants.MovementDirection;
import org.casestudy.chess.constants.PieceColor;
import org.casestudy.chess.constants.PieceType;
import org.casestudy.chess.core.Square;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class Pawn extends Piece {

    private MovementDirection direction;

    public Pawn(PieceColor pieceColor, MovementDirection movementDirection, Square current) {
        super(PieceType.Pawn, pieceColor, current);
        this.direction = movementDirection;
    }

    public boolean canMoveToSquare(Square[][] places, Square targetSquare) {
        switch (direction) {
            case Down: {
                if (hasMoved) {
                    int[] rowMoves = new int[]{1};
                    int[] columntMoves = new int[]{0};
                    for (int i = 0; i < columntMoves.length; i++) {
                        Square square = new Square(currentPlace.getRow() + rowMoves[i], currentPlace.getColumn() + columntMoves[i]);
                        if (MoveUtil.isValidSquare(square) && square.equals(targetSquare)) {
                            return true;
                        }
                    }
                    if (validateCrossMove(targetSquare)) {
                        return true;
                    }
                    return false;
                    // x+1, y, x+1, y-1, x+1, y+1
                } else {
                    // x+1, y, x+1, y-1, x+1, y+1, x+2, y
                    int[] rowMoves = new int[]{1, 2};
                    int[] columnMoves = new int[]{0, 0};
                    for (int i = 0; i < columnMoves.length; i++) {
                        Square square = new Square(currentPlace.getRow() + rowMoves[i], currentPlace.getColumn() + columnMoves[i]);
                        if (MoveUtil.isValidSquare(square) && square.equals(targetSquare)) {
                            return true;
                        }
                    }
                    if (validateCrossMove(targetSquare)) {
                        return true;
                    }
                    return false;
                }
            }
            case Up: {
                if (hasMoved) {
                    // x-1, y, x-1, y-1, x-1, y+1
                    int[] rowMoves = new int[]{-1};
                    int[] columnMoves = new int[]{0};
                    for (int i = 0; i < columnMoves.length; i++) {
                        Square square = new Square(currentPlace.getRow() + rowMoves[i], currentPlace.getColumn() + columnMoves[i]);
                        if (MoveUtil.isValidSquare(square) && square.equals(targetSquare)) {
                            return true;
                        }
                    }
                    if (validateCrossMove(targetSquare)) {
                        return true;
                    }
                    return false;
                } else {
                    // x-1, y, x+-1, y-1, x-1, y+1, x-2, y
                    int[] rowMoves = new int[]{-1, -2};
                    int[] columnMoves = new int[]{0, 0};
                    for (int i = 0; i < columnMoves.length; i++) {
                        Square square = new Square(currentPlace.getRow() + rowMoves[i], currentPlace.getColumn() + columnMoves[i]);
                        if (MoveUtil.isValidSquare(square) && square.equals(targetSquare)) {
                            return true;
                        }
                    }
                    if (validateCrossMove(targetSquare)) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    private boolean validateCrossMove(Square targetSquare) {
        int[] crossMovesRow = null;
        switch (direction) {
            case Down: {
                crossMovesRow = new int[]{1, 1};
                break;
            }
            case Up: {
                crossMovesRow = new int[]{-1, -1};
                break;
            }
        }
        int[] crossMovesColumn = new int[]{-1, 1};
        if (targetSquare.getOccupiedPiece() != null) {
            // check cross move
            for (int i = 0; i < crossMovesRow.length; i++) {
                Square square = new Square(currentPlace.getRow() + crossMovesRow[i],
                        currentPlace.getColumn() + crossMovesColumn[i]);
                if (MoveUtil.isValidSquare(square) && square.equals(targetSquare)) {
                    return true;
                }
            }
        }
        return false;
    }
}