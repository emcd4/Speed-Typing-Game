package ui.components;

import model.LeaderBoard;

import javax.swing.*;

/**
 * Leader board as a visual list
 */
public class LeaderBoardJList extends MyJList {

    // EFFECTS: constructs new JList with information from given leader board
    public LeaderBoardJList(LeaderBoard leaderBoard) {
        super(leaderBoard.getLeaderBoardAsListString());
        setLayoutOrientation(JList.VERTICAL);
    }

}
