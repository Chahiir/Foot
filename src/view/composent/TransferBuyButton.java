package view.composent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import service.interfaces.JoueurService;

public class TransferBuyButton extends ActionButton {
	
	private int targetId;
    
    public TransferBuyButton(int targetId, JoueurService joueurService) {
    	super("Acheter");
    	this.targetId = targetId;  // Initialize with specific row ID
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
        //joueurService.buyPlayerById(targetId);
        System.out.println("Achat du joueur avec l'ID " + targetId + " en cours...");
    }
}
