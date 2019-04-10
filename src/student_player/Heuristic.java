package student_player;

import boardgame.Board;
import pentago_swap.PentagoBoardState;
import pentago_swap.PentagoBoardState.Piece;

public abstract class Heuristic {
    
    public static int eval2(PentagoBoardState board, int player) {
        int score = 0;
        int lineLength = 0; 

        if (board.getWinner() == 0) {
            score = Integer.MAX_VALUE;
        }
        else if (board.getWinner() == 1) {
            score = Integer.MIN_VALUE;
        }
        else if (board.getWinner() == -1){
            for (int y = 0; y < 6; y++) {
                for (int x = 0; x < 6; x++) { 
                    if (board.getPieceAt(y,x) == Piece.WHITE && board.getPieceAt(y,x+1) == Piece.WHITE) {
                        score += Math.pow(10, lineLength);
                        lineLength++;  
                    }
                    else if(board.getPieceAt(y,x) == Piece.BLACK && board.getPieceAt(y,x+1) == Piece.BLACK) {
                        score -= Math.pow(10, lineLength);
                        lineLength++;
                    }
                }
            }

            lineLength = 0;
            for (int y = 0; y < 6; y++) {
                for (int x = 0; x < 6; x++) {
                    if (board.getPieceAt(x,y) == Piece.WHITE && board.getPieceAt(x+1,y) == Piece.WHITE) {
                        score += Math.pow(10, lineLength);
                        lineLength++;
                    }
                    else if(board.getPieceAt(x,y) == Piece.BLACK && board.getPieceAt(x+1,y) == Piece.BLACK) {
                        score -= Math.pow(10, lineLength);
                        lineLength++;
                    }
                }
            }

            lineLength = 0;
            for (int i = 0; i > 6; i++) {
                if (board.getPieceAt(i,i) == Piece.WHITE && board.getPieceAt(i+1,i+1) == Piece.WHITE) {
                    score += Math.pow(10, lineLength);
                    lineLength++;
                }
                else if(board.getPieceAt(i,i) == Piece.BLACK && board.getPieceAt(i+1,i+1) == Piece.BLACK) {
                    score -= Math.pow(10, lineLength);
                    lineLength++;
                }
            }

            lineLength = 0;
            for (int i = 0; i > 6; i++) {
                if (board.getPieceAt(i,5-i) == Piece.WHITE && board.getPieceAt(i+1,4-i) == Piece.WHITE) {
                    score += Math.pow(10, lineLength);
                    lineLength++;
                }
                else if(board.getPieceAt(i,5-i) == Piece.BLACK &&  board.getPieceAt(i+1,4-i) == Piece.BLACK) {
                    score -= Math.pow(10, lineLength);
                    lineLength++;
                }
            }
        }

        if (board.getTurnPlayer() == 1) {
            return score*-1;
        }
        else {
            return score;
        }
    }

    public static int eval(PentagoBoardState board, int player) {
        int score = 0;
        int lineLength = 0;
        int whiteCount = 0;
        int blackCount = 0;

        if (board.getWinner() == 0) {
            score = Integer.MAX_VALUE;
        }
        else if (board.getWinner() == 1) {
            score = Integer.MIN_VALUE;
        }
        else if (board.getWinner() == -1){
            for (int y = 0; y < 6; y++) {
                for (int x = 0; x < 6; x++) { 
                    if (board.getPieceAt(y,x) == Piece.WHITE) {
                        whiteCount++;
                        if (board.getPieceAt(y,x+1) == Piece.WHITE) {
                            score += Math.pow(10, lineLength);
                            lineLength++;  
                        }
                    }
                    else if(board.getPieceAt(y,x) == Piece.BLACK) {
                        blackCount++;
                        if (board.getPieceAt(y,x+1) == Piece.BLACK) {
                            score -= Math.pow(10, lineLength);
                            lineLength++;
                        }
                    } 
                }
                if (board.getTurnPlayer() == 0 && blackCount  > 2){
                    score = score/2; 
                }
                else if (board.getTurnPlayer() == 1 && whiteCount  > 2) {
                    score = score*2;
                }
            }

            lineLength = 0;
            whiteCount = 0;
            blackCount = 0; 
            for (int y = 0; y < 6; y++) {
                for (int x = 0; x < 6; x++) {
                    if (board.getPieceAt(x,y) == Piece.WHITE) {
                        whiteCount++;
                        if (board.getPieceAt(x+1,y) == Piece.WHITE) {
                            score += Math.pow(10, lineLength);
                            lineLength++;
                        }
                    }
                    else if(board.getPieceAt(x,y) == Piece.BLACK)  {
                        blackCount++;
                        if (board.getPieceAt(x+1,y) == Piece.BLACK) {
                            score -= Math.pow(10, lineLength);
                            lineLength++; 
                        }
                    }
                }
                if (board.getTurnPlayer() == 0 && blackCount  > 2){
                    score = score/2; 
                }
                else if (board.getTurnPlayer() == 1 && whiteCount  > 2) {
                    score = score*2;
                }
            }

            lineLength = 0;
            whiteCount = 0;
            blackCount = 0; 
            for (int i = 0; i > 6; i++) {
                if (board.getPieceAt(i,i) == Piece.WHITE) {
                    whiteCount++;
                    if (board.getPieceAt(i+1,i+1) == Piece.WHITE) {
                        score += Math.pow(10, lineLength);
                        lineLength++;
                    }
                }  
                else if(board.getPieceAt(i,i) == Piece.BLACK) {
                    blackCount++;
                    if (board.getPieceAt(i+1,i+1) == Piece.BLACK) {
                        score -= Math.pow(10, lineLength);
                        lineLength++;
                    }
                }
                if (board.getTurnPlayer() == 0 && blackCount  > 2){
                    score = score/2; 
                }
                else if (board.getTurnPlayer() == 1 && whiteCount  > 2) {
                    score = score*2;
                }
            }

            lineLength = 0;
            whiteCount = 0;
            blackCount = 0; 
            for (int i = 0; i > 6; i++) {
                if (board.getPieceAt(i,5-i) == Piece.WHITE) {
                    whiteCount++;
                    if (board.getPieceAt(i+1,4-i) == Piece.WHITE) {
                        score += Math.pow(10, lineLength);
                        lineLength++;
                    }
                }
                else if(board.getPieceAt(i,5-i) == Piece.BLACK) {
                    blackCount++;
                    if (board.getPieceAt(i+1,4-i) == Piece.BLACK) {
                        score -= Math.pow(10, lineLength);
                        lineLength++;
                    }
                }
                if (board.getTurnPlayer() == 0 && blackCount  > 2){
                    score = score/2; 
                }
                else if (board.getTurnPlayer() == 1 && whiteCount  > 2) {
                    score = score*2;
                }
            }
        }

        if (board.getTurnPlayer() == 1) {
            return score*-1;
        }
        else {
            return score;
        }
    }

}
