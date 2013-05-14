package queens.util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Kieran
 * @version 0.1
 *
 * Provides the default print routine - enables easy changing from console to GUI
 *
 */
public class Debug {

    public static void print(String s) {
        JOptionPane.showMessageDialog(new JFrame(), s);
    }

}
