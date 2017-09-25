package org.casestudy.chess.pieces;

import org.casestudy.chess.core.Square;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class MoveUtil {

    public static boolean checkMoveDiagonal(Square currentPlace, Square targetSquare) {
        Square currentsquare = new Square(currentPlace.getX(), currentPlace.getY());
        currentsquare.setX(currentsquare.getX() - 1);
        currentsquare.setY(currentsquare.getY() - 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else {
                currentsquare.setX(currentsquare.getX() - 1);
                currentsquare.setY(currentsquare.getY() - 1);
            }
        }

        // diagonal down left
        currentsquare = new Square(currentPlace.getX(), currentPlace.getY());
        currentsquare.setX(currentsquare.getX() - 1);
        currentsquare.setY(currentsquare.getY() + 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else {
                currentsquare.setX(currentsquare.getX() - 1);
                currentsquare.setY(currentsquare.getY() + 1);
            }
        }

        // diagonal down right
        currentsquare = new Square(currentPlace.getX(), currentPlace.getY());
        currentsquare.setX(currentsquare.getX() + 1);
        currentsquare.setY(currentsquare.getY() + 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else {
                currentsquare.setX(currentsquare.getX() + 1);
                currentsquare.setY(currentsquare.getY() + 1);
            }
        }

        // diagonal up right
        currentsquare = new Square(currentPlace.getX(), currentPlace.getY());
        currentsquare.setX(currentsquare.getX() + 1);
        currentsquare.setY(currentsquare.getY() - 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else {
                currentsquare.setX(currentsquare.getX() + 1);
                currentsquare.setY(currentsquare.getY() - 1);
            }
        }

        return false;

    }

    public static boolean checkMoveLateral(Square currentPlace, Square targetSquare) {

        // traverse left
        Square currentSquare = new Square(currentPlace.getX(), currentPlace.getY());
        currentSquare.setX(currentSquare.getX() - 1);
        while (isValidSquare(currentSquare)) {
            if (currentSquare.equals(targetSquare)) {
                return true;
            } else {
                currentSquare.setX(currentSquare.getX() - 1);
            }
        }

        // traverse right
        currentSquare = new Square(currentPlace.getX(), currentPlace.getY());
        currentSquare.setX(currentSquare.getX() + 1);
        while (isValidSquare(currentSquare)) {
            if (currentSquare.equals(targetSquare)) {
                return true;
            } else {
                currentSquare.setX(currentSquare.getX() + 1);
            }
        }

        // traverse up
        currentSquare = new Square(currentPlace.getX(), currentPlace.getY());
        currentSquare.setY(currentSquare.getY() - 1);
        while (isValidSquare(currentSquare)) {
            if (currentSquare.equals(targetSquare)) {
                return true;
            } else {
                currentSquare.setY(currentSquare.getY() - 1);
            }
        }

        // traverse down
        currentSquare = new Square(currentPlace.getX(), currentPlace.getY());
        currentSquare.setY(currentSquare.getY() + 1);
        while (isValidSquare(currentSquare)) {
            if (currentSquare.equals(targetSquare)) {
                return true;
            } else {
                currentSquare.setY(currentSquare.getY() + 1);
            }
        }

        return false;
    }


    public static boolean isValidSquare(Square square) {
        if ((square.getX()  >= 1 && square.getX() <= 8) && (square.getY() >= 1 && square.getY() <= 8)) {
            return true;
        }
        return false;
    }
}
