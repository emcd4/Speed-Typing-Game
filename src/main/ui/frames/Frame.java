package ui.frames;

import persistance.LeaderBoardWriter;
import persistance.WordBankWriter;
import ui.TypingTutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * A frame that represents one of the stages of the application
 */
public abstract class Frame extends JFrame {

    protected static final int SPACE_SIZE = 10;
    protected static final Dimension BUTTON_DIMENSION = new Dimension(400, 60);
    protected static final Dimension TEXT_DIMENSION = new Dimension(400, 60);
    protected static final Dimension FRAME_DIMENSION = new Dimension(900, 700);

    public static final Font STANDARD_FONT = new Font("SansSerif", Font.PLAIN, 40);

    protected TypingTutor tt;

    // constructs a frame
    // EFFECTS: creates frame in centre of screen at specified size
    public Frame(TypingTutor tt) {
        super("Elizabeth's Fantastic Typing Tutor");
        this.tt = tt;

        initializeComponents();
        implementComponentsFunctionality();
        addToFrame();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitProcedure();
            }
        });

        ImageIcon img = new ImageIcon("./data/logo.jpg");
        setIconImage(img.getImage());
        setMinimumSize(FRAME_DIMENSION);
        getInsets().set(SPACE_SIZE, SPACE_SIZE, SPACE_SIZE, SPACE_SIZE);
        centreOnScreen();
    }

    // MODIFIES: this, leaderBoard.json, wordBank.json
    // EFFECTS: saves the leader board and word bank, closes this frame and ends the program
    private void exitProcedure() {
        tt.saveLeaderBoard();
        tt.saveWordBank();

        dispose();
        System.exit(0);
    }

    // From: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
    // Centres frame on desktop
    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // MODIFIES: this, frame
    // EFFECTS: opens the given frame and closes this frame
    public void openFrame(Frame frame) {
        frame.setVisible(true);
        dispose();
    }

    // MODIFIES: this
    // EFFECTS: initializes frame components
    public abstract void initializeComponents();

    // MODIFIES: this
    // EFFECTS: adds components to frame at proper positions and sizes
    public abstract void addToFrame();

    // MODIFIES: this
    // EFFECTS: implements the interactive components
    public abstract void implementComponentsFunctionality();

    // MODIFIES: s
    // EFFECTS: returns s as all lowercase string and removes leading and trailing white space
    public String formatString(String s) {
        s = s.toLowerCase();
        s = s.trim();
        return s;
    }
}
