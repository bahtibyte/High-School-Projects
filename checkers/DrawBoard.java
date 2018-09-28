package checkers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DrawBoard {

    int blue = 2;
    int red = 1;
    int empty = 0;
    
    private BufferedImage menu;
    
    public void drawBackground(Graphics g) {
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 800);
        g.setColor(Color.BLACK);
        
        for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                if (r%2 == 0){
                    if (c%2 == 1){
                        g.fillRect(c*100,r*100,100,100);
                    }
                }
                if (r%2 == 1){
                    if (c%2 == 0){
                        g.fillRect(c*100,r*100,100,100);
                    }
                }
            }
        }
        
    }
    
    public void drawCoords(Graphics g){
        for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                g.setColor(Color.GRAY);
                g.drawString("("+r+","+c+")", c*100 + 35, r*100 + 55);
            }
        }
    }
    
    public void drawCheckers(Graphics g,int[][] board){
        for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                if (r%2 == 0){
                    if (c%2 == 1){

                        if (board[r][c] == 1){
                            g.setColor(Color.RED);
                            g.fillOval(c*100 + 12,r*100 + 12,75,75);
                        }if (board[r][c] == 2){
                            g.setColor(Color.BLUE);
                            g.fillOval(c*100 + 12,r*100 + 12,75,75);
                        }if (board[r][c] == 3){
                            g.setColor(Color.RED);
                            g.fillOval(c*100 + 12,r*100 + 12,75,75);
                            g.setColor(Color.ORANGE);
                            g.fillOval(c*100 + 25,r*100 + 25,50,50);
                        }if (board[r][c] == 4){
                            g.setColor(Color.BLUE);
                            g.fillOval(c*100 + 12,r*100 + 12,75,75);
                            g.setColor(Color.ORANGE);
                            g.fillOval(c*100 + 25,r*100 + 25,50,50);
                        }

                        
                    }
                }
                if (r%2 == 1){
                    if (c%2 == 0){
                    
                        if (board[r][c] == 1){
                            g.setColor(Color.RED);
                            g.fillOval(c*100 + 12,r*100 + 12,75,75);
                        }if (board[r][c] == 2){
                            g.setColor(Color.BLUE);
                            g.fillOval(c*100 + 12,r*100 + 12,75,75);
                        }if (board[r][c] == 3){
                            g.setColor(Color.RED);
                            g.fillOval(c*100 + 12,r*100 + 12,75,75);
                            g.setColor(Color.ORANGE);
                            g.fillOval(c*100 + 25,r*100 + 25,50,50);
                        }if (board[r][c] == 4){
                            g.setColor(Color.BLUE);
                            g.fillOval(c*100 + 12,r*100 + 12,75,75);
                            g.setColor(Color.ORANGE);
                            g.fillOval(c*100 + 25,r*100 + 25,50,50);
                        }
                    }
                }
            }
        }
    }
    
    public void drawHighLight(Graphics g,int x1, int y1, boolean click,int[][] board, int playerTurn){
        
        if (click && playerTurn == blue){
            
            if (board[x1][y1] == blue+2){
                drawKingHighLight(g,x1,y1,click,board,playerTurn);
                
            }else {

                g.setColor(Color.WHITE);
                g.drawRect(y1*100 + 8,x1*100 + 8,83,83);

                if ((y1+1<=7)&&(x1-1>=0)&&board[x1-1][y1+1] == 0){
                    g.drawRect((y1+1)*100 + 8,(x1-1)*100 + 8,83,83);
                }if ((y1-1>=0)&&(x1-1>=0)&&board[x1-1][y1-1] == 0){
                    g.drawRect((y1-1)*100 + 8,(x1-1)*100 + 8,83,83);
                }if ((y1+2<=7)&&(x1-2>=0)&&board[x1-1][y1+1] == red && board[x1-2][y1+2] == 0){
                    g.drawRect((y1+2)*100 + 8,(x1-2)*100 + 8,83,83);
                }if ((y1-2>=0)&&(x1-2>=0)&&board[x1-1][y1-1] == red && board[x1-2][y1-2] == 0){
                    g.drawRect((y1-2)*100 + 8,(x1-2)*100 + 8,83,83);
                }
            }
            
        }if (click && playerTurn == red){
            
            if (board[x1][y1] == 3){
                System.out.println("gg");
                drawKingHighLight(g,x1,y1,click,board,playerTurn);
                
            }else {
            
                g.setColor(Color.WHITE);
                g.drawRect(y1*100 + 8,x1*100 + 8,83,83);
                
                if ((y1-1>=0)&&(x1+1<=7)&&board[x1+1][y1-1] == 0){
                    g.drawRect((y1-1)*100 + 8,(x1+1)*100 + 8,83,83);
                }if ((y1+1<=7)&&(x1+1<=7)&&board[x1+1][y1+1] == 0){
                    g.drawRect((y1+1)*100 + 8,(x1+1)*100 + 8,83,83);
                }if ((y1-2>=0)&&(x1+2<=7)&&board[x1+1][y1-1] == blue && board[x1+2][y1-2] == 0){
                    g.drawRect((y1-2)*100 + 8,(x1+2)*100 + 8,83,83);
                }if ((y1+2<=7)&&(x1+2<=7)&&board[x1+1][y1+1] == blue && board[x1+2][y1+2] == 0){
                    g.drawRect((y1+2)*100 + 8,(x1+2)*100 + 8,83,83);
                }
            }
        }
    }
    
    public void drawKingHighLight(Graphics g, int x1, int y1, boolean click, int[][] board, int playerTurn){
        
        int topLeft  = (x1 > y1) ? y1 : x1;
        int topRight = (x1 > 7 - y1) ? 7-y1 : x1;
        int botLeft = (7-x1 > y1) ? y1 : 7-x1;
        int botRight = (7-x1 > 7-y1) ? 7-y1 : 7-x1;
        
        g.setColor(Color.WHITE);
        g.drawRect(y1*100 + 8,x1*100 + 8,83,83);
        
        int countBlue = 0;
        int countRed = 0;
        
        //Top Left
        for (int i = 1; i <= topLeft; i++){
            
            if (board[x1-i][y1-i] == playerTurn+2){
                break;
            }
            
            if (playerTurn == red){
                if (board[x1-i][y1-i] == 4){
                    countBlue++;
                }
                if (countBlue == 2){
                    countBlue = 0;
                    break;
                }
            }else if (playerTurn == blue){
                if (board[x1-i][y1-i] == 3){
                    countRed++;
                }
                if (countRed == 2){
                    countRed = 0;
                    break;
                }
            }
            
            if (board[x1-i][y1-i] == empty){
                g.drawRect((y1-i)*100 + 8,(x1-i)*100 + 8,83,83);
            }
        }
        
        //Top Right
        for (int i = 1; i <= topRight; i++){
            
            if (board[x1-i][y1+i] == playerTurn+2){
                break;
            }
            
            if (playerTurn == red){
                if (board[x1-i][y1+i] == 4){
                    countBlue++;
                }
                if (countBlue == 2){
                    countBlue = 0;
                    break;
                }
            }else if (playerTurn == blue){
                if (board[x1-i][y1+i] == 3){
                    countRed++;
                }
                if (countRed == 2){
                    countRed = 0;
                    break;
                }
            }
            
            if (board[x1-i][y1+i] == empty){
                g.drawRect((y1+i)*100 + 8,(x1-i)*100 + 8,83,83);
            }
            
        }
        
        //Bot Left
        for (int i = 1; i <= botLeft; i++){
            
            if (board[x1+i][y1-i] == playerTurn+2){
                break;
            }
            
            if (playerTurn == red){
                if (board[x1+i][y1-i] == 4){
                    countBlue++;
                }
                if (countBlue == 2){
                    countBlue = 0;
                    break;
                }
            }else if (playerTurn == blue){
                if (board[x1+i][y1-i] == 3){
                    countRed++;
                }
                if (countRed == 2){
                    countRed = 0;
                    break;
                }
            }
            
            if (board[x1+i][y1-i] == empty){
                g.drawRect((y1-i)*100 + 8,(x1+i)*100 + 8,83,83);
            }
            
        }
        
        //Bot Right
        for (int i = 1; i <= botRight; i++){
            
            if (board[x1+i][y1+i] == playerTurn+2){
                break;
            }
            
            if (playerTurn == red){
                if (board[x1+i][y1+i] == 4){
                    countBlue++;
                }
                if (countBlue == 2){
                    countBlue = 0;
                    break;
                }
            }else if (playerTurn == blue){
                if (board[x1+i][y1+i] == 3){
                    countRed++;
                }
                if (countRed == 2){
                    countRed = 0;
                    break;
                }
            }
            
            if (board[x1+i][y1+i] == empty){
                g.drawRect((y1+i)*100 + 8,(x1+i)*100 + 8,83,83);
            }
            
        }
    }
    
    
    
    public void drawMenu(Graphics g){
        
        //BackGround 
        g.setColor(Color.WHITE);
        g.fillRect(800, 0, 200, 800);
        
        try {
            menu = ImageIO.read(getClass().getResourceAsStream("menu.png"));
        } catch(IOException e){
        }
        
        g.drawImage(menu,800,0,200,800,null);
        
        
    }
    
    public void drawSelectMode(Graphics g){
        
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 8F);
        g.setFont(newFont);
        g.setColor(Color.GREEN);
        g.drawString("SELECT MODE", 48, 435);
    }
    
    public void log(Object m){
        System.out.println(m);
    }
    
}