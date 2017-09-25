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

        String[] moves;
        int index = 0;

        public MockMoveProvider(String... moves) {
            this.moves = moves;
        }

        public String getNextMove() {
            if (index < moves.length) {
                String next = moves[index];
                index += 1;
                return next;
            }

            return null;
        }
    }

    private class MockMoveOutCollector implements IMoveOutCollector {

        List<String> outputs = new ArrayList<String>();

        public void collectOutput(String output) {
            this.outputs.add(output);
        }

        public List<String> getOutputs() {
            return outputs;
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

        IMoveGenerator generator = new MockMoveProvider("e5", "e4", "d6", "Nf3",
                "Bg4", "d4", "Bxf3", "d4xe5",
                "d6xe5", "Qxf3", "Nf6", "Bc4", "Qe7", "Qb3",
                "c6", "Nc3", "b5", "Bg5", "c6xb5", "Nxb5",
                "Nd7", "Bxb5+", "Rd8", "0-0-0");
        MockMoveOutCollector collector = new MockMoveOutCollector();
        Game g = new Game(generator, collector);
        g.initGame();
        g.play();
        for(String out: collector.getOutputs()) {
            System.out.println(out);
        }

    }
}
