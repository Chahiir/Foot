package view.composent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.Joueur;
import service.interfaces.JoueurService;
import view.PlayerEditDialog;

public class AddPlayerButton extends ActionButton {

    private JoueurService joueurService;

	public AddPlayerButton(String label, JoueurService joueurService) {
        super(label);
        this.joueurService = joueurService;
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
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        PlayerEditDialog dialog = new PlayerEditDialog(frame, new Joueur());
        dialog.setVisible(true);
        Joueur editedPlayer = dialog.getJoueur();
        joueurService.updatePlayer(editedPlayer);
    }
}
