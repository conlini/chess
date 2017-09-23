package org.casestudy.target.chess.core;

import java.util.List;
import java.util.Map;

/**
 * Created by adityabhasin on 23/09/17.
 */
public class PieceSet {
    private Map<PieceType, List<Piece>> pieces;
    private PieceColor color;

    public List<Piece> getPieces(PieceType pieceType) {
        return pieces.get(pieceType);
    }
}
