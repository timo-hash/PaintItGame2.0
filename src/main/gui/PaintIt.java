package gui;

import ui.GameApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintIt extends JFrame implements ActionListener {

    private ScreenSize screenSize;
    private ControlPanel cp;
    private GamePanel gp;
    private PIGame game;

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


        cp = new ControlPanel();
        add(cp);
        setupActionListeners();

        gp = new GamePanel();
        add(gp);



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
            System.out.println("up");
        } else if (e.getSource() == cp.getDownButton()) {
            System.out.println("d");
        } else if (e.getSource() == cp.getLeftButton()) {
            System.out.println("l");
        } else if (e.getSource() == cp.getRightButton()) {
            System.out.println("r");
        }
    }

}
