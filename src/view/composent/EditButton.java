package view.composent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditButton extends ActionButton {
	
	private int targetId;
    
    public EditButton(String label, int targetId) {
    	super(label);
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
        System.out.println("Modification de l'élément avec l'ID " + targetId + " en cours...");
    }
}
