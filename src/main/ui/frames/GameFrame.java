package ui.frames;

import ui.SimpleDocumentListener;
import ui.TypingTutor;
import ui.components.MyJLabel;
import ui.components.MyJTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.io.File;

/**
 * Frame displays the main game; timer counts down until game ends, the user's current score is displayed, and the user
 * is given a target word and can type into a prompt
 */
public class GameFrame extends Frame implements SimpleDocumentListener {

    private static final int SECONDS_PER_GAME = 30;
    private static final Font SMALL_FONT =
            new Font(Frame.STANDARD_FONT.getFamily(), Frame.STANDARD_FONT.getStyle(), 30);
    private static final Font LARGE_FONT =
            new Font(Frame.STANDARD_FONT.getFamily(), Frame.STANDARD_FONT.getStyle(), 70);

    private JLabel label;
    private JLabel targetWord;
    private JLabel timerLabel;
    private JLabel timerDisplay;
    private JLabel scoreLabel;
    private JLabel scoreDisplay;
    private JTextField textField;
    private Timer timer;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel timerPanel;
    private JPanel scorePanel;

    private ui.AudioPlayer correctSoundPlayer;
    private int timeLeft;

    // constructs a game frame
    // EFFECTS: initializes, implements and places components, and starts game and timer
    public GameFrame(TypingTutor tt) {
        super(tt);

        correctSoundPlayer = new ui.AudioPlayer(new File("./data/correct-sound.wav"));

        tt.startGame();
        timer.start();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: initializes components
    public void initializeComponents() {
        timerLabel = new MyJLabel("Time left: ");
        timerLabel.setFont(SMALL_FONT);
        timeLeft = SECONDS_PER_GAME;
        timerDisplay = new MyJLabel(timeLeft + " seconds");
        timerDisplay.setFont(SMALL_FONT);

        scoreLabel = new MyJLabel("Score: ");
        scoreLabel.setFont(SMALL_FONT);
        scoreDisplay = new MyJLabel(Integer.toString(tt.getScore()));
        scoreDisplay.setFont(SMALL_FONT);

        targetWord = new MyJLabel(tt.getNextWord());
        targetWord.setFont(LARGE_FONT);
        label = new MyJLabel("type the word above");
        textField = new MyJTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);

        mainPanel = new JPanel();
        inputPanel = new JPanel();
        timerPanel = new JPanel();
        scorePanel = new JPanel();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds components to frame at proper size and position
    public void addToFrame() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.X_AXIS));
        timerPanel.setBorder(new EmptyBorder(SPACE_SIZE, SPACE_SIZE, SPACE_SIZE, SPACE_SIZE));
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.setBorder(new EmptyBorder(SPACE_SIZE, SPACE_SIZE, SPACE_SIZE, SPACE_SIZE));

        resizeComponents();
        alignComponents();

        timerPanel.add(timerLabel);
        timerPanel.add(timerDisplay);

        scorePanel.add(scoreLabel);
        scorePanel.add(scoreDisplay);

        Box b1 = createTimerAndScoreBox();

        inputPanel.add(targetWord);
        inputPanel.add(Box.createVerticalStrut(SPACE_SIZE * 4));
        inputPanel.add(label);
        inputPanel.add(textField);

        mainPanel.add(b1);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: returns a box with the timer and score panels formatted correctly inside
    private Box createTimerAndScoreBox() {
        timerPanel.add(timerLabel);
        timerPanel.add(timerDisplay);

        scorePanel.add(scoreLabel);
        scorePanel.add(scoreDisplay);

        Box b1 = Box.createHorizontalBox();
        b1.add(timerPanel);
        b1.add(Box.createHorizontalGlue());
        b1.add(scorePanel);

        return b1;
    }

    // MODIFIES: this
    // EFFECTS: positions components on frame
    private void alignComponents() {
        targetWord.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // MODIFIES: this
    // EFFECTS: re-sizes components
    private void resizeComponents() {
        textField.setMaximumSize(TEXT_DIMENSION);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: implements the functionality of interactive components
    public void implementComponentsFunctionality() {
        timer = new Timer(1000, e -> {
            if (timeLeft > 0) {
                timeLeft--;
                timerDisplay.setText(timeLeft + " seconds");
            } else {
                timer.stop();
                EndGameFrame endGameFrame = new EndGameFrame(tt);
                openFrame(endGameFrame);
            }
        });

        textField.getDocument().addDocumentListener(this);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: when the input field is modified, check if the input is the same as the target word.
    //          If input is the same as target word, update the target word, increment the score,
    //          play correct sound, and clear the input field. Otherwise, do nothing.
    public void update(DocumentEvent e) {
        String input = formatString(textField.getText());
        if (input.equals(targetWord.getText())) {
            targetWord.setText(tt.getNextWord());
            tt.incrementScore();
            scoreDisplay.setText(Integer.toString(tt.getScore()));
            correctSoundPlayer.play();
            SwingUtilities.invokeLater(() -> textField.setText(""));
        }
    }

}
