package org.casestudy.chess.pieces;

import org.casestudy.chess.core.Game;
import org.casestudy.chess.core.IMoveGenerator;
import org.casestudy.chess.core.IMoveOutCollector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adbhasin on 25/09/17.
 */
public class GameTest {

    private class MockMoveProvider implements IMoveGenerator {

        public String getNextMove() {
            return null;
        }
    }

    private class MockMoveOutCollector implements IMoveOutCollector {

        private List<String> output = new ArrayList<String>();

        public void collectOutput(String output) {
            
        }
    }
    @Test
    public void testGamePlay() {
        // init game
        // play moves
        // normal move
        // check move
        // en passant
        // castling

        IMoveOutCollector collector = new MockMoveOutCollector();
        Game g = new Game(new MockMoveProvider(),collector);
        g.initGame();
        g.play();


    }
}
