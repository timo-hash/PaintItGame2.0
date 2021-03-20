package gui;

import javax.swing.*;
import java.awt.*;

public class StopGamePanel extends JPanel {

    private ScreenSize screenSize = new ScreenSize();

    private int xpos = 0;
    private int ypos = xpos;
    private int width = screenSize.getScreenWidth();
    private int height = screenSize.getScreenHeight();

    public StopGamePanel() {
        setBounds(xpos, ypos, width, height);
        overlayGameOverText();
    }

    private void overlayGameOverText() {
        JLabel gameOverText = new JLabel("GAME OVER", JLabel.CENTER);
        gameOverText.setSize(width, height);
        gameOverText.setForeground(Color.WHITE);
        gameOverText.setVerticalAlignment(JLabel.CENTER);
        gameOverText.setFont(new Font("TimesRoman", Font.BOLD, 100));
        this.add(gameOverText);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.BLACK);
        g.drawRect(xpos, ypos, width, height);
    }

}
