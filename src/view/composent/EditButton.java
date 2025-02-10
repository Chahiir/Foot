package view.composent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import dao.Joueur;
import service.interfaces.PlayerService;
import view.PlayerEditDialog;

public class EditButton extends ActionButton {
	
	private int targetId;
    PlayerService playerService;
    
    public EditButton(String label, int targetId, PlayerService playerService) {
    	super(label);
        this.playerService = playerService; // injection pour réaliser les actions
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
        PlayerEditDialog dialog = new PlayerEditDialog(frame, playerService.getPlayerById(targetId));
        dialog.setVisible(true);
        Joueur editedPlayer = dialog.getJoueur();
        playerService.updatePlayer(editedPlayer);
        System.out.println("Modification de l'élément avec l'ID " + targetId + " en cours...");
    }
}
