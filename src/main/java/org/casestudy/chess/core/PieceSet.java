package org.casestudy.chess.core;

import org.casestudy.chess.constants.PieceColor;
import org.casestudy.chess.constants.PieceType;
import org.casestudy.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adityabhasin on 23/09/17.
 */
public class PieceSet {
    private Map<PieceType, List<Piece>> pieces;
    private final PieceColor color;

    public PieceSet(PieceColor color) {
        this.color = color;
        this.pieces = new HashMap<PieceType, List<Piece>>();
    }

    public List<Piece> getPieces(PieceType pieceType) {
        return pieces.get(pieceType);
    }

    public void addPiece(Piece piece) {
        List<Piece> typePieces = this.pieces.get(piece.getPieceType());
        if (typePieces == null) {
            typePieces = new ArrayList<Piece>();
            this.pieces.put(piece.getPieceType(), typePieces);
        }
        typePieces.add(piece);
    }
}
