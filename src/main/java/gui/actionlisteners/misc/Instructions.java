package gui.actionlisteners.misc;

import gui.HeroRPG;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Instructions implements ActionListener {

    public Instructions() {}

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        HeroRPG.heroRPG.frame.setContentPane(HeroRPG.heroRPG.instructions);
        HeroRPG.heroRPG.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HeroRPG.heroRPG.frame.pack();
        HeroRPG.heroRPG.frame.setVisible(true);
    }
}
