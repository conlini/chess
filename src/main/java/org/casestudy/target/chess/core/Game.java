package org.casestudy.target.chess.core;

import org.casestudy.target.chess.constants.PieceColor;
import org.casestudy.target.chess.helpers.MoveDecoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityabhasin on 23/09/17.
 */

//TODO: 3. castling
public class Game {

    private final Board board;

    private PieceColor currentPlayer;
    private List<Move> moveList; // do we care for moveList if this is just a move validator app?
    private int fullMoveCount = 1;

    public Game() {
        this.board = new Board();
        this.moveList = new ArrayList<Move>();
    }


    // TODO 1. write a main that starts the game. Perhaps we should wire in a MoveString provider
    public void play() {
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
        Board.MoveValidity moveValidity = board.isValidMove(move, currentPlayer);
        if (moveValidity.isValidMove()) {
            board.move((NormalMove) move, currentPlayer, moveValidity.getTargetPiece());
            this.moveList.add(move);
            this.promoteNextPlayer();
            this.printGameFEN();
        } else {
            // report error
        }


    }

    private void printGameFEN() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.board.getBoardAsFEN());
        builder.append(" ");
        builder.append(this.currentPlayer.getColorCode());
        builder.append(" ");
        if (canBlackCastleKingSide() || canBlackCastleQueenSide() || canWhiteCastleKingSide() || canWhiteCastleQueenSide()) {
            if (canWhiteCastleKingSide()) {
                builder.append("K");
            }
            if (canWhiteCastleQueenSide()) {
                builder.append("Q");
            }
            if (canBlackCastleKingSide()) {
                builder.append("k");
            }
            if (canBlackCastleQueenSide()) {
                builder.append("q");
            }
        } else {
            builder.append("-");
        }
        builder.append(" ");
        builder.append("-"); // en passant square
        builder.append(" ");
        builder.append(0);// half move clock
        builder.append(" ");
        builder.append(fullMoveCount);
        System.out.println(builder.toString());

    }

    private void promoteNextPlayer() {
        this.currentPlayer = currentPlayer == PieceColor.White ? PieceColor.Black : PieceColor.White;
        if (this.currentPlayer == PieceColor.Black) {
            this.fullMoveCount++;
        }
    }

    private boolean canWhiteCastleKingSide() {
        // for  white, check if king has moved and king side rook has moved
        return !board.hasKingMoved(PieceColor.White) && !board.hasKingSideRookMoved(PieceColor.White);
    }

    private boolean canWhiteCastleQueenSide() {
        // for  white, check if king has moved and queen side rook has moved
        return !board.hasKingMoved(PieceColor.White) && !board.hasQueenSideRookMoved(PieceColor.White);
    }

    private boolean canBlackCastleKingSide() {
        // for  black, check if king has moved and king side rook has moved
        return !board.hasKingMoved(PieceColor.Black) && !board.hasKingSideRookMoved(PieceColor.Black);
    }

    private boolean canBlackCastleQueenSide() {
        // for  white, check if king has moved and queen side rook has moved
        return !board.hasKingMoved(PieceColor.Black) && !board.hasQueenSideRookMoved(PieceColor.Black);
    }

    public void initGame() {
        this.board.init();
        this.currentPlayer = PieceColor.White;
        this.moveList.clear();
        this.fullMoveCount = 0;
        this.printGameFEN();
    }
}
