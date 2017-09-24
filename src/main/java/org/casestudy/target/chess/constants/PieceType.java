package org.casestudy.target.chess.constants;

/**
 * Created by adityabhasin on 23/09/17.
 */
public enum PieceType {
    Rook("R"), Knight("N"), Bishop("B"), King("K"), Queen("Q"), Pawn("P");
    private final String shortname;

    PieceType(String shortname) {
        this.shortname = shortname;
    }

    public String getShortname() {
        return shortname;
    }

    public static PieceType decode(String shortName) {
        if (shortName == null || shortName == "") {
            return Pawn;
        } else if (shortName == "R") {
            return Rook;
        } else if (shortName == "N") {
            return Knight;
        } else if (shortName == "K") {
            return King;
        } else if (shortName == "Q") {
            return Queen;
        } else {
            return Bishop;
        }

    }
}
