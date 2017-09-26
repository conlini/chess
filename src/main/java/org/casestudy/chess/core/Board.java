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

public class Board implements ILayoutOwner {

    private Square[][] places = new Square[9][9];
    private Map<PieceColor, PieceSet> pieceSets;
    private PieceColor pieceOnTop; // we may not need this if we decide always black on top and init the pawn directions accordingly


    public MoveValidity isValidMove(Move nextMove, PieceColor pieceColor) {
        switch (nextMove.getMoveType()) {
            case Normal: {
                return getNormalMoveValidity((NormalMove) nextMove, pieceColor);
            }
            case Castling: {
                return getCastlingMoveValidity((CastlingMove) nextMove, pieceColor);

            }
        }
        return new MoveValidity(false, null);
    }

    private MoveValidity getCastlingMoveValidity(CastlingMove nextMove, PieceColor pieceColor) {
        CastlingMove cm = nextMove;
        if (!hasKingMoved(pieceColor)) {
            switch (cm.getCastlingSide()) {
                case KingSide: {
                    if (!hasKingSideRookMoved(pieceColor) && kingSideRookCanSwap(pieceColor)) {
                        return new MoveValidity(true, null);
                    } else {
                        return new MoveValidity(false, null);
                    }
                }
                case QueenSide: {
                    if (!hasQueenSideRookMoved(pieceColor) && queenSideRookCanSwap(pieceColor)) {
                        return new MoveValidity(true, null);
                    } else {
                        return new MoveValidity(false, null);
                    }
                }
            }
        }
        return new MoveValidity(false, null);

    }

    private boolean kingSideRookCanSwap(PieceColor pieceColor) {
        int[] columnMoves = new int[]{7, 6};
        int row = pieceColor == PieceColor.White ? 1 : 8;
        for (int i = 0; i < columnMoves.length; i++) {
            if (isOccupied(row, columnMoves[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean queenSideRookCanSwap(PieceColor pieceColor) {
        int[] columnMoves = new int[]{2, 3, 4};
        int row = pieceColor == PieceColor.White ? 1 : 8;
        for (int i = 0; i < columnMoves.length; i++) {
            if (isOccupied(row, columnMoves[i])) {
                return false;
            }
        }
        return true;
    }

    private MoveValidity getNormalMoveValidity(NormalMove nextMove, PieceColor pieceColor) {
        NormalMove move = nextMove;
        if (move.getTargetSquare().getOccupiedPiece() != null && move.getTargetSquare().getOccupiedPiece().getPieceColor() == pieceColor) {
            return new MoveValidity(false, null);
        }
        PieceSet pieceSet = pieceSets.get(pieceColor);
        List<Piece> pieces = pieceSet.getPieces(move.getPieceType());
        List<Piece> possibleCandidates = new ArrayList<Piece>();
        for (Piece piece : pieces) {

            if (!piece.isCaptured() && matchesDisambiguity(move, piece) && piece.canMoveToSquare(this, move.getTargetSquare())) {
                possibleCandidates.add(piece);
            }
        }
        if (possibleCandidates.isEmpty()) {
            return new MoveValidity(false, null);
        }
        Piece currentPiece = possibleCandidates.get(0);

        if (isCheckValid(move, currentPiece, pieceColor) && isCaptureValid(move, currentPiece, pieceColor) && isPromotionValid(move, currentPiece, pieceColor)) {
            return new MoveValidity(true, currentPiece);
        } else {
            return new MoveValidity(false, currentPiece);
        }
    }

    private boolean isPromotionValid(NormalMove move, Piece currentPiece, PieceColor pieceColor) {
        if (move.getPromotedTo() == null) {
            return true;
        } else {
            if (currentPiece.getPieceType() != PieceType.Pawn) {
                return false;
            } else {
                switch (pieceColor) {
                    case Black: {
                        if (move.getTargetSquare().getRow() == 1) {
                            return true;
                        }
                    }
                    case White: {
                        if (move.getTargetSquare().getRow() == 8) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
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
            boolean answer = currentPiece.canMoveToSquare(this, king.getCurrentPlace());
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
    public void move(Move nextMove, PieceColor pieceColor, Piece targetPiece) {
        /*
        1. get target Piece
        2. get target square
        3. update existing Piece in targetSquare as captured
        4. update targetPiece to targetSquare
         */
        switch (nextMove.getMoveType()) {
            case Normal: {
                NormalMove move = (NormalMove) nextMove;
                targetPiece.getCurrentPlace().setOccupiedPiece(null);
                Square targetSquare = this.places[move.getTargetSquare().getRow()][move.getTargetSquare().getColumn()];
                if (targetSquare.getOccupiedPiece() != null) {
                    targetSquare.getOccupiedPiece().markCaptured();
                }
                //targetSquare.setOccupiedPiece(targetPiece);
                targetPiece.moveTo(targetSquare);
                if (move.getPromotedTo() != null) {
                    ((Pawn) targetPiece).promote(move.getPromotedTo());
                }
                break;
            }
            case Castling: {
                CastlingMove cm = (CastlingMove) nextMove;
                Piece king = pieceSets.get(pieceColor).getPieces(PieceType.King).get(0);
                Piece swapRook = null;
                List<Piece> rooks = pieceSets.get(pieceColor).getPieces(PieceType.Rook);
                RookLocation testLocation = cm.getCastlingSide() == CastlingSide.KingSide ? RookLocation.KingSide : RookLocation.QueenSide;
                for (Piece r : rooks) {
                    Rook rook = (Rook) r;
                    if (rook.getRookLocation() == testLocation) {
                        swapRook = rook;
                        break;
                    }
                }
                int row = pieceColor == PieceColor.White ? 1 : 8;
                int kingColumn = cm.getCastlingSide() == CastlingSide.KingSide ? 7 : 3;
                int rookColumn = cm.getCastlingSide() == CastlingSide.KingSide ? 6 : 4;
                king.getCurrentPlace().setOccupiedPiece(null);
                swapRook.getCurrentPlace().setOccupiedPiece(null);
                king.moveTo(places[row][kingColumn]);
                swapRook.moveTo(places[row][rookColumn]);

                break;
            }
        }


    }

    public boolean hasKingMoved(PieceColor pieceColor) {
        //get king, check return hasMoved
        Piece king = pieceSets.get(pieceColor).getPieces(PieceType.King).get(0);
        return king.isHasMoved();
    }

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
        for (int rown = 8; rown > 0; rown--) {
            StringBuilder row = new StringBuilder();
            int emptySlots = 0;
            for (int column = 1; column < 9; column++) {
                if (places[rown][column].getOccupiedPiece() == null) {
                    //row.append(1);
                    emptySlots++;
                } else {
                    if (emptySlots > 0) {
                        row.append(emptySlots);
                        emptySlots = 0;
                    }
                    row.append(places[rown][column].getOccupiedPiece().getPieceTypeCode());
                }
            }
            if (emptySlots > 0) {
                row.append(emptySlots);
            }
            board.append(row.toString());
            if (rown != 1) {
                board.append("/");
            }
        }

        return board.toString();
    }

    public boolean isOccupied(int row, int column) {
        return places[row][column].getOccupiedPiece() != null;
    }

    public Piece getOccupant(int row, int column) {
        return places[row][column].getOccupiedPiece();
    }

    public Square getSquare(int row, int column) {
        return places[row][column];
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

