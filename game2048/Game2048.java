package game2048;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
 
public class Game2048 implements KeyListener{
    
    @Override //Gets the arrow key pressed
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT  && key && movePossible()) {
            key = false;
            pushLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT  && key && movePossible()) {
            key = false;
            pushRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP  && key && movePossible()) {
            key = false;
            pushUp();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN  && key && movePossible()) {
            key = false;
            pushDown();
        }
        
    }
    
    @Override //Gets if key is released
    public void keyReleased(KeyEvent e) {
        key = true;
    }
    //Colors the Board
    public static boolean color = true;
    
    //Console messages
    public static boolean console = true;
    
    //To see if user let go of a key
    public static boolean key = true;
    
    //The game board
    public static int[][] board = new int[4][4];
    
    //The temporary board 
    public static int[][] tempBoard = new int[4][4];
    
    //used for a empty tile
    public static int empty = 0;
    
    //Keeps track of the score and best score
    public static int score = 0;
    public static int bestScore = 0;
    
    //Setting up the tiles
    public static JLabel tileA1 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileA2 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileA3 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileA4 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileB1 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileB2 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileB3 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileB4 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileC1 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileC2 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileC3 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileC4 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileD1 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileD2 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileD3 = new JLabel("",SwingConstants.CENTER);
    public static JLabel tileD4 = new JLabel("",SwingConstants.CENTER);
    
    //Keep track of score and best score
    public static JLabel scoreBoard = new JLabel("Score: 0",SwingConstants.CENTER);
    public static JLabel bestScoreBoard = new JLabel("Best: 0", SwingConstants.CENTER);
    
    //Setting up the panel and frame
    public static JPanel panel = new JPanel();
    public static JFrame frame = new JFrame("2048");
    
    //Initial setup
    public static void setupBoard(){
        
        //Sets up a empty board
        for (int i = 0; i < 4; i++){
            for (int k = 0; k<4; k++){
                board[i][k] = empty;
            }
        }
        
        //Setting up the frame
        frame.addKeyListener(new Game2048());
        frame.add(panel);
        frame.setSize(375, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        //Setting up the panel
        panel.setBackground(new java.awt.Color(204, 192, 178));
        panel.setLayout(new FlowLayout());
        panel.setLayout(null);
        
        
        //Set up the score board
        panel.add(scoreBoard);
        scoreBoard.setLocation(15, 15);
        scoreBoard.setSize(105,50);
        scoreBoard.setOpaque(true);
        scoreBoard.setBackground(new java.awt.Color(238,228,218));
        
        //Set up the score board
        panel.add(bestScoreBoard);
        bestScoreBoard.setLocation(135, 15);
        bestScoreBoard.setSize(105,50);
        bestScoreBoard.setOpaque(true);
        bestScoreBoard.setBackground(new java.awt.Color(238,228,218));
        
        //Tile A1 (0,0)
        panel.add(tileA1);
        tileA1.setLocation(15, 120);
        tileA1.setSize(75, 75);
        tileA1.setOpaque(true);
        tileA1.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile A2 (0,1)
        panel.add(tileA2);
        tileA2.setLocation(105, 120);
        tileA2.setSize(75, 75);
        tileA2.setOpaque(true);
        tileA2.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile A3 (0,2)
        panel.add(tileA3);
        tileA3.setLocation(195, 120);
        tileA3.setSize(75, 75);
        tileA3.setOpaque(true);
        tileA3.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile A4 (0,3)
        panel.add(tileA4);
        tileA4.setLocation(285, 120);
        tileA4.setSize(75, 75);
        tileA4.setOpaque(true);
        tileA4.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile B1 (1,0)
        panel.add(tileB1);
        tileB1.setLocation(15, 210);
        tileB1.setSize(75, 75);
        tileB1.setOpaque(true);
        tileB1.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile B2 (1,1)
        panel.add(tileB2);
        tileB2.setLocation(105, 210);
        tileB2.setSize(75, 75);
        tileB2.setOpaque(true);
        tileB2.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile B3 (1,2)
        panel.add(tileB3);
        tileB3.setLocation(195, 210);
        tileB3.setSize(75, 75);
        tileB3.setOpaque(true);
        tileB3.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile B4 (1,3)
        panel.add(tileB4);
        tileB4.setLocation(285, 210);
        tileB4.setSize(75, 75);
        tileB4.setOpaque(true);
        tileB4.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile C1 (2,0)
        panel.add(tileC1);
        tileC1.setLocation(15, 300);
        tileC1.setSize(75, 75);
        tileC1.setOpaque(true);
        tileC1.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile C2 (2,1)
        panel.add(tileC2);
        tileC2.setLocation(105, 300);
        tileC2.setSize(75, 75);
        tileC2.setOpaque(true);
        tileC2.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile C3 (2,2)
        panel.add(tileC3);
        tileC3.setLocation(195, 300);
        tileC3.setSize(75, 75);
        tileC3.setOpaque(true);
        tileC3.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile C4 (2,3)
        panel.add(tileC4);
        tileC4.setLocation(285, 300);
        tileC4.setSize(75, 75);
        tileC4.setOpaque(true);
        tileC4.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile D1 (3,0)
        panel.add(tileD1);
        tileD1.setLocation(15, 390);
        tileD1.setSize(75, 75);
        tileD1.setOpaque(true);
        tileD1.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile D2 (3,1)
        panel.add(tileD2);
        tileD2.setLocation(105, 390);
        tileD2.setSize(75, 75);
        tileD2.setOpaque(true);
        tileD2.setBackground(new java.awt.Color(238, 228, 218));
        
        //Tile D3 (3,2)
        panel.add(tileD3);
        tileD3.setLocation(195, 390);
        tileD3.setSize(75, 75);
        tileD3.setOpaque(true);
        tileD3.setBackground(new java.awt.Color(238, 228, 218));
       
        //Tile D4 (3,3)
        panel.add(tileD4);
        tileD4.setLocation(285, 390);
        tileD4.setSize(75, 75);
        tileD4.setOpaque(true);
        tileD4.setBackground(new java.awt.Color(238, 228, 218));
        
        frame.setVisible(true);
        
        //Starts with 2 random tiles
        randomTile();
        randomTile();
        copyBoard();
        System.out.println("");
        drawBoard();
    }
    
    //Copies the board
    public static void copyBoard(){
        log("Before Board");
        for (int i =0; i <4; i++){
            for (int k = 0; k<4; k++){
                tempBoard[i][k] = board[i][k];
                System.out.print(tempBoard[i][k]+" ");
            }
            System.out.println("");
        }
    }
    
    //Shows current board
    public static void currentBoard(){
        log("\nCurrentBoard");
        for (int i =0; i <4; i++){
            for (int k = 0; k<4; k++){
                System.out.print(board[i][k]+" ");
            }
            System.out.println("");
        }
    
    }
    
    //Checks if the board is full
    public static boolean boardIsFull(){
        
        for (int i = 0; i < 4; i++){
            for (int k = 0; k < 4; k++){
                if (board[i][k] == empty) return false;
            }
        }
        log("board is full here");
        return true;
    }
    
    //Spawns a random tile
    public static void randomTile(){
        
        if (boardIsFull()) return;
        int x = (int) (Math.random()*4);
        int y = (int) (Math.random()*4);
        
        while (board[x][y] != empty){
            x = (int) (Math.random()*4);
            y = (int) (Math.random()*4);
        }
        
        //10% chance of spawning a 4
        int num = (int) (Math.random() * 9);

        if (num == 1) {
            log("new tile (4) at ("+x+","+y+")");
            board[x][y] = 4;
        }
        else {
            log("new tile (2) at("+x+","+y+")");
            board[x][y] = 2;
        }
            
        
    }
    
    //Checks if a move is possible
    public static boolean movePossible(){
        
        for (int i =0; i<4; i++){
            for (int k = 0; k<3; k++){
                if (board[i][k] == board[i][k+1]) return true;
            }
        }
        for (int i = 0; i<3; i++){
            for (int k = 0; k<4; k++){
                if (board[i][k] == board[i+1][k]) return true;
            }
        }
        for (int i = 0; i<4; i++){
            for (int k = 0; k<4; k++){
                if (board[i][k] == empty) return true;
            }
        }

        gameOver();
        return false;
    }
    
    //Checks if the tiles ever moved
    public static boolean tileMoved(){
        
        for (int i = 0; i<4;i++){
            for (int k =0; k<4; k++){
                if (tempBoard[i][k] != board[i][k]) return true;
            }
        }
        
        return false;
    }
    
    //Draws the board
    public static void drawBoard(){
        
        tileA1.setText(board[0][0] == empty ? "": "" + board[0][0]);
        tileA2.setText(board[0][1] == empty ? "": "" + board[0][1]);
        tileA3.setText(board[0][2] == empty ? "": "" + board[0][2]);
        tileA4.setText(board[0][3] == empty ? "": "" + board[0][3]);
        tileB1.setText(board[1][0] == empty ? "": "" + board[1][0]);
        tileB2.setText(board[1][1] == empty ? "": "" + board[1][1]);
        tileB3.setText(board[1][2] == empty ? "": "" + board[1][2]);
        tileB4.setText(board[1][3] == empty ? "": "" + board[1][3]);
        tileC1.setText(board[2][0] == empty ? "": "" + board[2][0]);
        tileC2.setText(board[2][1] == empty ? "": "" + board[2][1]);
        tileC3.setText(board[2][2] == empty ? "": "" + board[2][2]);
        tileC4.setText(board[2][3] == empty ? "": "" + board[2][3]);
        tileD1.setText(board[3][0] == empty ? "": "" + board[3][0]);
        tileD2.setText(board[3][1] == empty ? "": "" + board[3][1]);
        tileD3.setText(board[3][2] == empty ? "": "" + board[3][2]);
        tileD4.setText(board[3][3] == empty ? "": "" + board[3][3]);
        
        
        
        if (color) colorBoard();
    }
    
    
    //Colors the tiles
    public static void colorBoard(){
        
        scoreBoard.setText("Score: "+score);
        if (bestScore < score) bestScore = score;
        bestScoreBoard.setText("Best: "+bestScore);
        
        
        JLabel[] tiles = {tileA1,tileA2,tileA3,tileA4,tileB1,tileB2,tileB3,tileB4,
                          tileC1,tileC2,tileC3,tileC4,tileD1,tileD2,tileD3,tileD4};
        
        for (int i = 0; i < 16; i++){  
            switch (tiles[i].getText()) {
                case "":
                    tiles[i].setBackground(new java.awt.Color(238, 228, 218));
                    tiles[i].setFont(new java.awt.Font("Arial", 1, 10));
                    break;
                case "2":
                    tiles[i].setBackground(new java.awt.Color(238, 228, 218));
                    tiles[i].setFont(new java.awt.Font("Arial", 1, 75));
                    break;
                case "4":
                    tiles[i].setBackground(new java.awt.Color(237, 224, 200));
                    tiles[i].setFont(new java.awt.Font("Arial", 1, 75));
                    break;
                case "8":
                    tiles[i].setBackground(new java.awt.Color(242, 177, 121));
                    tiles[i].setFont(new java.awt.Font("Arial", 1, 75));
                    break;
                case "16":
                    tiles[i].setBackground(new java.awt.Color(245, 149, 99));
                    tiles[i].setFont(new java.awt.Font("Arial", 1, 55));
                    break;
                case "32":
                    tiles[i].setBackground(new java.awt.Color(246, 124, 95));
                    tiles[i].setFont(new java.awt.Font("Arial", 1, 55));
                    break;
                case "64":
                    tiles[i].setBackground(new java.awt.Color(246, 94, 59));
                    tiles[i].setFont(new java.awt.Font("Arial", 1, 55));
                    break;
                case "128":
                    tiles[i].setBackground(new java.awt.Color(237, 207, 114));              
                    tiles[i].setFont(new java.awt.Font("Arial", 1, 35));
                    break;
                case "256":
                    tiles[i].setBackground(new java.awt.Color(237, 204, 97));
                    tiles[i].setFont(new java.awt.Font("Arial", 1, 35));
                    break;
                case "512":
                    tiles[i].setBackground(new java.awt.Color(237, 200, 80));
                    tiles[i].setFont(new java.awt.Font("Arial", 1, 35));
                    break;
                case "1024":
                    tiles[i].setBackground(new java.awt.Color(235,175,80));
                    tiles[i].setFont(new java.awt.Font("Arial",1,30));
                    break;
            }
        }
    }
    
    //No possible move left
    public static void gameOver(){
        log("game over"); 
        log("Need to implement a way to play a new game...After fixing the jlabel problem");
    }
    
    //Pushes all tiles Left
    public static void pushLeft(){
        log("\nTile pushed left");
        copyBoard();
        
        //Moves all units to left without combing
        for (int i =0; i< 4;i++){
            
            for (int a = 0; a<4; a++){
                
                for (int k = 0; k < 3; k++){

                    if (board[i][k] == empty){
                        board[i][k] = board[i][k+1];
                        board[i][k+1] = empty;
                    }
                }
            }
        }
        
        //Combines the like terms
        for (int i = 0; i <4; i++){
            
            for (int a = 0; a<3;a++){
                
                if (board[i][a] == board[i][a+1] && board[i][a] != empty){
                    log(board[i][a] +" + "+board[i][a+1] +" = "+(board[i][a]*2) +" at ("+i+","+a+") + ("+i+","+(a+1)+")");
                    board[i][a] = board[i][a] *2;
                    score+=board[i][a];
                    board[i][a+1] = empty;
                }
            }
        }
        
        //Re move all to left
        for (int i =0; i< 4;i++){
            
            for (int k = 0; k < 2; k++){

                if (board[i][k] == empty){
                    board[i][k] = board[i][k+1];
                    board[i][k+1] = empty;
                }
            }
        }
        
        
        if (tileMoved()) {
            randomTile();
            currentBoard();
            drawBoard();
        }
        
    }
    
    //Pushes all tiles Right
    public static void pushRight(){
        log("\nTile pushed right");
        copyBoard();
        
        //Moves all units to right without combing
        for (int i =0; i< 4;i++){
            
            for (int a = 0; a<4; a++){
                
                for (int k = 3; k > 0; k--){

                    if (board[i][k] == empty){
                        board[i][k] = board[i][k-1];
                        board[i][k-1] = empty;
                    }
                }
            }
        }
        
        //Combines the like terms
        for (int i = 0; i <4; i++){
            
            for (int a = 3; a>0;a--){
                
                if (board[i][a] == board[i][a-1] && board[i][a] != empty){
                    log(board[i][a] +" + "+board[i][a-1] +" = "+(board[i][a]*2) +" at ("+i+","+a+") + ("+i+","+(a-1)+")");
                    board[i][a] = board[i][a] *2;
                    score+=board[i][a];
                    board[i][a-1] = empty;
                }
            }
        }
        
        //Re move all to right
        for (int i =0; i< 4;i++){
            
            for (int k = 2; k > 0; k--){

                if (board[i][k] == empty){
                    board[i][k] = board[i][k-1];
                    board[i][k-1] = empty;
                }
            }
        }
        
        
        if (tileMoved()) {
            randomTile();
            currentBoard();
            drawBoard();
        }
    }
    
    //Pushes all tiles up
    public static void pushUp(){
        log("\nTile pushed up");
        copyBoard();
        
        //Moves all units to top without combing
        for (int i =0; i< 4;i++){
            
            for (int a = 0; a<4; a++){
                
                for (int k = 0; k < 3 ; k++){

                    if (board[k][i] == empty){
                        board[k][i] = board[k+1][i];
                        board[k+1][i] = empty;
                    }
                }
            }
        }
        
        //Combines the like terms
        for (int i = 0; i <4; i++){
            
            for (int a = 0; a<3;a++){
                
                if (board[a][i] == board[a+1][i] && board[a][i] != empty){
                    log(board[a][i] +" + "+board[a+1][i] +" = "+(board[a][i]*2) +" at ("+a+","+i+") + ("+(a+1)+","+i+")");
                    board[a][i] = board[a][i] *2;
                    score+=board[a][i];
                    board[a+1][i] = empty;
                }
            }
        }
        
        //Re moves all to top
        for (int i =0; i< 4;i++){
            
            for (int k = 0; k < 2; k++){

                if (board[k][i] == empty){
                    board[k][i] = board[k+1][i];
                    board[k+1][i] = empty;
                }
            }
        }
        
        if (tileMoved()) {
            randomTile();
            currentBoard();
            drawBoard();
        }
    }
    
    //Pushed all tiles down
    public static void pushDown(){
        log("\nTile pushed down");
        copyBoard();
        
        //Moves all units to bottom without combing
        for (int i =0; i< 4;i++){
            
            for (int a = 0; a<4; a++){
                
                for (int k = 3; k > 0; k--){

                    if (board[k][i] == empty){
                        board[k][i] = board[k-1][i];
                        board[k-1][i] = empty;
                    }
                }
            }
        }
        
        //Combines the like terms
        for (int i = 0; i <4; i++){
            
            for (int a = 3; a>0;a--){
                
                if (board[a][i] == board[a-1][i] && board[a][i] != empty){
                    log(board[a][i] +" + "+board[a-1][i] +" = "+(board[a][i]*2) +" at ("+(a-1)+","+i+") + ("+(a-1)+","+i+")");
                    board[a][i] = board[a][i] *2;
                    score+=board[a][i];
                    board[a-1][i] = empty;
                }
            }
        }
        
        //Re moves all to bottom
        for (int i =0; i< 4;i++){
            
            for (int k = 2; k > 0; k--){

                if (board[k][i] == empty){
                    board[k][i] = board[k-1][i];
                    board[k-1][i] = empty;
                }
            }
        }
        
        if (tileMoved()) {
            randomTile();
            currentBoard();
            drawBoard();
        }
        
    }
    
    //main method
    public static void main(String s[]) {
        setupBoard();
        
    }

    //Simple printer
    public static void log(Object m){
        if (console) System.out.println(m);
    }

    //Changes a int to a string
    public static String s(int x){
        return x+"";
    }
    
    //Had to include this
    private static void keyTyped() {
        
    }
    
    @Override //Had to include this
    public void keyTyped(KeyEvent e) {
        
    }    
    
}
