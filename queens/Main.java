package queens;

import queens.gui.GUI;

/**
 *
 * @author Kieran
 * @version 0.3
 *
 * The main class initialises the necessary class components.
 * Created: 18:30:25
 */
public class Main
{

    /**
     * Size of the board (force only one variable to create a square (same width + height)
     */
    private static final int BOARD_SIZE = 8;
    

    /**
     * Main Class invokes the GUI
     * @param args
     */
    public static void main(String[] args)
    {
        GUI i = new GUI(BOARD_SIZE);
        i.setVisible(true);
    }

}
