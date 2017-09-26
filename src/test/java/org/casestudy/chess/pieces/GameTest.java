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

        IMoveGenerator generator = new MockMoveProvider( "e4","e5", "Nf3", "d6",
                 "d4","Bg4",  "d4xe5","Bxf3",
                 "Qxf3", "d6xe5", "Bc4","Nf6", "Qb3", "Qe7",
                 "Nc3","c6", "Bg5", "b5", "Nxb5", "c6xb5",
                 "Bxb5+","Nd7", "0-0-0", "Rd8");
        MockMoveOutCollector collector = new MockMoveOutCollector();
        Game g = new Game(generator, collector);
        g.initGame();
        g.play();
        for(String out: collector.getOutputs()) {
            System.out.println(out);
        }

    }
}
