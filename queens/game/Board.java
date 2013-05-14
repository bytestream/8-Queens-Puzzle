package queens.game;

/**
 *
 * @author Kieran
 * @version 0.1
 *
 * Provides the default operations required to create a board game
 *
 */
public class Board {
	
    private int numRows;
    private int numCols;
    private final int initChar = 0;

    private int[][] board;

    /**
     * Initialises variables required for the class to operate
     * @param numRows
     * @param numCols
     * @param c
     */
    public Board(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;

        board = new int[numRows][numCols];
    }

    /**
     * Prints the game board to console
     */
    public void display() {
        for (int i = 0; i < board.length; i++) {
            System.out.print("\t");
            for (int k = 0; k < board[0].length; k++) {
                System.out.print(board[i][k] + " ");
            }
            System.out.print("\n");
        }
    }

    /**
     * Initialises the board using the rows and columns provided in the constructor
     * @see Configuration getDefaultCharacter()
     */
    public void initialise() {
        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[0].length; k++) {
                board[i][k] = initChar;
            }
        }
    }

    /**
     * Checks if the board is full of attacked squares (not the default character)
     * @see Configuration getDefaultCharacter()
     * @return boolean
     */
    public boolean boardFull() {
        for (int i = 0; i < board[0].length; i++) {
            for (int k = 0; k < board.length; k++) {
                if (board[i][k] == initChar)
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if the specified row and column are within the bounds of the board
     * @param numRow
     * @param numCol
     * @return boolean
     */
    public boolean isValidCoord(int numRow, int numCol) {
        if ((numRow >= 0 && numRow < this.numRows) && (numCol >= 0 && numCol < this.numCols))
            return true;

        else {
            return false;
        }
    }

    /**
     * Returns if there is a title on the specified square (row x column)
     * @param numRow
     * @param numCol
     * @return char
     */
    public int getSquare(int numRow, int numCol) {
        if (isValidCoord(numRow, numCol))
            return board[numRow][numCol];
        
        else
            return (Character) null;
    }

    /**
     * Sets the specified character to numRow x numCol
     * @param numRow
     * @param numCol
     * @param c
     */
    public void setSquare(int numRow, int numCol) {
        if (isValidCoord(numRow, numCol))
            board[numRow][numCol] += 1;
    }

    /**
     * Set the specified square back to the default character
     * @param numRow Row number
     * @param numCol Column number
     */
    public void unsetSquare(int numRow, int numCol) {
        if (isValidCoord(numRow, numCol))
        {
            if (board[numRow][numCol] > 0)
                board[numRow][numCol] -= 1;
        }
    }

    /**
     * Returns the width of the board (number of rows)
     * @return int
     */
    public int getBoardWidth() {
        return board[0].length;
    }

    /**
     * Returns the height of the board (number of columns)
     * @return
     */
    public int getBoardHeight() {
        return board.length;
    }

}
