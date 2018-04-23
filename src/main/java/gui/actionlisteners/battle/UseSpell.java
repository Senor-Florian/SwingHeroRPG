package gui.actionlisteners.battle;

import gui.HeroRPG;
import heroes.Mage;
import heroes.Thief;
import heroes.Warrior;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UseSpell implements ActionListener {
    private JTextField player1Health;
    private JTextField player2Health;
    private JTextField player1AP;
    private JTextField player2AP;
    private JTextField battleLog;

    public UseSpell(JTextField player1Health, JTextField player2Health, JTextField player1AP, JTextField player2AP, JTextField battleLog) {
        this.player1Health = player1Health;
        this.player2Health = player2Health;
        this.player1AP = player1AP;
        this.player2AP = player2AP;
        this.battleLog = battleLog;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (HeroRPG.player == HeroRPG.Player.PLAYER1) {
            if (HeroRPG.hero1 instanceof Warrior) {
                battleLog.setText(HeroRPG.hero1.Spell(HeroRPG.hero1));
                player1AP.setText(HeroRPG.hero1.getCurrentAP() + " AP");
            } else if(HeroRPG.hero1 instanceof Mage) {
                battleLog.setText(HeroRPG.hero1.Spell(HeroRPG.hero2));
                player1AP.setText(HeroRPG.hero1.getCurrentAP() + " AP");
            } else if(HeroRPG.hero1 instanceof Thief) {
                battleLog.setText(HeroRPG.hero1.Spell(HeroRPG.hero2));
                player1AP.setText(HeroRPG.hero1.getCurrentAP() + " AP");
                player2Health.setText(HeroRPG.hero2.getHealth() + " HP");
            }
        } else {
            if (HeroRPG.hero2 instanceof Warrior) {
                battleLog.setText(HeroRPG.hero2.Spell(HeroRPG.hero2));
                player2AP.setText(HeroRPG.hero2.getCurrentAP() + " AP");
            } else if(HeroRPG.hero2 instanceof Mage) {
                battleLog.setText(HeroRPG.hero2.Spell(HeroRPG.hero1));
                player2AP.setText(HeroRPG.hero2.getCurrentAP() + " AP");
            } else if(HeroRPG.hero2 instanceof Thief) {
                battleLog.setText(HeroRPG.hero2.Spell(HeroRPG.hero1));
                player2AP.setText(HeroRPG.hero2.getCurrentAP() + " AP");
                player1Health.setText(HeroRPG.hero1.getHealth() + " HP");
            }
        }
    }
}