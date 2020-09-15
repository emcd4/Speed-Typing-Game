package ui.components;

import ui.frames.Frame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A visual list
 */
public class MyJList extends JList {

    // EFFECTS: creates new JList with the given list
    public MyJList(List list) {
        super(list.toArray());
        setFont(new Font("Monospaced", Frame.STANDARD_FONT.getStyle(), 30));
    }

}
