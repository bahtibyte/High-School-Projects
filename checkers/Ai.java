package checkers;

public class Ai {
    
    public void main(int[][] board,int[] checkersLeft){
        if (takeOver(board,checkersLeft)){
            return;
        }if (safeMove(board)){
            return;
        }if (randomMove(board)){
            
        }
    }
    
    int playerRed = 1;
    int playerBlue = 2;
    
    int playerRedKing = 3;
    int playerBlueKing = 4;
    int empty = 0;
    
    public boolean takeOver(int[][] board, int[] checkersLeft){
        
        for (int r = 5; r >= 0; r--){
            
            for (int c = 0; c <= 7; c++){
                
                if (board[r][c] == playerRed){
                    
                    if (inLimit(r+1,c-1) && board[r+1][c-1] == playerBlue){
                        
                        if (inLimit(r+2,c-2) && board[r+2][c-2] == empty){
                            
                            log("AI Red movement from ("+(r)+","+(c)+") to ("+(r+2)+","+(c-2)+")");
                            log("AI Red took over player Blue at ("+(r+1)+","+(c-1)+")");
                            
                            board[r][c] = empty;
                            board[r+1][c-1] = empty;
                            
                            
                            checkersLeft[1]--;
                            
                            if (r+2 == 7){
                                board[r+2][c-2] = playerRedKing;
                            }else {
                                board[r+2][c-2] = playerRed; 
                            }
                            return true;
                            
                        }
                        
                    }
                    
                    if (inLimit(r+1,c-1) && board[r+1][c-1] == playerBlueKing){
                        
                        if (inLimit(r+2,c-2) && board[r+2][c-2] == empty){
                            
                            log("AI Red movement from ("+(r)+","+(c)+") to ("+(r+2)+","+(c-2)+")");
                            log("AI Red took over player Blue at ("+(r+1)+","+(c-1)+")");
                            
                            board[r][c] = empty;
                            board[r+1][c-1] = empty;
                            
                            
                            checkersLeft[1]--;
                            
                            if (r+2 == 7){
                                board[r+2][c-2] = playerRedKing;
                            }else {
                                board[r+2][c-2] = playerRed; 
                            }
                            return true;
                            
                        }
                        
                    }
                    
                    if (inLimit(r+1,c+1) && board[r+1][c+1] == playerBlue){
                        
                        if (inLimit(r+2,c+2) && board[r+2][c+2] == empty){
                            
                            log("AI Red movement from ("+(r)+","+(c)+") to ("+(r+2)+","+(c+2)+")");
                            log("AI Red took over player Blue at ("+(r+1)+","+(c+1)+")");
                            
                            board[r][c] = empty;
                            board[r+1][c+1] = empty;             
                            
                            checkersLeft[1]--;
                            
                            if (r+2 == 7){
                               board[r+2][c+2] = playerRedKing; 
                            }else {
                                board[r+2][c+2] = playerRed;
                            }
                            return true;
                            
                        }
                    }
                    
                    if (inLimit(r+1,c+1) && board[r+1][c+1] == playerBlueKing){
                        
                        if (inLimit(r+2,c+2) && board[r+2][c+2] == empty){
                            
                            log("AI Red movement from ("+(r)+","+(c)+") to ("+(r+2)+","+(c+2)+")");
                            log("AI Red took over player Blue at ("+(r+1)+","+(c+1)+")");
                            
                            board[r][c] = empty;
                            board[r+1][c+1] = empty;             
                            
                            checkersLeft[1]--;
                            
                            if (r+2 == 7){
                               board[r+2][c+2] = playerRedKing; 
                            }else {
                                board[r+2][c+2] = playerRed;
                            }
                            return true;
                            
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean safeMove(int[][] board){
        
        for (int r = 6; r >= 0; r--){
            
            for (int c = 0; c <= 7; c++){
                
                if (board[r][c] == playerRed){
                    
                    if (inLimit(r+1,c-1) && board[r+1][c-1] == empty){
                        
                        if (inLimit(r+2,c-2) && inLimit(r+2,c) && board[r+2][c-2] != playerBlue && board[r+2][c-2] != playerBlueKing
                            && board[r+2][c] != playerBlue && board[r+2][c] != playerBlueKing){
                            
                            log("Ai red safe movemenet from ("+r+","+c+") to ("+(r+1)+","+(c-1)+")");
                       
                            board[r][c] = empty;
                            
                            if (r+1 == 7){
                                board[r+1][c-1] = playerRedKing;
                            }else {
                                board[r+1][c-1] = playerRed;
                            }
                            
                            return true;
                            
                        } 
                        
                        if (inLimit(r+2,c-2) && inLimit(r,c+2) && board[r+2][c-2] != playerBlue && board[r+2][c-2] != playerBlueKing
                            && board[r][c+2] != empty){
                            
                            log("Ai red safe movemenet from ("+r+","+c+") to ("+(r+1)+","+(c-1)+")");
                       
                            board[r][c] = empty;
                            
                            if (r+1 == 7){
                                board[r+1][c-1] = playerRedKing;
                            }else {
                                board[r+1][c-1] = playerRed;
                            }
                            
                            return true;
                            
                        }
                        
                        if (!inLimit(r+2,c-2) && inLimit(r+2,c)){
                            
                            log("Ai red safe movemenet from ("+r+","+c+") to ("+(r+1)+","+(c-1)+")");
                       
                            board[r][c] = empty;
                            
                            if (r+1 == 7){
                                board[r+1][c-1] = playerRedKing;
                            }else {
                                board[r+1][c-1] = playerRed;
                            }
                            
                            return true;
                            
                        }
                        
                        if (!inLimit(r+2,c-2) && !inLimit(r+2,c)){
                            
                            log("Ai red safe movemenet from ("+r+","+c+") to ("+(r+1)+","+(c-1)+")");
                       
                            board[r][c] = empty;
                            
                            if (r+1 == 7){
                                board[r+1][c-1] = playerRedKing;
                            }else {
                                board[r+1][c-1] = playerRed;
                            }
                            
                            return true;
                            
                        }
                        
                        
                        
                        
                        
                    }
                    
                    if (inLimit(r+1,c+1) && board[r+1][c+1] == empty){
                        
                        if (inLimit(r+2,c+2) && inLimit(r+2,c) && board[r+2][c+2] != playerBlue && board[r+2][c+2] != playerBlueKing
                            && board[r+2][c] != playerBlue && board[r+2][c] != playerBlueKing){
                            
                            log("Ai red safe movemenet from ("+r+","+c+") to ("+(r+1)+","+(c+1)+")");
                       
                            board[r][c] = empty;
                            
                            if (r+1 == 7){
                                board[r+1][c+1] = playerRedKing;
                            }else {
                                board[r+1][c+1] = playerRed;
                            }
                            
                            return true;
                            
                        } 
                        
                        if (inLimit(r+2,c+2) && inLimit(r,c+2) && board[r+2][c+2] != playerBlue && board[r+2][c+2] != playerBlueKing
                            && board[r][c+2] != empty){
                            
                            log("Ai red safe movemenet from ("+r+","+c+") to ("+(r+1)+","+(c+1)+")");
                       
                            board[r][c] = empty;
                            
                            if (r+1 == 7){
                                board[r+1][c+1] = playerRedKing;
                            }else {
                                board[r+1][c+1] = playerRed;
                            }
                            
                            return true;
                            
                        } 
                        
                        if (!inLimit(r+2,c+2) && inLimit(r+2,c)){
                            
                            log("Ai red safe movemenet from ("+r+","+c+") to ("+(r+1)+","+(c+1)+")");
                       
                            board[r][c] = empty;
                            
                            if (r+1 == 7){
                                board[r+1][c+1] = playerRedKing;
                            }else {
                                board[r+1][c+1] = playerRed;
                            }
                            
                            return true;
                            
                        }
                        
                        if (!inLimit(r+2,c+2) && !inLimit(r+2,c)){
                            
                            log("Ai red safe movemenet from ("+r+","+c+") to ("+(r+1)+","+(c+1)+")");
                       
                            board[r][c] = empty;
                            
                            if (r+1 == 7){
                                board[r+1][c+1] = playerRedKing;
                            }else {
                                board[r+1][c+1] = playerRed;
                            }
                            
                            return true;
                            
                        }
                        
                        
                        
                    }
                    
                }
                
            }
            
        }
        
        
        
        return false;
    }
    
    public boolean randomMove(int[][] board){
        
        for (int r = 6; r >= 0; r--){
            for (int c = 0; c <= 7; c++){
                if (board[r][c] == playerRed && inLimit(r+1,c-1) && board[r+1][c-1] == empty){
                    log("Ai red random movemenet from ("+r+","+c+") to ("+(r+1)+","+(c-1)+")");

                    board[r][c] = empty;

                    if (r+1 == 7){
                        board[r+1][c-1] = playerRedKing;
                    }else {
                        board[r+1][c-1] = playerRed;
                    }

                    return true;
                }if (board[r][c] == playerRed && inLimit(r+1,c+1) && board[r+1][c+1] == empty){
                    log("Ai red random movemenet from ("+r+","+c+") to ("+(r+1)+","+(c+1)+")");

                    board[r][c] = empty;

                    if (r+1 == 7){
                        board[r+1][c+1] = playerRedKing;
                    }else {
                        board[r+1][c+1] = playerRed;
                    }

                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean inLimit(int r, int c){
        if (r >= 0 && r<= 7){
            if (c >= 0 && c <= 7){
                return true;
            }
        }
        return false;
    }
    
    public void log(Object m){
        System.out.println(m);
    }
    
}