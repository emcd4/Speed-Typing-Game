package ui.frames;

import model.ScoreBoard;
import ui.TypingTutor;
import ui.components.MyJButton;
import ui.components.MyJLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Frame directly before the game begins, displays the instructions
 */
public class StartGameFrame extends Frame {

    private JButton startButton;
    private JButton backButton;
    private JLabel info1;
    private JLabel info2;
    private JLabel message;

    // constructs a start game frame
    // EFFECTS: resets scoreboard, initializes, implements and places components
    public StartGameFrame(TypingTutor tt) {
        super(tt);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: initializes components
    public void initializeComponents() {
        info1 = new MyJLabel("After you begin, you will have 30 seconds to");
        info2 = new MyJLabel("correctly type as many words as you can.");

        message = new MyJLabel("Good luck!");

        startButton = new MyJButton("Start");
        backButton = new MyJButton("Back");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds components to frame at proper size and position
    public void addToFrame() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        resizeComponents();
        alignComponents();

        textPanel.add(info1);
        textPanel.add(info2);
        textPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        textPanel.add(message);

        buttonPanel.add(startButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(SPACE_SIZE, BUTTON_DIMENSION.height)));
        buttonPanel.add(backButton);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(SPACE_SIZE, SPACE_SIZE, SPACE_SIZE, SPACE_SIZE));
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(textPanel);
        mainPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: positions components on frame
    private void alignComponents() {
        info1.setAlignmentX(Component.CENTER_ALIGNMENT);
        info2.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // MODIFIES: this
    // EFFECTS: re-sizes components
    private void resizeComponents() {
        startButton.setMaximumSize(BUTTON_DIMENSION);
        backButton.setMaximumSize(BUTTON_DIMENSION);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: implements the functionality of interactive components
    public void implementComponentsFunctionality() {
        startButton.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(tt);
            openFrame(gameFrame);
        });

        backButton.addActionListener(e -> {
            MainMenuFrame mainMenuFrame = new MainMenuFrame(tt);
            openFrame(mainMenuFrame);
        });

    }
}
