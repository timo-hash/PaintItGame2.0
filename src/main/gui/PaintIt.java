package gui;

import ui.GameApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintIt extends JFrame implements ActionListener {

    private ScreenSize screenSize;
    private ControlPanel cp;
    private GamePanel gp;
    private PIGame guiGame;

    public static void main(String[] args) {
        new PaintIt();
    }

    // Constructs main window
    // effects: sets up window in which Space PaintIt game will be played
    public PaintIt() {
        super("Paint It");
        screenSize = new ScreenSize();
        setSize(screenSize.getScreenWidth(), screenSize.getScreenHeight());
        setVisible(true);

        guiGame = new PIGame();
        cp = new ControlPanel();
        gp = new GamePanel(guiGame);

        add(cp);
        setupActionListeners();

        add(gp);

        guiGame.startGame();



    }

    private void setupActionListeners() {
        cp.getUpButton().addActionListener(this);
        cp.getDownButton().addActionListener(this);
        cp.getLeftButton().addActionListener(this);
        cp.getRightButton().addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cp.getUpButton()) {
            guiGame.moveUp();
        } else if (e.getSource() == cp.getDownButton()) {
            guiGame.moveDown();
        } else if (e.getSource() == cp.getLeftButton()) {
            guiGame.moveLeft();
        } else if (e.getSource() == cp.getRightButton()) {
            guiGame.moveRight();
        }

        gp.repaint();
    }

}
