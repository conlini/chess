package org.casestudy.chess.constants;

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
        if (shortName == null || shortName.equals("") ){
            return Pawn;
        } else if (shortName.equals("R")) {
            return Rook;
        } else if (shortName.equals("N")) {
            return Knight;
        } else if (shortName.equals("K")){
            return King;
        } else if (shortName.equals("Q")) {
            return Queen;
        } else {
            return Bishop;
        }

    }
}
