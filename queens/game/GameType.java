package queens.game;

import javax.swing.JButton;

/**
 *
 * @author Kieran
 * @version 0.01
 *
 * Provides default functionality required for each game type
 * (Rooks, Bishops, Queens)
 */
public interface GameType {

    /**
     * Places a character on a specific square
     * @param numRow Number of rows in the board
     * @param numCol Number of columns in the board
     * @return boolean
     */
    public boolean placeMark(JButton[][] btns, int numRow, int numCol);

    /**
     * Adds the square to the GUI and associated attacked squares
     * @param btns Buttons array from the GUI
     * @param numRow Number of rows in the board
     * @param numCol Number of columns in the board
     */
    public void addComponent2GUI(JButton[][] btns, int numRow, int numCol);

    /**
     * Add the square to the board at the specified position
     * @param numRow Number of rows in the board
     * @param numCol Number of columns in the board
     */
    public void addComponent(int numRow, int numCol);

    /**
     * Unset a square including all associated under attack squares (checking for overlaps with other tiles)
     * @param btns 2D matrix of the chess board (interface)
     * @param numRow Row number we want to remove
     * @param numCol Column number we want to remove
     */
    public void unset(JButton[][] btns, int numRow, int numCol);

}
