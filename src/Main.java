import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main extends JFrame{

    public static void main(String args[]){
        new Main();
    }

    public Main(){
        setTitle("Minesweeper");
        setSize(360,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(new Board());
        pack();
        setResizable(false);
        setVisible(true);
    }
}