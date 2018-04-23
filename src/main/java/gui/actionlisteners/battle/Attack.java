package gui.actionlisteners.battle;

import gui.HeroRPG;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Attack implements ActionListener {
    private JTextField player1Health;
    private JTextField player2Health;
    private JTextField player1AP;
    private JTextField player2AP;
    private JTextField battleLog;

    public Attack(JTextField player1Health, JTextField player2Health, JTextField player1AP, JTextField player2AP, JTextField battleLog) {
        this.player1Health = player1Health;
        this.player2Health = player2Health;
        this.player1AP = player1AP;
        this.player2AP = player2AP;
        this.battleLog = battleLog;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (HeroRPG.player == HeroRPG.Player.PLAYER1) {
            battleLog.setText(HeroRPG.hero1.Attack(HeroRPG.hero2));
            player2Health.setText(HeroRPG.hero2.getHealth() + " HP");
            player1AP.setText(HeroRPG.hero1.getCurrentAP() + " AP");
        } else {
            battleLog.setText(HeroRPG.hero2.Attack(HeroRPG.hero1));
            player1Health.setText(HeroRPG.hero1.getHealth() + " HP");
            player2AP.setText(HeroRPG.hero2.getCurrentAP() + " AP");
        }
    }
}
