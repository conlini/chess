package org.casestudy.chess.core;

import java.util.Scanner;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class ConsoleMoveGenerator implements IMoveGenerator {

    private Scanner s;

    public ConsoleMoveGenerator() {
        this.s = new Scanner(System.in);
    }

    public String getNextMove() {
        if(s.hasNext()) {
            return s.nextLine();
        }
        return null;
    }
}
