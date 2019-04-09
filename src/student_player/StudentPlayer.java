package student_player;

import boardgame.Move;
import student_player.MyTools;
import pentago_swap.PentagoPlayer;
import pentago_swap.PentagoBoardState;

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
        long start = System.currentTimeMillis();
        Move myMove = ABPrune.alphabeta(0, boardState.getTurnPlayer(), boardState, Integer.MIN_VALUE, Integer.MAX_VALUE);
        long end = System.currentTimeMillis();
        System.out.println("move took " + (double)(end-start)/1000 + "s");
        
        return myMove;
    }
} 