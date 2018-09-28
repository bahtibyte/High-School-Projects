package checkers;

import javax.swing.JFrame;

public class Checkers extends JFrame{
    
    public Checkers(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,820);
        setResizable(false);
        setTitle("Checkers");

        setLocation(950, 250);
        
        Screen s = new Screen();
        add(s);
        
        setVisible(true);
        
    }

    public static void main(String[]args){
        Checkers checkers = new Checkers();
    }

}