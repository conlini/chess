package org.casestudy.target.chess.main;

import org.casestudy.target.chess.core.ConsoleMoveGenerator;
import org.casestudy.target.chess.core.Game;
import org.casestudy.target.chess.core.IMoveGenerator;

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
