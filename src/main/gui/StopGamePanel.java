package gui;

import javax.swing.*;
import java.awt.*;

/*
 * The panel in which the game over screen is rendered.
 */
public class StopGamePanel extends JPanel {

    private ScreenSize screenSize = new ScreenSize();

    private int xpos = 0;
    private int ypos = xpos;
    private int width = screenSize.getScreenWidth();
    private int height = screenSize.getScreenHeight();

    // Constructs a game over panel
    // EFFECTS:  sets size and position of panel with GAME OVER text
    public StopGamePanel() {
        setBounds(xpos, ypos, width, height);
        overlayGameOverText();
    }


    // Draws the squares where player has already passed
    // effects:  set size, font, color, and position of GAME OVER text; add the label to the panel
    private void overlayGameOverText() {
//        JLabel gameOverText = new JLabel("GAME OVER", JLabel.CENTER);
//        gameOverText.setSize(width, height);
//        gameOverText.setForeground(Color.WHITE);
//        gameOverText.setVerticalAlignment(JLabel.CENTER);
//        gameOverText.setFont(new Font("TimesRoman", Font.BOLD, 100));
//        this.add(gameOverText);

        ImageIcon gameOverIcon = new ImageIcon("data/GameOver.jpg");
        JLabel gameOverLabel = new JLabel(gameOverIcon);
        gameOverLabel.setSize(width, height);
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setVerticalAlignment(JLabel.CENTER);
        this.add(gameOverLabel);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.BLACK);
        g.drawRect(xpos, ypos, width, height);
    }

}
