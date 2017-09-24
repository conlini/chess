package org.casestudy.chess.constants;

/**
 * Created by adityabhasin on 23/09/17.
 */
public enum PieceColor {
    Black("b"), White("w");

    private String colorCode;

    PieceColor(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return this.colorCode;
    }
}
