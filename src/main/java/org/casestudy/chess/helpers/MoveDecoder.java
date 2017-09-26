package org.casestudy.chess.helpers;

import org.casestudy.chess.core.CastlingMove;
import org.casestudy.chess.core.ILayoutOwner;
import org.casestudy.chess.core.Move;
import org.casestudy.chess.core.NormalMove;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by adityabhasin on 23/09/17.
 */
public class MoveDecoder {

    // Patern - PieceType|Capture|File|Rank|Check
    //private static Pattern p = Pattern.compile("([KQBNR]?)(x?)([a-h])([1-8])(\\+?)");
    private static Pattern p = Pattern.compile("([KQBNR]?)([a-h]?)([1-8]?)(x?)([a-h])([1-8])(\\+?)");

    public static Move decode(String moveString, ILayoutOwner layoutOwner) {
        if (moveString.startsWith("0-0")) {
            // castling move
            return new CastlingMove(moveString);
        } else {

            Matcher m = p.matcher(moveString);
            if (m.find()) {
                String pieceType = m.group(1);
                String disFile = m.group(2);
                String disRank = m.group(3);
                String capture = m.group(4);
                String file = m.group(5);
                String rank = m.group(6);
                String check = m.group(7);

                return new NormalMove().setPieceType(pieceType).setAttemptToCapture(capture).
                        setTargetSquare(file, rank, layoutOwner).setAttemptToCheck(check).setDisambiguitySquare(disFile, disRank);
            }
        }
        return null;
    }


}
