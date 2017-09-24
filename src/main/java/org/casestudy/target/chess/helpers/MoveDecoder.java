package org.casestudy.target.chess.helpers;

import org.casestudy.target.chess.core.CastlingMove;
import org.casestudy.target.chess.core.Move;
import org.casestudy.target.chess.core.NormalMove;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by adityabhasin on 23/09/17.
 */
public class MoveDecoder {

    // Patern - PieceType|Capture|File|Rank|Check
    private static Pattern p = Pattern.compile("([KQBNR]?)(x?)([a-h])([1-8])(\\+?)");

    public static Move decode(String moveString) {
        if (moveString.startsWith("0-0")) {
            // castling move
            return new CastlingMove();
        } else {
            Matcher m = p.matcher(moveString);
            if(m.find()) {
                String pieceType = m.group(1);
                String capture = m.group(2);
                String file = m.group(3);
                String rank = m.group(4);
                String check = m.group(5);
                return new NormalMove().setPieceType(m.group(1)).setAttemptToCapture(m.group(2)).
                        setTargetSquare(m.group(3), m.group(4)).setAttemptToCheck(m.group(5));
            }
        }
        return null;
    }

}
