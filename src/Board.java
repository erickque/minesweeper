import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Board extends JPanel implements ActionListener{

    private int totalMines, totalSquares;
    private Square[][] squares;
    private Dimension boardSize;

    private JLabel statusBar;

    Color LIGHTBLUE = new Color(46, 171, 228);
    Color DARKGREEN = new Color (0, 100, 0);
    public Board(){
        this.statusBar = statusBar;
        boardSize = new Dimension(9,9);

        newGame();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showSquare((Square)(e.getSource()));
    }

    private void newGame(){
        setLayout(new GridLayout(boardSize.height,boardSize.width));
        totalMines = 10;
        totalSquares = boardSize.height * boardSize.width;

        squares = new Square[boardSize.height][boardSize.width];
        for (int row = 0; row < boardSize.height; row++){
            for (int col = 0; col < boardSize.width; col++){
                squares[row][col] = new Square(row, col);
                squares[row][col].addActionListener(this);
                squares[row][col].setFocusable(false);
                add(squares[row][col]);
            }
        }

        newMines();
    }

    private void newMines(){
        int mines = 0;
        Random rand = new Random();

        do{
            int row = rand.nextInt(8) + 1;
            int col = rand.nextInt(8) + 1;

            if (!squares[row][col].isMine()){
                squares[row][col].setMine();

                // Increment nMines for neighbouring mines.

                for (int i = row - 1; i <= row + 1; i++){
                    for (int j = col - 1; j <= col + 1; j++){
                        if (i == row && j == col)
                            continue;
                        if (i < 0 || i > boardSize.height-1 || j < 0 || j > boardSize.width-1)
                            continue;
                        if (!squares[i][j].isMine()){
                            squares[i][j].addMines();
                            // squares[i][j].setText(Integer.toString(squares[i][j].getNeighbours()));
                        }
                    }
                }
                // squares[row][col].setText("X");
                mines++;
            }
        }
        while (mines < totalMines);
    }

    private void gameOver() {
        String message;
        if (totalSquares != totalMines) {
            for (int row = 0; row < boardSize.height; row++)
                for (int col = 0; col < boardSize.width; col++)
                    if (squares[row][col].isMine())
                        squares[row][col].setText("M");

            message = "You lost.";
        } else {
            message = "You won!";
        }

        Object[] options = {"New Game",
                "Quit"};

        int result = JOptionPane.showOptionDialog(this, message, "End of Game",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

        if (result == JOptionPane.YES_OPTION) {
            removeAll();
            newGame();
            revalidate();
            repaint();

        }
        else
            System.exit(0);

    }

    private void showSquare(Square square){
        square.setEnabled(false);
        //System.out.println(square.getRow());
        //System.out.println(square.getCol());

        if (!square.isMine()) {
            if (square.getNeighbours() == 0)
                showNeighbours(square);
            else {
                int neighbours = square.getNeighbours();

                switch (neighbours){
                    case 1: square.setForeground(LIGHTBLUE);
                        break;
                    case 2: square.setForeground(Color.GREEN);
                        break;
                    case 3: square.setForeground(Color.RED);
                        break;
                    case 4: square.setForeground(Color.BLUE);
                        break;
                    case 5: square.setForeground(DARKGREEN);
                        break;
                    default:
                        break;
                }

                square.setFont(new Font("Helvetica", Font.PLAIN, 20));
                square.setText(Integer.toString(neighbours));
            }
        }
        else
            gameOver();

        totalSquares--;
        if (totalSquares == totalMines)
            gameOver();

    }

    public void showNeighbours(Square square){
        int row = square.getRow();
        int col = square.getCol();

        for (int i = row - 1; i <= row + 1; i++){
            for (int j = col - 1; j <= col + 1; j++){
                if (i == row && j == col)
                    continue;
                if (i < 0 || i > boardSize.height-1 || j < 0 || j > boardSize.width-1)
                    continue;
                if (squares[i][j].isEnabled()){
                    showSquare(squares[i][j]);
                }
            }
        }
    }

}