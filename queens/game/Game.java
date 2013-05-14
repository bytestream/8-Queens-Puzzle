package queens.game;

import javax.swing.JButton;
import javax.swing.UIManager;
import queens.gui.GUI;
import queens.util.Debug;

/**
 *
 * @author Kieran
 * @version 0.08
 * @see Board
 *
 * Provides specific functionality required to initialise and maintain an
 * 8-Queens game.
 */
public class Game extends Board {

    // Variable declaration
    private int gameType = 0;
    private int gameCounter = 0;

    private final int initChar = 0;
    // End variable declaration

    /**
     * Creates a board of size numRows x numCols
     *
     * @param numRows
     * @param numCols
     * @param c
     * @see Board
     */
    public Game(int numRows, int numCols, int gameType) {
        super(numRows, numCols);

        this.gameType = gameType;

        initialise();
    }

    /**
     * Checks if a square at position numRow, numCol is occupied by a tile
     * @param numRow
     * @param numCol
     * @return boolean
     */
    public boolean isOccupied(int numRow, int numCol) {
        if (getSquare(numRow, numCol) != initChar)
            return true;
        else
            return false;
    }

    /**
     * Checks if all tiles have been attacked using the correct number of tiles
     * for that particular game type (Rooks, Bishops, Queens)
     * @return boolean
     */
    public boolean hasWon() {
        // Check Rooks and Queens
        if (gameType == 1 || gameType == 3) {
            if (getCounter() < 8 && boardFull()) {
                Debug.print("You failed to complete the puzzle. \nClick reset to try again.");
                return true; // stop the game
            }

            else if (getCounter() == 8) {
                for (int i = 0; i < getBoardWidth(); i++) {
                    for (int k = 0; k < getBoardHeight(); k++) {
                        if (getSquare(i, k) == initChar) {
                            Debug.print("You failed to complete the puzzle2. \nClick reset to try again.");
                            return true; // stop the game
                        }
                    }
                }

                Debug.print("CONGRATULATIONS YOU SOLVED THE PUZZLE!!");
                return true;
            }
        }

        // check bishops
        else if(gameType == 2) {
            if (getCounter() < 14 && boardFull()) {
                Debug.print("You failed to complete the puzzle. \nClick reset to try again.");
                return true; // stop the game
            }

            else if (getCounter() == 14) {
                for (int i = 0; i < getBoardWidth(); i++) {
                    for (int k = 0; k < getBoardHeight(); k++) {
                        if (getSquare(i, k) == initChar) {
                            Debug.print("You failed to complete the puzzle2. \nClick reset to try again.");
                            return true; // stop the game
                        }
                    }
                }

                Debug.print("CONGRATULATIONS YOU SOLVED THE PUZZLE!!");
                return true;
            }
        }

        return false;
    }

    /**
     * Unset the buttons on the interface based on whether the square is under attack or not
     * @param btns 2D matrix of the chess board (JButtons)
     */
    public void unset(JButton[][] btns) {
        for (int i = 0; i < getBoardWidth(); i++) {
            for (int k = 0; k < getBoardHeight(); k++) {
                // Check if the square is under attack
                if (getSquare(i,k) == 0) {
                    btns[i][k].setEnabled(true);
                    btns[i][k].setIcon(null);
                    btns[i][k].setEnabled(true);
                    btns[i][k].setBackground(UIManager.getColor("Button.background"));
                }
            }
        }
    }

    /**
     *
     * @return 'int' of number of tiles placed
     */
    public int getCounter() {
        return gameCounter;
    }

    /**
     * Decrease the tile counter by 1
     */
    public void decCounter() {
        this.gameCounter--;
        GUI.updateCounter(getCounter());
    }

    /**
     *
     * Increments the number of tiles placed to the board.
     */
    public void incCounter() {
        this.gameCounter++;
        GUI.updateCounter(getCounter());
    }

    /**
     * Resets the number of tiles placed to 0
     */
    public void resetCounter() {
        this.gameCounter = 0;
    }

    /**
     * Removes the current game type (Rooks, Bishops, Queens) forcing reselection
     */
    public void resetGameType() {
        this.gameType = 0;
    }
    
}
