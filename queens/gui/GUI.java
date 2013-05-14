package queens.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import queens.game.Game;
import queens.game.type.*;
import queens.util.Debug;

/**
 *
 * @author Kieran
 */
public class GUI extends JFrame implements ActionListener {

    /**
     * Game components to pass to other classes
     */
    private int board_size = 8;

    /**
     * GUI Components
     */
    JButton[][] btns;

    JComboBox gameModes = new JComboBox(new String[] {
        "Rooks", "Bishops", "Queens"
    });
    static JProgressBar progressBar = null;
    JButton resetButton = new JButton("Reset");
    JButton solveButton = new JButton("Solve");
    
    /**
     * Menu bar components
     */
    private JMenuItem resGame = new JMenuItem("Reset Game");
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem rooksMenu = new JMenuItem("Rooks");
    private JMenuItem bishopsMenu = new JMenuItem("Bishops");
    private JMenuItem queensMenu = new JMenuItem("Queens");
    private JMenuItem aboutMenu = new JMenuItem("About");
    
    
    /**
     * Game Modes
     */
    private Game game;
    private Rooks rooks = null; // initialise to null so we know its not in use
    private Bishops bishops = null;
    private Queens queens = null;

    private int gameType;


    /**
     * Initialises and manages all GUI components
     */
    public GUI(int boardSize) {
        // Game components
        board_size = boardSize;

        // Initialise GUI components
        setTitle("8 Queens Puzzle");
        //setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set the background panel (content pane) to which we add our components
        JPanel bgPnl = new JPanel(new BorderLayout());
        setContentPane(bgPnl);

        /**
         * JMenu Components initialised below
         */
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create File menu
        JMenu fileMenu = new JMenu("File");
        resGame.addActionListener(this);
        fileMenu.add(resGame);
        exit.addActionListener(this);
        fileMenu.add(exit);
        menuBar.add(fileMenu);

        // Create Game Modes menu
        JMenu gameMenu = new JMenu("Game Modes");
        rooksMenu.addActionListener(this);
        gameMenu.add(rooksMenu);
        bishopsMenu.addActionListener(this);
        gameMenu.add(bishopsMenu);
        queensMenu.addActionListener(this);
        gameMenu.add(queensMenu);
        menuBar.add(gameMenu);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        aboutMenu.addActionListener(this);
        helpMenu.add(aboutMenu);
        menuBar.add(helpMenu);

        /**
         * Northern panel of the GUI consisting of game information initialised here
         */
        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("8 - Queens Puzzle", JLabel.CENTER);     // Title of the GUI
        title.setFont(new Font("sansserif", Font.BOLD, 32)); // Set the font to large
        topPanel.add(title, BorderLayout.NORTH);

        JLabel gameModeLabel = new JLabel("Select a game mode: ");
        gameModeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // set padding around the element
        topPanel.add(gameModeLabel, BorderLayout.WEST);
        
        gameModes.setPreferredSize(new Dimension(130, 20)); // set size of component
        gameModes.addActionListener(this);
        gameModes.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // set padding around the element (matching label)
        topPanel.add(gameModes, BorderLayout.EAST);

        bgPnl.add(topPanel, BorderLayout.NORTH); // Add panel to the content pane

        /**
         * Game grid initialised and managed here
         */
        JPanel gridPanel = new JPanel(new GridLayout(board_size + 1, board_size));
        btns = new JButton[board_size][board_size];
        
        for (int i = 0; i < board_size; i++) {
            for (int k = 0; k < board_size; k++) {
                btns[i][k] = new JButton(" ");
                btns[i][k].setPreferredSize(new Dimension(50, 50));

                final int numRow = i, numCol = k; // provide variable access to actionlistener class

                // Add a mouse listener to detect when the disabled buttons are clicked
                btns[i][k].addMouseListener(new MouseListener() 
                {

                    public void mouseClicked(MouseEvent e) {
                        /* Unset button event */
                        if (!btns[numRow][numCol].isEnabled() && btns[numRow][numCol].getBackground() == Color.GREEN)
                        {
                            if (gameType == 1 && rooks != null)
                                rooks.unset(btns, numRow, numCol);
                            else if (gameType == 2 && bishops != null)
                                bishops.unset(btns, numRow, numCol);
                            else if (gameType == 3 && queens != null)
                                queens.unset(btns, numRow, numCol);
                        }
                        else
                        {
                            /* Game events.. */
                            if(gameType != 0) // ensure game type is selected to avoid exception errors..
                            {
                                if (!game.hasWon())
                                {
                                    if (gameType == 1 && rooks != null)
                                        rooks.placeMark(btns, numRow, numCol);

                                    else if(gameType == 2 && bishops != null)
                                        bishops.placeMark(btns, numRow, numCol);

                                    else if(gameType == 3 && queens != null)
                                        queens.placeMark(btns, numRow, numCol);

                                }
                            }
                            else
                                Debug.print("Please select a game type");
                        }
                    }

                    public void mousePressed(MouseEvent e) { }

                    public void mouseReleased(MouseEvent e) { }

                    public void mouseEntered(MouseEvent e) { }

                    public void mouseExited(MouseEvent e) { }

                    
                });

                gridPanel.add(btns[i][k]);
            }
        }
        bgPnl.add(gridPanel, BorderLayout.CENTER);

        /**
         * Content to be displayed at the bottom of the GUI
         */
        JPanel bottomPnl = new JPanel(new BorderLayout());

        // create new panel to house the progress bar components
        JPanel internalPnl1 = new JPanel(new BorderLayout());
        JLabel progressLabel = new JLabel("Your progress: ");
        internalPnl1.add(progressLabel, BorderLayout.NORTH);
        progressBar = new JProgressBar(0, 0); // we'll initiaise the maximum value when the game type is selected
        progressBar.setValue(0);
        progressBar.setStringPainted(true); // enable custom messages to be written
        progressBar.setString("0");       // write message to the progress bar
        internalPnl1.add(progressBar, BorderLayout.SOUTH);

        // add panel to the bottom house
        bottomPnl.add(internalPnl1, BorderLayout.NORTH);

        JPanel internalPnl2 = new JPanel(new FlowLayout());
        resetButton.addActionListener(this);
        internalPnl2.add(resetButton);
        solveButton.addActionListener(this);
        internalPnl2.add(solveButton);

        bottomPnl.add(internalPnl2, BorderLayout.SOUTH);
        bgPnl.add(bottomPnl, BorderLayout.SOUTH); // add bottom panel to the content pane

        /**
         * Finish up
         */
        pack();
    }

    public void actionPerformed(ActionEvent e)
    {
        /* File Menu */
        if (e.getSource() == resGame || e.getSource() == resetButton)
        {
            resetGame();
        }
        else if(e.getSource() == exit)
        {
            System.exit(0);
        }
        else if (e.getSource() == aboutMenu)
        {
            Help h = new Help();
        }

        /* Game modes (from menu bar and dropdown) */
        else if (e.getSource() == rooksMenu ||
                (e.getSource() == gameModes && gameModes.getSelectedItem() == "Rooks"))
        {
            gameType = 1;

            game = rooks = new Rooks(board_size, board_size, gameType);

            progressBar.setMaximum(8);

            disableGameTypes();
        }
        else if (e.getSource() == bishopsMenu  ||
                (e.getSource() == gameModes && gameModes.getSelectedItem() == "Bishops"))
        {
            gameType = 2;

            game = bishops = new Bishops(board_size, board_size, gameType);

            progressBar.setMaximum(13);

            disableGameTypes();
        }
        else if (e.getSource() == queensMenu  ||
                (e.getSource() == gameModes && gameModes.getSelectedItem() == "Queens"))
        {
            gameType = 3;

            game = queens = new Queens(board_size, board_size, gameType);

            progressBar.setMaximum(8); 

            disableGameTypes();
        }

        /* Buttons */
        else if (e.getSource() == solveButton)
        {
            throw new UnsupportedOperationException("Not yet implemented: solve feature");
        }
    }

    /**
     * Enable all game type buttons
     */
    private void enableGameTypes() {
        gameModes.setEnabled(true);
    }

    /**
     * Disable all game type buttons (stop user causing problems)
     */
    private void disableGameTypes() {
        gameModes.setEnabled(false);
    }

    /**
     * Update the information label with the new number of pieces placed to the board
     * @param num Number of tiles placed on the board
     */
    public static void updateCounter(int num) {
        if (num < progressBar.getValue()) {
            int update = progressBar.getValue() - 1;

            progressBar.setValue(update);
            progressBar.setString(Integer.toString(update));
        }
        else if (num > progressBar.getValue()) {
            int update = progressBar.getValue() + 1;

            progressBar.setValue(update);
            progressBar.setString(Integer.toString(update));
        }
    }

    /**
     * Completely reset the interface and back-end (Board)
     * All buttons, information labels and game type reset to their original state
     * @see Board
     */
    private void resetGame() {
        for (int i = 0; i < btns[0].length; i++) {
            for (int k = 0; k < btns.length; k++) {
                btns[i][k].setIcon(null);
                btns[i][k].setEnabled(true);
                btns[i][k].setBackground(UIManager.getColor("Button.background"));
            }
        }

        gameType = 0; // reset game type (force re-check to avoid bugs..)
        progressBar.setValue(0);
        progressBar.setString("0");
        enableGameTypes(); // re-enable all the GUI components

        // reset backend
        game.initialise(); // reset the board to default character
        game.resetCounter();
        game.resetGameType();
    }

}
