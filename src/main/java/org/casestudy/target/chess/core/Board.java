package org.casestudy.target.chess.core;

import org.casestudy.target.chess.constants.PieceColor;
import org.casestudy.target.chess.constants.PieceType;
import org.casestudy.target.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by adityabhasin on 23/09/17.
 */

public class Board {

    private Square[][] places;
    private Map<PieceColor, PieceSet> pieceSets;
    private PieceColor pieceOnTop;


    public Piece getPiece(PieceType pieceType, PieceColor pieceColor) {
        return null;
    }

    public MoveValidity isValidMove(Move move, PieceColor pieceColor) {
        /*
        1. get current piece object --
            a. identify via valid moves
            b. identfy via disambiguity data
        2. go to piece object and get valid moves
        3. validate if the proposed move is applicable
        4. Validate if the check is valid
        5. validate if the capture is valid
         */
        // check if target square is occupied by the same color, if yes then dont bother computing canMove unless castling
        if(move.getTargetSquare().getOccupiedPiece() != null && move.getTargetSquare().getOccupiedPiece().getPieceColor() == pieceColor) {
            return new MoveValidity(false, null);
        }
        PieceSet pieceSet = pieceSets.get(pieceColor);
        List<Piece> pieces = pieceSet.getPieces(move.getPieceType());
        List<Piece> possibleCandidates = new ArrayList<Piece>();
        for(Piece piece: pieces) {

            if (piece.canMoveToSquare(move.getTargetSquare())) {
                possibleCandidates.add(piece);
            }
        }
        // assume for now that list has 1 item. We work out disambiguity later
        Piece currentPiece = possibleCandidates.get(0);

        if(isCheckValid(move, currentPiece, pieceColor) && isCaptureValid(move, currentPiece, pieceColor)) {
            return new MoveValidity(true, currentPiece);
        } else {
            return new MoveValidity(false, currentPiece);
        }
    }

    private boolean isCheckValid(Move move, Piece currentPiece, PieceColor pieceColor) {
        // get king of other color
        // check from target square if the king can be reached
        if(move.isAttemptToCheck()) {
            PieceColor other = pieceColor == PieceColor.White ? PieceColor.Black : PieceColor.White;
            Piece king = pieceSets.get(other).getPieces(PieceType.King).get(0);
            return currentPiece.canMoveToSquare(king.getCurrentPlace());
        } else {
            return true;
        }
    }

    private boolean isCaptureValid(Move move, Piece currentPiece, PieceColor pieceColor) {
        // return true if target square is not null
        if(move.isAttemptToCapture()) {
            return !(this.places[move.getTargetSquare().getX()][move.getTargetSquare().getY()].getOccupiedPiece() == null);
        } else {
            return true;
        }
    }

    // TODO: Should move capture details like en passant and return that to the Game. Possible even return "Captured Piece Type info"
    // TODO: castling
    public void move(Move move, PieceColor pieceColor, Piece targetPiece) {
        /*
        1. get target Piece
        2. get target square
        3. update existing Piece in targetSquare as captured
        4. update targetPiece to targetSquare
         */
        Square targetSquare = this.places[move.getTargetSquare().getX()][move.getTargetSquare().getY()];
        targetSquare.getOccupiedPiece().markCaptured();
        targetSquare.setOccupiedPiece(targetPiece);
        targetPiece.setHasMoved(true);

    }

    public boolean hasKingMoved(PieceColor pieceColor) {
        //get king, check return hasMoved
        return false;
    }

    public boolean hasKingSideRookMoved(PieceColor pieceColor) {
        // check if king side square is a rook and return hasMoved
        return false;
    }

    public boolean hasQueenSideRookMoved(PieceColor pieceColor) {
        // check if queen side square is a rook and return hasMoved
        return false;
    }

    // TODO complete this
    public void init() {

    }

    // TODO: complete this
    public String getBoardAsFEN() {
        return null;
    }

    public static class MoveValidity {
        private final Boolean isValidMove;
        private final Piece targetPiece;

        public MoveValidity(Boolean isValidMove, Piece targetPiece) {
            this.isValidMove = isValidMove;
            this.targetPiece = targetPiece;
        }

        public Boolean isValidMove() {
            return isValidMove;
        }

        public Piece getTargetPiece() {
            return targetPiece;
        }
    }
}

