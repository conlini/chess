package org.casestudy.chess.pieces;

import org.casestudy.chess.core.Square;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class MoveUtil {

    public static boolean checkMoveDiagonal(Square[][] places, Square currentPlace, Square targetSquare) {
        Square currentsquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        // diagnol up left
        currentsquare.setRow(currentsquare.getRow() - 1);
        currentsquare.setColumn(currentsquare.getColumn() - 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else {
                currentsquare.setRow(currentsquare.getRow() - 1);
                currentsquare.setColumn(currentsquare.getColumn() - 1);
            }
        }

        // diagonal down right
        currentsquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        currentsquare.setRow(currentsquare.getRow() - 1);
        currentsquare.setColumn(currentsquare.getColumn() + 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else {
                currentsquare.setRow(currentsquare.getRow() - 1);
                currentsquare.setColumn(currentsquare.getColumn() + 1);
            }
        }

        // diagonal down right
        currentsquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        currentsquare.setRow(currentsquare.getRow() + 1);
        currentsquare.setColumn(currentsquare.getColumn() + 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else {
                currentsquare.setRow(currentsquare.getRow() + 1);
                currentsquare.setColumn(currentsquare.getColumn() + 1);
            }
        }

        // diagonal down left
        currentsquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        currentsquare.setRow(currentsquare.getRow() + 1);
        currentsquare.setColumn(currentsquare.getColumn() - 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else {
                currentsquare.setRow(currentsquare.getRow() + 1);
                currentsquare.setColumn(currentsquare.getColumn() - 1);
            }
        }

        return false;

    }

    public static boolean checkMoveLateral(Square[][] places, Square currentPlace, Square targetSquare) {

        // traverse down
        Square currentSquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        currentSquare.setRow(currentSquare.getRow() - 1);
        while (isValidSquare(currentSquare)) {
            if (currentSquare.equals(targetSquare)) {
                return true;
            } else {
                currentSquare.setRow(currentSquare.getRow() - 1);
            }
        }

        // traverse up
        currentSquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        currentSquare.setRow(currentSquare.getRow() + 1);
        while (isValidSquare(currentSquare)) {
            if (currentSquare.equals(targetSquare)) {
                return true;
            } else {
                currentSquare.setRow(currentSquare.getRow() + 1);
            }
        }

        // traverse left
        currentSquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        currentSquare.setColumn(currentSquare.getColumn() - 1);
        while (isValidSquare(currentSquare)) {
            if (currentSquare.equals(targetSquare)) {
                return true;
            } else {
                currentSquare.setColumn(currentSquare.getColumn() - 1);
            }
        }

        // traverse right
        currentSquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        currentSquare.setColumn(currentSquare.getColumn() + 1);
        while (isValidSquare(currentSquare)) {
            if (currentSquare.equals(targetSquare)) {
                return true;
            } else {
                currentSquare.setColumn(currentSquare.getColumn() + 1);
            }
        }

        return false;
    }


    public static boolean isValidSquare(Square square) {
        if ((square.getRow() >= 1 && square.getRow() <= 8) && (square.getColumn() >= 1 && square.getColumn() <= 8)) {
            return true;
        }
        return false;
    }
}
