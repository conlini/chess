package org.casestudy.chess.core;

import org.casestudy.chess.constants.PieceColor;
import org.casestudy.chess.helpers.MoveDecoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityabhasin on 23/09/17.
 */

public class Game {

    private final Board board;

    private PieceColor currentPlayer;
    private List<Move> moveList; // do we care for moveList if this is just a move validator app?
    private int fullMoveCount = 1;
    private IMoveGenerator moveGenerator;
    private IMoveOutCollector outputCollector;

    public Game(IMoveGenerator moveGenerator, IMoveOutCollector outputCollector) {
        this.board = new Board();
        this.moveGenerator = moveGenerator;
        this.outputCollector = outputCollector;
        this.moveList = new ArrayList<Move>();
    }


    public void play() {
        String movestring = moveGenerator.getNextMove();
        while (movestring != null) {
            Move move = MoveDecoder.decode(movestring, board);
            Board.MoveValidity moveValidity = board.isValidMove(move, currentPlayer);
            if (moveValidity.isValidMove()) {
                board.move(move, currentPlayer, moveValidity.getTargetPiece());
                this.moveList.add(move);
                this.promoteNextPlayer();
                this.printGameFEN();
                if (this.currentPlayer == PieceColor.White) {
                    this.fullMoveCount += 1;
                }
            } else {
                // report error
                this.outputCollector.collectOutput(String.format("Invalid Move. Player %s, Move %s", this.currentPlayer.getColorCode(), movestring));
                // promote the player for now as we need to handle tests
                this.promoteNextPlayer();
            }
            movestring = moveGenerator.getNextMove();
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
        this.outputCollector.collectOutput(builder.toString());

    }

    private void promoteNextPlayer() {
        this.currentPlayer = currentPlayer == PieceColor.White ? PieceColor.Black : PieceColor.White;
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
        this.fullMoveCount = 1;
        this.printGameFEN();
    }
}
