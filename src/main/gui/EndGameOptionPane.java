package gui;

import javax.swing.*;


/*
 * The pop up option pane that allows user to save data, try again, or quit.
 */

public class EndGameOptionPane extends JOptionPane {

    private LeaderboardPanel lbp;
    private PIGame guiGame;

    private int responseValue;


    // Constructs an end game option pane
    // effects: setup option pane to record user input; display leaderboard
    public EndGameOptionPane(PIGame guiGame) {
        this.guiGame = guiGame;
        lbp = new LeaderboardPanel(this.guiGame);


        String[] options = {"Save", "Try Again", "Quit"};
        this.responseValue = showOptionDialog(
                null,
                lbp,
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                0);

    }

    // getters
    public int getResponseValue() {
        return this.responseValue;
    }
}
