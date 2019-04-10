package student_player;

import pentago_swap.PentagoBoardState;
import pentago_swap.PentagoMove;
import java.util.AbstractMap;
import java.util.ArrayList;

public class AlphaBeta {

    public static AbstractMap.SimpleEntry<Integer,PentagoMove> alphabeta(int depth, int player, PentagoBoardState board, int alpha, int beta) {
        int bestScore = Heuristic.lines(board, player);
        ArrayList<PentagoMove> moves = board.getAllLegalMoves();
        // System.out.println("Alpha: "+alpha+" Beta: "+beta);

        if (depth <= 0 || board.getAllLegalMoves().isEmpty()) {
            return new AbstractMap.SimpleEntry<>(bestScore, moves.get(0));
        }
        else if (player == PentagoBoardState.WHITE) {
            // System.out.println("running max with depth:"+ depth--);
            return max(depth-1, 1, board, alpha, beta); 
        }
        else {
            // System.out.println("running min with depth:"+ depth--);
            return min(depth-1, 0, board, alpha, beta); 
        }
    }

    public static AbstractMap.SimpleEntry<Integer,PentagoMove> max(int depth, int player, PentagoBoardState board, int alpha, int beta) {
        int score = Heuristic.lines(board, player);
        PentagoMove bestMove = board.getAllLegalMoves().get(0);
        for (PentagoMove move : board.getAllLegalMoves()) {
            PentagoBoardState clone = (PentagoBoardState) board.clone();
            clone.processMove(move);
            score = alphabeta(depth, player, clone, alpha, beta).getKey();
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
        int score = Heuristic.lines(board, player);
        PentagoMove bestMove = board.getAllLegalMoves().get(0);
        for (PentagoMove move : board.getAllLegalMoves()) {
            PentagoBoardState clone = (PentagoBoardState) board.clone();
            clone.processMove(move);
            score = alphabeta(depth, player, clone, alpha, beta).getKey();
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