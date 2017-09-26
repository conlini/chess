package org.casestudy.chess.pieces;

import org.casestudy.chess.constants.MovementDirection;
import org.casestudy.chess.constants.PieceColor;
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
        Assert.assertTrue(pawn.canMoveToSquare(null, new Square(4, 3 )));
        // can move 1 step right
        Assert.assertTrue(pawn.canMoveToSquare(null, new Square(5, 2)));
        pawn.moveTo(new Square(4, 3));
        // verify can no longer move 2 steps
        Assert.assertFalse(pawn.canMoveToSquare(null, new Square(4, 5)));
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
    public void testQueenMovement() {}

    @Test
    public void testBishopMovement() {}

    @Test
    public void testRookMovement() {}


}
