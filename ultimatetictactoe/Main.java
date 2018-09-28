package ultimatetictactoe;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main extends JFrame {
    
    //The grid for 3 by 3 Tic Tac Toe frame
    Container[][] grid = new Container[3][3];
    
    //The grid for 81 JButtons
    JButton[][][][] buttons = new JButton[3][3][3][3];
    
    //The physical board of the game
    int[][][][] board = new int[3][3][3][3];
    
    //The 3 by 3 grid
    int[][] gridBoard = new int[3][3];
    
    //The player values
    int empty = 0;
    int playerX = 1;
    int playerO = 2;
    
    //Any is for any available grid
    int any = 4;
    
    //The playable grid location
    int playGridRow = any;
    int playGridCol = any;
    
    //Flag variable to see if game is playing
    boolean gameOver = false;
    
    //Main Constructor
    public Main(){
        
        //Intializing frame
        setTitle("Ultimate Tic Tac Toe");
        setSize(900,900);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,3,15,15));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Loops through the grids row
        for (int gridRow = 0; gridRow < 3; gridRow++){
            
            //Loops through the grids col
            for (int gridCol = 0; gridCol < 3; gridCol++){
                
                //Setting up the 9 main grids
                grid[gridRow][gridCol] = new Container();
                grid[gridRow][gridCol].setLayout(new GridLayout(3,3,2,2));
                
                //Loops through the button row
                for (int buttonRow = 0; buttonRow < 3; buttonRow++){
                    
                    //Loops through the button col
                    for (int buttonCol = 0; buttonCol < 3; buttonCol++){
                        
                        //Settings up the 81 buttons
                        buttons[gridRow][gridCol][buttonRow][buttonCol] = new JButton();
                        
                        //Sets the font and the font size
                        buttons[gridRow][gridCol][buttonRow][buttonCol].setFont(new Font("Apple Chancery", Font.PLAIN,50));
                        
                        //Makes the background color visible
                        buttons[gridRow][gridCol][buttonRow][buttonCol].setOpaque(true);
                        
                        //Setting action command
                        buttons[gridRow][gridCol][buttonRow][buttonCol].setActionCommand(gridRow+"|"+gridCol+"|"+buttonRow+"|"+buttonCol);
                        
                        //Adding a action listener for the button
                        buttons[gridRow][gridCol][buttonRow][buttonCol].addActionListener(new ActionListener(){
                            
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                
                                //Extracts the values from buttons action command
                                int gridRow = getGridRow(e.getActionCommand());
                                int gridCol = getGridCol(e.getActionCommand());
                                int buttonRow = getButtonRow(e.getActionCommand());
                                int buttonCol = getButtonCol(e.getActionCommand());
                                
                                //Runs the main part of Tic Tac Toe if game isnt over
                                if (!gameOver) run(gridRow, gridCol, buttonRow, buttonCol);
                                
                            }});
                        
                        //Adding the correct button to its grid
                        grid[gridRow][gridCol].add(buttons[gridRow][gridCol][buttonRow][buttonCol]);                        
                    }
                }
                //Adding the grid to the frame
                add(grid[gridRow][gridCol]);
            }
        }
        
        //Sets the buttons and the frame to show
        setVisible(true);
        
    }
    
    //Runs the main part of the program
    public void run(int gridRow, int gridCol, int buttonRow, int buttonCol){
           
        //Checks if the game not played any grid
        if (playGridRow != any && playGridCol != any){
            
            //Makes sure the grid location matches with the playable grid
            if (playGridRow == gridRow && playGridCol == gridCol){
                
                //Makes sure the grid isnt won
                if (gridBoard[gridRow][gridCol] == empty) {
                
                    //Makes sure the location is empty
                    if (board[gridRow][gridCol][buttonRow][buttonCol] == empty){

                        //Prints the location of the button pressed
                        log("Grid: ("+gridRow+","+gridCol+") Button: "+"("+buttonRow+","+buttonCol+")");

                        //Plugs in the player X to the board
                        board[gridRow][gridCol][buttonRow][buttonCol] = playerX;

                        //Calls the method to see if there are any winnings
                        checkForWin();
                        
                        //Checks if the entire game is won
                        gridWin();
                        
                        //Checks if player X has won a grid
                        if (gridBoard[buttonRow][buttonCol] != empty){
                            
                            playGridRow = any;
                            playGridCol = any;
                            
                            //Prints the next playable grid
                            log("Next Grid: (ANY,ANY)");
                        }
                        
                        else if (gridBoard[buttonRow][buttonCol] == empty){
                         
                            //Sets the next playing grid
                            playGridRow = buttonRow;
                            playGridCol = buttonCol;
                            
                            //Prints the next playable grid
                            log("Next Grid: ("+playGridRow+","+playGridCol+")");
                        }

                        //Calls the method to place X on the frame
                        setX(gridRow,gridCol,buttonRow,buttonCol);

                        //Runs the AI
                        runAi();
                    } 

                    //If the location is already taken
                    else log("Location already taken! Grid: ("+playGridRow+","+playGridCol+")");
                }
                
            }
            
            //If the location clicked is invalid
            else log("Invalid Movement! Grid: ("+playGridRow+","+playGridCol+")");
            
        }
        
        //Checks if the game can be played on any grid
        else if (playGridRow == any && playGridCol == any){
            
            //Makes sure the grid isnt won
            if (gridBoard[gridRow][gridCol] == empty){
            
                //Makes sure the location is empty
                if (board[gridRow][gridCol][buttonRow][buttonCol] == empty){

                    //Prints the location of the button pressed
                    log("Grid: ("+gridRow+","+gridCol+") Button: "+"("+buttonRow+","+buttonCol+")");

                    //Plugs in the player X to the board
                    board[gridRow][gridCol][buttonRow][buttonCol] = playerX;

                    //Calls the method to see if there are any winnings
                    checkForWin();
                    
                    //Checks if the entire game is won
                    gridWin();

                    //Checks if player X has won a grid
                    if (gridBoard[buttonRow][buttonCol] != empty){

                        playGridRow = any;
                        playGridCol = any;

                        //Prints the next playable grid
                        log("Next Grid: (ANY,ANY)");
                    }

                    else if (gridBoard[buttonRow][buttonCol] == empty){

                        //Sets the next playing grid
                        playGridRow = buttonRow;
                        playGridCol = buttonCol;

                        //Prints the next playable grid
                        log("Next Grid: ("+playGridRow+","+playGridCol+")");
                    }

                    //Calls the method to place X on the frame
                    setX(gridRow,gridCol,buttonRow,buttonCol);
                    
                    //Runs the Ai
                    runAi();

                }
            }
        }
        
        //If the location is already taken
        else log("Location already taken! Grid: ("+playGridRow+","+playGridCol+")");
        
        
        
    }
    
    //Runs the Ai
    public void runAi(){
        
        //Temp value for the best grid
        int best = -1000;
        int gridRow = 0;
        int gridCol = 0;
        
        //Makes sure the game isnt over
        if (!gameOver) {
            
            //Checks if the play grid isnt any
            if (playGridRow != any && playGridCol != any){

                //Gets a new board
                int[][] newBoard = getBoard(board,playGridRow,playGridCol);

                //Sends the board to the AI
                Ai ai = new Ai(newBoard);

                //Retreives the row and col
                int row = ai.getRow();
                int col = ai.getCol();

                //Prints where the computer made its move
                log("Ai Move Grid: ("+playGridRow+","+playGridCol+") Button: "+"("+row+","+col+")");

                //Places a value in the board
                board[playGridRow][playGridCol][row][col] = playerO;

                //Sets O in the GUI
                setO(playGridRow,playGridCol,row,col);

                //Calls the method to see if there are any winnings
                checkForWin();
                
                //Checks if the entire game is won
                gridWin();

                //Checks if player X has won a grid
                if (gridBoard[row][col] != empty){

                    //Next game would be on any grid
                    playGridRow = any;
                    playGridCol = any;

                    //Prints the next playable grid
                    log("Next Grid: (ANY,ANY)");
                }

                //Checks if player X has won a grid
                else if (gridBoard[row][col] == empty){

                    //Sets the next playing grid
                    playGridRow = row;
                    playGridCol = col;

                    //Prints the next playable grid
                    log("Next Grid: ("+playGridRow+","+playGridCol+")");
                }

            }

            //Runs this if AI's turn is on any grid
            else if (playGridRow == any && playGridCol == any){

                //Array list holding the left over grids
                ArrayList<Integer> gridLeftRow = new ArrayList<>();
                ArrayList<Integer> gridLeftCol = new ArrayList<>();

                //Loops through the rows
                for (int r = 0; r < 3; r++){
                    
                    //Loops through the cols
                    for (int c = 0; c < 3; c++){
                        
                        //Checks if the board is empty
                        if (gridBoard[r][c] == empty){

                            //Adds the row and col to the array
                            gridLeftRow.add(r);
                            gridLeftCol.add(c);

                        }
                    }
                }
                
                //Loops through available grids
                for (int i = 0; i < gridLeftRow.size(); i++){

                    //The 2D array for the grid values
                    int[][] grid2D = getBoard(board,gridLeftRow.get(i),gridLeftCol.get(i));

                    //Sends the board to the AI
                    Ai ai = new Ai(grid2D);
                    
                    //Gets the value of that grid
                    int moveVal = ai.getGridValue();

                    //Sees if the new value is better than the old one
                    if (moveVal > best){
                        
                        //Sets the new value for the best one
                        best = moveVal;
                        
                        //Sets the values for the playing grid row and col
                        gridRow = gridLeftRow.get(i);
                        gridCol = gridLeftCol.get(i);
                    }
                }

                //Gets a new board
                int[][] newBoard = getBoard(board,gridRow,gridCol);

                //Sends the board to the AI
                Ai ai = new Ai(newBoard);

                //Retreives the row and col 
                int row = ai.getRow();
                int col = ai.getCol();

                //Prints the AI movement
                log("Ai Move Grid: ("+playGridRow+","+playGridCol+") Button: "+"("+row+","+col+")");

                //Sets the values at location AI moved
                board[gridRow][gridCol][row][col] = playerO;

                //Sets O on the GUI at given location
                setO(gridRow,gridCol,row,col);

                //Calls the method to see if there are any winnings
                checkForWin();
                
                //Checks if the entire game is won
                gridWin();

                //Checks if player X has won a grid
                if (gridBoard[row][col] != empty){

                    //Next play is on any grid
                    playGridRow = any;
                    playGridCol = any;

                    //Prints the next playable grid
                    log("Next Grid: (ANY,ANY)");
                }

                else if (gridBoard[row][col] == empty){

                    //Sets the next playing grid
                    playGridRow = row;
                    playGridCol = col;

                    //Prints the next playable grid
                    log("Next Grid: ("+playGridRow+","+playGridCol+")");
                }
            }
        }
    }
    
    //Checks to see if the game is won
    public void gridWin(){
        
        //Loops 3 times
        for (int i = 0; i < 3; i++){
            
            //Checks for 3 in a row in row
            if (gridBoard[i][0] == gridBoard[i][1] && gridBoard[i][1] == gridBoard[i][2]){
                
                //Player X won
                if (gridBoard[i][0] == playerX) {
                    
                    //Prints the message
                    log("PLAYER X WON");
                    
                    //Sets the flag variable
                    gameOver = true;
                }
                
                //Player O won
                else if (gridBoard[i][0] == playerO){
                    
                    //Prints the message
                    log("PLAYER O WON");
                    
                    //Sets the flag variable
                    gameOver = true;
                }
                
            }
            
            //Checks for 3 in a row in col
            else if (gridBoard[0][i] == gridBoard[1][i] && gridBoard[1][i] == gridBoard[2][i]){
                
                //Player X won
                if (gridBoard[0][i] == playerX){
                    
                    //Prints the message
                    log("PLAYER X WON");
                    
                    //Sets the flag variable
                    gameOver = true;
                }
                else if (gridBoard[0][i] == playerO){
                    log("PLAYER O WON");
                    gameOver = true;
                }
                
            }
            
        }
        
        //Checks for daigonally win
        if (gridBoard[0][0] == gridBoard[1][1] && gridBoard[1][1] == gridBoard[2][2]){
            
            //Player X has won
            if (gridBoard[0][0] == playerX){
                
                //Prints the message
                log("Player X won");
                
                //Sets the flag variable
                gameOver = true;
            }
            else if (gridBoard[0][0] == playerO){
                
                //Prints the message
                log("PLAYER O WON");
                
                //Sets the flag variable
                gameOver = true;
            }

        }
        
        //Checks for daigonally iwn
        if (gridBoard[0][2] == gridBoard[1][1] && gridBoard[1][1] == gridBoard[2][0]){
            
            if (gridBoard[0][2] == playerX){
                    log("PLAYER X WON");
                    gameOver = true;
                }
                else if (gridBoard[0][2] == playerO){
                    log("PLAYER O WON");
                    gameOver = true;
                }
            
        }
        
        
    }
    
    //Checks to see if any of the grids are won
    public void checkForWin(){
        
        //Makes a temp board
        int[][] tempBoard;
        
        //Loops through the row of the grid
        for (int row = 0; row < 3; row++){
            
            //Loops through the col of the grid
            for (int col = 0; col < 3; col++){
                
                //Makes sure the grid hasnt won yet
                if (gridBoard[row][col] == empty){
                    
                    //Gets a the grid from the row and col
                    tempBoard = getBoard(board, row, col);
                
                    //Loops 3 times to get the winnings for row and col
                    for (int i = 0; i < 3; i++){

                        //Checks if there are any winnings on row
                        if (tempBoard[i][0] == tempBoard[i][1] && tempBoard[i][1] == tempBoard[i][2]){
                            
                            //Player X has won row and col
                            if (tempBoard[i][0] == playerX) {
                                
                                //Prints the message
                                log("Player X won Grid: ("+row+","+col+")");
                                
                                //Sets the grid with correct player
                                gridBoard[row][col] = playerX;
                                
                                //Calls method to color the grid
                                colorGrid(row,col,playerX);

                            }
                            //Player O has won row and col
                            else if (tempBoard[i][0] == playerO) {
                                
                                //Prints the message
                                log("Player O won Grid: ("+row+","+col+")");
                                
                                //Sets the grid with correct player
                                gridBoard[row][col] = playerO;
                                
                                //Calls method to color the grid
                                colorGrid(row,col,playerO);
                            }

                        }
                        
                        //Checks if there are any winnings on the col
                        else if (tempBoard[0][i] == tempBoard[1][i] && tempBoard[1][i] == tempBoard[2][i]){

                            //Player X has won row and col
                            if (tempBoard[0][i] == playerX) {
                                
                                //Prints the message
                                log("Player X won Grid: ("+row+","+col+")");
                                
                                //Sets the grid with correct player
                                gridBoard[row][col] = playerX;
                                
                                //Calls method to color the grid
                                colorGrid(row,col,playerX);
                            }
                            
                            //Player O has won row and col
                            else if (tempBoard[0][i] == playerO) {
                                
                                //Prints the message
                                log("Player O won Grid: ("+row+","+col+")");
                                
                                //Sets the grid with correct player
                                gridBoard[row][col] = playerO;
                                
                                //Calls method to color the grid
                                colorGrid(row,col,playerO);
                            }
                        }
                    }
                    
                    //Checks for diagonally win
                    if (tempBoard[0][0] == tempBoard[1][1] && tempBoard[1][1] == tempBoard[2][2]){
                        
                        //Player X has won
                        if (tempBoard[0][0] == playerX) {
                            
                            //Prints the message
                            log("Player X won Grid: ("+row+","+col+")");
                            
                            //Sets the grid with correct player
                            gridBoard[row][col] = playerX;
                            
                            //Calls method to color the grid
                            colorGrid(row,col,playerX);
                        }
                        
                        //Player O has won
                        else if (tempBoard[0][0] == playerO) {
                            
                            //Prints the message
                            log("Player O won Grid: ("+row+","+col+")");
                            
                            //Sets the grid with correct player
                            gridBoard[row][col] = playerO;
                            
                            //Calls method to color the grid
                            colorGrid(row,col,playerO);
                        }
                    }
                    
                    //Checks for diagonally win
                    else if (tempBoard[0][2] == tempBoard[1][1] && tempBoard[1][1] == tempBoard[2][0]){
                        
                        //Player X has won
                        if (tempBoard[0][2] == playerX) {
                            
                            //Prints the message
                            log("Player X won Grid: ("+row+","+col+")");
                            
                            //Sets the grid with correct player 
                            gridBoard[row][col] = playerX;
                            
                            //Calls method to color the grid
                            colorGrid(row,col,playerX);
                        }
                        
                        //Player O has won
                        else if (tempBoard[0][2] == playerO) {
                            
                            //Prints the message
                            log("Player O won Grid: ("+row+","+col+")");
                            
                            //Sets the grid with correct player
                            gridBoard[row][col] = playerO;
                            
                            //Calls method to color the grid
                            colorGrid(row,col,playerO);
                        }
                    }   
                } 
            }
        }
    }
    
    //Colors the grid with correct winner
    public void colorGrid(int gridRow, int gridCol, int player){

        //Loops through the row
        for (int r = 0; r < 3; r++){
            
            //Loops through the col
            for (int c  = 0; c < 3; c++){
                
                //If the player is X it colors GREEN
                if (player == playerX) buttons[gridRow][gridCol][r][c].setBackground(Color.GREEN);
                
                //If the player is O it colors RED
                else if (player == playerO) buttons[gridRow][gridCol][r][c].setBackground(Color.RED);
            }
        }
    }
    
    //Gets a 2D array of the grid from the 4D array
    public int[][] getBoard(int[][][][] board, int gridRow, int gridCol){
        
        //The 2d array holding the values of the grid
        int[][] output = new int[3][3];
        
        //Loops through the rows
        for (int row = 0; row < 3; row++){
            
            //Copys the cols
            for (int col = 0; col < 3; col++){
                output[row][col] = board[gridRow][gridCol][row][col];
            }
        }
        
        //Returns the 2D array
        return output;
    }
    
    //Sets X at the location given
    public void setX(int gridRow, int gridCol, int buttonRow, int buttonCol){
        buttons[gridRow][gridCol][buttonRow][buttonCol].setText("X");
    }
    
    //Sets O at the location given
    public void setO(int gridRow, int gridCol, int buttonRow, int buttonCol){
        buttons[gridRow][gridCol][buttonRow][buttonCol].setText("O");
    }
    
    //Return the grid row
    public int getGridRow(String str){
        return Integer.parseInt(str.substring(0,1));
    }
    
    //Returns the grid col
    public int getGridCol(String str){
        return Integer.parseInt(str.substring(2,3));
    }
    
    //Returns the button row
    public int getButtonRow(String str){
        return  Integer.parseInt(str.substring(4,5));
    }
    
    //Returns the button col
    public int getButtonCol(String str){
        return Integer.parseInt(str.substring(6));
    }
    
    //Fancy way to print
    public void log(Object m){
        System.out.println(m);
    }
    
    //Main method
    public static void main(String[]args){
        new Main();
    }
    
}
