package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import gui.actionlisteners.battle.Attack;
import gui.actionlisteners.battle.EndTurn;
import gui.actionlisteners.battle.Heal;
import gui.actionlisteners.battle.UseSpell;
import gui.actionlisteners.character_creation.CreateCharacter;
import gui.actionlisteners.character_creation.DecreaseAttribute;
import gui.actionlisteners.character_creation.IncreaseAttribute;
import gui.actionlisteners.misc.*;
import heroes.*;

public class HeroRPG {

    public JFrame frame = new JFrame("HeroRPG");
    private JPanel mainForm;
    public JPanel titleScreen;
    public JPanel characterCreator;
    public JPanel battle;
    public JPanel end;
    public JPanel instructions;

    //Title panel
    private JLabel mainTitle;
    private JButton startGame;
    private JButton exitGame;

    //Character creation panel
    private JLabel title1;
    private JLabel whichPlayer;
    private JTextField unspentPoints;
    private JButton increaseStrength;
    private JButton increaseDexterity;
    private JButton increaseIntelligence;
    private JButton increaseConstitution;
    private JButton increaseSpeed;
    private JButton increasePerception;
    private JButton decreaseStrength;
    private JButton decreaseDexterity;
    private JButton decreaseIntelligence;
    private JButton decreaseConstitution;
    private JButton decreaseSpeed;
    private JButton decreasePerception;
    private JTextField strengthNumber;
    private JTextField dexterityNumber;
    private JTextField intelligenceNumber;
    private JTextField constitutionNumber;
    private JTextField speedNumber;
    private JTextField perceptionNumber;
    private JRadioButton warrior;
    private JRadioButton mage;
    private JRadioButton thief;
    private ButtonGroup group = new ButtonGroup();
    private JTextField nameField;
    private JLabel heroClass;
    private JButton createCharacter;

    //Battle panel
    private JLabel title2;
    private JTextField player1Name;
    private JTextField player2Name;
    private JTextField player1Health;
    private JTextField player2Health;
    private JTextField player1AP;
    private JTextField player2AP;
    private JLabel whosTurn;
    private JButton attackButton;
    private JButton spellButton;
    private JButton healButton;
    private JButton endTurn;
    private JTextField battleLog;
    private JLabel player1Wins;
    private JLabel player2Wins;
    private JLabel player1Pic;
    private JLabel player2Pic;

    //End panel
    private JLabel title3;
    private JButton restartButton;
    private JButton exitButton;
    private JLabel result;

    //Instructions panel
    private JLabel title4;
    private JLabel instructionsLabel;
    private JLabel attributes;
    private JLabel strengthDesc;
    private JLabel dexterityDesc;
    private JLabel intelligenceDesc;
    private JLabel constitutionDesc;
    private JLabel speedDesc;
    private JLabel perceptionDesc;
    private JLabel classSpells;
    private JLabel warriorSpell;
    private JLabel mageSpell;
    private JLabel thiefSpell;
    private JButton returnButton;
    private JButton instructionsButton;
    private JLabel logLabel;

    public static int spentAttributePoints;
    public static Hero hero1;
    public static Hero hero2;
    public static int hero1Wins;
    public static int hero2Wins;
    public static HashMap<String, Object> hero1Stats = new HashMap();
    public static HashMap<String, Object> hero2Stats = new HashMap();
    public enum Player {PLAYER1, PLAYER2}
    public static Player player = Player.PLAYER1;

    public static HeroRPG heroRPG = new HeroRPG();


    public static void main(String[] args) {
        heroRPG.initialSetup();
    }

    public void initialSetup() {
        heroRPG.setTitle(heroRPG.mainTitle, "./assets/hero_title.png");
        heroRPG.setTitle(heroRPG.title1, "./assets/hero_title2.png");
        heroRPG.setTitle(heroRPG.title2, "./assets/hero_title2.png");
        heroRPG.setTitle(heroRPG.title3, "./assets/hero_title2.png");
        heroRPG.setTitle(heroRPG.title4, "./assets/hero_title2.png");
        heroRPG.unspentPoints.setEditable(false);
        heroRPG.strengthNumber.setEditable(false);
        heroRPG.dexterityNumber.setEditable(false);
        heroRPG.intelligenceNumber.setEditable(false);
        heroRPG.constitutionNumber.setEditable(false);
        heroRPG.speedNumber.setEditable(false);
        heroRPG.perceptionNumber.setEditable(false);
        heroRPG.player1Name.setEditable(false);
        heroRPG.player2Name.setEditable(false);
        heroRPG.player1Health.setEditable(false);
        heroRPG.player2Health.setEditable(false);
        heroRPG.player1AP.setEditable(false);
        heroRPG.player2AP.setEditable(false);
        heroRPG.battleLog.setEditable(false);
        heroRPG.frame.setContentPane(heroRPG.titleScreen);
        heroRPG.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        heroRPG.frame.setMinimumSize(new Dimension(500, 700));
        heroRPG.frame.setVisible(true);
        heroRPG.group.add(heroRPG.warrior);
        heroRPG.group.add(heroRPG.mage);
        heroRPG.group.add(heroRPG.thief);
        heroRPG.group.setSelected(heroRPG.warrior.getModel(), true);
        heroRPG.unspentPoints.setText("Unspent attribute points: " + String.valueOf(40 - spentAttributePoints));
        heroRPG.renderInstructions();
        heroRPG.player1Wins.setText("Rounds won: " + hero1Wins);
        heroRPG.player2Wins.setText("Rounds won: " + hero2Wins);
    }

    public void setTitle(JLabel label, String path) {
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

    public void renderInstructions() {
        heroRPG.strengthDesc.setText("- Strength: determines attack damage");
        heroRPG.dexterityDesc.setText("- Dexterity: determines defensive rating");
        heroRPG.intelligenceDesc.setText("- Intelligence: determines offensive rating and which player starts the game");
        heroRPG.constitutionDesc.setText("- Constitution: determines health");
        heroRPG.speedDesc.setText("- Speed: determines turn action points regeneration");
        heroRPG.perceptionDesc.setText("- Perception: determines start action points and critical hit chance");
        heroRPG.warriorSpell.setText("- Warrior: buffs attack damage");
        heroRPG.mageSpell.setText("- Mage: debuffs enemy's defensive rating");
        heroRPG.thiefSpell.setText("- Thief: poisons the enemy");
    }

    public HeroRPG() {
        startGame.addActionListener(new Start(whichPlayer));
        instructionsButton.addActionListener(new Instructions());
        exitGame.addActionListener(new Exit());

        returnButton.addActionListener(new Return());

        increaseStrength.addActionListener(new IncreaseAttribute(Integer.parseInt(strengthNumber.getText()), strengthNumber, unspentPoints));
        increaseDexterity.addActionListener(new IncreaseAttribute(Integer.parseInt(dexterityNumber.getText()), dexterityNumber, unspentPoints));
        increaseIntelligence.addActionListener(new IncreaseAttribute(Integer.parseInt(intelligenceNumber.getText()), intelligenceNumber, unspentPoints));
        increaseConstitution.addActionListener(new IncreaseAttribute(Integer.parseInt(constitutionNumber.getText()), constitutionNumber, unspentPoints));
        increaseSpeed.addActionListener(new IncreaseAttribute(Integer.parseInt(speedNumber.getText()), speedNumber, unspentPoints));
        increasePerception.addActionListener(new IncreaseAttribute(Integer.parseInt(perceptionNumber.getText()), perceptionNumber, unspentPoints));

        decreaseStrength.addActionListener(new DecreaseAttribute(Integer.parseInt(strengthNumber.getText()), strengthNumber, unspentPoints));
        decreaseDexterity.addActionListener(new DecreaseAttribute(Integer.parseInt(dexterityNumber.getText()), dexterityNumber, unspentPoints));
        decreaseIntelligence.addActionListener(new DecreaseAttribute(Integer.parseInt(intelligenceNumber.getText()), intelligenceNumber, unspentPoints));
        decreaseConstitution.addActionListener(new DecreaseAttribute(Integer.parseInt(constitutionNumber.getText()), constitutionNumber, unspentPoints));
        decreaseSpeed.addActionListener(new DecreaseAttribute(Integer.parseInt(speedNumber.getText()), speedNumber, unspentPoints));
        decreasePerception.addActionListener(new DecreaseAttribute(Integer.parseInt(perceptionNumber.getText()), perceptionNumber, unspentPoints));

        createCharacter.addActionListener(new CreateCharacter(frame, unspentPoints, nameField, warrior, mage, thief, strengthNumber, dexterityNumber, intelligenceNumber,
                constitutionNumber, speedNumber, perceptionNumber, whosTurn, player1Name, player2Name,
                player1Health, player2Health, player1AP, player2AP, whichPlayer, player1Pic, player2Pic));

        attackButton.addActionListener(new Attack(player1Health, player2Health, player1AP, player2AP, battleLog));
        spellButton.addActionListener(new UseSpell(player1Health, player2Health, player1AP, player2AP, battleLog));
        healButton.addActionListener(new Heal(player1Health, player2Health, player1AP, player2AP, battleLog));
        endTurn.addActionListener(new EndTurn(frame, player1AP, player2AP, battleLog, result, whosTurn,
                player1Name, player2Name, player1Health, player2Health, player1Wins, player2Wins));

        restartButton.addActionListener(new Restart(nameField, strengthNumber, dexterityNumber, intelligenceNumber,
                constitutionNumber, speedNumber, perceptionNumber, battleLog, whichPlayer, unspentPoints));
        exitButton.addActionListener(new Exit());
    }
}
