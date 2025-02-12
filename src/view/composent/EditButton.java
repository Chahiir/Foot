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

public class EditButton extends ActionButton {
	
	private int targetId;
    private JoueurService joueurService;
    private EquipeService equipeService;
    
    public EditButton(String label, int targetId, JoueurService joueurService, EquipeService equipeService) {
    	super(label);
        this.joueurService = joueurService; // injection pour réaliser les actions
        this.equipeService = equipeService; // injection pour réaliser les actions
    	this.targetId = targetId;  // Initialize with specific row ID
    	setBackground_color(new Color(93, 173, 226));
    	border_color = new Color(200,200,200);
    	pressed_background_color = background_color.darker();
    	
    	addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performEditAction();
            }
        });
    	setPressedColor();
	}
    
    public void setTargetId(int id) {
        this.targetId = id;  // Method to update the ID
    }
    
    private void performEditAction() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        PlayerEditDialog dialog = new PlayerEditDialog(frame, joueurService.getPlayerById(targetId), equipeService);
        dialog.setVisible(true);
        Joueur editedPlayer = dialog.getJoueur();
        joueurService.updatePlayer(editedPlayer);
        System.out.println("Modification de l'élément avec l'ID " + targetId + " en cours...");
    }
}
