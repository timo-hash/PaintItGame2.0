package gui;

import ui.GameApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintIt extends JFrame {

    private ScreenSize screenSize;
    private ControlPanel cp;
    private GamePanel gp;
    private GameOverOptionPane goop;
    private EndGameOptionPane egop;
    private PIGame guiGame;
    private StopGamePanel stopGamePanel;


    public static void main(String[] args) {
        new PaintIt();
    }

    // Constructs main window
    // effects: sets up window in which Space PaintIt game will be played
    public PaintIt() {
        super("Paint It");
        screenSize = new ScreenSize();
        setVisible(true);
        setResizable(false);
        setSize(screenSize.getScreenWidth(), screenSize.getScreenHeight());
        centreOnScreen();

        init();
        startGame();
    }

    private void init() {
        guiGame = new PIGame();
        cp = new ControlPanel();
        gp = new GamePanel(guiGame);
        stopGamePanel = new StopGamePanel();

        add(cp);
        add(gp);
        add(stopGamePanel);

        cp.repaint();
        gp.repaint();
    }

    public void startGame() {
        checkUpButton();
        checkDownButton();
        checkLeftButton();
        checkRightButton();

    }



    private void checkUpButton() {
        cp.getUpButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiGame.moveUp();
                gp.repaint();
                checkGameOver();
            }
        });
    }

    private void checkDownButton() {
        cp.getDownButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiGame.moveDown();
                gp.repaint();
                checkGameOver();
            }
        });
    }

    private void checkLeftButton() {
        cp.getLeftButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiGame.moveLeft();
                gp.repaint();
                checkGameOver();
            }
        });
    }

    private void checkRightButton() {
        cp.getRightButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiGame.moveRight();
                gp.repaint();
                checkGameOver();
            }
        });
    }



    public void checkGameOver() {
        if (!guiGame.getGameStillGoing() || !guiGame.checkNoMoreMoves()) {
            goop = new GameOverOptionPane(guiGame.gameOver());
            guiGame.recordNameAndScore(goop.getPlayerNameInput());
            guiGame.saveLeaderboardToStringArray();

            guiGame.setContinuePlaying(true);
            while (guiGame.getContinuePlaying()) {
                egop = new EndGameOptionPane(guiGame);
                processEndGameOptions();
            }
        }
    }

    private void processEndGameOptions() {
        if (egop.getResponseValue() == 0) {
            guiGame.saveLeaderboard();
        } else if (egop.getResponseValue() == 1) {
            guiGame.restart();
            gp.repaint();
            guiGame.setContinuePlaying(false);
        } else {
            stopGame();
            guiGame.setContinuePlaying(false);
        }
    }

    public void stopGame() {
        stopGamePanel.setVisible(true);
        cp.getUpButton().setEnabled(false);
        cp.getDownButton().setEnabled(false);
        cp.getLeftButton().setEnabled(false);
        cp.getRightButton().setEnabled(false);
        stopGamePanel.repaint();
    }

    // This method was taken from SpaceInvaders
    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2,
                (scrn.height - getHeight()) / 2);
    }


}
