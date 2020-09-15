package ui.frames;

import ui.TypingTutor;
import ui.components.LeaderBoardJList;
import ui.components.LeaderBoardJScrollPane;
import ui.components.MyJButton;
import ui.components.MyJLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Frame displays the leader board
 */
public class LeaderBoardFrame extends Frame {

    private JScrollPane leaderBoard;
    private JList leaderBoardJList;
    private JLabel label;
    private JButton backButton;

    // creates a leader board frame
    // EFFECTS: initializes, implements and places components
    public LeaderBoardFrame(TypingTutor tt) {
        super(tt);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: initializes components
    public void initializeComponents() {
        label = new MyJLabel("Leader Board");
        leaderBoardJList = new LeaderBoardJList(tt.getLeaderBoard());
        leaderBoard = new LeaderBoardJScrollPane(leaderBoardJList);
        backButton = new MyJButton("Back");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds components to frame at proper size and position
    public void addToFrame() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        resizeComponents();
        alignComponents();

        mainPanel.setBorder(BorderFactory.createEmptyBorder(SPACE_SIZE,SPACE_SIZE,SPACE_SIZE,SPACE_SIZE));
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(label);
        mainPanel.add(leaderBoard);
        mainPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        mainPanel.add(backButton);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: re-sizes components
    private void resizeComponents() {
        backButton.setMaximumSize(BUTTON_DIMENSION);
    }

    // MODIFIES: this
    // EFFECTS: positions components on frame
    private void alignComponents() {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: implements the functionality of interactive components
    public void implementComponentsFunctionality() {
        backButton.addActionListener(e -> {
            MainMenuFrame mainMenuFrame = new MainMenuFrame(tt);
            openFrame(mainMenuFrame);
        });
    }
}
