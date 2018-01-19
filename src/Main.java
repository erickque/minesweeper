import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;

public class Main extends JFrame{

    private JLabel statusbar;
    private static boolean gameOver;

    public static void main(String args[]){
        new Main();
    }

    public Main(){
        setTitle("Minesweeper");
        setSize(360,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new Board());
        pack();
        setResizable(false);
        setVisible(true);
    }

    public void newGame(){
        dispose();
    }

}