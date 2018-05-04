package gui.actionlisteners.character_creation;

import gui.HeroRPG;
import heroes.Hero;
import heroes.Mage;
import heroes.Thief;
import heroes.Warrior;
import gui.HeroRPG;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class CreateCharacter implements ActionListener {
    private JFrame frame;
    private JTextField unspentPoints;
    private JTextField nameField;
    private JRadioButton warrior;
    private JRadioButton mage;
    private JRadioButton thief;
    private JTextField strengthNumber;
    private JTextField dexterityNumber;
    private JTextField intelligenceNumber;
    private JTextField constitutionNumber;
    private JTextField speedNumber;
    private JTextField perceptionNumber;
    private JLabel whosTurn;
    private JTextField player1Name;
    private JTextField player2Name;
    private JTextField player1Health;
    private JTextField player2Health;
    private JTextField player1AP;
    private JTextField player2AP;
    private JLabel whichPlayer;
    private JLabel player1Pic;
    private JLabel player2Pic;

    public CreateCharacter(JFrame frame, JTextField unspentPoints, JTextField nameField, JRadioButton warrior, JRadioButton mage, JRadioButton thief,
                           JTextField strengthNumber, JTextField dexterityNumber, JTextField intelligenceNumber,
                           JTextField constitutionNumber, JTextField speedNumber, JTextField perceptionNumber, JLabel whosTurn, JTextField player1Name,
                           JTextField player2Name, JTextField player1Health, JTextField player2Health,
                           JTextField player1AP, JTextField player2AP, JLabel whichPlayer, JLabel player1Pic, JLabel player2Pic) {
        this.frame = frame;
        this.unspentPoints = unspentPoints;
        this.nameField = nameField;
        this.warrior = warrior;
        this.mage = mage;
        this.thief = thief;
        this.strengthNumber = strengthNumber;
        this.dexterityNumber = dexterityNumber;
        this.intelligenceNumber = intelligenceNumber;
        this.constitutionNumber = constitutionNumber;
        this.speedNumber = speedNumber;
        this.perceptionNumber = perceptionNumber;
        this.whosTurn = whosTurn;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Health = player1Health;
        this.player2Health = player2Health;
        this.player1AP = player1AP;
        this.player2AP = player2AP;
        this.whichPlayer = whichPlayer;
        this.player1Pic = player1Pic;
        this.player2Pic = player2Pic;
    }

    private void storeStats(Hero hero, HashMap<String, Object> heroStats) {
        heroStats.put("health", hero.getHealth());
        heroStats.put("offensiveRating", hero.getOffensiveRating());
        heroStats.put("defensiveRating", hero.getDefensiveRating());
        heroStats.put("startAP", hero.getStartAP());
        heroStats.put("turnAP", hero.getTurnAP());
        heroStats.put("criticalChance", hero.getCriticalChance());
        heroStats.put("damageModifier", hero.getDamageModifier());
        heroStats.put("currentAP", hero.getStartAP());
        heroStats.put("spellCooldown", 0);
    }

    private void setAvatar(JLabel label, String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(classLoader.getResource(path).getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(image);
        label.setIcon(icon);
    }

    private void instantiateHero() {
        Hero hero;
        boolean whichPlayer = HeroRPG.player == HeroRPG.Player.PLAYER1;
        if(warrior.isSelected()) {
            hero = new Warrior(nameField.getText(), Integer.parseInt(strengthNumber.getText()), Integer.parseInt(dexterityNumber.getText()),
                    Integer.parseInt(intelligenceNumber.getText()), Integer.parseInt(constitutionNumber.getText()),
                    Integer.parseInt(speedNumber.getText()), Integer.parseInt(perceptionNumber.getText()));
                    setAvatar(whichPlayer ? player1Pic : player2Pic, whichPlayer ? "./assets/sword1.png" : "./assets/sword2.png");
        } else if(mage.isSelected()) {
            hero = new Mage(nameField.getText(), Integer.parseInt(strengthNumber.getText()), Integer.parseInt(dexterityNumber.getText()),
                    Integer.parseInt(intelligenceNumber.getText()), Integer.parseInt(constitutionNumber.getText()),
                    Integer.parseInt(speedNumber.getText()), Integer.parseInt(perceptionNumber.getText()));
                    setAvatar(whichPlayer ? player1Pic : player2Pic, whichPlayer ? "./assets/staff1.png" : "./assets/staff2.png");
        } else {
            hero = new Thief(nameField.getText(), Integer.parseInt(strengthNumber.getText()), Integer.parseInt(dexterityNumber.getText()),
                    Integer.parseInt(intelligenceNumber.getText()), Integer.parseInt(constitutionNumber.getText()),
                    Integer.parseInt(speedNumber.getText()), Integer.parseInt(perceptionNumber.getText()));
                    setAvatar(whichPlayer ? player1Pic : player2Pic, whichPlayer ? "./assets/bow1.png" : "./assets/bow2.png");
        }
        if(whichPlayer) {
            HeroRPG.hero1 = hero;
        } else {
            HeroRPG.hero2 = hero;
        }
    }

    private void resetFields() {
        HeroRPG.spentAttributePoints = 0;
        strengthNumber.setText("0");
        dexterityNumber.setText("0");
        intelligenceNumber.setText("0");
        constitutionNumber.setText("0");
        speedNumber.setText("0");
        perceptionNumber.setText("0");
        nameField.setText("");
        unspentPoints.setText("Unspent attribute points: " + String.valueOf(40 - HeroRPG.spentAttributePoints));
        whichPlayer.setText("Player 2's character creator");
    }

    private void prepareBattleScreen() {
        HeroRPG.heroRPG.frame.setContentPane(HeroRPG.heroRPG.battle);
        HeroRPG.heroRPG.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HeroRPG.heroRPG.frame.pack();
        HeroRPG.heroRPG.frame.setVisible(true);
        player1Name.setText(HeroRPG.hero1.getName());
        player2Name.setText(HeroRPG.hero2.getName());
        player1Health.setText(HeroRPG.hero1.getHealth() + " HP");
        player2Health.setText(HeroRPG.hero2.getHealth() + " HP");
        player1AP.setText(HeroRPG.hero1.getCurrentAP() + " AP");
        player2AP.setText(HeroRPG.hero2.getCurrentAP() + " AP");
    }

    private void decideWhoStarts() {
        if (HeroRPG.hero1.getOffensiveRating() >= HeroRPG.hero2.getOffensiveRating()) {
            HeroRPG.player = HeroRPG.Player.PLAYER1;
            whosTurn.setText(HeroRPG.hero1.getName() + "'s turn");
        } else {
            whosTurn.setText(HeroRPG.hero2.getName() + "'s turn");
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (HeroRPG.spentAttributePoints == 40 && !nameField.getText().equals("")) {
            if (HeroRPG.player == HeroRPG.Player.PLAYER1) {
                instantiateHero();
                storeStats(HeroRPG.hero1, HeroRPG.hero1Stats);
                HeroRPG.player = HeroRPG.Player.PLAYER2;
                resetFields();
            } else {
                instantiateHero();
                storeStats(HeroRPG.hero2, HeroRPG.hero2Stats);
                prepareBattleScreen();
                decideWhoStarts();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Your character is not ready to be created.");
        }
    }
}