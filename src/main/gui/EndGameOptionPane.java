package gui;

import model.Leaderboard;

import javax.swing.*;
import java.awt.*;

public class EndGameOptionPane extends JOptionPane {

    private LeaderboardPanel lbp;
    private PIGame guiGame;

    private int responseValue;

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

    public int getResponseValue() {
        return this.responseValue;
    }
}
