package view.composent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.Joueur;
import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import view.teamManagmentView.PlayerEditDialog;

public class AddPlayerButton extends ActionButton {

    private JoueurService joueurService;
    private EquipeService equipeService;

	public AddPlayerButton(String label, JoueurService joueurService, EquipeService equipeService) {
        super(label);
        this.joueurService = joueurService;
        this.equipeService = equipeService;
        setBackground_color(new Color(80,112,242));
        pressed_background_color = background_color.darker();
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAddAction();
            }
        });
        setPressedColor();
    }

    private void performAddAction() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        PlayerEditDialog dialog = new PlayerEditDialog(frame, new Joueur(), equipeService);
        dialog.setVisible(true);
        Joueur editedPlayer = dialog.getJoueur();
        joueurService.addPlayer(editedPlayer);
    }
}
