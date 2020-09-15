package ui.frames;

import ui.TypingTutor;
import ui.components.MyJButton;
import ui.components.MyJLabel;
import ui.components.MyJTextField;

import javax.swing.*;
import java.awt.*;

/**
 * Frame with adding and removing words from word bank functionality
 */
public class WordFrame extends Frame {

    private static final String ADD_SUCCESS = " was added successfully";
    private static final String ADD_ALREADY_IN = " is already in word bank";
    private static final String ADD_FAIL = " could not be added to word bank";
    private static final String REMOVE_SUCCESS = " was removed successfully";
    private static final String REMOVE_FAIL = " was not found in word bank";
    private static final Dimension SMALL_BUTTON_DIMENSION = new Dimension(200, BUTTON_DIMENSION.height);

    private JPanel mainPanel;
    private JPanel textPanel;
    private JPanel subButtonPanel1;
    private JPanel subButtonPanel2;
    private JLabel instructions;
    private JLabel resultMessage;
    private JTextField textField;
    private JButton addButton;
    private JButton removeButton;
    private JButton backButton;

    // constructs an add/remove word frame
    // EFFECTS: initializes, implements and places components
    public WordFrame(TypingTutor tt) {
        super(tt);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: initializes components
    public void initializeComponents() {
        instructions = new MyJLabel("Type the word you want to add/remove");
        resultMessage = new MyJLabel();
        resultMessage.setFont(new Font(Frame.STANDARD_FONT.getFamily(), Frame.STANDARD_FONT.getStyle(), 25));
        textField = new MyJTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        addButton = new MyJButton("Add");
        removeButton = new MyJButton("Remove");
        backButton = new MyJButton("Back");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds components to frame at proper size and position
    public void addToFrame() {
        initPanels();
        resizeComponents();
        alignComponents();

        textPanel.add(instructions);
        textPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        textPanel.add(textField);
        textPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        textPanel.add(resultMessage);

        subButtonPanel1.add(addButton);
        subButtonPanel1.add(Box.createRigidArea(new Dimension(SPACE_SIZE, BUTTON_DIMENSION.height)));
        subButtonPanel1.add(removeButton);

        subButtonPanel2.add(backButton);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(SPACE_SIZE,SPACE_SIZE,SPACE_SIZE,SPACE_SIZE));
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(textPanel);
        mainPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        mainPanel.add(subButtonPanel1);
        mainPanel.add(Box.createVerticalStrut(SPACE_SIZE));
        mainPanel.add(subButtonPanel2);
        mainPanel.add(Box.createVerticalGlue());
        add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes the panels for frame layout
    private void initPanels() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        subButtonPanel1 = new JPanel();
        subButtonPanel1.setLayout(new BoxLayout(subButtonPanel1, BoxLayout.X_AXIS));

        subButtonPanel2 = new JPanel();
        subButtonPanel2.setLayout(new BoxLayout(subButtonPanel2, BoxLayout.Y_AXIS));
    }

    // MODIFIES: this
    // EFFECTS: re-sizes components
    private void resizeComponents() {
        addButton.setMaximumSize(SMALL_BUTTON_DIMENSION);
        removeButton.setMaximumSize(SMALL_BUTTON_DIMENSION);
        backButton.setMaximumSize(SMALL_BUTTON_DIMENSION);
        textField.setMaximumSize(TEXT_DIMENSION);
    }

    // MODIFIES: this
    // EFFECTS: positions components on frame
    private void alignComponents() {
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        addButton.addActionListener(e -> {
            tryAddWord(formatString(textField.getText()));
        });

        removeButton.addActionListener(e -> {
            tryRemoveWord(formatString(textField.getText()));
        });
    }

    // MODIFIES: this, (TypingTutor) tt
    // EFFECTS: tries to add word to word bank. If word is added to word bank successfully displays success message,
    //          otherwise, does nothing to word bank. If word wasn't added because it was already in the word bank,
    //          displays the already in word bank message, otherwise displays the fail message.
    private void tryAddWord(String word) {
        boolean success = tt.getWordBank().addWord(word);
        boolean alreadyIn = tt.getWordBank().contains(word);
        if (success) {
            resultMessage.setText(word + ADD_SUCCESS);
        } else {
            if (alreadyIn) {
                resultMessage.setText(word + ADD_ALREADY_IN);
            } else {
                resultMessage.setText(word + ADD_FAIL);
            }
        }
    }

    // MODIFIES: this, (TypingTutor) tt
    // EFFECTS: if word is removed from the word bank successfully and displays success message,
    //          otherwise, does nothing and displays fail message.
    private void tryRemoveWord(String word) {
        boolean success = tt.getWordBank().removeWord(word);
        if (success) {
            resultMessage.setText(word + REMOVE_SUCCESS);
        } else {
            resultMessage.setText(word + REMOVE_FAIL);
        }
    }
}
