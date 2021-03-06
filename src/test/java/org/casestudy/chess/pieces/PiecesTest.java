package org.casestudy.chess.pieces;

import org.casestudy.chess.constants.MovementDirection;
import org.casestudy.chess.constants.PieceColor;
import org.casestudy.chess.constants.RookLocation;
import org.casestudy.chess.core.ILayoutOwner;
import org.casestudy.chess.core.Square;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class PiecesTest {

    @Test
    public void testPawnMovement() {
        Piece pawn = new Pawn(PieceColor.White, MovementDirection.Down, new Square(4, 1));
        // can move 2 steps
        Assert.assertTrue(pawn.canMoveToSquare(null, new Square(6, 1 )));
        // can not move 1 step right if no occupant
        Assert.assertFalse(pawn.canMoveToSquare(null, new Square(5, 2)));
        Square sq = new Square(5, 2);
        sq.setOccupiedPiece(new Rook(PieceColor.Black, sq, RookLocation.QueenSide));
        Assert.assertTrue(pawn.canMoveToSquare(null, sq));
        pawn.moveTo(new Square(6, 1));
        // verify can no longer move 2 steps
        Assert.assertFalse(pawn.canMoveToSquare(null, new Square(8, 1)));
    }

    @Test
    public void testKnightMovement() {
        Piece knight = new Knight(PieceColor.White, new Square(3,3));
        Assert.assertTrue(knight.canMoveToSquare(null, new Square(4,5)));
        Assert.assertFalse(knight.canMoveToSquare(null, new Square(6,6)));
    }

    @Test
    public void testKingMovement() {
        Piece king = new King(PieceColor.Black, new Square(2,3));
        Assert.assertTrue(king.canMoveToSquare(null, new Square(3,4)));
        Assert.assertFalse(king.canMoveToSquare(null, new Square(5,5)));
    }

    @Test
    public void testQueenMovement() {
        Piece queen = new Queen(PieceColor.Black, new Square(2,6));
        class LayoutProvider implements ILayoutOwner {

            public boolean isOccupied(int row, int column) {
                if(row == 3 && column == 7) {
                    return true;
                }
                return false;
            }

            public Piece getOccupant(int row, int column) {
                return null;
            }

            public Square getSquare(int row, int column) {
                return null;
            }
        }
        LayoutProvider layoutOwner = new LayoutProvider();
        Assert.assertTrue(queen.canMoveToSquare(layoutOwner, new Square(8,6)));
        Assert.assertFalse(queen.canMoveToSquare(layoutOwner, new Square(4,8)));
        Assert.assertFalse(queen.canMoveToSquare(layoutOwner, new Square(4,5)));
    }

    @Test
    public void testBishopMovement() {
        class LayoutProvider implements ILayoutOwner {

            public boolean isOccupied(int row, int column) {
                if(row == 5 && column == 4) {
                    return true;
                }
                return false;
            }

            public Piece getOccupant(int row, int column) {
                return null;
            }

            public Square getSquare(int row, int column) {
                return null;
            }
        }
        Piece bishop = new Bishop(PieceColor.Black, new Square(3, 2));
        LayoutProvider layoutOwner = new LayoutProvider();
        Assert.assertFalse(bishop.canMoveToSquare(layoutOwner, new Square(7, 8)));
        Assert.assertTrue(bishop.canMoveToSquare(layoutOwner, new Square(4, 1)));
    }

    @Test
    public void testRookMovement() {
        class LayoutProvider implements ILayoutOwner {

            public boolean isOccupied(int row, int column) {
                if(row == 2 && column == 2) {
                    return true;
                }
                return false;
            }

            public Piece getOccupant(int row, int column) {
                return null;
            }

            public Square getSquare(int row, int column) {
                return null;
            }
        }
        Piece rook = new Rook(PieceColor.Black, new Square(3, 2), RookLocation.QueenSide);
        LayoutProvider layoutOwner = new LayoutProvider();
        Assert.assertFalse(rook.canMoveToSquare(layoutOwner, new Square(1,2)));
        Assert.assertTrue(rook.canMoveToSquare(layoutOwner, new Square(3, 6)));
    }


}
