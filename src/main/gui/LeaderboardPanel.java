package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
 * The panel in which the leaderboard is rendered.
 */
public class LeaderboardPanel extends JPanel {

    private PIGame guiGame;
    private ArrayList<String> leaderboardList;
    private int spaceBetweenLines = 20;
    private int width = 160;
    private int fontSize = 14;
    private int lbFirstLineYCoord = 50;

    // Constructs a game panel
    // EFFECTS: sets size of the panel; add leaderboard scores to the panel
    public LeaderboardPanel(PIGame guiGame) {
        this.guiGame = guiGame;
        this.leaderboardList = guiGame.getLeaderboardList();
        int height = leaderboardList.size() * spaceBetweenLines + lbFirstLineYCoord;
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLeaderboard(g);
    }

    // Draws the leaderboard
    // modifies: g
    // effects:  draws the leaderboard onto g
    private void drawLeaderboard(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
        g.drawString("LEADERBOARD", 50, 20);

        for (int i = 0; i < guiGame.getLeaderboard().numOfPlayers(); i++) {
            g.drawString(this.leaderboardList.get(i), 0, i * spaceBetweenLines + lbFirstLineYCoord);

        }
    }
}
