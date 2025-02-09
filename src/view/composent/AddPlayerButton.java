package view.composent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerButton extends ActionButton {

	public AddPlayerButton(String label) {
        super(label);
        setBackground_color(new Color(80,112,242));
        pressed_background_color = background_color.darker();
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performDeleteAction();
            }
        });
        setPressedColor();
    }

    private void performDeleteAction() {
        System.out.println("Ajout Joueur en cours...");
    }
}
