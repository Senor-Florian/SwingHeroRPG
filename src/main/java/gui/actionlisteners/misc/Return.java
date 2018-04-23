package gui.actionlisteners.misc;

import gui.HeroRPG;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Return implements ActionListener {

    public Return() {}

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        HeroRPG.heroRPG.frame.setContentPane(HeroRPG.heroRPG.titleScreen);
        HeroRPG.heroRPG.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HeroRPG.heroRPG.frame.pack();
        HeroRPG.heroRPG.frame.setVisible(true);
    }
}
