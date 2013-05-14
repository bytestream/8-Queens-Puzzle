package queens.gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Kieran
 * @version 0.01
 *
 * Creates a small JFrame consisting of solely help text
 */
public class Help extends JFrame
{

    /**
     * Initialises all necessary JFrame methods and GUI components
     */
    public Help()
    {
        setTitle("Help");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel pnl = new JPanel();
        setContentPane(pnl);

        JTextArea helpTxt = new JTextArea(
                "The aim of the Eight Queens problem is "
                + "to place 8 Queens on the chess board, so that no "
                + "Queen threatens the territory of another Queen.\n\n"
                + "The game can also be played with different chess"
                + "peices .e.g 8 Rooks can be placed on the board (trivial "
                + "to solve!)... or 14 Bishops can be placed on the board.");
        helpTxt.setEditable(false);
        helpTxt.setLineWrap(true);      // wrap lines
        helpTxt.setWrapStyleWord(true); // force words that don't fit on the line to move to the next one
        helpTxt.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.LIGHT_GRAY));
        helpTxt.setPreferredSize(new Dimension(300, 150));
        pnl.add(helpTxt);

        pack();
        setVisible(true);
    }

}
