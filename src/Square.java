import java.awt.*;
import javax.swing.JButton;

public class Square extends JButton{

    private int row, col;
    private boolean mine, flag;
    private int nMines;

    public Square(int row, int col){
        setPreferredSize(new Dimension(32, 32));
        setMargin(new Insets(2,2,2,2));

        this.row = row;
        this.col = col;

        this.mine = false;
        this.flag = false;

        this.nMines = 0;

    }

    public void setMine(){
        mine = true;
    }

    public boolean isMine(){
        return mine;
    }

    public void addMines(){
        nMines++;
    }

    public int getNeighbours(){
        return nMines;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }


}