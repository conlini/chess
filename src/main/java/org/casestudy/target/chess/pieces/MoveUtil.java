package org.casestudy.target.chess.pieces;

import org.casestudy.target.chess.core.Square;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class MoveUtil {

    public static boolean checkMoveDiagonal(Square currentPlace, Square targetSquare) {
        Square currentsquare = new Square(currentPlace.getX(), currentPlace.getY());
        currentsquare.setX(currentsquare.getX() - 1);
        currentsquare.setY(currentsquare.getY() - 1);
        while (currentsquare.getY() >= 0 && currentsquare.getX() >= 0) {
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
        while (currentsquare.getY() < 8 && currentsquare.getX() >= 0) {
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
        while (currentsquare.getY() < 8 && currentsquare.getX() < 8) {
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
        while (currentsquare.getY() >= 0 && currentsquare.getX() < 80) {
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
        while (currentSquare.getX() >= 0) {
            if (currentSquare.equals(targetSquare)) {
                return true;
            } else {
                currentSquare.setX(currentSquare.getX() - 1);
            }
        }

        // traverse right
        currentSquare = new Square(currentPlace.getX(), currentPlace.getY());
        currentSquare.setX(currentSquare.getX() + 1);
        while (currentSquare.getX() < 8) {
            if (currentSquare.equals(targetSquare)) {
                return true;
            } else {
                currentSquare.setX(currentSquare.getX() + 1);
            }
        }

        // traverse up
        currentSquare = new Square(currentPlace.getX(), currentPlace.getY());
        currentSquare.setY(currentSquare.getY() - 1);
        while (currentSquare.getY() >= 0) {
            if (currentSquare.equals(targetSquare)) {
                return true;
            } else {
                currentSquare.setY(currentSquare.getY() - 1);
            }
        }

        // traverse down
        currentSquare = new Square(currentPlace.getX(), currentPlace.getY());
        currentSquare.setY(currentSquare.getY() + 1);
        while (currentSquare.getY() < 8) {
            if (currentSquare.equals(targetSquare)) {
                return true;
            } else {
                currentSquare.setY(currentSquare.getY() + 1);
            }
        }

        return false;
    }

}
