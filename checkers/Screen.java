package checkers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Screen extends JPanel implements ActionListener, KeyListener {
    
    //Declaring and setting variables
    DrawBoard drawBoard = new DrawBoard(); 
    Ai ai = new Ai();
    
    int[][] board = new int[8][8];
    int[] checkersLeft = new int[2];
    
    int playerRed = 1;
    int playerBlue = 2;
    int playerRedKing = 3;
    int playerBlueKing = 4;
    int empty = 0;
    
    int playerTurn = -1;
    
    boolean click1 = false;
    boolean click2 = false;
    
    int c1x = -1;
    int c1y = -1;
    int c2x = -1;
    int c2y = -1;
    
    int PvP = 1;
    int PvAI = 2;
    
    int gameMode = 0;
    
    boolean showMoves = true;
    boolean showCoords = false;
    
    boolean intial = true;
    
    int direction = 0;
    
    int topLeft = 1; 
    int topRight = 2;
    int botLeft = 3;
    int botRight = 4;
    
    int countRed = 0;
    int redX = -1,redY = -1;
    
    int countBlue = 0;
    int blueX = -1,blueY = -1;
    
    public Screen() {

        addKeyListener(this);
        setFocusable(true);
        
        if (intial){
            //Reset the checkers
            setCheckers();
            randomPlayer();
            intial = false;
        }
        
        addMouseListener(new MouseAdapter() { 
        @Override
        public void mousePressed(MouseEvent m) {
            
            
            if (m.getX() >= 820 && m.getX() <= 985){
                
                if (m.getY() >= 490 && m.getY() <= 540){
                    if (showCoords) {
                        log("Settings - Coords are now hidden");
                        showCoords = false;
                    }else {
                        log("Settings - Coords are now showen");
                        showCoords = true;
                    }
                }else if (m.getY() >= 560 && m.getY() <= 610){
                    if (showMoves) {
                        log("Settings - Possible Moves are now hidden");
                        showMoves = false;
                    }else {
                        log("Settings - Possible Moves are now showen");
                        showMoves = true;
                    }
                }else if (m.getY() >= 335 && m.getY() <= 385){
                    gameMode = PvAI;
                    System.out.println("\nNew Game");
                    log("Game Mode - Player vs AI set");
                    resetClicks();
                    setCheckers();
                    randomPlayer();
                    
                }else if (m.getY() >= 265 && m.getY() <= 315){
                    gameMode = PvP;
                    System.out.println("\nNew Game");
                    resetClicks();
                    setCheckers();
                    randomPlayer();
                    log("Game Mode - Player vs Player set");
                }
                
            }
            
            
            if (m.getX() >= 805 && m.getX() <= 990){
                if (m.getY() >= 110 && m.getY() <= 172){
                    System.out.println("\nNew Game");
                    randomPlayer();
                    resetClicks();
                    setCheckers();
                }
                
                if (m.getY() >= 665 && m.getY() <= 855){
                    System.exit(0);
                }
            }
            
            else {
                clickLoc(m.getX(),m.getY());
            }
            
            repaint();
            
            } 
        }); 

        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        //Repaints the background
        drawBoard.drawBackground(g);
        
        //Paints all the checkers
        drawBoard.drawCheckers(g,board);
        
        
        //High lights current click
        if (showMoves){
            drawBoard.drawHighLight(g,c1x,c1y,click1,board,playerTurn);
        }
        
        if (showCoords){
            drawBoard.drawCoords(g);
        }
        
        drawBoard.drawMenu(g);
        
        if (gameMode == empty){
            drawBoard.drawSelectMode(g);
        }
        
    }

    public void clickLoc(int x, int y){
        
        for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                
                //Coordinates of the click location
                if (x >= c*100 && x < (c+1)*100){
                    if (y >= r*100 && y < (r+1) * 100){
                        
                        //Even row / Odd col
                        if (r%2 == 0){
                            if (c%2 == 1){
                                
                                //Player Red clicked
                                if (playerTurn == playerRed){
                                    
                                    //Red Normal Checker 
                                    if (board[r][c] == playerRed){
                                        
                                        //Sets first click if both is false
                                        if (!click1 && !click2){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 set at ("+c1x+","+c1y+") (Red)");
                                        }
                                        
                                        //Resets the first click
                                        else if (click1){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 Re-set at ("+c1x+","+c1y+") (Red)");
                                        }
                                        
                                    }
                                    
                                    //Red King Checker
                                    else if (board[r][c] == playerRedKing){
                                        
                                        //Sets first click if both is false
                                        if (!click1 && !click2){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 set at ("+c1x+","+c1y+") (Red King)");
                                        }
                                        
                                        //Resets the first click
                                        else if (click1){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 Re-set at ("+c1x+","+c1y+") (Red King");
                                        }
                                        
                                    }
                                    
                                    //2nd Click for Red
                                    else if (click1 && !click2){
                                        
                                        click2 = true;
                                        c2x = r;
                                        c2y = c;
                                        log("Click 2 set at ("+c2x+","+c2y+") ");
                                        
                                        moveChecker(c1x,c1y,c2x,c2y);
                                        
                                    }
                                    
                                }
                                
                                //Even row / Odd col for player Blue
                                if (playerTurn == playerBlue){
                                    
                                    //Blue Normal Checker
                                    if (board[r][c] == playerBlue){
                                        
                                        //Sets the first click if both is false
                                        if (!click1 && !click2){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 set at ("+c1x+","+c1y+") (Blue)");
                                        }
                                        
                                        //Re sets the first click
                                        else if (click1){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 Re-set at ("+c1x+","+c1y+") (Blue)");
                                        }
                                        
                                    }
                                    
                                    //Blue King Checker
                                    else if (board[r][c] == playerBlueKing){
                                        
                                        //Sets the first click if both is false
                                        if (!click1 && !click2){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 set at ("+c1x+","+c1y+") (Blue King)");
                                        }
                                        
                                        //Re sets the first click
                                        else if (click1){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 Re-set at ("+c1x+","+c1y+") (Blue King)");
                                        }
                                        
                                    }
                                    
                                    //2nd click for blue
                                    else if (click1 && !click2){
                                        click2 = true;
                                        c2x = r;
                                        c2y = c;
                                        log("Click 2 set at ("+c2x+","+c2y+")");
                                        
                                        moveChecker(c1x,c1y,c2x,c2y);
                                    }
                                }
                                
                            }
                        }
                        
                        //Odd row / Even col
                        if (r%2 == 1){
                            if (c%2 == 0){
                                
                                //Blue clicks
                                if (playerTurn == playerRed){
                                    
                                    //Red Normal Checker
                                    if (board[r][c] == playerRed){
                                        
                                        //Sets first click if both are false
                                        if (!click1 && !click2){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 set at ("+c1x+","+c1y+") (Red)");
                                        }
                                        
                                        //Re sets the first click
                                        else if (click1){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 Re-set at ("+c1x+","+c1y+") (Red)");
                                        }
                                    }
                                    
                                    //Red King Checker
                                    else if (board[r][c] == playerRedKing){
                                        
                                        //Sets the first click if both are false
                                        if (!click1 && !click2){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 set at ("+c1x+","+c1y+") (Red King)");
                                        }
                                        
                                        //Re sets the first click
                                        else if (click1){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 Re-set at ("+c1x+","+c1y+") (Red King)");
                                        }
                                    }
                                    
                                    //2nd click is set
                                    else if (click1 && !click2){
                                        click2 = true;
                                        c2x = r;
                                        c2y = c;
                                        log("Click 2 set at ("+c2x+","+c2y+")");
                                        
                                        moveChecker(c1x,c1y,c2x,c2y);
                                        
                                    }
                                }      
                                
                                //Blue player clicks
                                if (playerTurn == playerBlue){
                                    
                                    //Blue Normal Checker
                                    if (board[r][c] == playerBlue){
                                        
                                        //Sets first click if both are false
                                        if (!click1 && !click2){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 set at ("+c1x+","+c1y+") (Blue)");
                                        }
                                        
                                        //Re sets the first click
                                        else if (click1){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 Re-set at ("+c1x+","+c1y+") (Blue)");
                                        }
                                    }
                                    
                                    //Blue King Checker
                                    else if (board[r][c] == playerBlueKing){
                                        
                                        //Sets the first click
                                        if (!click1 && !click2){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 set at ("+c1x+","+c1y+") (Blue King)");
                                        }
                                        
                                        //Re sets the first click
                                        else if (click1){
                                            click1 = true;
                                            c1x = r;
                                            c1y = c;
                                            log("Click 1 Re-set at ("+c1x+","+c1y+") (Blue King)");
                                        }
                                    }
                                    
                                    //2nd click is set
                                    else if (click1 && !click2){
                                        click2 = true;
                                        c2x = r;
                                        c2y = c;
                                        log("Click 2 set at ("+c2x+","+c2y+")");
                                        
                                        moveChecker(c1x,c1y,c2x,c2y);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void setCheckers(){
        
        checkersLeft[0] = 12;
        checkersLeft[1] = 12;
        
        //Emptys the board
        for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                board[r][c] = empty;
            }
        }
        
        //Sets the board for the 2 players
        for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                if (r%2 == 0){
                    if (c%2 == 1){
                        if (r<3){
                            board[r][c] = playerRed;
                        }if (r > 4){
                            board[r][c] = playerBlue;
                        }
                    }
                }
                if (r%2 == 1){
                    if (c%2 == 0){
                        if (r<3) {
                            board[r][c] = playerRed;
                        }if (r > 4){
                            board[r][c] = playerBlue;
                        }
                    }
                }
                
            }
        }
        

    }
    
    public void resetClicks(){
        click1 = false;
        click2 = false;
        c1x = -1;
        c1y = -1;
        c2x = -1;
        c2y = -1;
        
        winner = 0;
    }
    
    public void randomPlayer(){
        int ran = (int) (Math.random()*2);
        
        
        if (ran == 0 && gameMode == PvP){
            log("Player Blue starts first");
            playerTurn = playerBlue;
        }else if (ran == 1 && gameMode == PvP){
            log("Player Red starts first");
            playerTurn = playerRed;
        }else if (ran == 0 && gameMode == PvAI){
            playerTurn = playerBlue;
            log("Player Blue starts first");
        }else if (ran == 1 && gameMode == PvAI){
            playerTurn = playerBlue;
            log("AI Red starts first");
            ai.main(board,checkersLeft);
            repaint();
        }
        
    }

    public boolean normalMovement(int x1, int y1, int x2, int y2){
        
        if (board[x2][y2] != empty){
            if (playerTurn == playerRed) {
                log("Player Red Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
            }if (playerTurn == playerBlue){
                log("Player Blue Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
            }
            log("Click 2 resetted");
            click2 = false;
            return false;
        }
        
        //log("x1: "+x1+" x2: "+x2+" y1: "+y1+" y2: "+y2);
        
        if (playerTurn == playerBlue){
            if (x2+1 == x1 && y2-1 == y1){
                log("Player Blue movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                return true;
            }else if (x2+1 == x1 && y2+1 == y1){
                log("Player Blue movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                return true;
            }else if (x2+2 == x1 && y2-2 == y1 && board[x1-1][y1+1] == playerRed){
                log("Player Blue movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                log("Player Blue took over player Red at ("+(x1-1)+","+(y1+1)+")");
                board[x1-1][y1+1] = empty;
                checkersLeft[0]--;
                return true;
            }else if (x2+2 == x1 && y2+2 == y1 && board[x1-1][y1-1] == playerRed){
                log("Player Blue movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                log("Player Blue took over player Red at ("+(x1-1)+","+(y1-1)+")");
                board[x1-1][y1-1] = empty;
                checkersLeft[0]--;
                return true;
            }else if (x2+2 == x1 && y2-2 == y1 && board[x1-1][y1+1] == playerRedKing){
                log("Player Blue movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                log("Player Blue took over player Red King at ("+(x1-1)+","+(y1+1)+")");
                board[x1-1][y1+1] = empty;
                checkersLeft[0]--;
                return true;
            }else if (x2+2 == x1 && y2+2 == y1 && board[x1-1][y1-1] == playerRedKing){
                log("Player Blue movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                log("Player Blue took over player Red King at ("+(x1-1)+","+(y1-1)+")");
                board[x1-1][y1-1] = empty;
                checkersLeft[0]--;
                return true;
            }else {
                log("Player Blue invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                log("Resetting all clicks - (Invalid Movement)");
                resetClicks();
            }
        }
        
        else if (playerTurn == playerRed){
            if (x2-1 == x1 && y2+1 == y1){
                log("Player Red movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                return true;
            }else if (x2-1 == x1 && y2-1 == y1){
                log("Player Red movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                return true;
            }else if (x2-2 == x1 && y2+2 == y1 && board[x1+1][y1-1] == playerBlue){
                log("Player Red movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                log("Player Red took over player Blue at ("+(x1+1)+","+(y1-1)+")");
                board[x1+1][y1-1] = empty;
                checkersLeft[1]--;
                return true;
            }else if (x2-2 == x1 && y2-2 == y1 && board[x1+1][y1+1] == playerBlue){
                log("Player Red movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                log("Player Red took over player Blue at ("+(x1+1)+","+(y1+1)+")");
                board[x1+1][y1+1] = empty;
                checkersLeft[1]--;
                return true;
            }else if (x2-2 == x1 && y2+2 == y1 && board[x1+1][y1-1] == playerBlueKing){
                log("Player Red movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                log("Player Red took over player Blue King at ("+(x1+1)+","+(y1-1)+")");
                board[x1+1][y1-1] = empty;
                checkersLeft[1]--;
                return true;
            }else if (x2-2 == x1 && y2-2 == y1 && board[x1+1][y1+1] == playerBlueKing){
                log("Player Red movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                log("Player Red King took over player Blue at ("+(x1+1)+","+(y1+1)+")");
                board[x1+1][y1+1] = empty;
                checkersLeft[1]--;
                return true;
            }else {
                log("Player Red invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                log("Resetting all clicks - (Invalid Movement)");
                resetClicks();
            }
        }
        
        return false;
    }
    
    public boolean kingMovement(int x1, int y1, int x2, int y2){
        
        //Top Left: x2 < x1 && y2 < y1
        //Top Right: x2 < x1 && y2 > y1
        //Bot Left: x2 > x1 && y2 < y1
        //Bot Right: x2 > x1 && y2 > y1
        
        direction = 0;
        countRed = 0;
        countBlue = 0;
        
        redX = -1;
        redY = -1;
        blueX = -1;
        blueY = -1;
        
        log("X1: "+x1+" X2: "+x2+" Y1: "+y1+" Y2: "+y2);
        
        //If the location isnt empty
        if (board[x2][y2] != empty){
            if (playerTurn == playerRed) {
                log("Player Red Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
            }if (playerTurn == playerBlue){
                log("Player Blue Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
            }
            log("Click 2 resetted");
            click2 = false;
            return false;
        }
        
        //If the second location isnt proportional
        if (Math.abs(x2-x1) != Math.abs(y2-y1)){
            if (playerTurn == playerRed) {
                log("Player Red Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
            }if (playerTurn == playerBlue){
                log("Player Blue Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
            }
            log("Click 2 resetted");
            click2 = false;
            return false;
        }
        
        if (direction == 0){
            if (x2 < x1 && y2 < y1){
                direction = topLeft;
            }
            else if (x2 < x1 && y2 > y1){
                direction = topRight;
            }
            else if (x2 > x1 && y2 < y1){
                direction = botLeft;
            }
            else if (x2 > x1 && y2 > y1){
                direction = botRight;
            }
        }
        
        if (playerTurn == playerBlue){
            
            for (int i = 1; i<= Math.abs(x2-x1);i++){
                
                if (Math.abs(x2-x1) == 1){
                    if (board[x2][y2] == empty){
                        log("Player Blue movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                        return true;
                    }
                }
                
                switch (direction){
                    case 1:
                        if (board[x1-i][y1-i] == playerTurn){
                            log("Player Blue Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }if (board[x1-i][y1-i] == playerRed || board[x1-i][y1-i] == playerRedKing){
                            countRed++;
                            redX = x1-i;
                            redY = y1-i;
                        }
                        if (countRed > 1){
                            log("Player Blue Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }
                        break;
                    case 2:
                        if (board[x1-i][y1+i] == playerTurn){
                            log("Player Blue Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }if (board[x1-i][y1+i] == playerRed || board[x1-i][y1+i] == playerRedKing){
                            countRed++;
                            redX = x1-i;
                            redY = y1+i;
                        }
                        if (countRed > 1){
                            log("Player Blue Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }
                        break;
                    case 3:
                        if (board[x1+i][y1-i] == playerTurn){
                            log("Player Blue Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }if (board[x1+i][y1-i] == playerRed || board[x1+i][y1-i] == playerRedKing){
                            countRed++;
                            redX = x1+i;
                            redY = y1-i;
                        }
                        if (countRed > 1){
                            log("Player Blue Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }
                        break;
                    case 4:
                        if (board[x1+i][y1+i] == playerTurn){
                            log("Player Blue Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }if (board[x1+i][y1+i] == playerRed || board[x1+i][y1+i] == playerRedKing){
                            countRed++;
                            redX = x1+i;
                            redY = y1+i;
                        }
                        if (countRed > 1){
                            log("Player Blue Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }
                        break;
                }
                
                
                
                
            }
            
            if (countRed == 1){
                board[redX][redY] = empty;
                checkersLeft[0]--;
                log("Player Blue took over player Red at ("+redX+","+redY+")");
                
            }
            
            return true;
        }
        
        if (playerTurn == playerRed){
            
            for (int i = 1; i<= Math.abs(x2-x1);i++){
                
                if (Math.abs(x2-x1) == 1){
                    if (board[x2][y2] == empty){
                        log("Player Red movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                        return true;
                    }
                }
                
                switch (direction){
                    case 1:
                        if (board[x1-i][y1-i] == playerTurn){
                            log("Player Red Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }if (board[x1-i][y1-i] == playerBlue || board[x1-i][y1-i] == playerBlueKing){
                            countBlue++;
                            blueX = x1-i;
                            blueY = y1-i;
                        }
                        if (countBlue > 1){
                            log("Player Red Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }
                        break;
                    case 2:
                        if (board[x1-i][y1+i] == playerTurn){
                            log("Player Red Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }if (board[x1-i][y1+i] == playerBlue || board[x1-i][y1+i] == playerBlueKing){
                            countBlue++;
                            blueX = x1-i;
                            blueY = y1+i;
                        }
                        if (countBlue > 1){
                            log("Player Red Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }
                        break;
                    case 3:
                        if (board[x1+i][y1-i] == playerTurn){
                            log("Player Red Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }if (board[x1+i][y1-i] == playerBlue || board[x1+i][y1-i] == playerBlueKing){
                            countBlue++;
                            blueX = x1+i;
                            blueY = y1-i;
                        }
                        if (countBlue > 1){
                            log("Player Red Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }
                        break;
                    case 4:
                        if (board[x1+i][y1+i] == playerTurn){
                            log("Player Red Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }if (board[x1+i][y1+i] == playerBlue || board[x1+i][y1+i] == playerBlueKing){
                            countBlue++;
                            blueX = x1+i;
                            blueY = y1+i;
                        }
                        if (countBlue > 1){
                            log("Player Red Invalid movement from ("+x1+","+y1+")"+" to "+"("+x2+","+y2+")");
                            return false;
                        }
                        break;
                }
                
                
                
                
            }
            
            if (countBlue == 1){
                board[blueX][blueY] = empty;
                checkersLeft[1]--;
                log("Player Red took over player Blue at ("+blueX+","+blueY+")");
                
            }
            
            return true;
        }
        
        
        return false;
    }
    
    public void moveChecker(int x1,int y1, int x2, int y2) {
        
        if (board[x1][y1] == playerTurn+2){
            if (kingMovement(x1,y1,x2,y2)){
                
                board[x1][y1] = empty;
                board[x2][y2] = playerTurn+2;
                
                resetClicks();
                
                if (gameMode == PvP){
                    if (playerTurn == playerBlue){
                        log("Player Red turn");
                        playerTurn = playerRed;
                    }

                    else {
                        log("Player Blue turn");
                        playerTurn = playerBlue;
                    }
                }else if (gameMode == PvAI){
                    ai.main(board,checkersLeft);
                    repaint();
                }
            }else {
                resetClicks();
                log("Resetting clicks");
            }
            
            if (checkForWin(checkersLeft)){
                gameOver();
            }
            
        }else if (board[x1][y1] == playerTurn){
            if (normalMovement(x1,y1,x2,y2)){
                
                board[x1][y1] = empty;
                
                if (checkForKing(x2,y2)){
                    board[x2][y2] = playerTurn+2;
                }
                
                else{
                     board[x2][y2] = playerTurn;
                }
                resetClicks();

                if (gameMode == PvP){
                    if (playerTurn == playerBlue){
                        log("Player Red turn");
                        playerTurn = playerRed;
                    }

                    else {
                        log("Player Blue turn");
                        playerTurn = playerBlue;
                    }
                }else if (gameMode == PvAI){
                    ai.main(board,checkersLeft);
                    repaint();
                }
            }
            
            if (checkForWin(checkersLeft)){
                gameOver();
            }
            
            
        }
    }
    
    public boolean checkForKing(int x, int y){
        if (x == 0){
            board[x][y] = playerBlueKing;
            log("Blue King set at ("+x+","+y+")");
            return true;
        }else if (x == 7){
            log("Red King set at ("+x+","+y+")");
            board[x][y] = playerRedKing;
            return true;
        }
        
        return false;
    }

    int winner = 0;
    
    public boolean checkForWin(int[] checkersLeft){
        
        if (checkersLeft[0] == 0){
            winner = playerBlue;
            return true;
        }if (checkersLeft[1] == 0){
            winner = playerRed;
            return true;
        }
        
        
        return false;
    }
    
    public void gameOver(){
        
        if (winner == playerRed){
            log("Game over: Winner Player Red");
        }if (winner == playerBlue){
            log("Game over: Winner Player Blue");
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {





    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    
    public void log(Object m) {
        System.out.println(m);
    }

}