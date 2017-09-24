package org.casestudy.chess.main;

import org.casestudy.chess.core.ConsoleMoveGenerator;
import org.casestudy.chess.core.Game;
import org.casestudy.chess.core.IMoveGenerator;

/**
 * Created by adityabhasin on 24/09/17.
 */
public class Main {

    public static void main(String[] args) {
        IMoveGenerator generator = new ConsoleMoveGenerator();
        Game g = new Game(generator);
        g.initGame();
        g.play();
    }
}
