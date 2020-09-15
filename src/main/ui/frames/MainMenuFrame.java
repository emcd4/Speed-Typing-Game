package ui.frames;

import ui.TypingTutor;
import ui.components.MyJButton;

import java.awt.*;
import javax.swing.*;

/**
 * Frame displays the main menu
 */
public class MainMenuFrame extends Frame {

    private JButton playButton;
    private JButton leaderBoardButton;
    private JButton wordButton;

    // constructs new main menu frame
    // EFFECTS: initializes, implements and places components
    public MainMenuFrame(TypingTutor tt) {
        super(tt);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: initializes components
    public void initializeComponents() {
        playButton = new MyJButton("Play");
        leaderBoardButton = new MyJButton("Leader Board");
        wordButton = new MyJButton("Add/Remove Word");
    }


    @Override
    // MODIFIES: this
    // EFFECTS: adds components to frame at proper size and position
    public void addToFrame() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        resizeComponents();
        alignComponents();

        mainPanel.setBorder(BorderFactory.createEmptyBorder(SPACE_SIZE,SPACE_SIZE,SPACE_SIZE,SPACE_SIZE));
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(playButton);
        mainPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        mainPanel.add(leaderBoardButton);
        mainPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        mainPanel.add(wordButton);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: re-sizes components
    private void resizeComponents() {
        playButton.setMaximumSize(BUTTON_DIMENSION);
        leaderBoardButton.setMaximumSize(BUTTON_DIMENSION);
        wordButton.setMaximumSize(BUTTON_DIMENSION);
    }

    // MODIFIES: this
    // EFFECTS: positions components on frame
    private void alignComponents() {
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderBoardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        wordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: implements the functionality of interactive components
    public void implementComponentsFunctionality() {
        playButton.addActionListener(e -> {
            StartGameFrame startGameFrame = new StartGameFrame(tt);
            openFrame(startGameFrame);
        });

        leaderBoardButton.addActionListener(e -> {
            LeaderBoardFrame leaderBoardFrame = new LeaderBoardFrame(tt);
            openFrame(leaderBoardFrame);
        });

        wordButton.addActionListener(e -> {
            WordFrame wordFrame = new WordFrame(tt);
            openFrame(wordFrame);
        });
    }
}
