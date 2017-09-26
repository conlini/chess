package org.casestudy.chess.core;

import org.casestudy.chess.constants.*;
import org.casestudy.chess.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adityabhasin on 23/09/17.
 */

public class Board {

    private Square[][] places = new Square[9][9];
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
        switch (nextMove.getMoveType()) {
            case Normal: {
                NormalMove move = (NormalMove) nextMove;
                // TODO FIXME this is a bad way to mark occupancy
                move.getTargetSquare().setOccupiedPiece(places[move.getTargetSquare().getRow()][move.getTargetSquare().getColumn()].getOccupiedPiece());
                if (move.getTargetSquare().getOccupiedPiece() != null && move.getTargetSquare().getOccupiedPiece().getPieceColor() == pieceColor) {
                    return new MoveValidity(false, null);
                }
                PieceSet pieceSet = pieceSets.get(pieceColor);
                List<Piece> pieces = pieceSet.getPieces(move.getPieceType());
                List<Piece> possibleCandidates = new ArrayList<Piece>();
                for (Piece piece : pieces) {

                    if (!piece.isCaptured() && matchesDisambiguity(move, piece) && piece.canMoveToSquare(move.getTargetSquare())) {
                        possibleCandidates.add(piece);
                    }
                }
                if (possibleCandidates.isEmpty()) {
                    return new MoveValidity(false, null);
                }
                // assume for now that list has 1 item. We work out disambiguity later
                Piece currentPiece = possibleCandidates.get(0);

                if (isCheckValid(move, currentPiece, pieceColor) && isCaptureValid(move, currentPiece, pieceColor)) {
                    return new MoveValidity(true, currentPiece);
                } else {
                    return new MoveValidity(false, currentPiece);
                }
            }
            case Castling: {
                CastlingMove cm = (CastlingMove) nextMove;
                if (!hasKingMoved(pieceColor)) {
                    switch (cm.getCastlingSide()) {
                        case KingSide: {
                            if (hasKingSideRookMoved(pieceColor)) {
                                return new MoveValidity(false, null);
                            } else {
                                return new MoveValidity(true, null);
                            }
                        }
                        case QueenSide: {
                            if (hasQueenSideRookMoved(pieceColor)) {
                                return new MoveValidity(false, null);
                            } else {
                                return new MoveValidity(true, null);
                            }
                        }
                    }
                }

            }
        }
        return new MoveValidity(false, null);
    }

    private boolean matchesDisambiguity(NormalMove nextMove, Piece piece) {
        if (nextMove.getDisAmbiguitySquare() == null) {
            return true; // no disambuity value
        }
        if (nextMove.getDisAmbiguitySquare().getRow() != 0 && nextMove.getDisAmbiguitySquare().getColumn() != 0) {
            if (nextMove.getDisAmbiguitySquare().getRow() == piece.getCurrentPlace().getRow() &&
                    nextMove.getDisAmbiguitySquare().getColumn() == piece.getCurrentPlace().getColumn()) {
                return true;
            } else {
                return false;
            }
        } else if (nextMove.getDisAmbiguitySquare().getColumn() != 0 &&
                nextMove.getDisAmbiguitySquare().getColumn() == piece.getCurrentPlace().getColumn()) {
            return true;
        } else if (nextMove.getDisAmbiguitySquare().getRow() != 0 &&
                nextMove.getDisAmbiguitySquare().getRow() == piece.getCurrentPlace().getRow()) {
            return true;
        }
        return false;
    }

    private boolean isCheckValid(NormalMove move, Piece currentPiece, PieceColor pieceColor) {
        // get king of other color
        // check from target square if the king can be reached
        if (move.isAttemptToCheck()) {
            PieceColor other = pieceColor == PieceColor.White ? PieceColor.Black : PieceColor.White;
            Piece king = pieceSets.get(other).getPieces(PieceType.King).get(0);
            // temporary set the currentPieces postion to the target and test if we can achieve a check from there
            Square prev = currentPiece.getCurrentPlace();
            currentPiece.moveTo(move.getTargetSquare());
            boolean answer = currentPiece.canMoveToSquare(king.getCurrentPlace());
            currentPiece.moveTo(prev);
            return answer;
        } else {
            return true;
        }
    }

    private boolean isCaptureValid(NormalMove move, Piece currentPiece, PieceColor pieceColor) {
        // return true if target square is not null
        if (move.isAttemptToCapture()) {
            return !(this.places[move.getTargetSquare().getRow()][move.getTargetSquare().getColumn()].getOccupiedPiece() == null);
        } else {
            return true;
        }
    }

    // TODO: 2. Should move capture details like en passant and return that to the Game. Possible even return "Captured Piece Type info"
    // TODO: 3. castling
    public void move(Move nextMove, PieceColor pieceColor, Piece targetPiece) {
        /*
        1. get target Piece
        2. get target square
        3. update existing Piece in targetSquare as captured
        4. update targetPiece to targetSquare
         */
        switch (nextMove.getMoveType()) {
            case Normal: {
                NormalMove move = (NormalMove)nextMove;
                targetPiece.getCurrentPlace().setOccupiedPiece(null);
                Square targetSquare = this.places[move.getTargetSquare().getRow()][move.getTargetSquare().getColumn()];
                if (targetSquare.getOccupiedPiece() != null) {
                    targetSquare.getOccupiedPiece().markCaptured();
                }
                targetSquare.setOccupiedPiece(targetPiece);
                targetPiece.moveTo(targetSquare);
                break;
            }
            default:{
                return;
            }
        }


    }

    // TODO 3. castling
    public boolean hasKingMoved(PieceColor pieceColor) {
        //get king, check return hasMoved
        Piece king = pieceSets.get(pieceColor).getPieces(PieceType.King).get(0);
        return king.isHasMoved();
    }

    // TODO 3. castling
    public boolean hasKingSideRookMoved(PieceColor pieceColor) {
        // check if king side square is a rook and return hasMoved
        List<Piece> rooks = pieceSets.get(pieceColor).getPieces(PieceType.Rook);
        for (Piece r : rooks) {
            Rook rook = (Rook) r;
            if (rook.getRookLocation() == RookLocation.KingSide) {
                return rook.isHasMoved();
            }
        }
        return false;
    }

    // TODO 3. castling
    public boolean hasQueenSideRookMoved(PieceColor pieceColor) {
        // check if queen side square is a rook and return hasMoved
        List<Piece> rooks = pieceSets.get(pieceColor).getPieces(PieceType.Rook);
        for (Piece r : rooks) {
            Rook rook = (Rook) r;
            if (rook.getRookLocation() == RookLocation.QueenSide) {
                return rook.isHasMoved();
            }
        }
        return false;
    }

    public void init() {
        // init each square
        for (int row = 1; row < 9; row++) {
            places[row] = new Square[9];
            for (int column = 1; column < 9; column++) {
                places[row][column] = new Square(row, column);
            }
        }
        // init each piece and pieceSet
        pieceSets = new HashMap<PieceColor, PieceSet>();
        PieceSet ps = new PieceSet(PieceColor.White);
        pieceSets.put(PieceColor.White, ps);
        ps.addPiece(new Rook(PieceColor.White, places[1][1], RookLocation.QueenSide));
        ps.addPiece(new Knight(PieceColor.White, places[1][2]));
        ps.addPiece(new Bishop(PieceColor.White, places[1][3]));
        ps.addPiece(new Queen(PieceColor.White, places[1][4]));
        ps.addPiece(new King(PieceColor.White, places[1][5]));
        ps.addPiece(new Bishop(PieceColor.White, places[1][6]));
        ps.addPiece(new Knight(PieceColor.White, places[1][7]));
        ps.addPiece(new Rook(PieceColor.White, places[1][8], RookLocation.KingSide));

        for (int i = 1; i < 9; i++) {
            ps.addPiece(new Pawn(PieceColor.White, MovementDirection.Down, places[2][i]));
        }

        // white ps
        ps = new PieceSet(PieceColor.Black);
        pieceSets.put(PieceColor.Black, ps);
        ps.addPiece(new Rook(PieceColor.Black, places[8][1], RookLocation.QueenSide));
        ps.addPiece(new Knight(PieceColor.Black, places[8][2]));
        ps.addPiece(new Bishop(PieceColor.Black, places[8][3]));
        ps.addPiece(new Queen(PieceColor.Black, places[8][4]));
        ps.addPiece(new King(PieceColor.Black, places[8][5]));
        ps.addPiece(new Bishop(PieceColor.Black, places[8][6]));
        ps.addPiece(new Knight(PieceColor.Black, places[8][7]));
        ps.addPiece(new Rook(PieceColor.Black, places[8][8], RookLocation.KingSide));

        for (int i = 1; i < 9; i++) {
            ps.addPiece(new Pawn(PieceColor.Black, MovementDirection.Up, places[7][i]));
        }
    }


    public String getBoardAsFEN() {
        StringBuilder board = new StringBuilder();
        for (int rown = 1; rown < 9; rown++) {
            StringBuilder row = new StringBuilder();
            int emptySlots = 0;
            for (int column = 1; column < 9; column++) {
                if (places[rown][column].getOccupiedPiece() == null) {
                    row.append(1);
                    emptySlots++;
                } else {
                    row.append(places[rown][column].getOccupiedPiece().getPieceTypeCode());
                }
            }
            if (emptySlots == 8) {
                board.append(8);
            } else {
                board.append(row.toString());
            }
            if (rown != 8) {
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

