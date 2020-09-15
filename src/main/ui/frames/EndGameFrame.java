package ui.frames;

import ui.TypingTutor;
import ui.components.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Frame displays the end screen, score is displayed, leader board is displayed and user can submit their score.
 */
public class EndGameFrame extends Frame {

    private static final Font NAME_FONT =
            new Font(Frame.STANDARD_FONT.getFamily(), Frame.STANDARD_FONT.getStyle(), 35);

    private JPanel mainPanel;
    private JPanel namePanel;
    private JLabel timesUpLabel;
    private JLabel scoreLabel;
    private JLabel leaderBoardLabel;
    private JScrollPane leaderBoard;
    private JList leaderBoardJList;
    private JLabel nameLabel;
    private JTextField inputNameField;
    private JButton submitScoreButton;
    private JButton mainMenuButton;

    // constructs new end game frame
    // EFFECTS: initializes, implements and places components
    public EndGameFrame(TypingTutor tt) {
        super(tt);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: initializes components
    public void initializeComponents() {
        timesUpLabel = new MyJLabel("Times Up!");
        scoreLabel = new MyJLabel("Score: " + tt.getScore());
        scoreLabel.setFont(new Font(Frame.STANDARD_FONT.getFamily(), Frame.STANDARD_FONT.getStyle(), 30));
        leaderBoardLabel = new MyJLabel("Leader board");
        leaderBoardJList = new LeaderBoardJList(tt.getLeaderBoard());
        leaderBoard = new LeaderBoardJScrollPane(leaderBoardJList);
        nameLabel = new MyJLabel("Your nickname: ");
        nameLabel.setFont(NAME_FONT);
        inputNameField = new MyJTextField();
        inputNameField.setFont(NAME_FONT);
        submitScoreButton = new MyJButton("Submit score");
        mainMenuButton = new MyJButton("Return to main menu");
        namePanel = new JPanel();
        mainPanel = new JPanel();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds components to frame at proper size and position
    public void addToFrame() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));

        resizeComponents();
        alignComponents();

        namePanel.add(nameLabel);
        namePanel.add(inputNameField);
        Box nameBox = Box.createHorizontalBox();
        nameBox.add(namePanel);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(timesUpLabel);
        mainPanel.add(scoreLabel);
        mainPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        mainPanel.add(leaderBoardLabel);
        mainPanel.add(leaderBoard);
        mainPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        mainPanel.add(namePanel);
        mainPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        mainPanel.add(submitScoreButton);
        mainPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        mainPanel.add(mainMenuButton);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: positions components on frame
    private void alignComponents() {
        timesUpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderBoardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitScoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // MODIFIES: this
    // EFFECTS: re-sizes components
    private void resizeComponents() {
        inputNameField.setMaximumSize(new Dimension(300, TEXT_DIMENSION.height));
        submitScoreButton.setMaximumSize(BUTTON_DIMENSION);
        mainMenuButton.setMaximumSize(BUTTON_DIMENSION);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: implements the functionality of interactive components
    public void implementComponentsFunctionality() {
        mainMenuButton.addActionListener(e -> {
            MainMenuFrame mainMenuFrame = new MainMenuFrame(tt);
            openFrame(mainMenuFrame);
        });

        submitScoreButton.addActionListener(e -> {
            String name = inputNameField.getText().trim();
            if (!name.isEmpty()) {
                inputNameField.setText("");
                inputNameField.setEditable(false);
                submitScoreButton.setEnabled(false);
                updateLeaderBoard(name);
            }
        });
    }

    // MODIFIES: this, (TypingTutor) tt
    // EFFECTS: updates the leader board
    private void updateLeaderBoard(String name) {
        tt.updateLeaderBoard(name);
        DefaultListModel<String> model = new DefaultListModel<>();
        List<String> entries = tt.getLeaderBoard().getLeaderBoardAsListString();
        for (String entry : entries) {
            model.addElement(entry);
        }
        leaderBoardJList.setModel(model);
    }
}
