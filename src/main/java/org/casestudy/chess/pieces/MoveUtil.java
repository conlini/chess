package org.casestudy.chess.pieces;

import org.casestudy.chess.core.ILayoutOwner;
import org.casestudy.chess.core.Square;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class MoveUtil {

    public static boolean checkMoveDiagonal(ILayoutOwner layoutOwner, Square currentPlace, Square targetSquare) {
        Square currentsquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        // diagnol up left
        currentsquare.setRow(currentsquare.getRow() - 1);
        currentsquare.setColumn(currentsquare.getColumn() - 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else if (layoutOwner.isOccupied(currentsquare.getRow(), currentsquare.getColumn())) {
                break;
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
            } else if (layoutOwner.isOccupied(currentsquare.getRow(), currentsquare.getColumn())) {
                break;
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
            } else if (layoutOwner.isOccupied(currentsquare.getRow(), currentsquare.getColumn())) {
                break;
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
            } else if (layoutOwner.isOccupied(currentsquare.getRow(), currentsquare.getColumn())) {
                break;
            } else {
                currentsquare.setRow(currentsquare.getRow() + 1);
                currentsquare.setColumn(currentsquare.getColumn() - 1);
            }
        }

        return false;

    }

    public static boolean checkMoveLateral(ILayoutOwner layoutOwner, Square currentPlace, Square targetSquare) {

        // traverse down
        Square currentsquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        currentsquare.setRow(currentsquare.getRow() - 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else if (layoutOwner.isOccupied(currentsquare.getRow(), currentsquare.getColumn())) {
                break;
            } else {
                currentsquare.setRow(currentsquare.getRow() - 1);
            }
        }

        // traverse up
        currentsquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        currentsquare.setRow(currentsquare.getRow() + 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else if (layoutOwner.isOccupied(currentsquare.getRow(), currentsquare.getColumn())) {
                break;
            } else {
                currentsquare.setRow(currentsquare.getRow() + 1);
            }
        }

        // traverse left
        currentsquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        currentsquare.setColumn(currentsquare.getColumn() - 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else if (layoutOwner.isOccupied(currentsquare.getRow(), currentsquare.getColumn())) {
                break;
            } else {
                currentsquare.setColumn(currentsquare.getColumn() - 1);
            }
        }

        // traverse right
        currentsquare = new Square(currentPlace.getRow(), currentPlace.getColumn());
        currentsquare.setColumn(currentsquare.getColumn() + 1);
        while (isValidSquare(currentsquare)) {
            if (currentsquare.equals(targetSquare)) {
                return true;
            } else if (layoutOwner.isOccupied(currentsquare.getRow(), currentsquare.getColumn())) {
                break;
            } else {
                currentsquare.setColumn(currentsquare.getColumn() + 1);
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

    public static boolean isKnightMoveValid(Square currentPlace, Square targetSquare) {
        int[] rowMoves = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] columnMoves = new int[]{1, -1, 2, -2, 2, -2, 1, -1};
        for (int i = 0; i < rowMoves.length; i++) {
            Square square = new Square(currentPlace.getRow() + rowMoves[i], currentPlace.getColumn() + columnMoves[i]);
            if (MoveUtil.isValidSquare(square) && square.equals(targetSquare)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isQueenMoveValid(ILayoutOwner layoutOwner, Square currentPlace, Square targetPlace) {
        return checkMoveLateral(layoutOwner, currentPlace, targetPlace) || checkMoveDiagonal(layoutOwner, currentPlace, targetPlace);
    }
}
