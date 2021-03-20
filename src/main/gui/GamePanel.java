package gui;

import model.Grid;
import model.PlayerSquare;
import ui.GameApp;

import javax.swing.*;
import java.awt.*;

/*
 * The panel in which the game is rendered.
 */
public class GamePanel extends JPanel {

    private PIGame guiGame;
    private Grid grid = new Grid();
    private ScreenSize screenSize = new ScreenSize();
    private int xpos = 0;
    private int ypos = xpos;
    private int width = (screenSize.getScreenWidth() / 2);
    private int height = screenSize.getScreenHeight();
    private int unitSize = width / grid.getGridSize();


    // Constructs a game panel
    // EFFECTS:  sets size and background colour of panel, and place buttons inside panel
    public GamePanel(PIGame guiGame) {
        setBounds(xpos, ypos,  width, height);
        setBackground(Color.MAGENTA);
        this.guiGame = guiGame;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) {
        drawGrid(g);
        drawStarterSquare(g);
        drawPlayerSquare(g);
        drawTrail(g);
    }


    public void drawGrid(Graphics g) {
        for (int i = 0; i < grid.getGridSize(); i++) {
            g.drawLine(i * unitSize, 0, i * unitSize, height);
            g.drawLine(0, i * unitSize, width, i * unitSize);
        }
    }

    public void drawStarterSquare(Graphics g) {
        int squareX = (grid.getGridSize() / 2) * unitSize;
        int squareY = (grid.getGridSize() / 2) * unitSize;

        g.setColor(Color.BLACK);
        g.fillRect(squareX, squareY, unitSize, unitSize);
    }

    public void drawPlayerSquare(Graphics g) {
        PlayerSquare ps = guiGame.getPlayerSquare();
        int squareX = ps.getCurrentXPos() * unitSize;
        int squareY = ps.getCurrentYPos() * unitSize;

        g.setColor(Color.BLACK);
        g.fillRect(squareX, squareY, unitSize, unitSize);
    }

    public void drawTrail(Graphics g) {
        for (int i = 0; i < grid.getGridSize(); i++) {
            for (int j = 0; j < grid.getGridSize(); j++) {
                if (guiGame.getGrid().gridUnitValue(i, j)) {
                    g.setColor(Color.BLACK);
                    g.fillRect(i * unitSize, j * unitSize, unitSize, unitSize);
                }
            }
        }
    }

}
