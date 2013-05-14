package queens.game.type;

import java.awt.Color;
import java.awt.Insets;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import queens.game.GameType;
import queens.game.Game;
import queens.gui.GUI;
import queens.util.Debug;

/**
 *
 * @author Kieran
 * @version 0.01
 *
 * Provides the functionality required to perform the Bishops game type
 */
public class Bishops extends Game implements GameType {

    /**
     * Initialises the board
     * @param row Number of rows in the board
     * @param col Number of columns in the board
     * @param gameType The selected game mode
     */
    public Bishops(int row, int col, int gameType) {
        super(row, col, gameType);
    }

    /**
     * Places a character on a specific square
     * @param numRow Number of rows in the board
     * @param numCol Number of columns in the board
     * @return boolean
     */
    public boolean placeMark(JButton[][] btns, int numRow, int numCol) {
        if (isValidCoord(numRow, numCol)) {
            if (isOccupied(numRow, numCol)) {
                Debug.print("Error: Square already occupied, please try again.");
                return false;
            }
            else {
                addComponent(numRow, numCol);
                addComponent2GUI(btns, numRow, numCol);

                hasWon(); // re-check if they've now won (so they don't have to place another square)

                return true;
            }
        }
        return false;
    }

    /**
     * Adds the square to the GUI and associated attacked squares
     * @param btns Buttons array from the GUI
     * @param numRow Number of rows in the board
     * @param numCol Number of columns in the board
     */
    public void addComponent2GUI(JButton[][] btns, int numRow, int numCol) {
        btns[numRow][numCol].setEnabled(false);
        btns[numRow][numCol].setBackground(Color.GREEN);
        btns[numRow][numCol].setMargin(new Insets(0,0,0,0));
        URL imageLoc = GUI.class.getResource("images/black-bishop-2d-icon.png"); // look in the same location as Rooks class
        btns[numRow][numCol].setIcon(new ImageIcon(imageLoc));

        // left diagonal starting from the square clicked to board width (Direction: \)
        for (int i = numRow, k = numCol; i < getBoardWidth(); i++, k++)
        {
            if (isValidCoord(i, k))
                btns[i][k].setEnabled(false);
        }
        // left diagonal starting from the square clicked and going backwards to 0 (Direction: \)
        for (int c = numRow, j = numCol; c >= 0; c--, j--)
        {
            if (isValidCoord(c, j))
                    btns[c][j].setEnabled(false);
        }

        // right diagonal starting from the square clicked to board width (Direction: /)
        for (int i = numRow, k = numCol; i < getBoardWidth(); i++, k--)
        {
            if (isValidCoord(i, k))
                btns[i][k].setEnabled(false);
        }
        // right diagonal starting from the square clicked and going backwards to 0 (Direction: /)
        for (int c = numRow, j = numCol; c >= 0; c--, j++)
        {
            if (isValidCoord(c, j))
                btns[c][j].setEnabled(false);
        }

    }

    /**
     * Add the square to the board at the specified position
     * @param numRow Number of rows in the board
     * @param numCol Number of columns in the board
     */
    public void addComponent(int numRow, int numCol) {
        setSquare(numRow, numCol);
        incCounter();

        // left diagonal starting from the square clicked to board width (Direction: \)
        for (int i = numRow, k = numCol; i < getBoardWidth(); i++, k++)
        {
            if (isValidCoord(i, k))
                setSquare(i, k);
        }
        // left diagonal starting from the square clicked and going backwards to 0 (Direction: \)
        for (int c = numRow, j = numCol; c >= 0; c--, j--)
        {
            if (isValidCoord(c, j))
                setSquare(c, j);
        }

        // right diagonal starting from the square clicked to board width (Direction: /)
        for (int i = numRow, k = numCol; i < getBoardWidth(); i++, k--)
        {
            if (isValidCoord(i, k))
                setSquare(i, k);
        }
        // right diagonal starting from the square clicked and going backwards to 0 (Direction: /)
        for (int c = numRow, j = numCol; c >= 0; c--, j++)
        {
            if (isValidCoord(c, j))
                setSquare(c, j);
        }
    }

    /**
     * Unset a square including all associated under attack squares (checking for overlaps with other tiles)
     * @param btns 2D matrix of the chess board (interface)
     * @param numRow Row number we want to remove
     * @param numCol Column number we want to remove
     */
    public void unset(JButton[][] btns, int numRow, int numCol) {
        decCounter();

        // left diagonal starting from the square clicked to board width (Direction: \)
        for (int i = numRow, k = numCol; i < getBoardWidth(); i++, k++)
        {
            if (isValidCoord(i, k))
                unsetSquare(i, k);
        }
        // left diagonal starting from the square clicked and going backwards to 0 (Direction: \)
        for (int c = numRow, j = numCol; c >= 0; c--, j--)
        {
            if (isValidCoord(c, j))
                unsetSquare(c, j);
        }

        // right diagonal starting from the square clicked to board width (Direction: /)
        for (int i = numRow, k = numCol; i < getBoardWidth(); i++, k--)
        {
            if (isValidCoord(i, k))
                unsetSquare(i, k);
        }
        // right diagonal starting from the square clicked and going backwards to 0 (Direction: /)
        for (int c = numRow, j = numCol; c >= 0; c--, j++)
        {
            if (isValidCoord(c, j))
                unsetSquare(c, j);
        }

        // Unset the buttons on the interface
        unset(btns);
    }
    
}

