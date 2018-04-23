package gui.actionlisteners.misc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.HeroRPG.heroRPG;

public class Start implements ActionListener {
    private JLabel whichPlayer;

    public Start(JLabel whichPlayer) {
        this.whichPlayer = whichPlayer;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        heroRPG.frame.setContentPane(heroRPG.characterCreator);
        heroRPG.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        heroRPG.frame.pack();
        whichPlayer.setText("Player 1's character creator");
    }
}
