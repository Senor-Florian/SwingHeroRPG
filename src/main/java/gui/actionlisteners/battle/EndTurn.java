package gui.actionlisteners.battle;

import gui.HeroRPG;
import heroes.Hero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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

    private void setPanel(JPanel panel) {
        HeroRPG.heroRPG.frame.setContentPane(panel);
        HeroRPG.heroRPG.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HeroRPG.heroRPG.frame.pack();
        HeroRPG.heroRPG.frame.setVisible(true);
    }

    private void resetHeroStats(Hero hero, HashMap<String, Object> heroStats) {
        hero.setHealth((float)heroStats.get("health"));
        hero.setOffensiveRating((float)heroStats.get("offensiveRating"));
        hero.setDefensiveRating((float)heroStats.get("defensiveRating"));
        hero.setStartAP((int)heroStats.get("startAP"));
        hero.setTurnAP((int)heroStats.get("turnAP"));
        hero.setCriticalChance((float)heroStats.get("criticalChance"));
        hero.setDamageModifier((float)heroStats.get("damageModifier"));
        hero.setCurrentAP((int)heroStats.get("currentAP"));
        hero.setSpellCooldown((int)heroStats.get("spellCooldown"));
    }

    private void resetBattleInterface() {
        player1Name.setText(HeroRPG.hero1.getName());
        player2Name.setText(HeroRPG.hero2.getName());
        player1Health.setText(HeroRPG.hero1.getHealth() + " HP");
        player2Health.setText(HeroRPG.hero2.getHealth() + " HP");
        player1AP.setText(HeroRPG.hero1.getCurrentAP() + " AP");
        player2AP.setText(HeroRPG.hero2.getCurrentAP() + " AP");
    }

    private int endTurn(Hero heroA, Hero heroB, int heroWins, JLabel playerWins) {
        if(heroA.getHealth() <= 0) {
            battleLog.setText(heroB.getName() + " won the round");
            JOptionPane.showMessageDialog(frame, heroA.getName() + " died!");
            heroWins++;
            if(heroWins == 3) {
                result.setText(heroB.getName() + (HeroRPG.player == HeroRPG.Player.PLAYER1 ?
                        " controlled by Player 1 won the game!" : " controlled by Player 2 won the game!"));
                setPanel(HeroRPG.heroRPG.end);
            } else {
                resetHeroStats(HeroRPG.hero1, HeroRPG.hero1Stats);
                resetHeroStats(HeroRPG.hero2, HeroRPG.hero2Stats);
                playerWins.setText("Rounds won: " + heroWins);
                resetBattleInterface();
                setPanel(HeroRPG.heroRPG.battle);
            }
        }
        return heroWins;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (HeroRPG.player == HeroRPG.Player.PLAYER1) {
            HeroRPG.hero1.setCurrentAP(HeroRPG.hero1.getCurrentAP() + HeroRPG.hero1.getTurnAP());
            if(HeroRPG.hero1.getSpellCooldown() > 0) {
                HeroRPG.hero1.setSpellCooldown(HeroRPG.hero1.getSpellCooldown() - 1);
            }
            player1AP.setText(HeroRPG.hero1.getCurrentAP() + " AP");
            HeroRPG.hero2Wins = endTurn(HeroRPG.hero1, HeroRPG.hero2, HeroRPG.hero2Wins, player2Wins);
            HeroRPG.hero1Wins = endTurn(HeroRPG.hero2, HeroRPG.hero1, HeroRPG.hero1Wins, player1Wins);
            whosTurn.setText(HeroRPG.hero2.getName() + "'s turn");
            HeroRPG.player = HeroRPG.Player.PLAYER2;
        } else {
            HeroRPG.hero2.setCurrentAP(HeroRPG.hero2.getCurrentAP() + HeroRPG.hero2.getTurnAP());
            if(HeroRPG.hero2.getSpellCooldown() > 0) {
                HeroRPG.hero2.setSpellCooldown(HeroRPG.hero2.getSpellCooldown() - 1);
            }
            player2AP.setText(HeroRPG.hero2.getCurrentAP() + " AP");
            HeroRPG.hero2Wins = endTurn(HeroRPG.hero1, HeroRPG.hero2, HeroRPG.hero2Wins, player2Wins);
            HeroRPG.hero1Wins = endTurn(HeroRPG.hero2, HeroRPG.hero1, HeroRPG.hero1Wins, player1Wins);
            whosTurn.setText(HeroRPG.hero1.getName() + "' turn");
            HeroRPG.player = HeroRPG.Player.PLAYER1;
        }
    }
}