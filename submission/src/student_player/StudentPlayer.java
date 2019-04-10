package student_player;

import boardgame.Board;
import boardgame.Move;
import student_player.MyTools;
import pentago_swap.PentagoPlayer;
import pentago_swap.PentagoBoardState;
import pentago_swap.PentagoMove;

/** A player file submitted by a student. */
public class StudentPlayer extends PentagoPlayer {

    /**
     * You must modify this constructor to return your student number. This is
     * important, because this is what the code that runs the competition uses to
     * associate you with your agent. The constructor should do nothing else.
     */
    public StudentPlayer() {
        super("260667187");
    }

    /**
     * This is the primary method that you need to implement. The ``boardState``
     * object contains the current state of the game, which your agent must use to
     * make decisions.
     */
    public Move chooseMove(PentagoBoardState boardState) {
        // You probably will make separate functions in MyTools.
        // For example, maybe you'll need to load some pre-processed best opening
        // strategies... 
        PentagoMove move = boardState.getAllLegalMoves().get(0);
        PentagoMove start1 = new PentagoMove(2, 3, PentagoBoardState.Quadrant.TR, PentagoBoardState.Quadrant.BL, 0);
        PentagoMove start2 = new PentagoMove(3, 2, PentagoBoardState.Quadrant.TR, PentagoBoardState.Quadrant.BL, 0);
        PentagoMove start3 = new PentagoMove(0, 2, PentagoBoardState.Quadrant.BL, PentagoBoardState.Quadrant.BR, 0);
        PentagoMove start4 = new PentagoMove(2, 3, PentagoBoardState.Quadrant.TR, PentagoBoardState.Quadrant.BL, 1);
        PentagoMove start5 = new PentagoMove(2, 3, PentagoBoardState.Quadrant.TR, PentagoBoardState.Quadrant.BL, 1);
        PentagoMove start6 = new PentagoMove(2, 3, PentagoBoardState.Quadrant.TR, PentagoBoardState.Quadrant.BL, 1);

        if (boardState.getTurnNumber() < 2 && boardState.getTurnPlayer() == 0) {
            if (boardState.isLegal(start1)) {
                move = start1;
            }
            else if (boardState.isLegal(start2)) {
                move = start2;
            }
            else if (boardState.isLegal(start3)) {
                move = start3;
            }
        }
        else if (boardState.getTurnNumber() < 2 && boardState.getTurnPlayer() == 1) {
            if (boardState.isLegal(start4)) {
                move = start4;
            }
            else if (boardState.isLegal(start5)) {
                move = start5;
            }
            else if (boardState.isLegal(start6)) {
                move = start6;
            }
        }
        else {
            move = AlphaBeta.alphabeta(MyTools.getDepth(boardState), boardState.getTurnPlayer(), boardState, Integer.MIN_VALUE, Integer.MAX_VALUE).getValue();
        }
        return move;
    }
} 