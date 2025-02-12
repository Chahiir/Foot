package view.composent;

import java.awt.Color;
import java.awt.Dimension;

public class InfoButton extends ActionButton {
    
    public InfoButton(String label) {
    	super(label);
    	setBackground_color(new Color(34, 139, 34));
    	border_color = new Color(200,200,200);	
		setForeground(Color.WHITE); // Texte blanc
		setMaximumSize(new Dimension(10,10));
	}
}
