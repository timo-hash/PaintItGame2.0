package gui;

import model.Player;

import javax.swing.*;

public class GameOverOptionPane extends JOptionPane {

    private JLabel text1;
    private JLabel text2;
    private String playerNameInput;
    private JPanel jpanel;

    public GameOverOptionPane(String scoreMessage) {
        text1 = new JLabel(scoreMessage);
        text2 = new JLabel("Enter Your Name");
        jpanel = new JPanel();

        jpanel.add(text1);
        jpanel.add(text2);

        this.playerNameInput = showInputDialog(
                null,
                jpanel,
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public String getPlayerNameInput() {
        return playerNameInput;
    }

}
