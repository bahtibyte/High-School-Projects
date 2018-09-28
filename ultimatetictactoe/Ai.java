package ultimatetictactoe; 

import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.ArrayList;

public class Ai {

    //Array list holding best row and col
    ArrayList<Integer> bestRows = new ArrayList<>();
    ArrayList<Integer> bestCols = new ArrayList<>();
    
    //Value for best row and col
    int ROW;
    int COL;
    
    //Values for the empty, player, and opponent
    int empty = 0;
    int player = 2;
    int opponent = 1;
    
    int bestGrid;
    
    public Ai(int[][] board){
        
        findBestMove(board);
        
    }
    
    //Returns the best row
    public int getRow(){
        return ROW;
    }
    
    //Returns the best col
    public int getCol(){
        return COL;
    }
    
    //Returns the best grid
    public int getGridValue(){
        return bestGrid;
    }
    
    //Makes sure there are moves left in the board
    public boolean isMovesLeft(int[][] board){
        
        //Loops through the rows
        for (int  row = 0; row < 3; row++){
            
            //Loops through the cols
            for (int col = 0; col < 3; col++){
                
                //If the board is empty it returns true
                if (board[row][col] == empty) return true;
            }
        }
        
        //If no empty location is found it returns false
        return false;
    }
        
    //Looks for any winnings
    public int evaluate(int[][] board){
        
        //Checks the col and row
        for (int i = 0; i < 3; i++){
            
            //Checks the row for winning
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                
                //Returns the correct value for each player
                if (board[i][0] == player) return +10;
                else if (board[i][0] == opponent) return -10;
                
            }
            
            //Checks the col for winning
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]){
                
                //Returns the correct value for each player
                if (board[0][i] == player) return +10;
                else if (board[0][i] == opponent) return -10;
                
            }
            
        }
        
        //Checks diagonally
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            
            //Returns the correct value for each player
            if (board[0][0] == player)return +10; 
            else if (board[0][0] == opponent) return -10;
            
        }
        
        //Checks diagonally
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            
            //Returns the correct value for each player
            if (board[0][2] == player) return +10;
            else if (board[2][0] == opponent) return -10;
        }
         
        //If there are no winnings found
        return 0;
    }
    
    //The recursion method
    public int minimax(int[][] board, int depth, boolean isMax){
        
        //Gets the score at its state
        int score = evaluate(board);
        
        //If there is a winning it returns value - depth
        if (score == 10){
            return score - depth;
        }
        
        //If there is a winning it returns value + depth
        if (score == -10){
            return score + depth;
        }
        
        //If the board is a tie it returns nothing
        if (isMovesLeft(board) == false){
            return 0;
        }
        
        //If its players turn
        if (isMax){
            
            //Temp value
            int best = -1000;
            
            //Loops through the rows
            for (int row = 0; row < 3; row++){
                
                //Loops through the cols
                for (int col = 0; col < 3; col++){
                    
                    //Makes sure the location is empty
                    if (board[row][col] == empty){
                        
                        //Sets a temp value at the location
                        board[row][col] = player;
                        
                        //Re calls it self 
                        best = max(best, minimax(board, depth+1, !isMax) );
                        
                        //Removes the temp value at the location
                        board[row][col] = empty;
                    }
                    
                }
            }
            
            //Returns the best value
            return best;
            
        }
        
        //If its the opponents turn
        else {
            
            //Temp value
            int best = 1000;
            
            //Loops through the rows
            for (int row = 0; row < 3; row++){
                
                //Loops through the cols
                for (int col = 0; col < 3; col++){
                    
                    //Makes sure the board is empty
                    if (board[row][col] == empty){
                        
                        //Temporary sets a value
                        board[row][col] = opponent;
                        
                        //Re calls it self
                        best = min(best,minimax(board, depth+1, !isMax));
                        
                        //Removes the temp value
                        board[row][col] = empty;
                        
                    }
                    
                }
            }
            
            //Returns the best value
            return best;
            
        }
        
        
    }
    
    //The main method that is called to get the best move
    public void findBestMove(int[][] board){
        
        //Temp value
        int bestVal = -1000;
        
        //Loops through the rows
        for (int row = 0; row < 3; row++){
            
            //Loops through the cols
            for (int col = 0; col < 3; col++){
                
                //Makes sure the board is empty
                if (board[row][col] == empty){
                    
                    //Temporary places a player
                    board[row][col] = player;
                    
                    //Checks if that temp value is the best move
                    int moveVal = minimax(board, 0, true);
                    
                    //Removes the temporary player
                    board[row][col] = empty;
                    
                    
                    //System.out.println("row: "+row+" col: "+row+" Value: "+moveVal);
                    
                    //If the move is better than the best it re sets the best value
                    if (moveVal > bestVal){
                        
                        //Sets the new best move
                        bestVal = moveVal;
                        bestGrid = moveVal;
                        
                        //Re sets the array list
                        bestRows.clear();
                        bestCols.clear();
                    }
                    
                    //If the move is same as best move it puts into array
                    if (moveVal == bestVal){
                        
                        //Adds the row to array list
                        bestRows.add(row);
                        
                        //Adds the col to array list
                        bestCols.add(col);
                    }
                }
                
            }
        }
        
        //System.out.println("The value of the best move is: "+bestVal);
        
        makeMove();
        
    }
    
    //Makes a move
    public void makeMove(){
        
        //Gets size of the array
        int len = bestRows.size();
        
        //Gets a random number
        int ran = (int) (Math.random() * len);
        
        //Sets the row and col from random location in the array
        ROW = bestRows.get(ran);
        COL = bestCols.get(ran);
        
        
    }
    
}
    
    
    
