package ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Visual, scrollable, representation of the game leader board
 */
public class LeaderBoardJScrollPane extends JScrollPane {

    // EFFECTS: constructs a new JScrollPane with the given JList
    public LeaderBoardJScrollPane(JList list) {
        super(list);
        setPreferredSize(new Dimension(650, 250));
        setMaximumSize(new Dimension(650, 250));
    }

}
