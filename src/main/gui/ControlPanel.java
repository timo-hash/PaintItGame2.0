package gui;

import javax.swing.*;
import java.awt.*;


/*
 * The panel in which the control buttons are rendered.
 */
public class ControlPanel extends JPanel {

    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;

    private ScreenSize screenSize = new ScreenSize();
    private int xpos = (screenSize.getScreenWidth() / 2);
    private int ypos = 0;
    private int width = (screenSize.getScreenWidth() / 2);
    private int height = screenSize.getScreenHeight();
    private int buttonDimension = 100;
    private int centerX = (width - buttonDimension) / 2;
    private int centerY = (height - buttonDimension) / 2;


    // Constructs a control panel
    // EFFECTS: sets size and background colour of panel, and place buttons inside panel
    public ControlPanel() {
        setBounds(xpos, ypos, width, height);
        setBackground(Color.LIGHT_GRAY);
        createDirectionButtons();
    }

    //getters
    public JButton getUpButton() {
        return upButton;
    }

    public JButton getDownButton() {
        return downButton;
    }

    public JButton getLeftButton() {
        return leftButton;
    }

    public JButton getRightButton() {
        return rightButton;
    }


    // effects: create all 4 directional buttons and add them to this panel
    public void createDirectionButtons() {
        createUpButton();
        createDownButton();
        createLeftButton();
        createRightButton();
    }

    // effects: create up button and add the button to this panel
    public void createUpButton() {
        upButton = new JButton("UP");
        upButton.setBounds(centerX, centerY - buttonDimension, buttonDimension, buttonDimension);
        this.add(upButton);
    }

    // effects: create down button and add the button to this panel
    public void createDownButton() {
        downButton = new JButton("DOWN");
        downButton.setBounds(centerX, centerY + buttonDimension, buttonDimension, buttonDimension);
        this.add(downButton);
    }

    // effects: create left button and add the button to this panel
    public void createLeftButton() {
        leftButton = new JButton("LEFT");
        leftButton.setBounds(centerX - buttonDimension, centerY, buttonDimension, buttonDimension);
        this.add(leftButton);
    }

    // effects: create right button and add the button to this panel
    public void createRightButton() {
        rightButton = new JButton("RIGHT");
        rightButton.setBounds(centerX + buttonDimension, centerY, buttonDimension, buttonDimension);
        this.add(rightButton);
    }


}
