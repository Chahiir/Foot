package view.composent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import service.interfaces.JoueurService;

public class SellButton extends ActionButton {
	
	private int targetId;
    private JoueurService joueurService;
    
	public SellButton(String label, int targetId, JoueurService joueurService) {
        super(label);
        this.targetId = targetId;  // Initialize with specific player ID
        this.joueurService = joueurService; // Initialize with service Instance
        setBackground_color(new Color(231, 76, 60));
        pressed_background_color = background_color.darker();
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performDeleteAction();
            }
        });
        setPressedColor();
    }

    public void setTargetId(int id) {
        this.targetId = id;  // Method to update the ID
    }

    private void performDeleteAction() {
        joueurService.sellPlayerById(targetId);
        System.out.println("Suppression de l'élément avec l'ID " + targetId + " en cours...");
    }
}
