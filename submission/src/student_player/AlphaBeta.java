package student_player;

import pentago_swap.PentagoBoardState;
import pentago_swap.PentagoMove;
import java.util.AbstractMap;
import java.util.ArrayList;
public class AlphaBeta {

    public static long timeLimit = System.currentTimeMillis() + 1900; 

    public static AbstractMap.SimpleEntry<Integer,PentagoMove> alphabeta(int depth, int player, PentagoBoardState board, int alpha, int beta) {
        ArrayList<PentagoMove> moves = board.getAllLegalMoves();
        PentagoMove bestMove =  moves.get(0);
        int bestScore = Heuristic.eval(board, player);
        AbstractMap.SimpleEntry<Integer,PentagoMove> bestPair = new AbstractMap.SimpleEntry<>(bestScore, bestMove); 
        if (depth <= 0 || board.getAllLegalMoves().isEmpty() || System.currentTimeMillis() >= timeLimit){
            return bestPair;
        }
        else if (player == PentagoBoardState.WHITE) {
            bestPair = max(depth, 1, board, alpha, beta); 
        }
        else {
            bestPair = min(depth, 0, board, alpha, beta); 
        }
        return bestPair;
    }

    public static AbstractMap.SimpleEntry<Integer,PentagoMove> max(int depth, int player, PentagoBoardState board, int alpha, int beta) {
        int score = Heuristic.eval(board, player);
        PentagoMove bestMove = board.getAllLegalMoves().get(0);
        for (PentagoMove move : board.getAllLegalMoves()) {
            PentagoBoardState clone = (PentagoBoardState) board.clone();
            clone.processMove(move);
            score = alphabeta(depth-1, player, clone, alpha, beta).getKey();
            if (score > alpha) {
                alpha = score;  
                bestMove = move;
            }
            if (alpha >= beta) {
                break;
            }
        }
        return new AbstractMap.SimpleEntry<Integer, PentagoMove>(score, bestMove);
    }

    public static AbstractMap.SimpleEntry<Integer,PentagoMove> min(int depth, int player, PentagoBoardState board, int alpha, int beta) {
        int score = Heuristic.eval(board, player);
        PentagoMove bestMove = board.getAllLegalMoves().get(0);
        for (PentagoMove move : board.getAllLegalMoves()) {
            PentagoBoardState clone = (PentagoBoardState) board.clone();
            clone.processMove(move);
            score = alphabeta(depth-1, player, clone, alpha, beta).getKey();
            if (score < beta) {
                beta = score;  
                bestMove = move;
            }
            if (alpha >= beta) { 
                break;
            }
        }
        return new AbstractMap.SimpleEntry<Integer, PentagoMove>(score, bestMove);
    }
}