package org.casestudy.chess.core;

import org.casestudy.chess.constants.MoveType;
import org.casestudy.chess.constants.MovementDirection;
import org.casestudy.chess.constants.PieceColor;
import org.casestudy.chess.constants.PieceType;
import org.casestudy.chess.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adityabhasin on 23/09/17.
 */

public class Board {

    private Square[][] places = new Square[8][8];
    private Map<PieceColor, PieceSet> pieceSets;
    private PieceColor pieceOnTop; // we may not need this if we decide always black on top and init the pawn directions accordingly


    public MoveValidity isValidMove(Move nextMove, PieceColor pieceColor) {
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
        if (nextMove.getMoveType() == MoveType.Normal) {
            NormalMove move = (NormalMove) nextMove;
            if (move.getTargetSquare().getOccupiedPiece() != null && move.getTargetSquare().getOccupiedPiece().getPieceColor() == pieceColor) {
                return new MoveValidity(false, null);
            }
            PieceSet pieceSet = pieceSets.get(pieceColor);
            List<Piece> pieces = pieceSet.getPieces(move.getPieceType());
            List<Piece> possibleCandidates = new ArrayList<Piece>();
            for (Piece piece : pieces) {

                if (piece.canMoveToSquare(move.getTargetSquare())) {
                    possibleCandidates.add(piece);
                }
            }
            if(possibleCandidates.isEmpty()) {
                return new MoveValidity(false, null);
            }
            // assume for now that list has 1 item. We work out disambiguity later
            Piece currentPiece = possibleCandidates.get(0);

            if (isCheckValid(move, currentPiece, pieceColor) && isCaptureValid(move, currentPiece, pieceColor)) {
                return new MoveValidity(true, currentPiece);
            } else {
                return new MoveValidity(false, currentPiece);
            }
        } else {
            return new MoveValidity(false, null);
        }
    }

    private boolean isCheckValid(NormalMove move, Piece currentPiece, PieceColor pieceColor) {
        // get king of other color
        // check from target square if the king can be reached
        if (move.isAttemptToCheck()) {
            PieceColor other = pieceColor == PieceColor.White ? PieceColor.Black : PieceColor.White;
            Piece king = pieceSets.get(other).getPieces(PieceType.King).get(0);
            return currentPiece.canMoveToSquare(king.getCurrentPlace());
        } else {
            return true;
        }
    }

    private boolean isCaptureValid(NormalMove move, Piece currentPiece, PieceColor pieceColor) {
        // return true if target square is not null
        if (move.isAttemptToCapture()) {
            return !(this.places[move.getTargetSquare().getX()][move.getTargetSquare().getY()].getOccupiedPiece() == null);
        } else {
            return true;
        }
    }

    // TODO: 2. Should move capture details like en passant and return that to the Game. Possible even return "Captured Piece Type info"
    // TODO: 3. castling
    public void move(NormalMove move, PieceColor pieceColor, Piece targetPiece) {
        /*
        1. get target Piece
        2. get target square
        3. update existing Piece in targetSquare as captured
        4. update targetPiece to targetSquare
         */
        targetPiece.getCurrentPlace().setOccupiedPiece(null);
        Square targetSquare = this.places[move.getTargetSquare().getX()][move.getTargetSquare().getY()];
        if (targetSquare.getOccupiedPiece() != null) {
            targetSquare.getOccupiedPiece().markCaptured();
        }
        targetSquare.setOccupiedPiece(targetPiece);
        targetPiece.moveTo(targetSquare);

    }

    // TODO 3. castling
    public boolean hasKingMoved(PieceColor pieceColor) {
        //get king, check return hasMoved
        return false;
    }

    // TODO 3. castling
    public boolean hasKingSideRookMoved(PieceColor pieceColor) {
        // check if king side square is a rook and return hasMoved
        return false;
    }

    // TODO 3. castling
    public boolean hasQueenSideRookMoved(PieceColor pieceColor) {
        // check if queen side square is a rook and return hasMoved
        return false;
    }

    public void init() {
        // init each square
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                places[x][y] = new Square(x, y);
            }
        }
        // init each piece and pieceSet
        pieceSets = new HashMap<PieceColor, PieceSet>();
        PieceSet ps = new PieceSet(PieceColor.Black);
        pieceSets.put(PieceColor.Black, ps);
        ps.addPiece(new Rook(PieceColor.Black, places[0][0]));
        ps.addPiece(new Knight(PieceColor.Black, places[0][1]));
        ps.addPiece(new Bishop(PieceColor.Black, places[0][2]));
        ps.addPiece(new Queen(PieceColor.Black, places[0][3]));
        ps.addPiece(new King(PieceColor.Black, places[0][4]));
        ps.addPiece(new Bishop(PieceColor.Black, places[0][5]));
        ps.addPiece(new Knight(PieceColor.Black, places[0][6]));
        ps.addPiece(new Rook(PieceColor.Black, places[0][7]));

        for (int i = 0; i < 8; i++) {
            ps.addPiece(new Pawn(PieceColor.Black, MovementDirection.Down, places[1][i]));
        }

        // white ps
        ps = new PieceSet(PieceColor.White);
        pieceSets.put(PieceColor.White, ps);
        ps.addPiece(new Rook(PieceColor.White, places[7][0]));
        ps.addPiece(new Knight(PieceColor.White, places[7][1]));
        ps.addPiece(new Bishop(PieceColor.White, places[7][2]));
        ps.addPiece(new Queen(PieceColor.White, places[7][3]));
        ps.addPiece(new King(PieceColor.White, places[7][4]));
        ps.addPiece(new Bishop(PieceColor.White, places[7][5]));
        ps.addPiece(new Knight(PieceColor.White, places[7][6]));
        ps.addPiece(new Rook(PieceColor.White, places[7][7]));

        for (int i = 0; i < 8; i++) {
            ps.addPiece(new Pawn(PieceColor.White, MovementDirection.Up, places[6][i]));
        }
    }


    public String getBoardAsFEN() {
        StringBuilder board = new StringBuilder();
        for (int x = 0; x < 8; x++) {
            StringBuilder row = new StringBuilder();
            int emptySlots = 0;
            for (int y = 0; y < 8; y++) {
                if (places[x][y].getOccupiedPiece() == null) {
                    row.append(1);
                    emptySlots++;
                } else {
                    row.append(places[x][y].getOccupiedPiece().getPieceTypeCode());
                }
            }
            if (emptySlots == 8) {
                board.append(8);
            } else {
                board.append(row.toString());
            }
            if(x != 7) {
                board.append("/");
            }
        }

        return board.toString();
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

