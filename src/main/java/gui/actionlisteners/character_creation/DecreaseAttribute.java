package gui.actionlisteners.character_creation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.HeroRPG;

public class DecreaseAttribute implements ActionListener {
    private int value;
    private JTextField field;
    private JTextField unspentPoints;

    public DecreaseAttribute(int value, JTextField field, JTextField unspentPoints) {
        this.value = value;
        this.field = field;
        this.unspentPoints = unspentPoints;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        value = Integer.parseInt(field.getText());
        if (Integer.parseInt(field.getText()) > 0 && HeroRPG.spentAttributePoints > 0) {
            value--;
            field.setText(String.valueOf(value));
            HeroRPG.spentAttributePoints--;
            unspentPoints.setText("Unspent attribute points: " + String.valueOf(40 - HeroRPG.spentAttributePoints));
        }
    }
}