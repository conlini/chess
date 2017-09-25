package org.casestudy.chess.core;

/**
 * Created by adbhasin on 25/09/17.
 */
public class ConsoleOutputCollector implements IMoveOutCollector {
    public void collectOutput(String output) {
        System.out.println(output);
    }
}
