package org.casestudy.chess.main;

import org.casestudy.chess.core.ConsoleMoveGenerator;
import org.casestudy.chess.core.ConsoleOutputCollector;
import org.casestudy.chess.core.Game;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class Main {

    public static void main(String[] args) {
        Game g = new Game(new ConsoleMoveGenerator(), new ConsoleOutputCollector());
        g.initGame();
        g.play();
    }
}
