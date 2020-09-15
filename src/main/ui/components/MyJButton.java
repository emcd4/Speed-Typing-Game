package ui.components;

import ui.frames.Frame;

import javax.swing.*;

/**
 * A button with text
 */
public class MyJButton extends JButton {

    // EFFECTS: constructs new JButton with given text
    public MyJButton(String text) {
        super(text);
        setFont(Frame.STANDARD_FONT);
    }
}
