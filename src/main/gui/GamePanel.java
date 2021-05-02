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
    private ScreenSize screenSize = new ScreenSize();
    private int xpos = 0;
    private int ypos = xpos;
    private int width = (screenSize.getScreenWidth() / 2);
    private int height = screenSize.getScreenHeight();
    private int unitSize;


    // Constructs a game panel
    // EFFECTS:  sets size and background colour of panel, and place buttons inside panel
    public GamePanel(PIGame guiGame) {
        setBounds(xpos, ypos,  width, height);
        setBackground(Color.MAGENTA);
        this.guiGame = guiGame;
        this.unitSize = width / guiGame.getGrid().getGridSize();
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
//        drawStarterSquare(g);
        drawTrail(g);
        drawPlayerSquare(g);
    }


    // Draws the grid
    // modifies: g
    // effects:  draws the grid onto g
    public void drawGrid(Graphics g) {
        for (int i = 0; i < guiGame.getGrid().getGridSize(); i++) {
            g.drawLine(i * unitSize, 0, i * unitSize, height);
            g.drawLine(0, i * unitSize, width, i * unitSize);
        }
    }

    // Draws the square where user starts moving
    // modifies: g
    // effects:  draws the starting square onto g
    public void drawStarterSquare(Graphics g) {
        int squareX = (guiGame.getGrid().getGridSize() / 2) * unitSize;
        int squareY = (guiGame.getGrid().getGridSize() / 2) * unitSize;

        g.setColor(Color.BLACK);
        g.fillRect(squareX, squareY, unitSize, unitSize);
    }

    // Draws the square the player controls
    // modifies: g
    // effects:  draws the current square of the user onto g
    public void drawPlayerSquare(Graphics g) {
        PlayerSquare ps = guiGame.getPlayerSquare();
        int squareX = ps.getCurrentXPos() * unitSize;
        int squareY = ps.getCurrentYPos() * unitSize;

        g.setColor(Color.YELLOW);
        g.fillRect(squareX, squareY, unitSize, unitSize);
    }

    // Draws the squares where player has already passed
    // modifies: g
    // effects:  draws the squares where the user has previously been onto g
    public void drawTrail(Graphics g) {
        for (int i = 0; i < guiGame.getGrid().getGridSize(); i++) {
            for (int j = 0; j < guiGame.getGrid().getGridSize(); j++) {
                if (guiGame.getGrid().gridUnitValue(i, j)) {
                    g.setColor(Color.BLACK);
                    g.fillRect(i * unitSize, j * unitSize, unitSize, unitSize);
                }
            }
        }
    }

}
