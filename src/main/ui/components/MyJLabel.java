package ui.components;

import ui.frames.Frame;

import javax.swing.*;

/**
 * A label
 */
public class MyJLabel extends JLabel {

    // EFFECTS: constructs new JLabel
    public MyJLabel() {
        super();
        setFont(Frame.STANDARD_FONT);
    }

    // EFFECTS: constructs new JLabel with given text
    public MyJLabel(String text) {
        super(text);
        setFont(Frame.STANDARD_FONT);
    }
}
