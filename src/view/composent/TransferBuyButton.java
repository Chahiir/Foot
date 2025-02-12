package view.composent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import service.interfaces.EquipeService;
import service.interfaces.JoueurService;

public class TransferBuyButton extends ActionButton {
	
	private int targetId;
    private JoueurService joueurService;
    private EquipeService equipeService;
    
    public TransferBuyButton(int targetId, JoueurService joueurService, EquipeService equipeService) {
    	super("Acheter");
    	this.targetId = targetId;  // Initialize with specific row ID
        this.joueurService = joueurService;
        this.equipeService = equipeService;
    	setBackground_color(new Color(93, 173, 226));
    	border_color = new Color(200,200,200);
    	pressed_background_color = background_color.darker();
    	
    	addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performBuyAction();
            }
        });
    	setPressedColor();
	}
    
    public void setTargetId(int id) {
        this.targetId = id;  // Method to update the ID
    }
    
    private void performBuyAction() {
        joueurService.transferPlayer(targetId, equipeService.getMonEquipe());
        System.out.println("Achat du joueur avec l'ID " + targetId + " en cours...");
    }
}
