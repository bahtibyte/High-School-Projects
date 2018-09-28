package minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Minesweeper implements ActionListener{

    //The length of the board
    int length = 10;
    
    //The buttons
    JFrame frame = new JFrame("Minesweeper");
    JButton settings = new JButton("Settings");
    JButton play = new JButton("Play");
    JButton[][] buttons;
    
    //The board
    int[][] board;
    Container grid = new Container();
    
    //Cases
    int spawnChance = 10;
    int revealed = 8;
    int empty = 0;
    int mines = 9;
    
    boolean gameOver = false;
    boolean mode = false;
    
    public void reveal(int row, int col){
        
        //Checks for mines around click location
        int count = 0;
        for (int r = row-1; r <= row+1; r++){
            for (int c = col-1; c <= col+1; c++){
                if (isValid(board, r,c) && board[r][c] == mines){
                    count++;

                }
            }
        }
        
        //Base case 
        if (count != 0){
            
            //Colors the text to make it fancy
            if (count == 1){
                buttons[row][col].setForeground(Color.BLUE);
            }if (count == 2){
                buttons[row][col].setForeground(new Color(20,109,53));
            }if (count == 3){
                buttons[row][col].setForeground(Color.RED);
            }
            
            //Sets the values
            board[row][col] = count;
            buttons[row][col].setText(count+"");
        }
        
        //Recursion
        else {
            for (int r = row-1; r <= row+1; r++){
                for (int c = col-1; c <= col+1; c++){
                    
                    if (isValid(board,r,c) && board[r][c] != revealed){   
                        
                        //Sets the values and makes it fancy
                        board[r][c] = revealed;
                        buttons[r][c].setText("#");
                        buttons[r][c].setForeground(Color.GRAY);
                        
                        //Recursion
                        reveal(r,c);
                    }
                }
            }
        }
        
    }
    
    public void gameLost(int row, int col){
        gameOver = true;
        
        for (int r = 0; r < board.length; r++){
            for (int c = 0; c < board[r].length; c++){
                if (board[r][c] == mines){
                    buttons[r][c].setText("@");
                    buttons[r][c].setForeground(Color.RED);
                    buttons[r][c].setFont(new Font("Serif", Font.PLAIN,25));
                }
            }
        }
    }
    
    //Main Frame
    public Minesweeper(int length){
        
        buttons = new JButton[length][length];
        board = new int[length][length];
        
        frame.setSize(length*60,length*60);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(settings, BorderLayout.SOUTH);
        settings.addActionListener(this);
        grid.setLayout(new GridLayout(length,length));
        
        
        for (int r = 0; r < length; r++){
            for (int c = 0; c < length; c++){
                buttons[r][c] = new JButton();
                buttons[r][c].addActionListener(this);
                buttons[r][c].setFont(new Font("Serif", Font.PLAIN,30));
                buttons[r][c].setForeground(Color.BLACK);
                grid.add(buttons[r][c]);
            }
        }
        
        frame.add(grid,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        setBoard();
    }
    
    //Checks if player won
    public void checkForWin(){
        for (int r = 0; r < board.length; r++){
            for (int c = 0; c < board[r].length; c++){
                if (board[r][c] == empty){
                    return;
                }
            }
        }
        
        buttons[0][0].setText("Y");
        buttons[0][1].setText("O");
        buttons[0][2].setText("U");
        buttons[0][3].setText("W");
        buttons[0][4].setText("O");
        buttons[0][5].setText("N");
        
        buttons[0][0].setForeground(Color.GREEN);
        buttons[0][1].setForeground(Color.GREEN);
        buttons[0][2].setForeground(Color.GREEN);
        buttons[0][3].setForeground(Color.GREEN);
        buttons[0][4].setForeground(Color.GREEN);
        buttons[0][5].setForeground(Color.GREEN);
    }
    
    //Setting frame
    public void settings(){
        //The frame
        JFrame settingFrame = new JFrame("Settings");
        settingFrame.setSize(250,250);
        settingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingFrame.setLocation(400,400);
        settingFrame.setVisible(true);
        settingFrame.setLayout(new GridLayout(4,1));
        
        //Select Size
        JLabel selectSize = new JLabel();
        settingFrame.add(selectSize);
        selectSize.setText("Select Size");
        selectSize.setVerticalAlignment(SwingConstants.CENTER);
        selectSize.setHorizontalAlignment(SwingConstants.CENTER);
        selectSize.setFont(new Font("Serif", Font.PLAIN, 30));
        
        //The length text
        final JLabel sizeValue = new JLabel("("+length +" by " + length+")");
        sizeValue.setVerticalAlignment(SwingConstants.CENTER);
        sizeValue.setHorizontalAlignment(SwingConstants.CENTER);
        sizeValue.setFont(new Font("Serif", Font.PLAIN, 30));
        
        //The slider to choose Size
        JSlider slider = new JSlider(7,20,10);
        settingFrame.add(slider);
        slider.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent ce) {
            length = (((JSlider) ce.getSource()).getValue());
            sizeValue.setText("("+length +" by " + length+")");
            } 
        });
        
        settingFrame.add(sizeValue);
        
        //Play game Button
        play.setFont(new Font("Serif", Font.PLAIN,30));
        settingFrame.add(play);
        play.addActionListener(this);
        
    }

    //Re sets the board
    public void setBoard(){
        int random = 0;
        for (int r = 0; r < board.length; r++){
            for (int c  = 0; c < board[r].length; c++){
                random = (int) (Math.random()*spawnChance);
                if (random == 0){
                    board[r][c] = mines;
                }else {
                    board[r][c] = empty;
                }
            }
        }
    }
    
    
    public void log(Object m){
        System.out.println(m);
    }
    
    public boolean isValid(int[][] board, int row, int col){
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
    
    public static void main(String[]args){
        new Minesweeper(10);
    }

    //Keeps track of clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(settings)){
            settings();
        }if (e.getSource().equals(play)){
            gameOver = false;
            frame.dispose();
            new Minesweeper(length);
        }
        
        else {
            for (int r = 0; r < board.length; r++){
                for (int c  = 0; c < board[r].length; c++){
                    if (!gameOver && board[r][c] == empty && e.getSource().equals(buttons[r][c])){
                        reveal(r,c);
                        checkForWin();
                        return;
                    }else if(board[r][c] == mines && e.getSource().equals(buttons[r][c])){
                        gameOver = true;
                        gameLost(r,c);
                        return;
                    }
                }
            }
        }
        
        
        
    }
    
}