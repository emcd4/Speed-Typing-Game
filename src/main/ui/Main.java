package ui;


import ui.frames.MainMenuFrame;

/**
 * Starts the application and opens the first window
 */
public class Main {
    public static void main(String[] args) {
        TypingTutor tt = new TypingTutor();
        MainMenuFrame mainMenu = new MainMenuFrame(tt);
        mainMenu.setVisible(true);
    }
}
