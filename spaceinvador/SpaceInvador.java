package spaceinvador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvador extends JFrame {
    
    public void log(Object m){
        System.out.println(m);
    }
    
    public SpaceInvador(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,800);
        setResizable(false);
        setTitle("Space Invador");

        init();
    }


    public void init(){
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,1,0,0));
        
        Screen s = new Screen();
        add(s);
        
        setVisible(true);
    }

    public static void main(String[]args){
        new SpaceInvador();
    }
    
    public class Screen extends JPanel implements ActionListener, KeyListener {
        
        
        private BufferedImage ship,back,loadingScreen,enemyship1,enemyship2,enemyship3,enemyship4,missile1,missile2,missile3,missile4,missile5,missile6; 
        private BufferedImage explodeImg1,explodeImg2,explodeImg3,explodeImg4,explodeImg5,explodeImg6;
        private BufferedImage level1Img,level2Img,level3Img;
        private BufferedImage gameOverScreen,rulesScreen;
        
        private BufferedImage enemyship1Lvl2,enemyship2Lvl2,enemyship3Lvl2,enemyship4Lvl2,enemyship5Lvl2;
        private BufferedImage enemyship1Lvl3,enemyship2Lvl3,enemyship3Lvl3,enemyship4Lvl3,enemyship5Lvl3;
        int x = 200;
        int y = 650;
        
        boolean intial = true;
        boolean rule = false;
        
        public void setShip(){
            
            try {
                
                ship = ImageIO.read(getClass().getResourceAsStream("res/ship.png"));
                back = ImageIO.read(getClass().getResourceAsStream("res/back.jpg"));
                loadingScreen = ImageIO.read(getClass().getResourceAsStream("res/loadingScreen.jpg"));
                enemyship1 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip.png"));
                enemyship2 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip.png"));
                enemyship3 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip.png"));
                enemyship4 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip.png"));
                missile1 = ImageIO.read(getClass().getResourceAsStream("res/missile.png"));
                missile2 = ImageIO.read(getClass().getResourceAsStream("res/missile.png"));
                missile3 = ImageIO.read(getClass().getResourceAsStream("res/missile.png"));
                missile4 = ImageIO.read(getClass().getResourceAsStream("res/missile.png"));
                missile5 = ImageIO.read(getClass().getResourceAsStream("res/missile.png"));
                missile6 = ImageIO.read(getClass().getResourceAsStream("res/missile.png"));
                
                explodeImg1 = ImageIO.read(getClass().getResourceAsStream("res/explosion.png"));
                explodeImg2 = ImageIO.read(getClass().getResourceAsStream("res/explosion.png"));
                explodeImg3 = ImageIO.read(getClass().getResourceAsStream("res/explosion.png"));
                explodeImg4 = ImageIO.read(getClass().getResourceAsStream("res/explosion.png"));
                explodeImg5 = ImageIO.read(getClass().getResourceAsStream("res/explosion.png"));
                explodeImg6 = ImageIO.read(getClass().getResourceAsStream("res/explosion.png"));
                
                level1Img = ImageIO.read(getClass().getResourceAsStream("res/level1.png"));
                level2Img = ImageIO.read(getClass().getResourceAsStream("res/level2.png"));
                level3Img = ImageIO.read(getClass().getResourceAsStream("res/level3.png"));
                
                enemyship1Lvl2 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip2.png"));
                enemyship2Lvl2 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip2.png"));
                enemyship3Lvl2 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip.png"));
                enemyship4Lvl2 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip.png"));
                enemyship5Lvl2 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip.png"));
                
                enemyship1Lvl3 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip3.png"));
                enemyship2Lvl3 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip2.png"));
                enemyship3Lvl3 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip2.png"));
                enemyship4Lvl3 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip.png"));
                enemyship5Lvl3 = ImageIO.read(getClass().getResourceAsStream("res/enemyShip.png"));
                
                gameOverScreen = ImageIO.read(getClass().getResourceAsStream("res/gameOverScreen.jpg"));
                rulesScreen = ImageIO.read(getClass().getResourceAsStream("res/rules.jpg"));
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        
        public Screen() {
            
            addKeyListener(this);
            setFocusable(true);
            
            
            setShip();
            repaint();
            
            
            addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) {
                if (intial) {
                    
                    if (rule){
                        if (me.getX() > 40 && me.getX() < 240){
                            if (me.getY() > 689 && me.getY() < 756){
                                rule = false;
                                repaint();
                            }
                        }
                    }
                    
                    if (me.getX() > 75 && me.getX() < 425){
                        if (me.getY() >417 && me.getY() < 517){
                            hp = 100;
                            intial = false;
                        }
                    }
                    if (me.getX() > 134 && me.getX() < 368) {
                        if (me.getY() > 562 && me.getY() < 663){
                            rule = true;
                            log("rule");
                            repaint();
                        }
                    }
                }
                if (gameOver){
                    if (me.getX() > 80 && me.getX() < 412){
                        if (me.getY() > 458 && me.getY() < 530){
                            gameOver = false;
                            score = 0;
                            hp = 100;
                        }
                    }
                    if (me.getX() > 118 && me.getX() < 371){
                        if (me.getY() > 595 && me.getY() < 685){
                            gameOver = false;
                            intial = true;
                            score = 0;
                            hp = 100;
                            repaint();
                        }
                    }
                }
                
             } 
            }); 
            
            
            
            new Timer(30, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if (intial) return;
                    if (gameOver) return;
                    if (rule) return;
                    
                    if (shot1){
                        y_shot1-=30;
                    }
                    if (shot2){
                        y_shot2-=30;  
                    }
                    if (shot3){
                        y_shot3-=30;
                    }
                    if (shot4){
                        y_shot4-=30;
                    }
                    if (shot5){
                        y_shot5-=30;
                    }
                    if (shot6){
                        y_shot6-=30;
                    }
                    
                    if (level1C) {
                        if (enemy1){
                            y_enemy1 +=10;
                        }
                        if (enemy2){
                            y_enemy2 +=10;
                        }
                        if (enemy3){
                            y_enemy3 +=10;
                        }
                        if (enemy4){
                            y_enemy4 +=10;
                        }
                        
                        if (score == 50) {
                            level2 = true;
                            level1 = false;
                            level1C = false;
                            level2C = true;
                        }
                        
                        outOfBorder();
                        collidePE();
                        randomEnemy();
                        collision();
                    }
                    
                    if (level2C){
                        
                        if (enemy1Lvl2){
                            y_enemy1Lvl2 +=5;
                        }
                        if (enemy2Lvl2){
                            y_enemy2Lvl2 +=5;
                        }
                        if (enemy3Lvl2){
                            y_enemy3Lvl2 +=10;
                        }
                        if (enemy4Lvl2){
                            y_enemy4Lvl2 +=10;
                        }
                        if (enemy5Lvl2){
                            y_enemy5Lvl2 +=10;
                        }
                        
                        
                        
                        outOfBorder();
                        collidePE();
                        randomEnemyLVL2();
                        collisionLVL2();
                        
                        if (score == 100) {
                            level3 = true;
                            level3C = true;
                            level2 = false;
                            level2C = false;
                        }
                        
                    }
                    
                    if (level3C){
                        
                        if (enemy1Lvl3){
                            y_enemy1Lvl3 +=2;
                        }
                        if (enemy2Lvl3){
                            y_enemy2Lvl3 +=5;
                        }
                        if (enemy3Lvl3){
                            y_enemy3Lvl3 +=5;
                        }
                        if (enemy4Lvl3){
                            y_enemy4Lvl3 +=10;
                        }
                        if (enemy5Lvl3){
                            y_enemy5Lvl3 +=10;
                        }
                        
                        
                        
                        outOfBorder();
                        collidePE();
                        randomEnemyLVL3();
                        collisionLVL3();                        
                    }
                    
                    
                    repaint();
                }
            }).start();
        }
        
        
        public void shotsFired(){
            
            if (!shot1){
                if (!shot1) x_shot1 = x+35;
                shot1 = true;
                return;
            }if (!shot2 && shot1){
                if (!shot2) x_shot2 = x+35;
                shot2 = true;
                return;
            }if (shot1 && shot2 && !shot3){
                if (!shot3) x_shot3 = x+35;
                shot3 = true;
                return;
            }if (!shot4 && shot1 && shot2 && shot3){
                if (!shot4) x_shot4 = x+35;
                shot4 = true;
                return;
            }if (!shot5 && shot1 && shot2 && shot3 && shot4) {
                if (!shot5) x_shot5 = x+35;
                shot5 = true;
                return;
            }if (!shot6 && shot1 && shot2 && shot3 && shot4 && shot5) {
                if (!shot6) x_shot6 = x+35;
                shot6 = true;
                return;
            }
            
            
            
        }

        boolean shot1 = false, shot2 = false, shot3 = false, shot4 = false, shot5 = false, shot6 = false;
        boolean level1C = true,level2C = false,level3C = false;
        boolean enemy1Lvl2,enemy2Lvl2,enemy3Lvl2,enemy4Lvl2,enemy5Lvl2;
        boolean enemy1 = false, enemy2 = false, enemy3 = false, enemy4 = false;
        boolean explode1,explode2,explode3,explode4,explode5,explode6;
        boolean level1 = true, level2 = false, level3 = false;
        int x_shot1 = x+35, x_shot2 = x+35, x_shot3 = x+35, x_shot4 = x+35, x_shot5 = x+35, x_shot6 = x+35;
        int y_shot1 = y+25, y_shot2 = y+25, y_shot3 = y+25, y_shot4 = y+25, y_shot5 = y+25, y_shot6 = y+25;
        int x_explode1,x_explode2,x_explode3,x_explode4,x_explode5,x_explode6;
        int y_explode1,y_explode2,y_explode3,y_explode4,y_explode5,y_explode6;
        int explodeC1=0,explodeC2=0,explodeC3=0,explodeC4=0,explodeC5=0,explodeC6=0;
        int x_enemy1 ,x_enemy2,x_enemy3,x_enemy4,x_enemy5,x_enemy6,x_enemy7,x_enemy8,x_enemy9,x_enemy10;
        int y_enemy1,y_enemy2,y_enemy3,y_enemy4,y_enemy5,y_enemy6,y_enemy7,y_enemy8,y_enemy9,y_enemy10;
        int enemySpeed1,enemySpeed2,enemySpeed3,enemySpeed4,enemySpeed5,enemySpeed6,enemySpeed7;
        int countLevelShow1 = 0, countLevelShow2 = 0, countLevelShow3 = 0;
        int x_enemy1Lvl2,x_enemy2Lvl2,x_enemy3Lvl2,x_enemy4Lvl2,x_enemy5Lvl2;
        int y_enemy1Lvl2,y_enemy2Lvl2,y_enemy3Lvl2,y_enemy4Lvl2,y_enemy5Lvl2;
        int enemy1Lvl2HP = 2,enemy2Lvl2HP = 2;
        int enemy1Lvl3HP = 3, enemy2Lvl3HP =2, enemy3Lvl3HP=2;
        int score =0;
        int hp = 100;
        
        boolean enemy1Lvl3=false,enemy2Lvl3=false,enemy3Lvl3=false,enemy4Lvl3=false,enemy5Lvl3=false;
        int x_enemy1Lvl3,x_enemy2Lvl3,x_enemy3Lvl3,x_enemy4Lvl3,x_enemy5Lvl3;
        int y_enemy1Lvl3,y_enemy2Lvl3,y_enemy3Lvl3,y_enemy4Lvl3,y_enemy5Lvl3;
        
        public void randomEnemy(){
            
            int spawnChance = (int) (Math.random()*15);
            
            if (spawnChance == 1 && !enemy1){
                x_enemy1 = (int) (Math.random()*8)*50;
                y_enemy1 = 10;
                enemy1 = true;
                return;
            }
            
            if (spawnChance == 1 && enemy1 && !enemy2){
                x_enemy2 = (int) (Math.random()*8)*50;
                y_enemy2 = 10;
                enemy2 = true;
                return;
            }
            
            if (spawnChance == 1 && enemy1 && enemy2 && !enemy3){
                x_enemy3 = (int) (Math.random()*8 )*50;
                y_enemy3 = 10;
                enemy3 = true;
                return;
            }
            if (spawnChance == 1 && enemy1 && enemy2 && enemy3 && !enemy4){
                x_enemy4 = (int) (Math.random()*8 )*50;
                y_enemy4 = 10;
                enemy4 = true;
            }
            
            
            
        }
        
        public void outOfBorder(){
            
            if (level1C){
                if (enemy1){
                    if (y_enemy1 >650){
                        log("10 HP loss from enemy1 (Out of map)");
                        hp-=10;
                        enemy1 = false;
                    }
                }

                if (enemy2){
                    if (y_enemy2 >650){
                        log("10 HP loss from enemy2 (Out of map)");
                        hp-=10;
                        enemy2 = false;
                    }
                }


                if (enemy3){
                    if (y_enemy3 >650){
                        log("10 HP loss from enemy3 (Out of map)");
                        hp-=10;
                        enemy3 = false;
                    }
                }


                if (enemy4){
                    if (y_enemy4 >650){
                        log("10 HP loss from enemy4 (Out of map)");
                        hp-=10;

                        enemy4 = false;
                    }
                }
            }
            
            if (level2C){
                if (enemy1Lvl2){
                    if (y_enemy1Lvl2 >650){
                        log("10 HP loss from enemy1Lvl2 (Out of map)");
                        hp-=10;
                        enemy1Lvl2 = false;
                    }
                }

                if (enemy2Lvl2){
                    if (y_enemy2Lvl2 >650){
                        log("10 HP loss from enemy2Lvl2 (Out of map)");
                        hp-=10;
                        enemy2Lvl2 = false;
                    }
                }


                if (enemy3Lvl2){
                    if (y_enemy3Lvl2 >650){
                        log("10 HP loss from enemy3Lvl2 (Out of map)");
                        hp-=10;
                        enemy3Lvl2 = false;
                    }
                }


                if (enemy4Lvl2){
                    if (y_enemy4Lvl2 >650){
                        log("10 HP loss from enemy4Lvl2 (Out of map)");
                        hp-=10;

                        enemy4Lvl2 = false;
                    }
                }
                
                if (enemy5Lvl2){
                    if (y_enemy5Lvl2 >650){
                        log("10 HP loss from enemy5Lvl2 (Out of map)");
                        hp-=10;

                        enemy5Lvl2 = false;
                    }
                }
            }
            
            if (level3C){
                
                if (enemy1Lvl3){
                    if (y_enemy1Lvl3 >650){
                        log("10 HP loss from enemy1Lvl3 (Out of map)");
                        hp-=10;
                        enemy1Lvl3 = false;
                    }
                }

                if (enemy2Lvl3){
                    if (y_enemy2Lvl3 >650){
                        log("10 HP loss from enemy2Lvl3 (Out of map)");
                        hp-=10;
                        enemy2Lvl3 = false;
                    }
                }


                if (enemy3Lvl3){
                    if (y_enemy3Lvl3 >650){
                        log("10 HP loss from enemy3Lvl3 (Out of map)");
                        hp-=10;
                        enemy3Lvl3 = false;
                    }
                }


                if (enemy4Lvl3){
                    if (y_enemy4Lvl3 >650){
                        log("10 HP loss from enemy4Lvl2 (Out of map)");
                        hp-=10;

                        enemy4Lvl3 = false;
                    }
                }
                
                if (enemy5Lvl3){
                    if (y_enemy5Lvl3 >650){
                        log("10 HP loss from enemy5Lvl3 (Out of map)");
                        hp-=10;

                        enemy5Lvl3 = false;
                    }
                }
            }
            
            if (hp <= 0) gameOver();
            
            
        }
        
        public void collision(){
            
            
            boolean[] shot = {shot1,shot2,shot3,shot4,shot5,shot6};
            int[] x_shot = {x_shot1,x_shot2,x_shot3,x_shot4,x_shot5,x_shot6};
            int[] y_shot = {y_shot1,y_shot2,y_shot3,y_shot4,y_shot5,y_shot6};
            
            boolean[] enemyship = {enemy1,enemy2,enemy3,enemy4};
            int[] x_enemy = {x_enemy1,x_enemy2,x_enemy3,x_enemy4};
            int[] y_enemy = {y_enemy1,y_enemy2,y_enemy3,y_enemy4};
                       
            /*
            for (int i = 0; i<6; i++){
                if (shot[i]) {
                    
                    if (y_shot[i] < y_enemy1 && x_shot[i] <x_enemy1+100 && x_shot[i]>x_enemy1){
                        enemy1 = false;
                        shot[i] = false;
                        x_shot[i] = x+35;
                        y_shot[i] = y+25;

                        explode1 = true;
                        x_explode1 = x_enemy1;
                        y_explode1 = y_enemy1;

                        score++;

                        x_enemy1 =0;
                        y_enemy1 = 0;
                        log("1 col "+i+" "+shot[i]);
                        repaint();
                    }else if (y_shot[i] < y_enemy2 && x_shot[i] <x_enemy2+100 && x_shot[i]>x_enemy2 && shot[i]){
                        enemy2 = false;
                        shot[i] = false;
                        x_shot[i] = x+35;
                        y_shot[i] = y+25;

                        explode2 = true;
                        x_explode2 = x_enemy2;
                        y_explode2 = y_enemy2;

                        x_enemy2 =0;
                        y_enemy2 = 0;
                        log("2 col "+i +" "+shot[i]);
                        score++;
                        repaint();
                    }else if (y_shot[i] < y_enemy3 && x_shot[i] <x_enemy3+100 && x_shot[i]>x_enemy3 && shot[i]){
                        enemy3 = false;
                        shot[i] = false;
                        x_shot[i] = x+35;
                        y_shot[i] = y+25;

                        explode3 = true;
                        x_explode3 = x_enemy3;
                        y_explode3 = y_enemy3;

                        x_enemy3 =0;
                        y_enemy3 = 0;
                        log("3 col "+ i+" "+shot[i]);
                        score++;
                        repaint();
                    }else if (y_shot[i] < y_enemy4 && x_shot[i] <x_enemy4+100 && x_shot[i]>x_enemy4 && shot[i]){
                        enemy4 = false;
                        shot[i] = false;
                        x_shot[i] = x+35;
                        y_shot[i] = y+25;

                        explode4 = true;
                        x_explode4 = x_enemy4;
                        y_explode4 = y_enemy4;

                        x_enemy4 =0;
                        y_enemy4 = 0;
                        log("4 col "+i+" "+shot[i]);
                        score++;
                        repaint();
                    }
                    
                }
            }
            

            */
           
            //shot 1
            if (y_shot1 < y_enemy1 && x_shot1 <x_enemy1+100 && x_shot1>x_enemy1){
                enemy1 = false;
                shot1 = false;
                x_shot1 = x+35;
                y_shot1 = y+25;
                
                explode1 = true;
                x_explode1 = x_enemy1;
                y_explode1 = y_enemy1;
                
                score++;
                
                x_enemy1 =0;
                y_enemy1 = 0;
            }
            if (y_shot1 < y_enemy2 && x_shot1 <x_enemy2+100 && x_shot1>x_enemy2){
                enemy2 = false;
                shot1 = false;
                x_shot1 = x+35;
                y_shot1 = y+25;
                
                explode2 = true;
                x_explode2 = x_enemy2;
                y_explode2 = y_enemy2;
                
                x_enemy2 =0;
                y_enemy2 = 0;
                score++;
            }
            if (y_shot1 < y_enemy3 && x_shot1 <x_enemy3+100 && x_shot1>x_enemy3){
                enemy3 = false;
                shot1 = false;
                x_shot1 = x+35;
                y_shot1 = y+25;
                
                explode3 = true;
                x_explode3 = x_enemy3;
                y_explode3 = y_enemy3;
                
                x_enemy3 =0;
                y_enemy3 = 0;
                score++;
            }
            if (y_shot1 < y_enemy4 && x_shot1 <x_enemy4+100 && x_shot1>x_enemy4){
                enemy4 = false;
                shot1 = false;
                x_shot1 = x+35;
                y_shot1 = y+25;
                
                explode4 = true;
                x_explode4 = x_enemy4;
                y_explode4 = y_enemy4;
                
                x_enemy4 =0;
                y_enemy4 = 0;
                score++;
            }
            
            
            
            
            //Shot 2
            if (y_shot2 < y_enemy1 && x_shot2 <x_enemy1+100 && x_shot2>x_enemy1){
                enemy1 = false;
                shot2 = false;
                x_shot2 = x+35;
                y_shot2 = y+25;
                
                explode1 = true;
                x_explode1 = x_enemy1;
                y_explode1 = y_enemy1;
                
                x_enemy1 =0;
                y_enemy1 = 0;
                score++;
            }
            if (y_shot2 < y_enemy2 && x_shot2 <x_enemy2+100 && x_shot2>x_enemy2){
                enemy2 = false;
                shot2 = false;
                x_shot2 = x+35;
                y_shot2 = y+25;
                
                explode2 = true;
                x_explode2 = x_enemy2;
                y_explode2 = y_enemy2;
                
                x_enemy2 =0;
                y_enemy2 = 0;
                score++;
            }
            if (y_shot2 < y_enemy3 && x_shot2 <x_enemy3+100 && x_shot2>x_enemy3){
                enemy3 = false;
                shot2 = false;
                x_shot2 = x+35;
                y_shot2 = y+25;
                
                explode3 = true;
                x_explode3 = x_enemy3;
                y_explode3 = y_enemy3;
                
                x_enemy3 =0;
                y_enemy3 = 0;
                score++;
            }
            if (y_shot2 < y_enemy4 && x_shot2 <x_enemy4+100 && x_shot2>x_enemy4){
                enemy4 = false;
                shot2 = false;
                x_shot2 = x+35;
                y_shot2 = y+25;
                
                
                explode4 = true;
                x_explode4 = x_enemy4;
                y_explode4 = y_enemy4;
                
                x_enemy4 =0;
                y_enemy4 = 0;
                score++;
            }
            
            //shot 3
            if (y_shot3 < y_enemy1 && x_shot3 <x_enemy1+100 && x_shot3>x_enemy1){
                enemy1 = false;
                shot3 = false;
                x_shot3 = x+35;
                y_shot3 = y+25;
                
                explode1 = true;
                x_explode1 = x_enemy1;
                y_explode1 = y_enemy1;
                
                x_enemy1 =0;
                y_enemy1 = 0;
                score++;
            }
            if (y_shot3 < y_enemy2 && x_shot3 <x_enemy2+100 && x_shot3>x_enemy2){
                enemy2 = false;
                shot3 = false;
                x_shot3 = x+35;
                y_shot3 = y+25;
                
                explode2 = true;
                x_explode2 = x_enemy2;
                y_explode2 = y_enemy2;
                
                x_enemy2 =0;
                y_enemy2 = 0;
                score++;
            }
            if (y_shot3 < y_enemy3 && x_shot3 <x_enemy3+100 && x_shot3>x_enemy3){
                enemy3 = false;
                shot3 = false;
                x_shot3 = x+35;
                y_shot3 = y+25;
                
                explode3 = true;
                x_explode3 = x_enemy3;
                y_explode3 = y_enemy3;
                
                x_enemy3 =0;
                y_enemy3 = 0;
                score++;
            }
            if (y_shot3 < y_enemy4 && x_shot3 <x_enemy4+100 && x_shot3>x_enemy4){
                enemy4 = false;
                shot3 = false;
                x_shot3 = x+35;
                y_shot3 = y+25;
                
                
                explode4 = true;
                x_explode4 = x_enemy4;
                y_explode4 = y_enemy4;
                
                x_enemy4 =0;
                y_enemy4 = 0;
                score++;
            }
            
            
            //shot 4
            if (y_shot4 < y_enemy1 && x_shot1 <x_enemy1+100 && x_shot1>x_enemy1){
                enemy1 = false;
                shot4 = false;
                x_shot4 = x+35;
                y_shot4 = y+25;
                
                explode1 = true;
                x_explode1 = x_enemy1;
                y_explode1 = y_enemy1;
                
                x_enemy1 =0;
                y_enemy1 = 0;
                score++;
            }
            if (y_shot4 < y_enemy2 && x_shot1 <x_enemy2+100 && x_shot1>x_enemy2){
                enemy2 = false;
                shot4 = false;
                x_shot4 = x+35;
                y_shot4 = y+25;
                
                explode2 = true;
                x_explode2 = x_enemy2;
                y_explode2 = y_enemy2;
                
                x_enemy2 =0;
                y_enemy2 = 0;
                score++;
            }
            if (y_shot4 < y_enemy3 && x_shot1 <x_enemy3+100 && x_shot1>x_enemy3){
                enemy3 = false;
                shot4 = false;
                x_shot4 = x+35;
                y_shot4 = y+25;
                
                explode3 = true;
                x_explode3 = x_enemy3;
                y_explode3 = y_enemy3;
                
                x_enemy3 =0;
                y_enemy3 = 0;
                score++;
            }
            if (y_shot4 < y_enemy4 && x_shot1 <x_enemy4+100 && x_shot1>x_enemy4){
                enemy4 = false;
                shot4 = false;
                x_shot4 = x+35;
                y_shot4 = y+25;
                
                explode4 = true;
                x_explode4 = x_enemy4;
                y_explode4 = y_enemy4;
                
                x_enemy4 =0;
                y_enemy4 = 0;
                score++;
            }

            //shot 5
            if (y_shot5 < y_enemy1 && x_shot5 <x_enemy1+100 && x_shot5>x_enemy1){
                enemy1 = false;
                shot5 = false;
                x_shot5 = x+35;
                y_shot5 = y+25;
                
                explode1 = true;
                x_explode1 = x_enemy1;
                y_explode1 = y_enemy1;
                
                x_enemy1 =0;
                y_enemy1 = 0;
                score++;
            }
            if (y_shot5 < y_enemy2 && x_shot5 <x_enemy2+100 && x_shot5>x_enemy2){
                enemy2 = false;
                shot5 = false;
                x_shot5 = x+35;
                y_shot5 = y+25;
                
                explode2 = true;
                x_explode2 = x_enemy2;
                y_explode2 = y_enemy2;
                
                x_enemy2 =0;
                y_enemy2 = 0;
                score++;
            }
            if (y_shot5 < y_enemy3 && x_shot1 <x_enemy3+100 && x_shot1>x_enemy3){
                enemy3 = false;
                shot5 = false;
                x_shot5 = x+35;
                y_shot5 = y+25;
                
                explode3 = true;
                x_explode3 = x_enemy3;
                y_explode3 = y_enemy3;
                
                x_enemy3 =0;
                y_enemy3 = 0;
                score++;
            }
            if (y_shot5 < y_enemy4 && x_shot5 <x_enemy4+100 && x_shot5>x_enemy4){
                enemy4 = false;
                shot5 = false;
                x_shot5 = x+35;
                y_shot5 = y+25;
                
                explode4 = true;
                x_explode4 = x_enemy4;
                y_explode4 = y_enemy4;
                
                x_enemy4 =0;
                y_enemy4 = 0;
                score++;
            }

            //shot 6
            if (y_shot6 < y_enemy1 && x_shot6 <x_enemy1+100 && x_shot6>x_enemy1){
                enemy1 = false;
                shot6 = false;
                x_shot6 = x+35;
                y_shot6 = y+25;
                
                explode1 = true;
                x_explode1 = x_enemy1;
                y_explode1 = y_enemy1;
                
                x_enemy1 =0;
                y_enemy1 = 0;
                score++;
            }
            if (y_shot6 < y_enemy2 && x_shot6 <x_enemy2+100 && x_shot6>x_enemy2){
                enemy2 = false;
                shot6 = false;
                x_shot6 = x+35;
                y_shot6 = y+25;
                
                explode2 = true;
                x_explode2 = x_enemy2;
                y_explode2 = y_enemy2;
                
                x_enemy2 =0;
                y_enemy2 = 0;
                score++;
            }
            if (y_shot6 < y_enemy3 && x_shot6 <x_enemy3+100 && x_shot6>x_enemy3){
                enemy3 = false;
                shot6 = false;
                x_shot6 = x+35;
                y_shot6 = y+25;
                
                explode3 = true;
                x_explode3 = x_enemy3;
                y_explode3 = y_enemy3;
                
                x_enemy3 =0;
                y_enemy3 = 0;
                score++;
            }
            if (y_shot6 < y_enemy4 && x_shot6 <x_enemy4+100 && x_shot6>x_enemy4){
                enemy4 = false;
                shot6 = false;
                x_shot6 = x+35;
                y_shot6 = y+25;
                
                explode4 = true;
                x_explode4 = x_enemy4;
                y_explode4 = y_enemy4;
                
                x_enemy4 =0;
                y_enemy4 = 0;
                score++;
            }
        }
                    
        public void randomEnemyLVL2(){
            int spawnChance = (int) (Math.random()*20);
            
            if (spawnChance == 1 && !enemy1Lvl2){
                x_enemy1Lvl2 = (int) (Math.random()*8)*50;
                y_enemy1Lvl2 = 10;
                enemy1Lvl2 = true;
                return;
            }
            
            if (spawnChance == 1 && enemy1Lvl2 && !enemy2Lvl2){
                x_enemy2Lvl2 = (int) (Math.random()*8)*50;
                y_enemy2Lvl2 = 10;
                enemy2Lvl2 = true;
                return;
            }
            
            if (spawnChance == 1 && enemy1Lvl2 && enemy2Lvl2 && !enemy3Lvl2){
                x_enemy3Lvl2 = (int) (Math.random()*8 )*50;
                y_enemy3Lvl2 = 10;
                enemy3Lvl2 = true;
                return;
            }
            if (spawnChance == 1 && enemy1Lvl2 && enemy2Lvl2 && enemy3Lvl2 && !enemy4Lvl2){
                x_enemy4Lvl2 = (int) (Math.random()*8 )*50;
                y_enemy4Lvl2 = 10;
                enemy4Lvl2 = true;
                return;
            }
            if (spawnChance == 1 && enemy1Lvl2 && enemy2Lvl2 && enemy3Lvl2 && enemy4Lvl2 && !enemy5Lvl2){
                x_enemy5Lvl2 = (int) (Math.random()*8 )*50;
                y_enemy5Lvl2 = 10;
                enemy5Lvl2 = true;
            }
        }
                    
        public void collisionLVL2(){
            
            if (shot1){
                //shot 1
                if (y_shot1 < y_enemy1Lvl2 && x_shot1 <x_enemy1Lvl2+100 && x_shot1>x_enemy1Lvl2){
                    enemy1Lvl2HP--;

                    explode1 = true;
                    x_explode1 = x_enemy1Lvl2;
                    y_explode1 = y_enemy1Lvl2;

                    if (enemy1Lvl2HP == 0){
                        enemy1Lvl2 = false;
                        x_enemy1Lvl2 =0;
                        y_enemy1Lvl2 = 0;
                        enemy1Lvl2HP = 2;
                    }

                    shot1 = false;
                    x_shot1 = x+35;
                    y_shot1 = y+25;

                    score++;
                }
                if (y_shot1 < y_enemy2Lvl2 && x_shot1 <x_enemy2Lvl2+100 && x_shot1>x_enemy2Lvl2){
                    enemy2Lvl2HP--;

                    explode2 = true;
                    x_explode2 = x_enemy2Lvl2;
                    y_explode2 = y_enemy2Lvl2;

                    if (enemy2Lvl2HP == 0){
                        enemy2Lvl2 = false;
                        x_enemy2Lvl2 =0;
                        y_enemy2Lvl2 = 0;
                        enemy2Lvl2HP = 2;
                    }

                    shot1 = false;
                    x_shot1 = x+35;
                    y_shot1 = y+25;

                    score++;
                }
                if (y_shot1 < y_enemy3Lvl2 && x_shot1 <x_enemy3Lvl2+100 && x_shot1>x_enemy3Lvl2){
                    enemy3Lvl2 = false;
                    shot1 = false;
                    x_shot1 = x+35;
                    y_shot1 = y+25;

                    explode3 = true;
                    x_explode3 = x_enemy3Lvl2;
                    y_explode3 = y_enemy3Lvl2;

                    x_enemy3Lvl2 =0;
                    y_enemy3Lvl2 = 0;
                    score++;
                }
                if (y_shot1 < y_enemy4Lvl2 && x_shot1 <x_enemy4Lvl2+100 && x_shot1>x_enemy4Lvl2){
                    enemy4Lvl2 = false;
                    shot1 = false;
                    x_shot1 = x+35;
                    y_shot1 = y+25;

                    explode4 = true;
                    x_explode4 = x_enemy4Lvl2;
                    y_explode4 = y_enemy4Lvl2;

                    x_enemy4Lvl2 =0;
                    y_enemy4Lvl2 = 0;
                    score++;
                }
                if (y_shot1 < y_enemy5Lvl2 && x_shot1 <x_enemy5Lvl2+100 && x_shot1>x_enemy5Lvl2){
                    enemy5Lvl2 = false;
                    shot1 = false;
                    x_shot1 = x+35;
                    y_shot1 = y+25;

                    explode5 = true;
                    x_explode5 = x_enemy5Lvl2;
                    y_explode5 = y_enemy5Lvl2;

                    x_enemy5Lvl2 =0;
                    y_enemy5Lvl2 = 0;
                    score++;
                }
            }
            
            if (shot2){
                if (y_shot2 < y_enemy1Lvl2 && x_shot2 <x_enemy1Lvl2+100 && x_shot2>x_enemy1Lvl2){
                    enemy1Lvl2HP--;

                    explode1 = true;
                    x_explode1 = x_enemy1Lvl2;
                    y_explode1 = y_enemy1Lvl2;

                    if (enemy1Lvl2HP == 0){
                        enemy1Lvl2 = false;
                        x_enemy1Lvl2 =0;
                        y_enemy1Lvl2 = 0;
                        enemy1Lvl2HP = 2;
                    }

                    shot2 = false;
                    x_shot2 = x+35;
                    y_shot2 = y+25;

                    score++;
                }
                if (y_shot2 < y_enemy2Lvl2 && x_shot2 <x_enemy2Lvl2+100 && x_shot2>x_enemy2Lvl2){
                    enemy2Lvl2HP--;

                    explode2 = true;
                    x_explode2 = x_enemy2Lvl2;
                    y_explode2 = y_enemy2Lvl2;

                    if (enemy2Lvl2HP == 0){
                        enemy2Lvl2 = false;
                        x_enemy2Lvl2 =0;
                        y_enemy2Lvl2 = 0;
                        enemy2Lvl2HP = 2;
                    }

                    shot2 = false;
                    x_shot2 = x+35;
                    y_shot2 = y+25;

                    score++;
                }
                if (y_shot2 < y_enemy3Lvl2 && x_shot2 <x_enemy3Lvl2+100 && x_shot2>x_enemy3Lvl2){
                    enemy3Lvl2 = false;
                    shot2 = false;
                    x_shot2= x+35;
                    y_shot2 = y+25;

                    explode3 = true;
                    x_explode3 = x_enemy3Lvl2;
                    y_explode3 = y_enemy3Lvl2;

                    x_enemy3Lvl2 =0;
                    y_enemy3Lvl2 = 0;
                    score++;
                }
                if (y_shot2 < y_enemy4Lvl2 && x_shot2 <x_enemy4Lvl2+100 && x_shot2>x_enemy4Lvl2){
                    enemy4Lvl2 = false;
                    shot2 = false;
                    x_shot2 = x+35;
                    y_shot2 = y+25;

                    explode4 = true;
                    x_explode4 = x_enemy4Lvl2;
                    y_explode4 = y_enemy4Lvl2;

                    x_enemy4Lvl2 =0;
                    y_enemy4Lvl2 = 0;
                    score++;
                }
                if (y_shot2 < y_enemy5Lvl2 && x_shot2 <x_enemy5Lvl2+100 && x_shot2>x_enemy5Lvl2){
                    enemy5Lvl2 = false;
                    shot2 = false;
                    x_shot2 = x+35;
                    y_shot2 = y+25;

                    explode5 = true;
                    x_explode5 = x_enemy5Lvl2;
                    y_explode5 = y_enemy5Lvl2;

                    x_enemy5Lvl2 =0;
                    y_enemy5Lvl2 = 0;
                    score++;
                }
            }
            
            if (shot3){
                if (y_shot3 < y_enemy1Lvl2 && x_shot3 <x_enemy1Lvl2+100 && x_shot3>x_enemy1Lvl2){
                    enemy1Lvl2HP--;

                    explode1 = true;
                    x_explode1 = x_enemy1Lvl2;
                    y_explode1 = y_enemy1Lvl2;

                    if (enemy1Lvl2HP == 0){
                        enemy1Lvl2 = false;
                        x_enemy1Lvl2 =0;
                        y_enemy1Lvl2 = 0;
                        enemy1Lvl2HP = 2;
                    }

                    shot3 = false;
                    x_shot3 = x+35;
                    y_shot3 = y+25;

                    score++;
                }
                if (y_shot3 < y_enemy2Lvl2 && x_shot3 <x_enemy2Lvl2+100 && x_shot3>x_enemy2Lvl2){
                    enemy2Lvl2HP--;

                    explode2 = true;
                    x_explode2 = x_enemy2Lvl2;
                    y_explode2 = y_enemy2Lvl2;

                    if (enemy2Lvl2HP == 0){
                        enemy2Lvl2 = false;
                        x_enemy2Lvl2 =0;
                        y_enemy2Lvl2 = 0;
                        enemy2Lvl2HP = 2;
                    }

                    shot3 = false;
                    x_shot3 = x+35;
                    y_shot3 = y+25;

                    score++;
                }
                if (y_shot3 < y_enemy3Lvl2 && x_shot3 <x_enemy3Lvl2+100 && x_shot3>x_enemy3Lvl2){
                    enemy3Lvl2 = false;
                    shot3 = false;
                    x_shot3= x+35;
                    y_shot3 = y+25;

                    explode3 = true;
                    x_explode3 = x_enemy3Lvl2;
                    y_explode3 = y_enemy3Lvl2;

                    x_enemy3Lvl2 =0;
                    y_enemy3Lvl2 = 0;
                    score++;
                }
                if (y_shot3 < y_enemy4Lvl2 && x_shot3 <x_enemy4Lvl2+100 && x_shot3>x_enemy4Lvl2){
                    enemy4Lvl2 = false;
                    shot3 = false;
                    x_shot3 = x+35;
                    y_shot3 = y+25;

                    explode4 = true;
                    x_explode4 = x_enemy4Lvl2;
                    y_explode4 = y_enemy4Lvl2;

                    x_enemy4Lvl2 =0;
                    y_enemy4Lvl2 = 0;
                    score++;
                }
                if (y_shot3 < y_enemy5Lvl2 && x_shot3 <x_enemy5Lvl2+100 && x_shot3>x_enemy5Lvl2){
                    enemy5Lvl2 = false;
                    shot3 = false;
                    x_shot3 = x+35;
                    y_shot3 = y+25;

                    explode5 = true;
                    x_explode5 = x_enemy5Lvl2;
                    y_explode5 = y_enemy5Lvl2;

                    x_enemy5Lvl2 =0;
                    y_enemy5Lvl2 = 0;
                    score++;
                }
            }
            
            if (shot4){
                if (y_shot4 < y_enemy1Lvl2 && x_shot4 <x_enemy1Lvl2+100 && x_shot4>x_enemy1Lvl2){
                    enemy1Lvl2HP--;

                    explode1 = true;
                    x_explode1 = x_enemy1Lvl2;
                    y_explode1 = y_enemy1Lvl2;

                    if (enemy1Lvl2HP == 0){
                        enemy1Lvl2 = false;
                        x_enemy1Lvl2 =0;
                        y_enemy1Lvl2 = 0;
                        enemy1Lvl2HP = 2;
                    }

                    shot4 = false;
                    x_shot4 = x+35;
                    y_shot4 = y+25;

                    score++;
                }
                if (y_shot4 < y_enemy2Lvl2 && x_shot4 <x_enemy2Lvl2+100 && x_shot4>x_enemy2Lvl2){
                    enemy2Lvl2HP--;

                    explode2 = true;
                    x_explode2 = x_enemy2Lvl2;
                    y_explode2 = y_enemy2Lvl2;

                    if (enemy2Lvl2HP == 0){
                        enemy2Lvl2 = false;
                        x_enemy2Lvl2 =0;
                        y_enemy2Lvl2 = 0;
                        enemy2Lvl2HP = 2;
                    }

                    shot4 = false;
                    x_shot4 = x+35;
                    y_shot4 = y+25;

                    score++;
                }
                if (y_shot4 < y_enemy3Lvl2 && x_shot4 <x_enemy3Lvl2+100 && x_shot4>x_enemy3Lvl2){
                    enemy3Lvl2 = false;
                    shot4 = false;
                    x_shot4= x+35;
                    y_shot4 = y+25;

                    explode3 = true;
                    x_explode3 = x_enemy3Lvl2;
                    y_explode3 = y_enemy3Lvl2;

                    x_enemy3Lvl2 =0;
                    y_enemy3Lvl2 = 0;
                    score++;
                }
                if (y_shot4 < y_enemy4Lvl2 && x_shot4 <x_enemy4Lvl2+100 && x_shot4>x_enemy4Lvl2){
                    enemy4Lvl2 = false;
                    shot4 = false;
                    x_shot4 = x+35;
                    y_shot4 = y+25;

                    explode4 = true;
                    x_explode4 = x_enemy4Lvl2;
                    y_explode4 = y_enemy4Lvl2;

                    x_enemy4Lvl2 =0;
                    y_enemy4Lvl2 = 0;
                    score++;
                }
                if (y_shot4 < y_enemy5Lvl2 && x_shot4 <x_enemy5Lvl2+100 && x_shot4>x_enemy5Lvl2){
                    enemy5Lvl2 = false;
                    shot4 = false;
                    x_shot4 = x+35;
                    y_shot4 = y+25;

                    explode5 = true;
                    x_explode5 = x_enemy5Lvl2;
                    y_explode5 = y_enemy5Lvl2;

                    x_enemy5Lvl2 =0;
                    y_enemy5Lvl2 = 0;
                    score++;
                }
            }
            
            if (shot5){
                if (y_shot5 < y_enemy1Lvl2 && x_shot5 <x_enemy1Lvl2+100 && x_shot5>x_enemy1Lvl2){
                    enemy1Lvl2HP--;

                    explode1 = true;
                    x_explode1 = x_enemy1Lvl2;
                    y_explode1 = y_enemy1Lvl2;

                    if (enemy1Lvl2HP == 0){
                        enemy1Lvl2 = false;
                        x_enemy1Lvl2 =0;
                        y_enemy1Lvl2 = 0;
                        enemy1Lvl2HP = 2;
                    }

                    shot5 = false;
                    x_shot5 = x+35;
                    y_shot5 = y+25;

                    score++;
                }
                if (y_shot5 < y_enemy2Lvl2 && x_shot5 <x_enemy2Lvl2+100 && x_shot5>x_enemy2Lvl2){
                    enemy2Lvl2HP--;

                    explode2 = true;
                    x_explode2 = x_enemy2Lvl2;
                    y_explode2 = y_enemy2Lvl2;

                    if (enemy2Lvl2HP == 0){
                        enemy2Lvl2 = false;
                        x_enemy2Lvl2 =0;
                        y_enemy2Lvl2 = 0;
                        enemy2Lvl2HP = 2;
                    }

                    shot5 = false;
                    x_shot5 = x+35;
                    y_shot5 = y+25;

                    score++;
                }
                if (y_shot5 < y_enemy3Lvl2 && x_shot5 <x_enemy3Lvl2+100 && x_shot5>x_enemy3Lvl2){
                    enemy3Lvl2 = false;
                    shot5 = false;
                    x_shot5 = x+35;
                    y_shot5 = y+25;

                    explode3 = true;
                    x_explode3 = x_enemy3Lvl2;
                    y_explode3 = y_enemy3Lvl2;

                    x_enemy3Lvl2 =0;
                    y_enemy3Lvl2 = 0;
                    score++;
                }
                if (y_shot5 < y_enemy4Lvl2 && x_shot5 <x_enemy4Lvl2+100 && x_shot5>x_enemy4Lvl2){
                    enemy4Lvl2 = false;
                    shot5 = false;
                    x_shot5 = x+35;
                    y_shot5 = y+25;

                    explode4 = true;
                    x_explode4 = x_enemy4Lvl2;
                    y_explode4 = y_enemy4Lvl2;

                    x_enemy4Lvl2 =0;
                    y_enemy4Lvl2 = 0;
                    score++;
                }
                if (y_shot5 < y_enemy5Lvl2 && x_shot5 < x_enemy5Lvl2+100 && x_shot5 > x_enemy5Lvl2){
                    enemy5Lvl2 = false;
                    shot5 = false;
                    x_shot5 = x+35;
                    y_shot5 = y+25;

                    explode5 = true;
                    x_explode5 = x_enemy5Lvl2;
                    y_explode5 = y_enemy5Lvl2;

                    x_enemy5Lvl2 =0;
                    y_enemy5Lvl2 = 0;
                    score++;
                }
            }
            
            if (shot6){
                if (y_shot6 < y_enemy1Lvl2 && x_shot6 <x_enemy1Lvl2+100 && x_shot6>x_enemy1Lvl2){
                    enemy1Lvl2HP--;

                    explode1 = true;
                    x_explode1 = x_enemy1Lvl2;
                    y_explode1 = y_enemy1Lvl2;

                    if (enemy1Lvl2HP == 0){
                        enemy1Lvl2 = false;
                        x_enemy1Lvl2 =0;
                        y_enemy1Lvl2 = 0;
                        enemy1Lvl2HP = 2;
                    }

                    shot6 = false;
                    x_shot6 = x+35;
                    y_shot6 = y+25;

                    score++;
                }
                if (y_shot6 < y_enemy2Lvl2 && x_shot6 <x_enemy2Lvl2+100 && x_shot6>x_enemy2Lvl2){
                    enemy2Lvl2HP--;

                    explode2 = true;
                    x_explode2 = x_enemy2Lvl2;
                    y_explode2 = y_enemy2Lvl2;

                    if (enemy2Lvl2HP == 0){
                        enemy2Lvl2 = false;
                        x_enemy2Lvl2 =0;
                        y_enemy2Lvl2 = 0;
                        enemy2Lvl2HP = 2;
                    }

                    shot6 = false;
                    x_shot6 = x+35;
                    y_shot6 = y+25;

                    score++;
                }
                if (y_shot6 < y_enemy3Lvl2 && x_shot6 <x_enemy3Lvl2+100 && x_shot6>x_enemy3Lvl2){
                    enemy3Lvl2 = false;
                    shot6 = false;
                    x_shot6= x+35;
                    y_shot6 = y+25;

                    explode3 = true;
                    x_explode3 = x_enemy3Lvl2;
                    y_explode3 = y_enemy3Lvl2;

                    x_enemy3Lvl2 =0;
                    y_enemy3Lvl2 = 0;
                    score++;
                }
                if (y_shot6 < y_enemy4Lvl2 && x_shot6 <x_enemy4Lvl2+100 && x_shot6>x_enemy4Lvl2){
                    enemy4Lvl2 = false;
                    shot6 = false;
                    x_shot6 = x+35;
                    y_shot6 = y+25;

                    explode4 = true;
                    x_explode4 = x_enemy4Lvl2;
                    y_explode4 = y_enemy4Lvl2;

                    x_enemy4Lvl2 =0;
                    y_enemy4Lvl2 = 0;
                    score++;
                }
                if (y_shot6 < y_enemy5Lvl2 && x_shot6 <x_enemy5Lvl2+100 && x_shot6>x_enemy5Lvl2){
                    enemy5Lvl2 = false;
                    shot2 = false;
                    x_shot6 = x+35;
                    y_shot6 = y+25;

                    explode5 = true;
                    x_explode5 = x_enemy5Lvl2;
                    y_explode5 = y_enemy5Lvl2;

                    x_enemy5Lvl2 =0;
                    y_enemy5Lvl2 = 0;
                    score++;
                }
            }
        }
        
        public void randomEnemyLVL3(){
            int spawnChance = (int) (Math.random()*20);
            
            if (spawnChance == 1 && !enemy1Lvl3){
                x_enemy1Lvl3 = (int) (Math.random()*8)*50;
                y_enemy1Lvl3 = 10;
                enemy1Lvl3 = true;
                return;
            }
            
            if (spawnChance == 1 && enemy1Lvl3 && !enemy2Lvl3){
                x_enemy2Lvl3 = (int) (Math.random()*8)*50;
                y_enemy2Lvl3 = 10;
                enemy2Lvl3 = true;
                return;
            }
            
            if (spawnChance == 1 && enemy1Lvl3 && enemy2Lvl3 && !enemy3Lvl3){
                x_enemy3Lvl3 = (int) (Math.random()*8 )*50;
                y_enemy3Lvl3 = 10;
                enemy3Lvl3 = true;
                return;
            }
            if (spawnChance == 1 && enemy1Lvl3 && enemy2Lvl3 && enemy3Lvl3 && !enemy4Lvl3){
                x_enemy4Lvl3 = (int) (Math.random()*8 )*50;
                y_enemy4Lvl3 = 10;
                enemy4Lvl3 = true;
                return;
            }
            if (spawnChance == 1 && enemy1Lvl3 && enemy2Lvl3 && enemy3Lvl3 && enemy4Lvl3 && !enemy5Lvl3){
                x_enemy5Lvl3 = (int) (Math.random()*8 )*50;
                y_enemy5Lvl3 = 10;
                enemy5Lvl3 = true;
            }
        }
        
        public void collisionLVL3(){
            
            if (shot1){
                //shot 1
                if (y_shot1 < y_enemy1Lvl3 && x_shot1 <x_enemy1Lvl3+100 && x_shot1>x_enemy1Lvl3){
                    enemy1Lvl3HP--;

                    explode1 = true;
                    x_explode1 = x_enemy1Lvl3;
                    y_explode1 = y_enemy1Lvl3;

                    if (enemy1Lvl3HP == 0){
                        enemy1Lvl3 = false;
                        x_enemy1Lvl3 =0;
                        y_enemy1Lvl3 = 0;
                        enemy1Lvl3HP = 3;
                    }

                    shot1 = false;
                    x_shot1 = x+35;
                    y_shot1 = y+25;

                    score++;
                }
                if (y_shot1 < y_enemy2Lvl3 && x_shot1 <x_enemy2Lvl3+100 && x_shot1>x_enemy2Lvl3){
                    enemy2Lvl3HP--;

                    explode2 = true;
                    x_explode2 = x_enemy2Lvl3;
                    y_explode2 = y_enemy2Lvl3;

                    if (enemy2Lvl3HP == 0){
                        enemy2Lvl3 = false;
                        x_enemy2Lvl3 =0;
                        y_enemy2Lvl3 = 0;
                        enemy2Lvl3HP = 2;
                    }

                    shot1 = false;
                    x_shot1 = x+35;
                    y_shot1 = y+25;

                    score++;
                }
                if (y_shot1 < y_enemy3Lvl3 && x_shot1 <x_enemy3Lvl3+100 && x_shot1>x_enemy3Lvl3){
                    enemy3Lvl3HP--;

                    explode3 = true;
                    x_explode3 = x_enemy3Lvl3;
                    y_explode3 = y_enemy3Lvl3;

                    if (enemy3Lvl3HP == 0){
                        enemy3Lvl3 = false;
                        x_enemy3Lvl3 =0;
                        y_enemy3Lvl3 = 0;
                        enemy3Lvl3HP = 2;
                    }

                    shot1 = false;
                    x_shot1 = x+35;
                    y_shot1 = y+25;

                    score++;
                }
                if (y_shot1 < y_enemy4Lvl3 && x_shot1 <x_enemy4Lvl3+100 && x_shot1>x_enemy4Lvl3){
                    enemy4Lvl3 = false;
                    shot1 = false;
                    x_shot1 = x+35;
                    y_shot1 = y+25;

                    explode4 = true;
                    x_explode4 = x_enemy4Lvl3;
                    y_explode4 = y_enemy4Lvl3;

                    x_enemy4Lvl3 =0;
                    y_enemy4Lvl3 = 0;
                    score++;
                }
                if (y_shot1 < y_enemy5Lvl3 && x_shot1 <x_enemy5Lvl3+100 && x_shot1>x_enemy5Lvl3){
                    enemy5Lvl3 = false;
                    shot1 = false;
                    x_shot1 = x+35;
                    y_shot1 = y+25;

                    explode5 = true;
                    x_explode5 = x_enemy5Lvl3;
                    y_explode5 = y_enemy5Lvl3;

                    x_enemy5Lvl3 =0;
                    y_enemy5Lvl3 = 0;
                    score++;
                }
            }
            
            if (shot2){
                //shot 1
                if (y_shot2 < y_enemy1Lvl3 && x_shot2 <x_enemy1Lvl3+100 && x_shot2>x_enemy1Lvl3){
                    enemy1Lvl3HP--;

                    explode1 = true;
                    x_explode1 = x_enemy1Lvl3;
                    y_explode1 = y_enemy1Lvl3;

                    if (enemy1Lvl3HP == 0){
                        enemy1Lvl3 = false;
                        x_enemy1Lvl3 =0;
                        y_enemy1Lvl3 = 0;
                        enemy1Lvl3HP = 3;
                    }

                    shot2 = false;
                    x_shot2 = x+35;
                    y_shot2 = y+25;

                    score++;
                }
                if (y_shot2 < y_enemy2Lvl3 && x_shot2 <x_enemy2Lvl3+100 && x_shot2>x_enemy2Lvl3){
                    enemy2Lvl3HP--;

                    explode2 = true;
                    x_explode2 = x_enemy2Lvl3;
                    y_explode2 = y_enemy2Lvl3;

                    if (enemy2Lvl3HP == 0){
                        enemy2Lvl3 = false;
                        x_enemy2Lvl3 =0;
                        y_enemy2Lvl3 = 0;
                        enemy2Lvl3HP = 2;
                    }

                    shot2 = false;
                    x_shot2 = x+35;
                    y_shot2 = y+25;

                    score++;
                }
                if (y_shot2 < y_enemy3Lvl3 && x_shot2 <x_enemy3Lvl3+100 && x_shot2>x_enemy3Lvl3){
                    enemy3Lvl3HP--;

                    explode3 = true;
                    x_explode3 = x_enemy3Lvl3;
                    y_explode3 = y_enemy3Lvl3;

                    if (enemy3Lvl3HP == 0){
                        enemy3Lvl3 = false;
                        x_enemy3Lvl3 =0;
                        y_enemy3Lvl3 = 0;
                        enemy3Lvl3HP = 2;
                    }

                    shot2 = false;
                    x_shot2 = x+35;
                    y_shot2 = y+25;

                    score++;
                }
                if (y_shot2 < y_enemy4Lvl3 && x_shot2 <x_enemy4Lvl3+100 && x_shot2>x_enemy4Lvl3){
                    enemy4Lvl3 = false;
                    shot2 = false;
                    x_shot2 = x+35;
                    y_shot2 = y+25;

                    explode4 = true;
                    x_explode4 = x_enemy4Lvl3;
                    y_explode4 = y_enemy4Lvl3;

                    x_enemy4Lvl3 =0;
                    y_enemy4Lvl3 = 0;
                    score++;
                }
                if (y_shot2 < y_enemy5Lvl3 && x_shot2 <x_enemy5Lvl3+100 && x_shot2>x_enemy5Lvl3){
                    enemy5Lvl3 = false;
                    shot2 = false;
                    x_shot2 = x+35;
                    y_shot2 = y+25;

                    explode5 = true;
                    x_explode5 = x_enemy5Lvl3;
                    y_explode5 = y_enemy5Lvl3;

                    x_enemy5Lvl3 =0;
                    y_enemy5Lvl3 = 0;
                    score++;
                }
            }
            
            if (shot3){
                //shot 1
                if (y_shot3 < y_enemy1Lvl3 && x_shot3 <x_enemy1Lvl3+100 && x_shot3>x_enemy1Lvl3){
                    enemy1Lvl3HP--;

                    explode1 = true;
                    x_explode1 = x_enemy1Lvl3;
                    y_explode1 = y_enemy1Lvl3;

                    if (enemy1Lvl3HP == 0){
                        enemy1Lvl3 = false;
                        x_enemy1Lvl3 =0;
                        y_enemy1Lvl3 = 0;
                        enemy1Lvl3HP = 3;
                    }

                    shot3 = false;
                    x_shot3 = x+35;
                    y_shot3 = y+25;

                    score++;
                }
                if (y_shot3 < y_enemy2Lvl3 && x_shot3 <x_enemy2Lvl3+100 && x_shot3>x_enemy2Lvl3){
                    enemy2Lvl3HP--;

                    explode2 = true;
                    x_explode2 = x_enemy2Lvl3;
                    y_explode2 = y_enemy2Lvl3;

                    if (enemy2Lvl3HP == 0){
                        enemy2Lvl3 = false;
                        x_enemy2Lvl3 =0;
                        y_enemy2Lvl3 = 0;
                        enemy2Lvl3HP = 2;
                    }

                    shot3 = false;
                    x_shot3 = x+35;
                    y_shot3 = y+25;

                    score++;
                }
                if (y_shot3 < y_enemy3Lvl3 && x_shot3 <x_enemy3Lvl3+100 && x_shot3>x_enemy3Lvl3){
                    enemy3Lvl3HP--;

                    explode3 = true;
                    x_explode3 = x_enemy3Lvl3;
                    y_explode3 = y_enemy3Lvl3;

                    if (enemy3Lvl3HP == 0){
                        enemy3Lvl3 = false;
                        x_enemy3Lvl3 =0;
                        y_enemy3Lvl3 = 0;
                        enemy3Lvl3HP = 2;
                    }

                    shot3 = false;
                    x_shot3 = x+35;
                    y_shot3 = y+25;

                    score++;
                }
                if (y_shot3 < y_enemy4Lvl3 && x_shot3 <x_enemy4Lvl3+100 && x_shot3>x_enemy4Lvl3){
                    enemy4Lvl3 = false;
                    shot3 = false;
                    x_shot3 = x+35;
                    y_shot3 = y+25;

                    explode4 = true;
                    x_explode4 = x_enemy4Lvl3;
                    y_explode4 = y_enemy4Lvl3;

                    x_enemy4Lvl3 =0;
                    y_enemy4Lvl3 = 0;
                    score++;
                }
                if (y_shot3 < y_enemy5Lvl3 && x_shot3 <x_enemy5Lvl3+100 && x_shot3>x_enemy5Lvl3){
                    enemy5Lvl3 = false;
                    shot3 = false;
                    x_shot3 = x+35;
                    y_shot3 = y+25;

                    explode5 = true;
                    x_explode5 = x_enemy5Lvl3;
                    y_explode5 = y_enemy5Lvl3;

                    x_enemy5Lvl3 =0;
                    y_enemy5Lvl3 = 0;
                    score++;
                }
            }
            
            if (shot4){
                //shot 4
                if (y_shot4 < y_enemy1Lvl3 && x_shot4 <x_enemy1Lvl3+100 && x_shot4>x_enemy1Lvl3){
                    enemy1Lvl3HP--;

                    explode1 = true;
                    x_explode1 = x_enemy1Lvl3;
                    y_explode1 = y_enemy1Lvl3;

                    if (enemy1Lvl3HP == 0){
                        enemy1Lvl3 = false;
                        x_enemy1Lvl3 =0;
                        y_enemy1Lvl3 = 0;
                        enemy1Lvl3HP = 3;
                    }

                    shot4 = false;
                    x_shot4 = x+35;
                    y_shot4 = y+25;

                    score++;
                }
                if (y_shot4 < y_enemy2Lvl3 && x_shot4 <x_enemy2Lvl3+100 && x_shot4>x_enemy2Lvl3){
                    enemy2Lvl3HP--;

                    explode2 = true;
                    x_explode2 = x_enemy2Lvl3;
                    y_explode2 = y_enemy2Lvl3;

                    if (enemy2Lvl3HP == 0){
                        enemy2Lvl3 = false;
                        x_enemy2Lvl3 =0;
                        y_enemy2Lvl3 = 0;
                        enemy2Lvl3HP = 2;
                    }

                    shot4 = false;
                    x_shot4 = x+35;
                    y_shot4 = y+25;

                    score++;
                }
                if (y_shot4 < y_enemy3Lvl3 && x_shot4 <x_enemy3Lvl3+100 && x_shot4>x_enemy3Lvl3){
                    enemy3Lvl3HP--;

                    explode3 = true;
                    x_explode3 = x_enemy3Lvl3;
                    y_explode3 = y_enemy3Lvl3;

                    if (enemy3Lvl3HP == 0){
                        enemy3Lvl3 = false;
                        x_enemy3Lvl3 =0;
                        y_enemy3Lvl3 = 0;
                        enemy3Lvl3HP = 2;
                    }

                    shot4 = false;
                    x_shot4 = x+35;
                    y_shot4 = y+25;

                    score++;
                }
                if (y_shot4 < y_enemy4Lvl3 && x_shot4 <x_enemy4Lvl3+100 && x_shot4>x_enemy4Lvl3){
                    enemy4Lvl3 = false;
                    shot4 = false;
                    x_shot4 = x+35;
                    y_shot4 = y+25;

                    explode4 = true;
                    x_explode4 = x_enemy4Lvl3;
                    y_explode4 = y_enemy4Lvl3;

                    x_enemy4Lvl3 =0;
                    y_enemy4Lvl3 = 0;
                    score++;
                }
                if (y_shot4 < y_enemy5Lvl3 && x_shot4 <x_enemy5Lvl3+100 && x_shot4>x_enemy5Lvl3){
                    enemy5Lvl3 = false;
                    shot4 = false;
                    x_shot4 = x+35;
                    y_shot4 = y+25;

                    explode5 = true;
                    x_explode5 = x_enemy5Lvl3;
                    y_explode5 = y_enemy5Lvl3;

                    x_enemy5Lvl3 =0;
                    y_enemy5Lvl3 = 0;
                    score++;
                }
            }
            
            if (shot5){
                //shot 5
                if (y_shot5 < y_enemy1Lvl3 && x_shot5 <x_enemy1Lvl3+100 && x_shot5>x_enemy1Lvl3){
                    enemy1Lvl3HP--;

                    explode1 = true;
                    x_explode1 = x_enemy1Lvl3;
                    y_explode1 = y_enemy1Lvl3;

                    if (enemy1Lvl3HP == 0){
                        enemy1Lvl3 = false;
                        x_enemy1Lvl3 =0;
                        y_enemy1Lvl3 = 0;
                        enemy1Lvl3HP = 3;
                    }

                    shot5 = false;
                    x_shot5 = x+35;
                    y_shot5 = y+25;

                    score++;
                }
                if (y_shot5< y_enemy2Lvl3 && x_shot5 <x_enemy2Lvl3+100 && x_shot5>x_enemy2Lvl3){
                    enemy2Lvl3HP--;

                    explode2 = true;
                    x_explode2 = x_enemy2Lvl3;
                    y_explode2 = y_enemy2Lvl3;

                    if (enemy2Lvl3HP == 0){
                        enemy2Lvl3 = false;
                        x_enemy2Lvl3 =0;
                        y_enemy2Lvl3 = 0;
                        enemy2Lvl3HP = 2;
                    }

                    shot5 = false;
                    x_shot5 = x+35;
                    y_shot5 = y+25;

                    score++;
                }
                if (y_shot5 < y_enemy3Lvl3 && x_shot5 <x_enemy3Lvl3+100 && x_shot5>x_enemy3Lvl3){
                    enemy3Lvl3HP--;

                    explode3 = true;
                    x_explode3 = x_enemy3Lvl3;
                    y_explode3 = y_enemy3Lvl3;

                    if (enemy3Lvl3HP == 0){
                        enemy3Lvl3 = false;
                        x_enemy3Lvl3 =0;
                        y_enemy3Lvl3 = 0;
                        enemy3Lvl3HP = 2;
                    }

                    shot5 = false;
                    x_shot5 = x+35;
                    y_shot5 = y+25;

                    score++;
                }
                if (y_shot5 < y_enemy4Lvl3 && x_shot5 <x_enemy4Lvl3+100 && x_shot5>x_enemy4Lvl3){
                    enemy4Lvl3 = false;
                    shot5 = false;
                    x_shot5 = x+35;
                    y_shot5 = y+25;

                    explode4 = true;
                    x_explode4 = x_enemy4Lvl3;
                    y_explode4 = y_enemy4Lvl3;

                    x_enemy4Lvl3 =0;
                    y_enemy4Lvl3 = 0;
                    score++;
                }
                if (y_shot5 < y_enemy5Lvl3 && x_shot5 <x_enemy5Lvl3+100 && x_shot5>x_enemy5Lvl3){
                    enemy5Lvl3 = false;
                    shot5 = false;
                    x_shot5 = x+35;
                    y_shot5 = y+25;

                    explode5 = true;
                    x_explode5 = x_enemy5Lvl3;
                    y_explode5 = y_enemy5Lvl3;

                    x_enemy5Lvl3 =0;
                    y_enemy5Lvl3 = 0;
                    score++;
                }
            }
            
            if (shot6){
                //shot 6
                if (y_shot6 < y_enemy1Lvl3 && x_shot6 <x_enemy1Lvl3+100 && x_shot6>x_enemy1Lvl3){
                    enemy1Lvl3HP--;

                    explode1 = true;
                    x_explode1 = x_enemy1Lvl3;
                    y_explode1 = y_enemy1Lvl3;

                    if (enemy1Lvl3HP == 0){
                        enemy1Lvl3 = false;
                        x_enemy1Lvl3 =0;
                        y_enemy1Lvl3 = 0;
                        enemy1Lvl3HP = 3;
                    }

                    shot6 = false;
                    x_shot6 = x+35;
                    y_shot6 = y+25;

                    score++;
                }
                if (y_shot6 < y_enemy2Lvl3 && x_shot6 <x_enemy2Lvl3+100 && x_shot6>x_enemy2Lvl3){
                    enemy2Lvl3HP--;

                    explode2 = true;
                    x_explode2 = x_enemy2Lvl3;
                    y_explode2 = y_enemy2Lvl3;

                    if (enemy2Lvl3HP == 0){
                        enemy2Lvl3 = false;
                        x_enemy2Lvl3 =0;
                        y_enemy2Lvl3 = 0;
                        enemy2Lvl3HP = 2;
                    }

                    shot6 = false;
                    x_shot6 = x+35;
                    y_shot6 = y+25;

                    score++;
                }
                if (y_shot6 < y_enemy3Lvl3 && x_shot6 <x_enemy3Lvl3+100 && x_shot6>x_enemy3Lvl3){
                    enemy3Lvl3HP--;

                    explode3 = true;
                    x_explode3 = x_enemy3Lvl3;
                    y_explode3 = y_enemy3Lvl3;

                    if (enemy3Lvl3HP == 0){
                        enemy3Lvl3 = false;
                        x_enemy3Lvl3 =0;
                        y_enemy3Lvl3 = 0;
                        enemy3Lvl3HP = 2;
                    }

                    shot6 = false;
                    x_shot6 = x+35;
                    y_shot6 = y+25;

                    score++;
                }
                if (y_shot6 < y_enemy4Lvl3 && x_shot6 <x_enemy4Lvl3+100 && x_shot6>x_enemy4Lvl3){
                    enemy4Lvl3 = false;
                    shot6 = false;
                    x_shot6 = x+35;
                    y_shot6 = y+25;

                    explode4 = true;
                    x_explode4 = x_enemy4Lvl3;
                    y_explode4 = y_enemy4Lvl3;

                    x_enemy4Lvl3 =0;
                    y_enemy4Lvl3 = 0;
                    score++;
                }
                if (y_shot6 < y_enemy5Lvl3 && x_shot6 <x_enemy5Lvl3+100 && x_shot6>x_enemy5Lvl3){
                    enemy5Lvl3 = false;
                    shot6 = false;
                    x_shot6 = x+35;
                    y_shot6 = y+25;

                    explode5 = true;
                    x_explode5 = x_enemy5Lvl3;
                    y_explode5 = y_enemy5Lvl3;

                    x_enemy5Lvl3 =0;
                    y_enemy5Lvl3 = 0;
                    score++;
                }
            }
            
        }
        
        public void collidePE(){
            if (level1C){
                if (x >= x_enemy1 && x <= x_enemy1+99){
                    if (y > y_enemy1 && y < y_enemy1+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy1: "+x_enemy1+" y_enemy1: "+y_enemy1);
                        enemy1 = false;
                        x_enemy1 = 0;
                        y_enemy1 = 0;
                        hp-=20;
                    }
                }
                if (x >= x_enemy2 && x <= x_enemy2+99){
                    if (y > y_enemy2 && y < y_enemy2+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy2: "+x_enemy2+" y_enemy2: "+y_enemy2);
                        enemy2 = false;
                        x_enemy2 = 0;
                        y_enemy2 = 0;
                        hp-=20;
                    }
                }
                if (x >= x_enemy3 && x <= x_enemy3+99){
                    if (y > y_enemy3 && y < y_enemy3+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy3: "+x_enemy3+" y_enemy3: "+y_enemy3);
                        enemy3 = false;
                        x_enemy3 = 0;
                        y_enemy3 = 0;
                        hp-=20;
                    }
                }
                if (x >= x_enemy4 && x <= x_enemy4+99){
                    if (y > y_enemy4 && y < y_enemy4+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy4: "+x_enemy3+" y_enemy4: "+y_enemy4);
                        enemy4 = false;
                        x_enemy4 = 0;
                        y_enemy4 = 0;
                        hp-=20;
                    }
                }
            }
            
            if (level2C){
                if (x >= x_enemy1Lvl2 && x <= x_enemy1Lvl2+99){
                    if (y > y_enemy1Lvl2 && y < y_enemy1Lvl2+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy1Lvl2: "+x_enemy1Lvl2+" y_enemy1Lvl2: "+y_enemy1Lvl2);
                        enemy1Lvl2 = false;
                        x_enemy1Lvl2 = 0;
                        y_enemy1Lvl2 = 0;
                        hp-=20;
                    }
                }
                if (x >= x_enemy2Lvl2 && x <= x_enemy2Lvl2+99){
                    if (y > y_enemy2Lvl2 && y < y_enemy2Lvl2+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy2Lvl2: "+x_enemy2Lvl2+" y_enemy2Lvl2: "+y_enemy2Lvl2);
                        enemy2Lvl2 = false;
                        x_enemy2Lvl2 = 0;
                        y_enemy2Lvl2 = 0;
                        hp-=20;
                    }
                }
                if (x >= x_enemy3Lvl2 && x <= x_enemy3Lvl2+99){
                    if (y > y_enemy3Lvl2 && y < y_enemy3Lvl2+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy3Lvl2: "+x_enemy3Lvl2+" y_enemy3Lvl2: "+y_enemy3Lvl2);
                        enemy3Lvl2 = false;
                        x_enemy3Lvl2 = 0;
                        y_enemy3Lvl2 = 0;
                        hp-=20;
                    }
                }
                if (x >= x_enemy4Lvl2 && x <= x_enemy4Lvl2+99){
                    if (y > y_enemy4Lvl2 && y < y_enemy4Lvl2+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy4Lvl2: "+x_enemy4Lvl2+" y_enemy4Lvl2: "+y_enemy4Lvl2);
                        enemy4Lvl2 = false;
                        x_enemy4Lvl2 = 0;
                        y_enemy4Lvl2 = 0;
                        hp-=20;
                    }
                }
                if (x >= x_enemy5Lvl2 && x <= x_enemy5Lvl2+99){
                    if (y > y_enemy5Lvl2 && y < y_enemy5Lvl2+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy5Lvl2: "+x_enemy5Lvl2+" y_enemy5Lvl2: "+y_enemy5Lvl2);
                        enemy5Lvl2 = false;
                        x_enemy5Lvl2 = 0;
                        y_enemy5Lvl2 = 0;
                        hp-=20;
                    }
                }
            }
            
            if (level3C){
                if (x >= x_enemy1Lvl3 && x <= x_enemy1Lvl3+99){
                    if (y > y_enemy1Lvl3 && y < y_enemy1Lvl3+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy1Lvl3: "+x_enemy1Lvl3+" y_enemy1Lvl3: "+y_enemy1Lvl3);
                        enemy1Lvl3 = false;
                        x_enemy1Lvl3 = 0;
                        y_enemy1Lvl3 = 0;
                        hp-=20;
                    }
                }
                if (x >= x_enemy2Lvl3 && x <= x_enemy2Lvl3+99){
                    if (y > y_enemy2Lvl3 && y < y_enemy2Lvl3+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy2Lvl3: "+x_enemy2Lvl3+" y_enemy2Lvl3: "+y_enemy2Lvl3);
                        enemy2Lvl3 = false;
                        x_enemy2Lvl3 = 0;
                        y_enemy2Lvl3 = 0;
                        hp-=20;
                    }
                }
                if (x >= x_enemy3Lvl3 && x <= x_enemy3Lvl3+99){
                    if (y > y_enemy3Lvl3 && y < y_enemy3Lvl3+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy3Lvl3: "+x_enemy3Lvl3+" y_enemy3Lvl3: "+y_enemy3Lvl3);
                        enemy3Lvl3 = false;
                        x_enemy3Lvl3 = 0;
                        y_enemy3Lvl3 = 0;
                        hp-=20;
                    }
                }
                if (x >= x_enemy4Lvl3 && x <= x_enemy4Lvl3+99){
                    if (y > y_enemy4Lvl3 && y < y_enemy4Lvl3+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy4Lvl3: "+x_enemy4Lvl3+" y_enemy4Lvl3: "+y_enemy4Lvl3);
                        enemy4Lvl3 = false;
                        x_enemy4Lvl3 = 0;
                        y_enemy4Lvl3 = 0;
                        hp-=20;
                    }
                }
                if (x >= x_enemy5Lvl3 && x <= x_enemy5Lvl3+99){
                    if (y > y_enemy5Lvl3 && y < y_enemy5Lvl3+50) {
                        log("20 HP loss at "+"x: "+x+" y: "+y+ "  x_enemy5Lvl3: "+x_enemy5Lvl3+" y_enemy5Lvl3: "+y_enemy5Lvl3);
                        enemy5Lvl3 = false;
                        x_enemy5Lvl3 = 0;
                        y_enemy5Lvl3 = 0;
                        hp-=20;
                    }
                }
            }
            
            if (hp <= 0){
                gameOver();
            }
            
        }
        
        boolean gameOver = false;
        boolean levelsSet = true;
        public void gameOver(){
            gameOver = true;
            enemy1 = false;
            enemy2 = false;
            enemy3 = false;
            enemy4 = false;
            x_enemy1 = 0;
            y_enemy1 = 0;
            x_enemy2 = 0;
            y_enemy2 = 0;
            x_enemy3 = 0;
            y_enemy3 = 0;
            x_enemy4 = 0;
            y_enemy4 = 0;
            
            if (levelsSet) {
                enemy1Lvl2 = false;
                enemy2Lvl2 = false;
                enemy3Lvl2 = false;
                enemy4Lvl2 = false;
                enemy5Lvl2 = false;

                x_enemy1Lvl2 = 0;
                x_enemy2Lvl2 = 0;
                x_enemy3Lvl2 = 0;
                x_enemy4Lvl2 = 0;
                x_enemy5Lvl2 = 0;

                y_enemy1Lvl2 = 0;
                y_enemy2Lvl2 = 0;
                y_enemy3Lvl2 = 0;
                y_enemy4Lvl2 = 0;
                y_enemy5Lvl2 = 0;

                enemy1Lvl3 = false;
                enemy2Lvl3 = false;
                enemy3Lvl3 = false;
                enemy4Lvl3 = false;
                enemy5Lvl3 = false;

                x_enemy1Lvl3 = 0;
                x_enemy2Lvl3 = 0;
                x_enemy3Lvl3 = 0;
                x_enemy4Lvl3 = 0;
                x_enemy5Lvl3 = 0;

                y_enemy1Lvl3 = 0;
                y_enemy2Lvl3 = 0;
                y_enemy3Lvl3 = 0;
                y_enemy4Lvl3 = 0;
                y_enemy5Lvl3 = 0;

                shot1 = false;
                shot2 = false;
                shot3 = false;
                shot4 = false;
                shot5 = false;
                shot6 = false;
            
        }
            level1C = true;
            level2C = false;
            level3C = false;
            
            cheat = 0;
            
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            
            super.paintComponent(g); 
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 4F);
            
            if (intial){
                if (rule) {
                    g.drawImage(rulesScreen,0,0,500,800,null);
                    log("drawing");
                    return;
                }
                
                g.drawImage(loadingScreen,0,0,500,800,null);
                log("intial");
                return;
            }
            
            if (gameOver) {
                newFont = currentFont.deriveFont(currentFont.getSize() * 8F);
                g.setFont(newFont);
                g.drawImage(gameOverScreen,0,0,500,800,null);
                g.setColor(Color.YELLOW);
                g.drawString(score+"",125,373);
                
                
                
                return;
            }
            
            
            g.drawImage(back, 0, 0, 500,800 ,null);

            
            
            if (shot1){
                g.drawImage(missile1,x_shot1,y_shot1,25,75,null);
                if (y_shot1 < 0){
                    shot1 = false;
                    y_shot1 = y+25;
                }
            }
            
            if (shot2){
                g.drawImage(missile2 ,x_shot2,y_shot2,25,75,null);
                if (y_shot2 < 0){
                    shot2 = false;
                    y_shot2 = y+25;
                }
            }
            
            if (shot3){
                g.drawImage(missile3,x_shot3,y_shot3,25,75,null);
                
                if (y_shot3 < 0){
                    shot3 = false;
                    y_shot3 = y+25;
                }
            }
            
            if (shot4){
                g.drawImage(missile4,x_shot4,y_shot4,25,75,null);
                
                if (y_shot4 < 0){
                    shot4 = false;
                    y_shot4 = y+25;
                }
            }
            if (shot5){
                g.drawImage(missile5,x_shot5,y_shot5,25,75,null);
                
                if (y_shot5 < 0){
                    shot5 = false;
                    y_shot5 = y+25;
                }
            }
            if (shot6){
                g.drawImage(missile6,x_shot6,y_shot6,25,75,null);
                
                if (y_shot6 < 0){
                    shot6 = false;
                    y_shot6 = y+25;
                }
            }
            
            if (level1C) {

                if (enemy1){
                    g.drawImage(enemyship1, x_enemy1, y_enemy1, 100,75 ,null);
                }if (enemy2){
                    g.drawImage(enemyship2, x_enemy2, y_enemy2, 100,75 ,null);
                }
                if (enemy3){
                    g.drawImage(enemyship3, x_enemy3, y_enemy3, 100,75 ,null);
                }
                if (enemy4){
                    g.drawImage(enemyship4, x_enemy4, y_enemy4, 100,75 ,null);
                }

                if (explode1) {
                    g.drawImage(explodeImg1, x_explode1, y_explode1, 100,100 ,null);

                    if (explodeC1 == 2){
                        explode1 = false;
                        explodeC1 = 0;
                        x_explode1 = 0;
                        y_explode1 = 0;
                    }else explodeC1++;

                }if (explode2) {
                    g.drawImage(explodeImg2, x_explode2, y_explode2, 100,100 ,null);
                    if (explodeC2 == 2){
                        explode2 = false;
                        explodeC2 = 0;
                        x_explode2 = 0;
                        y_explode2 = 0;
                    }else explodeC2++;

                }if (explode3) {
                    g.drawImage(explodeImg3, x_explode3, y_explode3, 100,100 ,null);
                    if (explodeC3 == 2){
                        explode3 = false;
                        explodeC3 = 0;
                        x_explode3 = 0;
                        y_explode3 = 0;
                    }else explodeC3++;
                }if (explode4) {
                    g.drawImage(explodeImg4, x_explode4, y_explode4, 100,100 ,null);
                    if (explodeC4 == 2){
                        explode4 = false;
                        explodeC4 = 0;
                        x_explode4 = 0;
                        y_explode4 = 0;
                    }else explodeC4++;

                }
            }
            
            if (level2C){
                if (enemy1Lvl2){
                    g.drawImage(enemyship1Lvl2, x_enemy1Lvl2, y_enemy1Lvl2, 100,75 ,null);
                }if (enemy2Lvl2){
                    g.drawImage(enemyship2Lvl2, x_enemy2Lvl2, y_enemy2Lvl2, 100,75 ,null);
                }if (enemy3Lvl2){
                    g.drawImage(enemyship3Lvl2, x_enemy3Lvl2, y_enemy3Lvl2, 100,75 ,null);
                }if (enemy4Lvl2){
                    g.drawImage(enemyship4Lvl2, x_enemy4Lvl2, y_enemy4Lvl2, 100,75 ,null);
                }if (enemy5Lvl2){
                    g.drawImage(enemyship5Lvl2, x_enemy5Lvl2, y_enemy5Lvl2, 100,75 ,null);
                }
                if (explode1) {
                    g.drawImage(explodeImg1, x_explode1, y_explode1, 100,100 ,null);

                    if (explodeC1 == 2){
                        explode1 = false;
                        explodeC1 = 0;
                        x_explode1 = 0;
                        y_explode1 = 0;
                    }else explodeC1++;

                }if (explode2) {
                    g.drawImage(explodeImg2, x_explode2, y_explode2, 100,100 ,null);
                    if (explodeC2 == 2){
                        explode2 = false;
                        explodeC2 = 0;
                        x_explode2 = 0;
                        y_explode2 = 0;
                    }else explodeC2++;

                }if (explode3) {
                    g.drawImage(explodeImg3, x_explode3, y_explode3, 100,100 ,null);
                    if (explodeC3 == 2){
                        explode3 = false;
                        explodeC3 = 0;
                        x_explode3 = 0;
                        y_explode3 = 0;
                    }else explodeC3++;
                }if (explode4) {
                    g.drawImage(explodeImg4, x_explode4, y_explode4, 100,100 ,null);
                    if (explodeC4 == 2){
                        explode4 = false;
                        explodeC4 = 0;
                        x_explode4 = 0;
                        y_explode4 = 0;
                    }else explodeC4++;

                }if (explode5) {
                    g.drawImage(explodeImg5, x_explode5, y_explode5, 100,100 ,null);
                    if (explodeC5 == 2){
                        explode5 = false;
                        explodeC5 = 0;
                        x_explode5 = 0;
                        y_explode5 = 0;
                    }else explodeC5++;

                }
            }
            
            if (level3C){
                if (enemy1Lvl3){
                    g.drawImage(enemyship1Lvl3, x_enemy1Lvl3, y_enemy1Lvl3, 100,75 ,null);
                }if (enemy2Lvl3){
                    g.drawImage(enemyship2Lvl3, x_enemy2Lvl3, y_enemy2Lvl3, 100,75 ,null);
                }if (enemy3Lvl3){
                    g.drawImage(enemyship3Lvl3, x_enemy3Lvl3, y_enemy3Lvl3, 100,75 ,null);
                }if (enemy4Lvl3){
                    g.drawImage(enemyship4Lvl3, x_enemy4Lvl3, y_enemy4Lvl3, 100,75 ,null);
                }if (enemy5Lvl3){
                    g.drawImage(enemyship5Lvl3, x_enemy5Lvl3, y_enemy5Lvl3, 100,75 ,null);
                }
                if (explode1) {
                    g.drawImage(explodeImg1, x_explode1, y_explode1, 100,100 ,null);

                    if (explodeC1 == 2){
                        explode1 = false;
                        explodeC1 = 0;
                        x_explode1 = 0;
                        y_explode1 = 0;
                    }else explodeC1++;

                }if (explode2) {
                    g.drawImage(explodeImg2, x_explode2, y_explode2, 100,100 ,null);
                    if (explodeC2 == 2){
                        explode2 = false;
                        explodeC2 = 0;
                        x_explode2 = 0;
                        y_explode2 = 0;
                    }else explodeC2++;

                }if (explode3) {
                    g.drawImage(explodeImg3, x_explode3, y_explode3, 100,100 ,null);
                    if (explodeC3 == 2){
                        explode3 = false;
                        explodeC3 = 0;
                        x_explode3 = 0;
                        y_explode3 = 0;
                    }else explodeC3++;
                }if (explode4) {
                    g.drawImage(explodeImg4, x_explode4, y_explode4, 100,100 ,null);
                    if (explodeC4 == 2){
                        explode4 = false;
                        explodeC4 = 0;
                        x_explode4 = 0;
                        y_explode4 = 0;
                    }else explodeC4++;

                }if (explode5) {
                    g.drawImage(explodeImg5, x_explode5, y_explode5, 100,100 ,null);
                    if (explodeC5 == 2){
                        explode5 = false;
                        explodeC5 = 0;
                        x_explode5 = 0;
                        y_explode5 = 0;
                    }else explodeC5++;

                }
            }
            
            
            
            newFont = currentFont.deriveFont(currentFont.getSize() * 4F);
            g.setFont(newFont);
            g.setColor(Color.RED);
            g.drawString(score+"", 375, 83);
            g.drawImage(ship, x, y, 100,100 ,null);
            
            g.drawString("HP: "+hp,10,45);
            
            if (!intial && level1){
                g.drawImage(level1Img,175,300,150,150,null);
                
                if (countLevelShow1 == 30){
                    level1 = false;
                    
                }else countLevelShow1++;
                
            }
            if (!intial && level2 && !level1){
                g.drawImage(level2Img,175,300,150,150,null);
                
                
                if (countLevelShow2 == 30){
                    level2 = false;
                    
                    
                }else countLevelShow2++;
                
            }
            if (!intial && level3 && !level2){
                g.drawImage(level3Img,175,300,150,150,null);
                
                
                if (countLevelShow3 == 30){
                    level3 = false;
                    
                }else countLevelShow3++;
                
            }
        }
        
        int cheat = 0;
        
        @Override
        public void keyPressed(KeyEvent e) {
            
            
            
            if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
                
                if (intial) return;
                
                x-=50;
                if (x <= 0){
                    x = 0;
                }
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (intial) return;
                
                x+=50;
                if (x>= 400){
                    x = 400;
                }
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (intial) return;
                y-=25;
                if (y<=550){
                    y=550;
                }
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (intial) return;
                y+=25;
                if (y >= 680){
                    y = 680;
                }
                repaint();
            }if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (intial) return;
                
                shotsFired();
                
                
            }if (e.getKeyCode() == KeyEvent.VK_T) {
                if (intial) return;
                if (cheat == 0){
                    score = 50;
                    log("Cheat Level UP");
                    cheat++;
                }else if (cheat == 1){
                    score = 100;
                    log("Cheat Level UP");
                }
            }if (e.getKeyCode() == KeyEvent.VK_Y) {
                if (intial) return;
                hp = 100;
                log("Cheat Full HP");
            }
        
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
        
    }
}