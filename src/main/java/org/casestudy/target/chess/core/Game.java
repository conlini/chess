package org.casestudy.target.chess.core;

import java.util.List;

/**
 * Created by adityabhasin on 23/09/17.
 */
public class Game {
    private Board board;
    private Player[] players; // do we care for this??
    private PieceColor currentPlayer;
    private List<Move> moveList;
    private int fullMoveCount;


    private void play() {
        // get move from command line
        /* decode move
            1. get piece type // default to Pawn --- we need a move decoder
            2. Identify and validate if Piece can move to this place - go to board with Move and player
            3. if check - validate that check can be made
            3.b if capture - validate that capture can be makde
            4. update the board - udate piece location, update piece capture
            5. update player status - promote next player
          */

        String movestring = ""; // this will be from command line
        Move move = MoveDecoder.decode(movestring);
        if(board.isValidMove(move, currentPlayer)) {
            board.move(move, currentPlayer);
            this.moveList.add(move);
            this.promoteNextPlayer();
            this.printGameFEN();
        } else {
            // report error
        }


    }

    private void printGameFEN() {

    }

    private void promoteNextPlayer() {
        this.currentPlayer = currentPlayer == PieceColor.White? PieceColor.Black: PieceColor.White;
        if (this.currentPlayer == PieceColor.Black) {
            this.fullMoveCount++;
        }
    }

    private boolean canWhiteCastleKingSide() {
        // for  white, check if king has moved and king side rook has moved
        return false;
    }

    private boolean canWhiteCastleQueenSide() {
        // for  white, check if king has moved and queen side rook has moved
        return false;
    }

    private boolean canBlackCastleKingSide() {
        // for  black, check if king has moved and king side rook has moved
        return false;
    }

    private boolean canBlackCastleQueenSide() {
        // for  white, check if king has moved and queen side rook has moved
        return false;
    }
}
