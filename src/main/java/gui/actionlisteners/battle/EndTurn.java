package gui.actionlisteners.battle;

import gui.HeroRPG;
import heroes.Hero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndTurn implements ActionListener {
    private JFrame frame;
    private JTextField player1AP;
    private JTextField player2AP;
    private JTextField battleLog;
    private JLabel result;
    private JLabel whosTurn;
    private JTextField player1Name;
    private JTextField player2Name;
    private JTextField player1Health;
    private JTextField player2Health;
    private JLabel player1Wins;
    private JLabel player2Wins;

    public EndTurn(JFrame frame, JTextField player1AP, JTextField player2AP, JTextField battleLog,
                   JLabel result, JLabel whosTurn, JTextField player1Name, JTextField player2Name,
                   JTextField player1Health, JTextField player2Health, JLabel player1Wins, JLabel player2Wins) {
        this.frame = frame;
        this.player1AP = player1AP;
        this.player2AP = player2AP;
        this.battleLog = battleLog;
        this.result = result;
        this.whosTurn = whosTurn;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Health = player1Health;
        this.player2Health = player2Health;
        this.player1Wins = player1Wins;
        this.player2Wins = player2Wins;
    }

    void setPanel(JPanel panel) {
        HeroRPG.heroRPG.frame.setContentPane(panel);
        HeroRPG.heroRPG.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HeroRPG.heroRPG.frame.pack();
        HeroRPG.heroRPG.frame.setVisible(true);
    }

    void resetHeroStats() {
        HeroRPG.hero1.setHealth((float)HeroRPG.hero1Stats.get("health"));
        HeroRPG.hero1.setOffensiveRating((float)HeroRPG.hero1Stats.get("offensiveRating"));
        HeroRPG.hero1.setDefensiveRating((float)HeroRPG.hero1Stats.get("defensiveRating"));
        HeroRPG.hero1.setStartAP((int)HeroRPG.hero1Stats.get("startAP"));
        HeroRPG.hero1.setTurnAP((int)HeroRPG.hero1Stats.get("turnAP"));
        HeroRPG.hero1.setCriticalChance((float)HeroRPG.hero1Stats.get("criticalChance"));
        HeroRPG.hero1.setDamageModifier((float)HeroRPG.hero1Stats.get("damageModifier"));
        HeroRPG.hero1.setCurrentAP((int)HeroRPG.hero1Stats.get("currentAP"));
        HeroRPG.hero1.setSpellCooldown((int)HeroRPG.hero1Stats.get("spellCooldown"));

        HeroRPG.hero2.setHealth((float)HeroRPG.hero2Stats.get("health"));
        HeroRPG.hero2.setOffensiveRating((float)HeroRPG.hero2Stats.get("offensiveRating"));
        HeroRPG.hero2.setDefensiveRating((float)HeroRPG.hero2Stats.get("defensiveRating"));
        HeroRPG.hero2.setStartAP((int)HeroRPG.hero2Stats.get("startAP"));
        HeroRPG.hero2.setTurnAP((int)HeroRPG.hero2Stats.get("turnAP"));
        HeroRPG.hero2.setCriticalChance((float)HeroRPG.hero2Stats.get("criticalChance"));
        HeroRPG.hero2.setDamageModifier((float)HeroRPG.hero2Stats.get("damageModifier"));
        HeroRPG.hero2.setCurrentAP((int)HeroRPG.hero2Stats.get("currentAP"));
        HeroRPG.hero2.setSpellCooldown((int)HeroRPG.hero2Stats.get("spellCooldown"));
    }

    void resetBattleInterface() {
        player1Name.setText(HeroRPG.hero1.getName());
        player2Name.setText(HeroRPG.hero2.getName());
        player1Health.setText(HeroRPG.hero1.getHealth() + " HP");
        player2Health.setText(HeroRPG.hero2.getHealth() + " HP");
        player1AP.setText(HeroRPG.hero1.getCurrentAP() + " AP");
        player2AP.setText(HeroRPG.hero2.getCurrentAP() + " AP");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (HeroRPG.player == HeroRPG.Player.PLAYER1) {
            HeroRPG.hero1.setCurrentAP(HeroRPG.hero1.getCurrentAP() + HeroRPG.hero1.getTurnAP());
            if(HeroRPG.hero1.getSpellCooldown() > 0) {
                HeroRPG.hero1.setSpellCooldown(HeroRPG.hero1.getSpellCooldown() - 1);
            }
            player1AP.setText(HeroRPG.hero1.getCurrentAP() + " AP");
            if(HeroRPG.hero1.getHealth() <= 0) {
                battleLog.setText(HeroRPG.hero2.getName() + " won the round");
                JOptionPane.showMessageDialog(frame, HeroRPG.hero1.getName() + " died!");
                HeroRPG.hero2Wins++;
                if(HeroRPG.hero2Wins == 3) {
                    result.setText(HeroRPG.hero2.getName() + " controlled by Player 2 won the game!");
                    setPanel(HeroRPG.heroRPG.end);
                } else {
                    resetHeroStats();
                    player2Wins.setText("Rounds won: " + HeroRPG.hero2Wins);
                    resetBattleInterface();
                    setPanel(HeroRPG.heroRPG.battle);
                }
            }
            if(HeroRPG.hero2.getHealth() <= 0) {
                battleLog.setText(HeroRPG.hero1.getName() + " won the round");
                JOptionPane.showMessageDialog(frame, HeroRPG.hero2.getName() + " died!");
                HeroRPG.hero1Wins++;
                if(HeroRPG.hero1Wins == 3) {
                    result.setText(HeroRPG.hero1.getName() + " controlled by Player 1 won the game!");
                    setPanel(HeroRPG.heroRPG.end);
                } else {
                    resetHeroStats();
                    player1Wins.setText("Rounds won: " + HeroRPG.hero1Wins);
                    resetBattleInterface();
                    setPanel(HeroRPG.heroRPG.battle);
                }
            }
            whosTurn.setText(HeroRPG.hero2.getName() + "'s turn");
            HeroRPG.player = HeroRPG.Player.PLAYER2;
        } else {
            HeroRPG.hero2.setCurrentAP(HeroRPG.hero2.getCurrentAP() + HeroRPG.hero2.getTurnAP());
            if(HeroRPG.hero2.getSpellCooldown() > 0) {
                HeroRPG.hero2.setSpellCooldown(HeroRPG.hero2.getSpellCooldown() - 1);
            }
            player2AP.setText(HeroRPG.hero2.getCurrentAP() + " AP");
            if(HeroRPG.hero1.getHealth() <= 0) {
                battleLog.setText(HeroRPG.hero2.getName() + " won the round");
                JOptionPane.showMessageDialog(frame, HeroRPG.hero1.getName() + " died!");
                HeroRPG.hero2Wins++;
                if(HeroRPG.hero2Wins == 3) {
                    result.setText(HeroRPG.hero2.getName() + " controlled by Player 2 won the game!");
                    setPanel(HeroRPG.heroRPG.end);
                } else {
                    resetHeroStats();
                    player2Wins.setText("Rounds won: " + HeroRPG.hero2Wins);
                    resetBattleInterface();
                    setPanel(HeroRPG.heroRPG.battle);
                }
            }
            if(HeroRPG.hero2.getHealth() <= 0) {
                battleLog.setText(HeroRPG.hero1.getName() + " won the round");
                JOptionPane.showMessageDialog(frame, HeroRPG.hero2.getName() + " died!");
                HeroRPG.hero1Wins++;
                if(HeroRPG.hero1Wins == 3) {
                    result.setText(HeroRPG.hero1.getName() + " controlled by Player 1 won the game!");
                    setPanel(HeroRPG.heroRPG.end);
                } else {
                    resetHeroStats();
                    player1Wins.setText("Rounds won: " + HeroRPG.hero1Wins);
                    resetBattleInterface();
                    setPanel(HeroRPG.heroRPG.battle);
                }
            }
            whosTurn.setText(HeroRPG.hero1.getName() + "' turn");
            HeroRPG.player = HeroRPG.Player.PLAYER1;
        }
    }
}