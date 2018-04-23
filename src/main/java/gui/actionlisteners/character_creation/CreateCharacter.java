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

    public void storeStats(Hero hero) {
        if(HeroRPG.player == HeroRPG.Player.PLAYER1) {
            HeroRPG.hero1Stats.put("health", hero.getHealth());
            HeroRPG.hero1Stats.put("offensiveRating", hero.getOffensiveRating());
            HeroRPG.hero1Stats.put("defensiveRating", hero.getDefensiveRating());
            HeroRPG.hero1Stats.put("startAP", hero.getStartAP());
            HeroRPG.hero1Stats.put("turnAP", hero.getTurnAP());
            HeroRPG.hero1Stats.put("criticalChance", hero.getCriticalChance());
            HeroRPG.hero1Stats.put("damageModifier", hero.getDamageModifier());
            HeroRPG.hero1Stats.put("currentAP", hero.getStartAP());
            HeroRPG.hero1Stats.put("spellCooldown", 0);
        } else {
            HeroRPG.hero2Stats.put("health", hero.getHealth());
            HeroRPG.hero2Stats.put("offensiveRating", hero.getOffensiveRating());
            HeroRPG.hero2Stats.put("defensiveRating", hero.getDefensiveRating());
            HeroRPG.hero2Stats.put("startAP", hero.getStartAP());
            HeroRPG.hero2Stats.put("turnAP", hero.getTurnAP());
            HeroRPG.hero2Stats.put("criticalChance", hero.getCriticalChance());
            HeroRPG.hero2Stats.put("damageModifier", hero.getDamageModifier());
            HeroRPG.hero2Stats.put("currentAP", hero.getStartAP());
            HeroRPG.hero2Stats.put("spellCooldown", 0);
        }
    }

    public void setAvatar(JLabel label, String path) {
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (HeroRPG.spentAttributePoints == 40 && !nameField.getText().equals("")) {
            if (HeroRPG.player == HeroRPG.Player.PLAYER1) {
                if(warrior.isSelected()) {
                    HeroRPG.hero1 = new Warrior(nameField.getText(), Integer.parseInt(strengthNumber.getText()), Integer.parseInt(dexterityNumber.getText()),
                            Integer.parseInt(intelligenceNumber.getText()), Integer.parseInt(constitutionNumber.getText()),
                            Integer.parseInt(speedNumber.getText()), Integer.parseInt(perceptionNumber.getText()));
                    setAvatar(player1Pic, "./assets/sword1.png");
                } else if(mage.isSelected()) {
                    HeroRPG.hero1 = new Mage(nameField.getText(), Integer.parseInt(strengthNumber.getText()), Integer.parseInt(dexterityNumber.getText()),
                            Integer.parseInt(intelligenceNumber.getText()), Integer.parseInt(constitutionNumber.getText()),
                            Integer.parseInt(speedNumber.getText()), Integer.parseInt(perceptionNumber.getText()));
                    setAvatar(player1Pic, "./assets/staff1.png");
                } else {
                    HeroRPG.hero1 = new Thief(nameField.getText(), Integer.parseInt(strengthNumber.getText()), Integer.parseInt(dexterityNumber.getText()),
                            Integer.parseInt(intelligenceNumber.getText()), Integer.parseInt(constitutionNumber.getText()),
                            Integer.parseInt(speedNumber.getText()), Integer.parseInt(perceptionNumber.getText()));
                    setAvatar(player1Pic, "./assets/bow1.png");
                }
                storeStats(HeroRPG.hero1);
                HeroRPG.player = HeroRPG.Player.PLAYER2;
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
            } else {
                if(warrior.isSelected()) {
                    HeroRPG.hero2 = new Warrior(nameField.getText(), Integer.parseInt(strengthNumber.getText()), Integer.parseInt(dexterityNumber.getText()),
                            Integer.parseInt(intelligenceNumber.getText()), Integer.parseInt(constitutionNumber.getText()),
                            Integer.parseInt(speedNumber.getText()), Integer.parseInt(perceptionNumber.getText()));
                    setAvatar(player2Pic, "./assets/sword2.png");
                } else if(mage.isSelected()) {
                    HeroRPG.hero2 = new Mage(nameField.getText(), Integer.parseInt(strengthNumber.getText()), Integer.parseInt(dexterityNumber.getText()),
                            Integer.parseInt(intelligenceNumber.getText()), Integer.parseInt(constitutionNumber.getText()),
                            Integer.parseInt(speedNumber.getText()), Integer.parseInt(perceptionNumber.getText()));
                    setAvatar(player2Pic, "./assets/staff2.png");
                } else {
                    HeroRPG.hero2 = new Thief(nameField.getText(), Integer.parseInt(strengthNumber.getText()), Integer.parseInt(dexterityNumber.getText()),
                            Integer.parseInt(intelligenceNumber.getText()), Integer.parseInt(constitutionNumber.getText()),
                            Integer.parseInt(speedNumber.getText()), Integer.parseInt(perceptionNumber.getText()));
                    setAvatar(player2Pic, "./assets/bow2.png");
                }
                storeStats(HeroRPG.hero2);
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
                // The player with the higher offensive rating starts the game
                // If they are equal player1 gets to be first
                if (HeroRPG.hero1.getOffensiveRating() >= HeroRPG.hero2.getOffensiveRating()) {
                    HeroRPG.player = HeroRPG.Player.PLAYER1;
                    whosTurn.setText(HeroRPG.hero1.getName() + "'s turn");
                } else {
                    whosTurn.setText(HeroRPG.hero2.getName() + "'s turn");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Your character is not ready to be created.");
        }
    }
}