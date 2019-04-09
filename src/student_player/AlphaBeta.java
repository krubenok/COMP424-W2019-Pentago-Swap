package student_player;

import pentago_swap.PentagoBoard;
import pentago_swap.PentagoBoardState;
import pentago_swap.PentagoMove;
import boardgame.Move;
import java.util.AbstractMap;
import java.util.ArrayList;

public class AlphaBeta {

    static int maxDepth = 12;

    public static PentagoMove alphabeta(int depth, int player, PentagoBoardState board, int alpha, int beta) {
        if (depth++ == maxDepth || board.getAllLegalMoves().isEmpty()) {
            return board.getAllLegalMoves().get(0);   
        }
        if (player == PentagoBoardState.WHITE) {
            return max(depth, player, board, alpha, beta); 
        }
        else {
            return min(depth, player, board, alpha, beta); 
        }
    }

    public static PentagoMove max(int depth, int player, PentagoBoardState board, int alpha, int beta) {
        PentagoMove bestMove =  null;
        // int bestScore = Integer.MIN_VALUE;

        for (PentagoMove move : board.getAllLegalMoves()) {
            PentagoBoardState clone = (PentagoBoardState) board.clone();
            clone.processMove(move);
            int score = Heuristic.lines(clone, player) ;

            if (score > alpha) {
                alpha = score;  
                // bestScore = score;
                bestMove = move;
            }
            
            if (alpha >= beta) {
                break;
            }
        }
        return bestMove;
    }

    public static PentagoMove min(int depth, int player, PentagoBoardState board, int alpha, int beta) {
        PentagoMove bestMove =  null;
        // int bestScore = Integer.MIN_VALUE;

        for (PentagoMove move : board.getAllLegalMoves()) {
            PentagoBoardState clone = (PentagoBoardState) board.clone();
            clone.processMove(move);
            int score = Heuristic.lines(clone, player) ;

            if (score < beta) {
                beta = score;  
                // bestScore = score;
                bestMove = move;
            }
            
            if (alpha >= beta) {
                break;
            }
        }
        return bestMove;
    }

    public static AbstractMap.SimpleEntry<Integer, PentagoMove> minimax(int depth, int player, PentagoBoardState pbs) {
        ArrayList<PentagoMove> nextmoves = pbs.getAllLegalMoves();

        int bestScore = (player == PentagoBoardState.WHITE) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        PentagoMove bestMove = nextmoves.get(0);

        if (nextmoves.isEmpty() || depth==0) {
            bestScore = Heuristic.lines(pbs, player);
        }  
        else {
            for (PentagoMove currentmove: nextmoves){
                PentagoBoardState boardwMove = (PentagoBoardState)pbs.clone();
                boardwMove.processMove(currentmove);
                if (player == PentagoBoardState.WHITE) {
                    currentScore = minimax(depth -1, PentagoBoardState.BLACK, boardwMove).getKey();
                    if(currentScore > bestScore) {
                        bestScore = currentScore;
                        bestMove = currentmove;
                    }
                }
                else {
                    currentScore = minimax(depth -1, PentagoBoardState.WHITE, boardwMove).getKey();
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestMove = currentmove;
                    }
                }
            }
        }
        return new AbstractMap.SimpleEntry<>(bestScore, bestMove);
    }

    public static AbstractMap.SimpleEntry<Integer, PentagoMove> abp(int depth, int player, PentagoBoardState pbs, int alpha, int beta) {
        ArrayList<PentagoMove> nextmoves = pbs.getAllLegalMoves();

        int bestScore;
        PentagoMove bestMove = nextmoves.get(0);


        if (nextmoves.isEmpty() || depth==0) {
            bestScore = Heuristic.lines(pbs, player);
            return new AbstractMap.SimpleEntry<>(bestScore, bestMove);
        }
        else {
            for (PentagoMove currentmove: nextmoves){
                PentagoBoardState boardwMove = (PentagoBoardState)pbs.clone();
                boardwMove.processMove(currentmove);
                if (player == PentagoBoardState.WHITE) {
                    bestScore = abp(depth -1, PentagoBoardState.BLACK, boardwMove, alpha, beta).getKey();
                    if(bestScore > alpha) {
                        alpha = bestScore;
                        bestMove = currentmove;
                    }
                }
                else {
                    bestScore = abp(depth -1, PentagoBoardState.WHITE, boardwMove, alpha, beta).getKey();
                    if (bestScore < beta) {
                        beta = bestScore;
                        bestMove = currentmove;
                    }
                }
                if (alpha >= beta) {
                    //System.out.println("pruned!");
                    break;
                }
            }
            return new AbstractMap.SimpleEntry<>((player == PentagoBoardState.WHITE) ? alpha: beta, bestMove);
        }
    }
}