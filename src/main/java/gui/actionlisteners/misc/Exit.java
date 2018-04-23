package gui.actionlisteners.misc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit implements ActionListener {

    public Exit() {}

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.exit(0);
    }
}
