package org.casestudy.target.chess.pieces;

import org.casestudy.target.chess.constants.MovementDirection;
import org.casestudy.target.chess.constants.PieceColor;
import org.casestudy.target.chess.constants.PieceType;
import org.casestudy.target.chess.core.Square;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class Pawn extends Piece {

    private MovementDirection direction;
    public Pawn(PieceColor pieceColor, MovementDirection movementDirection, Square current) {
        super(PieceType.Pawn, pieceColor, current);
        this.direction = movementDirection;
    }

    public boolean canMoveToSquare(Square targetSquare) {
        switch (direction) {
            case Up: {
                if(hasMoved) {
                    int[] ymoves = new int[]{1, 1, 1};
                    int[] xmoves = new int[]{0, -1, 1};
                    for (int i = 0; i < xmoves.length; i++) {
                        if (new Square(currentPlace.getX() + xmoves[i], currentPlace.getY() + ymoves[i]).equals(targetSquare)) {
                            return true;
                        }
                    }
                    return false;
                    // x+1, y, x+1, y-1, x+1, y+1
                } else {
                    // x+1, y, x+1, y-1, x+1, y+1, x+2, y
                    int[] ymoves = new int[]{1, 1, 1, 2};
                    int[] xmoves = new int[]{0, -1, 1,0};
                    for (int i = 0; i < xmoves.length; i++) {
                        if (new Square(currentPlace.getX() + xmoves[i], currentPlace.getY() + ymoves[i]).equals(targetSquare)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            case Down: {
                if(hasMoved) {
                    // x-1, y, x-1, y-1, x-1, y+1
                    int[] ymoves = new int[]{-1, -1, -1};
                    int[] xmoves = new int[]{0, -1, 1};
                    for (int i = 0; i < xmoves.length; i++) {
                        if (new Square(currentPlace.getX() + xmoves[i], currentPlace.getY() + ymoves[i]).equals(targetSquare)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    // x-1, y, x+-1, y-1, x-1, y+1, x-2, y
                    int[] ymoves = new int[]{-1, -1, -1, -2};
                    int[] xmoves = new int[]{0, -1, 1,0};
                    for (int i = 0; i < xmoves.length; i++) {
                        if (new Square(currentPlace.getX() + xmoves[i], currentPlace.getY() + ymoves[i]).equals(targetSquare)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }
}