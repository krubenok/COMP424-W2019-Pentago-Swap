package student_player;

import pentago_swap.PentagoBoardState;

public class MyTools {
    public static int getDepth(PentagoBoardState board) {
        int depth = 3;
        
        if (board.getAllLegalMoves().size() < 50) {
            depth = 6;
        }
        else if (board.getAllLegalMoves().size() < 80) {
            depth = 5;
        }
        else if (board.getAllLegalMoves().size() < 180) {
            depth = 4;
        }

        return depth;
    }
}