package org.casestudy.target.chess.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by adityabhasin on 23/09/17.
 */
public class Board {

    private Square[][] places;
    private Map<PieceColor, PieceSet> pieceSets;


    public Piece getPiece(PieceType pieceType, PieceColor pieceColor) {
        return null;
    }

    public boolean isValidMove(Move move, PieceColor pieceColor) {
        /*
        1. get current piece object --
            a. identify via valid moves
            b. identfy via disambiguity data
        2. go to piece object and get valid moves
        3. validate if the proposed move is applicable
        4. Validate if the check is valid
        5. validate if the capture is valid


         */

        PieceSet pieceSet = pieceSets.get(pieceColor);
        List<Piece> pieces = pieceSet.getPieces(move.getPieceType());
        List<Piece> possibleCandidates = new ArrayList<Piece>();
        for(Piece piece: pieces) {
            if (piece.canMoveToSquare(move.getTargetSquare())) {
                possibleCandidates.add(piece);
            }
        }
        // assume for now that listhas 1 item. We work out disambiguity later
        Piece currentPiece = possibleCandidates.get(0);
        return false;
    }

    private boolean isCheckValid(Move move, Piece currentPiece, PieceColor pieceColor) {
        // get kind of other color
        // check from target square if the king can be reached
        return false;
    }

    private boolean isCaptureValid(Move move, Piece currentPiece, PieceColor pieceColor) {
        // return true if target square is not null
        return false;
    }

    public void move(Move move, PieceColor pieceColor) {
        /*
        1. get target Piece
        2. get target square
        3. update existing Piece in targetSquare as captured
        4. update targetPiece to targetSquare
         */
        
    }

}
