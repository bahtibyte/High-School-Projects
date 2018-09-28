package connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connect4 extends JFrame implements MouseListener { 

    
    int yAxis = 5;
    int xAxis = 6;
    int col1 = 7, col2 = 7, col3 = 7, col4 = 7, col5 = 7, col6 = 7, col7 = 7;
    int temp1 = 0;
    
    private int x=1000;  
    private int y=1000;  

    int player1 = 1;
    int player2 = 2;
    int player = player1;
    int empty = 0;
    int winner = 0;

    boolean intial = true;

    int[][] board = new int[6][7];

    public void setUp(){
        for (int i = 0; i <6; i++){
            for (int k = 0; k<7; k++){
                board[i][k] = empty;
            }
        }
    }
    
    public void drawBoard(){
    
        for (int i =0; i<6;i++){
            for (int k =0; k<7;k++){
                System.out.print(board[i][k]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
    
    }
    
    public void log(Object m){
        System.out.println(m);
    }
    
    public void checkWin(){
        
        for (int i =0; i<6;i++){
            for (int k = 0;k<4;k++){
                if (board[i][k] == board[i][k+1] && board[i][k+1] == board[i][k+2] && board[i][k+2] == board[i][k+3] && board[i][k] == player1) {
                     
                    winner = player1;
                    log("winner player1 - hoz");
                    repaint();
                    return;
                }
                if (board[i][k] == board[i][k+1] && board[i][k+1] == board[i][k+2] && board[i][k+2] == board[i][k+3] && board[i][k] == player2) {
                    winner = player2;
                    log("winner player2");
                    repaint();
                    return;
                }
            }
        }
        
        
        
        for (int i = 0; i<3;i++){
            for (int k =0; k<7; k++){
                
                if (board[i][k] == board[i+1][k] && board[i+1][k] == board[i+2][k] && board[i+2][k] == board[i+3][k] && board[i][k] == player1){
                    winner = player1;
                    log("winner player1");
                    repaint();
                    return;
                }
                if (board[i][k] == board[i+1][k] && board[i+1][k] == board[i+2][k] && board[i+2][k] == board[i+3][k] && board[i][k] == player2){
                    winner = player2;
                    log("winner player2");
                    repaint();
                    return;
                }
            }
        }
        
        
        for (int i =0; i<3;i++){
            for (int k = 5;k>2;k--){
                
                if (board[i][k] == board[i+1][k-1] && board[i+1][k-1] == board[i+2][k-2] && board[i+2][k-2] == board[i+3][k-3] && board[i][k] == player1) {
                    winner = player1;
                    log("winner player1");
                    repaint();
                    return;
                }
                
                if (board[i][k] == board[i+1][k-1] && board[i+1][k-1] == board[i+2][k-2] && board[i+2][k-2] == board[i+3][k-3] && board[i][k] == player2) {
                    winner = player2;
                    log("winner player2");
                    repaint();
                    return;
                }
            }
        }
        
        for (int i =0; i<3;i++){
            for (int k = 0;k<4;k++){
                
                if (board[i][k] == board[i+1][k+1] && board[i+1][k+1] == board[i+2][k+2] && board[i+2][k+2] == board[i+3][k+3] && board[i][k] == player1) {
                    winner = player1;
                    log("winner player1");
                    repaint();
                    return;
                }
                
                if (board[i][k] == board[i+1][k+1] && board[i+1][k+1] == board[i+2][k+2] && board[i+2][k+2] == board[i+3][k+3] && board[i][k] == player2) {
                    winner = player2;
                    log("winner player2");
                    repaint();
                    return;
                }
            }
        }
    }
    
    public Connect4() {
    setSize(820,850);
    setLocation(100,100);
    addMouseListener(this); 
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    
    setUp();
  }

    public void paint(Graphics g) {

    
        
    int temp = y;
    
    Font currentFont = g.getFont();
    Font newFont = currentFont.deriveFont(currentFont.getSize() * 5F);

    if (winner > empty) {
        
       
        
        g.setColor(new Color(100,255,100));
        g.fillRect(0, 0, 820, 130);
        newFont = currentFont.deriveFont(currentFont.getSize() * 5F);
        g.setFont(newFont);
        g.setColor(Color.BLACK);
        
        
        
        if (winner == player1) {
            g.drawString("Player Black Won", 50, 100);
        }
        if (winner == player2) {
            g.drawString("Player Red Won", 50, 100);
        }
        
        g.drawRect(700, 25, 100, 100);
        newFont = currentFont.deriveFont(currentFont.getSize() * 2.5F);
        g.setFont(newFont);
        g.drawString("New",715,65);
        g.drawString("Game",705,100);
        
        return;
    }
    
    if (intial) {
        intial = false;
        g.setColor(new Color(0,150,255));
        g.fillRect(0,130,820,850);
        g.setColor(Color.WHITE);
        for (int i = 15; i < 820; i+=115){

            for (int k = 160; k < 850; k+=115){
                g.fillOval(i,k,100,100);
                
            }
        }
        g.setColor(new Color(100,255,100));
        g.fillRect(0, 0, 820, 130);
        
        g.setColor(Color.BLACK);
        
        
    }

    
    if (!intial){
        
        for (int i = 0; i<temp1; i++){
            
            g.setColor(Color.WHITE);
            g.fillOval(x,temp,100,100);
            if (player == player1) g.setColor(Color.BLACK);
            if (player == player2) g.setColor(Color.RED);
            g.fillOval(x,y+(i*115),100,100);
            temp = y+(i*115);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
        
        g.setFont(newFont);
        
        g.setColor(new Color(100,255,100));
        g.fillRect(0, 0, 820, 130);
        
        g.setColor(Color.BLACK);
        if (player == player2) g.drawString("Player Black Turn", 50, 100);
        if (player == player1) g.drawString("Player Red Turn", 50,100);
        
        
        
        temp1 = empty;
        
        
        
    }
    g.drawRect(700, 25, 100, 100);
    newFont = currentFont.deriveFont(currentFont.getSize() * 2.5F);
    g.setFont(newFont);
    g.drawString("New",715,65);
    g.drawString("Game",705,100);
    checkWin();

}
    
    

    public void mouseClicked(MouseEvent e) { 
       
        
        x = e.getX();   
        y = 160;   

        if (player == player1)player = player2;
        else player = player1;
        
        
        if (x >= 700 && x <= 800 && e.getY() >= 25 && e.getY() <= 125) {
            winner = 0;
            col1 = 7; col2 = 7; col3 = 7; col4 = 7; col5 = 7; col6 = 7; col7 = 7;
            setUp();
            intial = true;
            repaint();
            return;
        }
        
        
        if (winner > 0) return;
        
        if (x >= 0 && x <= 121) {
            x=15;
            col1--;
            temp1 = col1;
            if (player == player1) board[col1-1][0] = player1;
            else board[col1-1][0] = player2;
        }
        if (x >= 122 && x <= 237) {
            x=130;
            col2--;
            temp1 = col2;
            if (player == player1) board[col2-1][1] = player1;
            else board[col2-1][1] = player2;
        }
        if (x >= 238 && x <= 352) {
            x = 245;
            col3--;
            temp1 = col3;
            if (player == player1) board[col3-1][2] = player1;
            else board[col3-1][2] = player2;
        }
        if (x >= 353 && x <= 467) {
            x = 360;
            col4--;
            temp1 = col4;
            if (player == player1) board[col4-1][3] = player1;
            else board[col4-1][3] = player2;
        }
        if (x >= 468 && x <= 572) {
            x = 475;
            col5--;
            temp1 = col5;
            if (player == player1) board[col5-1][4] = player1;
            else board[col5-1][4] = player2;
        }
        if (x >= 573 && x <= 687) {
            x = 590;
            col6--;
            temp1 = col6;
            if (player == player1) board[col6-1][5] = player1;
            else board[col6-1][5] = player2;
        }
        if (x >= 688 && x <= 820) {
            x = 705;
            col7--;
            temp1 = col7;
            if (player == player1) board[col7-1][6] = player1;
            else board[col7-1][6] = player2;
        }

        drawBoard();

        repaint(); 
    }

    public static void main(String argv[]) {
      Connect4 c = new Connect4();
    }
    public void mouseReleased(MouseEvent e ) { }
    public void mouseEntered(MouseEvent e)   { }
    public void mouseExited(MouseEvent e)    { }
    public void mousePressed(MouseEvent e)   { }
}