package view.composent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButton extends ActionButton {
	
	private int targetId;
    
	public DeleteButton(String label, int targetId) {
        super(label);
        this.targetId = targetId;  // Initialize with specific row ID
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
        System.out.println("Suppression de l'élément avec l'ID " + targetId + " en cours...");
    }
}
